package kr.ant.kpa.pharmcrew.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.util.DateUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.EducationConverter;
import kr.ant.kpa.pharmcrew.converter.MemberConverter;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PartnersDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.common.AppVersionVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberAuthVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberPartnerVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.member.Member;
import kr.ant.kpa.pharmcrew.resp.member.MemberInfoResp;
import kr.ant.kpa.pharmcrew.resp.member.NotiResp;
import kr.ant.kpa.pharmcrew.resp.member.PassPhoneResp;
import kr.ant.kpa.pharmcrew.resp.member.ProfileResp;
import kr.ant.kpa.pharmcrew.resp.member.SettingResp;
import kr.ant.kpa.pharmcrew.service.MemberService;
import kr.ant.kpa.pharmcrew.sms.SmsSender;
import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyRegMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundAuthCodeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberNotiException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundPartnersException;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private CommonDao commDao;

	@Autowired
	private PushDao	pushDao;
	
	@Autowired
	private PartnersDao partnersDao;

	@Autowired
	private MemberConverter converter;
	
	@Autowired
	private EducationConverter	eduConverter;
	
	@Autowired
	private Storage storage;
	@Autowired
	private PcUtils pcUtils;
	@Autowired
	private SmsSender smsSender;

	@Transactional("transactionManager")

	/**
	 * 1.사용자 인증 및 인증코드 요청
	 * 
	 * @param licenseNo - 면허번호
	 * @param birthDay  - 생년월일
	 * @param name      - 이름
	 * @param phone     - 휴대전화번호
	 **/
	public PcResp postAuthMember(String licenseNo, String birthDay, String name, String phone)
			throws NotFoundMemberInfoException, AlreadyRegMemberException, SmsException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "license_no", licenseNo);
		MapUtils.put(param, "birthday", birthDay);
		MapUtils.put(param, "name", name);
//		MapUtils.put(param, "handphone", phone);

		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new NotFoundMemberInfoException();
		}
		
		// 상태가 이미 등록된 경우에는 이미 등록되었다고 회신 
		if ("N".equals(memberVo.getState_div())) {
			throw new AlreadyRegMemberException();
		}

		// SMS 난수 생성
		String authCode = "";
		while (true) {
			authCode = StringUtils.generateAuthKey(4);
			// 인증번호 확인
			MapUtils.put(param, "auth_code", authCode);
			MemberAuthVo authVo = memberDao.selectMemberAuth(param);
			// 중복 되지 않도록 체크 하기
			if (authVo == null) {
				break;
			}
		}
		
		// 테스트 계정으로는 인증코드는 모두 99999 - beksung 20200715
		if ("0000".equals(licenseNo)) {
			authCode="9999";
		}

		// 난수 서버에 저장
		MemberAuthVo authVo = new MemberAuthVo();
		authVo.setMember_id(memberVo.getMember_id());
		authVo.setAuth_code(authCode);
		memberDao.insertMemberAuth(authVo);

		// SMS 전송 
		String authCodeMsg = "[KPA-PASS] 본인인증을 위해 인증번호 ["+authCode+"]를 입력해 주세요.";
		smsSender.send(phone, authCodeMsg);
		
		return resp;
	}

	/**
	 * 2.문자 인증하기
	 * 
	 * @param authCode - 인증번호
	 * @throws NotFoundAuthCodeException
	 **/
	public PcResp postAuthPhone(String authCode) throws NotFoundAuthCodeException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 인증번호 확인
		MapUtils.put(param, "auth_code", authCode);
		MemberAuthVo authVo = memberDao.selectMemberAuth(param);
		if (authVo == null) {
			throw new NotFoundAuthCodeException();
		}

		return resp;
	}


	/**
	 * 03. 회원정보 등록
	 * 
	 * @param licenseNo  - 면허번호
	 * @param authCode   - 인증번호
	 * @param agreeThird - 개인정보 제 3자 제공동의
	 * @param expireDate - 개인정보 유효기간제도 설정
	 * @param agreeSms   - sms 마케팅 수신 동의
	 * @param agreePush  - 푸시 마케팅 수신 동의
	 * @param password   - 패스워드
	 * @throws NotFoundMemberInfoException 
	 * @throws NotFoundAuthCodeException 
	 **/
	public PcResp postUpdateInfo(String licenseNo, String authCode, String agreeThird, 
			String expireDate, String agreeSms, String agreePush, String password)
					throws NotFoundMemberInfoException, AlreadyRegMemberException, NotFoundAuthCodeException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 회원 아이디 
		MapUtils.put(param, "license_no", licenseNo);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new NotFoundMemberInfoException();
		}
		
		// 상태가 이미 등록된 경우에는 이미 등록되었다고 회신 
		if ("N".equals(memberVo.getState_div())) {
			throw new AlreadyRegMemberException();
		}
		
		// 인증번호 확인
		MapUtils.put(param, "member_id", memberVo.getMember_id());
		MapUtils.put(param, "auth_code", authCode);
		MemberAuthVo authVo = memberDao.selectMemberAuth(param);
		if (authVo == null) {
			throw new NotFoundAuthCodeException();
		}

		memberVo.setPassword(pcUtils.encryptPassword(password));
		memberVo.setAd_push_yn(agreePush);
		memberVo.setAd_sms_yn(agreeSms);
		memberVo.setAgree_dt(new Date());
		// 회원상태는 등록한 상태로 변경
		// FIXME:beksung 테스트 중에만 사용 ; 상용에서 변경 필요
//		memberVo.setState_div("N");

		memberVo.setPush_yn("Y");
		memberVo.setNoti_read_dt(new Date());	// 메시지 읽은 날짜 초기화 

		// 사용자 정보 업데이트 
		memberDao.updateMember(memberVo);

		// 문자인증 정보 삭제
		memberDao.deleteMemberAuth(authVo);

		return resp;
	}

	/**
	 * 4.내 정보 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public MemberInfoResp getGetMemberInfo(Long sessionId) throws InvalidMemberException {
		MemberInfoResp resp = new MemberInfoResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}
		
		Member member = converter.convertMember(memberVo); 
		
		// 교육이 진행 중인지 확인 
		// 교육을 시작했고, 종료가 없는 것 : 시간이 너무 지나지 않은 것 : 기본 1시간 이내
		param.clear();
		MapUtils.put(param, "member_id", memberVo.getMember_id());
		MapUtils.put(param, "nowProgress", true);	// 현재 진행 중인 교육 확인 	
		MapUtils.put(param, "approval", 	"R");						// 승인대기 중인 것 	
		// 정렬  
		MapUtils.put(param, "orderby", "ORIGIN.start_dt desc");		// 정렬은 날짜 역순 
		MapUtils.pagingMaridDb(param, 1, 1);	// 최종 1개만 
		MemberEducationVo myEduVo = memberDao.selectMemberEducation(param);
		if (myEduVo != null) {
			EducationVo educationVo = myEduVo.getEducationVo();
			EducationCourseVo courseVo = myEduVo.getCourseVo();

			String endTime;
			if (courseVo != null) {	 
				endTime = courseVo.getEnd_time();
			} else {
				endTime = educationVo.getEnd_time();
			}

			// FIXME: 예외처리 개발시 주석
			member.setMyEdu(eduConverter.convertMyEdu(myEduVo));
			member.setIsIngEdu("Y");
			
			// FIXME: 예외처리 상용 시 주석 제거
//			// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시 
//			Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getEnd_date(),  "yyyyMMdd") + endTime, "yyyyMMddHHmm");
//			BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
//			Long diffTime = DateUtils.diffNow(calEnd);
//			if (diffTime > 60*60*1000) {
//				member.setIsIngEdu("N");
//			} else {
//				// 내 교육 정보를 회신해야 함
//				member.setMyEdu(eduConverter.convertMyEdu(myEduVo));
//				member.setIsIngEdu("Y");
//			}
		} else {
			member.setIsIngEdu("N");
		}
		
		// 멤버 정보 회신 
		resp.setMember(member);
		
		// 교육 이수 시간 확인
		param.clear();
		MapUtils.put(param, "search_year", DateUtils.getStringFromCalendar(Calendar.getInstance(), "yyyy"));
		MapUtils.put(param, "approval", "Y");
		MapUtils.put(param, "member_id", sessionId);
		List<MemberEducationVo> myEduVos = memberDao.listMemberEducation(param);

		int eduMinutes = 0;

		for (MemberEducationVo eduVo : myEduVos) {
			if ("Y".equals(eduVo.getApproval())) {
				eduMinutes += eduVo.getTake_min();
			}
		}
		// 내가 이수한 교육
		resp.setEduTime(eduMinutes);
		// 년간 교육 시간
		resp.setTotalTime(Constants.EDU_YEAR_MINUTE);

		return resp;
	}

	/**
	 * 5.설정 정보 요청
	 * @param		os - 요청 OS - A:android, I:iOS
	 * @throws InvalidMemberException
	 **/
	public SettingResp getGetSetting(String os, Long sessionId) 
		throws InvalidMemberException {
		SettingResp resp = new SettingResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}

		// 등록된 파트너 서비스 전체 정보 가져오기
		param.clear();
		MapUtils.put(param, "withMine", sessionId);
		MapUtils.put(param, "is_open", "Y");
		List<PartnersVo> partnersVos = partnersDao.listPartners(param);

		for (int i = 0; i < partnersVos.size(); i++) {
			PartnersVo partnersVo = partnersVos.get(i);
			resp.addPartners(converter.convertPartner(partnersVo, i));
		}

		// 푸시 정보 설정
		resp.setAcceptPush(memberVo.getPush_yn());
		resp.setMSms(memberVo.getAd_sms_yn());
		resp.setMPush(memberVo.getAd_push_yn());

		if (!StringUtils.isEmpty(os)) {
			param.clear();
			MapUtils.put(param, "os_type", os);
			MapUtils.put(param, "open_yn", "Y");
			MapUtils.put(param, "with_forced", true);			// forced_yn 정보 확인하기 위해서 
			MapUtils.put(param, "orderby", "ORIGIN.version desc");		// open_yn은  모두 "Y"로 해도 최후에 등록된 하나만 가지고 오기  
			MapUtils.pagingMySql(param, 1, 1);					// 최후의 1개 
			// 버전 정보 가져오기 
			AppVersionVo versionVo = commDao.selectAppVersion(param);
			if (versionVo != null) {
				resp.setVersion(versionVo.getVersion());
			}
		}
		
		return resp;
	}

	/**
	 * 6.파트너서비스 업데이트
	 * 
	 * @param partnerId - 설정 On/Off 파트너 서비스 목록
	 * @param useYn     - on : Y, off : N
	 * @throws NotFoundPartnersException
	 **/
	public PcResp postUpdatePartners(Long partnerId, String useYn, Long sessionId) throws NotFoundPartnersException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "partner_id", partnerId);
		PartnersVo partnersVo = partnersDao.selectPartners(param);
		if (partnersVo == null) {
			throw new NotFoundPartnersException();
		}

		MemberPartnerVo mpVo = new MemberPartnerVo();
		mpVo.setPartner_id(partnerId);
		mpVo.setMember_id(sessionId);
		if ("Y".equals(useYn.trim())) {
			memberDao.insertMemberPartner(mpVo);
		} else {
			memberDao.deleteMemberPartner(mpVo);
		}

		return resp;
	}

	/**
	 * 7.푸시 설정 업데이트
	 * 
	 * @param category - PU : 푸시, MS : 마케팅 SMS, MP : 마케팅 푸시
	 * @param agree    - 설정정보 - Y : 수신, N : 미수신
	 * @throws InvalidMemberException
	 **/
	public PcResp postAcceptPush(String category, String agree, Long sessionId) throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}
		
		// 설정 타입에 따른 동의 여부 적용
		if ("PU".equals(category)) {
			memberVo.setPush_yn(agree);
		} else if ("MS".equals(category)) {
			memberVo.setAd_sms_yn(agree);
		} else if ("MP".equals(category)) {
			memberVo.setAd_push_yn(agree);
		}

		// 정보 업데이트 
		memberDao.updateMember(memberVo);

		return resp;
	}

	/**
	 * 8.프로필 이미지 등록
	 * 
	 * @param image - 이미지 파일
	 * @throws FailSaveFileException
	 * @throws InvalidMemberException
	 **/
	public ProfileResp postUploadProfile(MultipartFile image, Long sessionId)
			throws FailSaveFileException, InvalidMemberException {
		ProfileResp resp = new ProfileResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}
		
		if (image != null && image.getSize() != 0) {
			File file = pcUtils.save(image);
			if (memberVo.getProfile_file_id() == null) {
				long fileId = storage.save(file, image.getOriginalFilename(), image.getSize()/* , FILE.P */);
				memberVo.setProfile_file_id(fileId);
				memberDao.updateMember(memberVo);
			} else {
				storage.replace(memberVo.getProfile_file_id(), file, image.getOriginalFilename(), image.getSize());
			}
		}

		// 파일 정보 확인해서 response
		param.clear();
		MapUtils.put(param, "file_id", memberVo.getProfile_file_id());
		FileVo fileVo = commDao.selectFile(param);
		if (fileVo != null) {
			resp.setProfileImgUrl(storage.fileUrl(fileVo));
		}

		return resp;
	}

	/**
	 * 9. 프로필 이미지 삭제
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp deleteDeleteProfile(Long sessionId) throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}

		memberVo.setIsEmptyProfileId(true);
		memberDao.updateMember(memberVo);

		if (memberVo.getProfileFile() != null) {
			storage.delete(memberVo.getProfileFile());
		}

		return resp;
	}


	/**
	 * 11. 임시비밀번호 발급 요청
	 * 
	 * @param name      - 이름
	 * @param licenseNo - 면허번호
	 * @throws NotFoundMemberInfoException 
	 * @throws SmsException 
	 **/
	public PassPhoneResp postRenewPassword(String name, String licenseNo) 
			throws NotFoundMemberInfoException, SmsException {
		PassPhoneResp resp = new PassPhoneResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 회원 아이디 
		MapUtils.put(param, "name", name);
		MapUtils.put(param, "license_no", licenseNo);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new NotFoundMemberInfoException();
		}

		String password = StringUtils.makeRandom(4, false);

		// 테스트 계정으로는 비밀번호는 모두 1234 - beksung 20200715
		if ("0000".equals(licenseNo)) {
			password="1234";
		}

		// 패스워드 지정 후 Member 정보 업데이트
		memberVo.setPassword(pcUtils.encryptPassword(password));
		memberDao.updateMember(memberVo);

		// 비밀번호 회신하기
		resp.setPhone(memberVo.getHandphone());
 
		// SMS 전송 
		String passwordMsg = "[KPA-PASS] 발급된 임시비밀번호는 ["+password+"] 입니다."; 
		smsSender.send(memberVo.getHandphone(), passwordMsg);
		
		return resp;
	}

	/**
	 * 12. 비밀번호 변경
	 * 
	 * @param password - 패스워드
	 * @throws InvalidMemberException 
	 **/
	public PcResp postChangePassword(String password, Long sessionId) 
			throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new InvalidMemberException();
		}
		
		// 패스워드 지정 후 Member 정보 업데이트
		memberVo.setPassword(pcUtils.encryptPassword(password));
		memberDao.updateMember(memberVo);
		
		return resp;
	}

	/**
	 * 13. 푸시 수신 등록
	 * 
	 * @param licenseNo - 사용자의 면허번호 - 로그인 하지 않을 수 있어서
	 * @param pushId    - 수신한 푸시 아이디
	 * @throws NotFoundMemberInfoException 
	 **/
	public PcResp postReceivePush(String licenseNo, Long pushId) 
			throws NotFoundMemberNotiException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 회원 아이디 
		MapUtils.put(param, "license_no", licenseNo);
		MapUtils.put(param, "push_id", pushId);
		MemberNotiVo notiVo = memberDao.selectMemberNoti(param);
		if (notiVo == null) {
			throw new NotFoundMemberNotiException();
		}
		
		// 알림이 없는 경우에만 업데이트 
		if (notiVo.getRecv_dt() == null) {
			// 수신 일자 확인
			notiVo.setRecv_dt(new Date());
			// 알림 업데이트
			memberDao.updateMemberNoti(notiVo);
		}
		
		return resp;
	}

	
	/**
	 * 14.알림 목록 요청
	 * 
	 * @param type  - 요청 타입-AD: 광고, NT: 약사회 알림, PT:파트너서비스 알림
	 * @param page  - 요청할 페이지 : 1 에서 시작
	 * @param count - 페이지당 카운트: Default=30개
	 **/
	public NotiResp getGetNotiList(String type, Integer page, Integer count, Long sessionId) {
		NotiResp resp = new NotiResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}

		MapUtils.put(param, "member_id", sessionId);
		MapUtils.put(param, "cartegory_div", type);

		// 전체 숫자 조회
		int totalCount = memberDao.countMemberNoti(param);
		resp.setTotalCount(totalCount);

		MapUtils.put(param, "orderby", "ORIGIN.noti_id desc"); // 진행 중인 것 : 날짜가 유효한 것
		// 페이징
		MapUtils.pagingMaridDb(param, page, count);
		List<MemberNotiVo> notiVos = memberDao.listMemberNoti(param);
		for (MemberNotiVo notiVo : notiVos) {
			resp.addNotis(converter.convertNoti(notiVo));
		}
		
		// 멤버 정보의 알림 읽은 시간 업데이트 
		MemberVo memberVo = new MemberVo();
		memberVo.setMember_id(sessionId);
		memberVo.setNoti_read_dt(new Date());
		memberDao.updateMember(memberVo);

		return resp;
	}

}
