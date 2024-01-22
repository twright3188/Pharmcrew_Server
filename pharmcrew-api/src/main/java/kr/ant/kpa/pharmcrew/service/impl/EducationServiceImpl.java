package kr.ant.kpa.pharmcrew.service.impl;

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

import com.bumdori.spring.BLogger;
import com.bumdori.util.DateUtils;
import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.converter.EducationConverter;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.EducationDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.education.EduDetail;
import kr.ant.kpa.pharmcrew.resp.education.EduDetailResp;
import kr.ant.kpa.pharmcrew.resp.education.EduListResp;
import kr.ant.kpa.pharmcrew.resp.education.EduMineListResp;
import kr.ant.kpa.pharmcrew.resp.education.MyEduResp;
import kr.ant.kpa.pharmcrew.service.EducationService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyFinishEduException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyProgressEduException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyQrStartException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidEvalStateException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidQrStartException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotEduStartTimeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundQrException;

@Service("educationService")
public class EducationServiceImpl implements EducationService {

	private static final Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);
	
	@Autowired
	private EducationDao eduDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CommonDao commDao;
	
	
	@Autowired
	private EducationConverter converter;

	@Transactional("transactionManager")

	/**
	*	1.교육 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public EduListResp getGetEduList(Integer page, Integer count, Long sessionId ) {
		EduListResp resp =  new EduListResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);
		
		MapUtils.put(param, "nowOpening", true);					// 진행 중인 것 : 날짜가 유효한 것
		MapUtils.put(param, "organize_member_id", sessionId);		// 사용자 조직에 따른 교육 확인 용
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");		// 정렬은 날짜 역순 
		
		List<EducationVo> eduVos = eduDao.listEducation(param);
		for (EducationVo eduVo : eduVos) {
			resp.addEducations(converter.convertEdu(eduVo));
		}
		
		return resp;
	}

	/**
	*	2.교육 상세
	* @param		eduId - 교육 아이디
	 * @throws NotFoundEducationException 
	**/
	public EduDetailResp getGetEduDetail(Long eduId, Long sessionId ) 
			throws NotFoundEducationException {
		EduDetailResp resp =  new EduDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "education_id", eduId);
		EducationVo eduVo = eduDao.selectEducation(param);
		if (eduVo == null) {
			throw new NotFoundEducationException();
		}
		
		// 상세 정보 converter 
		EduDetail detail = converter.convertEduDetail(eduVo);
		
		// 시간표 정보 가져오기 
		param.clear();
		MapUtils.put(param, "education_id", eduId);
		MapUtils.put(param, "orderby", "ORIGIN.days asc, ORIGIN.idx asc");
		List<EducationCourseVo> courseVos = eduDao.listEducationCourse(param);
		for (EducationCourseVo courseVo : courseVos) {
			detail.addTimeTables(converter.convertTimeTable(courseVo));
		}
		
		// 상세 조회 카운트 업데이트 
		eduVo.setView_cnt(eduVo.getView_cnt()+1);
		eduDao.updateEducation(eduVo);

		resp.setDetail(detail);
		
		return resp;
	}

	/**
	*	3.나의 교육 목록
	* @param		year - 검색할 연도
	**/
	public EduMineListResp getGetEduMine(Integer year, Long sessionId ) {
		EduMineListResp resp =  new EduMineListResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "search_year", year);
		MapUtils.put(param, "member_id", sessionId);
		MapUtils.put(param, "orderby", "ORIGIN.start_dt desc");		// 정렬은 날짜 역순
		List<MemberEducationVo> myEduVos = memberDao.listMemberEducation(param);

		int eduMinutes = 0;
		
		for (MemberEducationVo eduVo : myEduVos) {
			resp.addEducations(converter.convertMyEdu(eduVo));
			if ("Y".equals(eduVo.getApproval())) {
				eduMinutes += eduVo.getTake_min();
			}
		}
		// 내가 이수한 교육
		resp.setEduTime(eduMinutes);
		// 년간 교육 시간 
		resp.setTotalTime(Constants.EDU_YEAR_MINUTE);
		
		// 현재 진행 중인 교육이 있는지 확인 
		// 교육을 시작했고, 종료가 없는 것 : 시간이 너무 지나지 않은 것 : 기본 1시간 이내
		param.clear();
		MapUtils.put(param, "member_id", sessionId);
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
			resp.setNowEdu(converter.convertMyEdu(myEduVo));
			
			// FIXME: 예외처리 상용 시 주석 제거 
			// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시 
//			Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getEnd_date(),  "yyyyMMdd") + endTime, "yyyyMMddHHmm");
//			BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
//			Long diffTime = DateUtils.diffNow(calEnd);
//			if (diffTime > 60*60*1000) {
//				
//			} else {
//				// 내 교육 정보를 회신해야 함
//				resp.setNowEdu(converter.convertMyEdu(myEduVo));
//			}
		}

		return resp;
	}

	/**
	*	4.출석, 퇴실 등록
	* @param		qrcode - 촬영한 QR 코드 정보
	 * @throws NotFoundQrException 
	 * @throws InvalidQrStartException, AlreadyQrStartException 
	 * @throws NotEduStartTimeException 
	 * @throws AlreadyFinishEduException 
	 * @throws AlreadyProgressEduException 
	**/
	public MyEduResp postPutEduAttend(String qrcode, Long sessionId ) 
			throws NotFoundQrException, InvalidQrStartException, AlreadyQrStartException, NotEduStartTimeException, AlreadyFinishEduException, AlreadyProgressEduException {
		MyEduResp resp =  new MyEduResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// qr 코드에 해당하는 정보가 있는지 확인한다.
		MapUtils.put(param, "code", qrcode);
		QrVo qrVo = commDao.selectQr(param);
		if (qrVo == null) {
			throw new NotFoundQrException();
		}
		
		// 교육정보 추출
		param.clear();
		MapUtils.put(param, "education_id", qrVo.getEducation_id());
		EducationVo educationVo = eduDao.selectEducation(param);

		// 교육정보 추출
		EducationCourseVo courseVo = null;
		if (qrVo.getCourse_id() != null) {
			param.clear();
			MapUtils.put(param, "course_id", qrVo.getCourse_id());
			courseVo = eduDao.selectEducationCourse(param);			
		}
		
		// qr에 등록한 나의 시작 정보가 있는지 확인한다.
		param.clear();
		MapUtils.put(param, "member_id", sessionId);
		MapUtils.put(param, "education_id", qrVo.getEducation_id());
		MapUtils.put(param, "course_id", qrVo.getCourse_id());
		MemberEducationVo myEduVo = memberDao.selectMemberEducation(param);
		
		if (myEduVo == null) {

			// 시작 등록해야 하는데, 종료 QR 인 경우 오류
			if ("E".equals(qrVo.getType())) {
				throw new InvalidQrStartException();
			}
			
			// %%% 기존에 종료 안한 진행하는 교육이 있는지? 교육 종료 이전에는 신규 등록 못하게 방지 
			param.clear();
			MapUtils.put(param, "member_id", sessionId);
			MapUtils.put(param, "nowProgress", true);	// 현재 진행 중인 교육 확인 	
			MapUtils.put(param, "approval", 	"R");						// 승인대기 중인 것 		
			// 페이징  
			MapUtils.put(param, "orderby", "ORIGIN.start_dt desc");		// 정렬은 날짜 역순 
			MapUtils.pagingMaridDb(param, 1, 1);	// 최종 1개만 
			MemberEducationVo oldMyEduVo = memberDao.selectMemberEducation(param);
			if (oldMyEduVo != null ) {
				EducationVo oldEduVo = oldMyEduVo.getEducationVo();
				EducationCourseVo oldCourseVo = oldMyEduVo.getCourseVo();

				String endTime;
				if (oldCourseVo != null) {	 
					endTime = oldCourseVo.getEnd_time();
				} else {
					endTime = oldEduVo.getEnd_time();
				}

				// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시 
				Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(oldEduVo.getEnd_date(),  "yyyyMMdd") + endTime, "yyyyMMddHHmm");
				BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
				Long diffTime = DateUtils.diffNow(calEnd);
				if (diffTime < 0) {
					throw new AlreadyProgressEduException();
				}
			}
			
			// FIXME:beksung 교육 QR 시작 예외처리 일단 테스트 시에는 주석  
			// FIXME: 예외처리 상용 시 주석 제거
//			// 교육 시작이 가능하지 확인 
//			if (courseVo != null) {	 
//				
//				// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시 
//				Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getEnd_date(),  "yyyyMMdd") + courseVo.getEnd_time(), "yyyyMMddHHmm");
//				BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
//				Long diffTime = DateUtils.diffNow(calEnd);
//				if (diffTime > 60*60*1000) {
//					throw new AlreadyFinishEduException();
//				}
//				
//				// 코스가 있으면 코스 시간으로 아직 시작 전인지 확인 : 30분 전에만 교육 등록 가능
//				Calendar calStart = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getStart_date(),  "yyyyMMdd") + courseVo.getStart_time(), "yyyyMMddHHmm");
//				BLogger.debug(logger, "calStart : {}", DateUtils.getStringFromCalendar(calStart, "yyyyMMdd HHmm"));
//				diffTime = DateUtils.diffNow(calStart); 
//				if (Math.abs(diffTime) > 30*60*1000) {
//					throw new NotEduStartTimeException();
//				}
//			} else {		
//				// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시
//				Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getEnd_date(),  "yyyyMMdd") + educationVo.getEnd_time(), "yyyyMMddHHmm");
//				BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
//				Long diffTime = DateUtils.diffNow(calEnd); 
//				if (diffTime > 60*60*1000) {
//					throw new AlreadyFinishEduException();
//				}
//				
//				// 교육만 있으면 교육 시간으로 아직 시작 전인지 확인 : 30분 전에만 교육 등록 가능
//				Calendar calStart = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getStart_date(),  "yyyyMMdd") + educationVo.getStart_time(), "yyyyMMddHHmm");
//				BLogger.debug(logger, "calStart : {}", DateUtils.getStringFromCalendar(calStart, "yyyyMMdd HHmm"));
//				diffTime = DateUtils.diffNow(calStart); 
//				if (Math.abs(diffTime) > 30*60*1000) {
//					throw new NotEduStartTimeException();
//				}
//			}

			// 회원 교육으로 등록
			myEduVo = new MemberEducationVo();
			myEduVo.setEducation_id(qrVo.getEducation_id());
			myEduVo.setCourse_id(qrVo.getCourse_id());
			myEduVo.setMember_id(sessionId);
			myEduVo.setStart_qr_id(qrVo.getQr_id());
			if (courseVo != null) {
				myEduVo.setTake_min(courseVo.getTake_min());
			} else {
				myEduVo.setTake_min(educationVo.getTake_min());
			}
			
			memberDao.insertMemberEducation(myEduVo);
			
		} else {
			// 시작 정보가 있으면 종료로 등록 - 종료가 등록된 경우에도 종료 업데이트 해야 함
			if ("S".equals(qrVo.getType())) {
				// 종료를 해야 하는데, 시작 QR인 경우 오류
				throw new AlreadyQrStartException();
			}
			
			// 이미 평가가 된 것은 등록할 수 없음 - 승인대기가 아닌 것은 업데이트 안됨
			if (!"R".equals(myEduVo.getApproval())) {
				throw new AlreadyFinishEduException();
			}
			// TODO:beksung 시간이 너무 짧으면 어떻게 하지?
			
			// TODO:beksung 종료 시간이 지나면 어떻게 해야 할까?
			
			
			// 교육 종료 등록
			myEduVo.setEnd_qr_id(qrVo.getQr_id());
			myEduVo.setEnd_dt(new Date());
			
			memberDao.updateMemberEducation(myEduVo);
		}
		
		param.clear();
		MapUtils.put(param, "member_id", sessionId);
		MapUtils.put(param, "education_id", qrVo.getEducation_id());
		MapUtils.put(param, "course_id", qrVo.getCourse_id());
		myEduVo = memberDao.selectMemberEducation(param);
		
		// 내 교육 정보를 회신해야 함
		resp.setMyEdu(converter.convertMyEdu(myEduVo));
		
		return resp;
	}

	/**
	*	6.교육 평가, 수정하기
	* @param		eduId - 교육 아이디
	* @param		courseId - 강의 아이디 - 나의 교육에서 주는 정보
	* @param		star - 교육 평가 별점 (1~5)
	* @param		body - 교육 평가 내용
	 * @throws NotFoundEducationException 
	 * @throws InvalidEvalStateException 
	**/
	public PcResp postEvalEdu(Long eduId, Long courseId, Integer star, String body, Long sessionId ) 
			throws NotFoundEducationException, InvalidEvalStateException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// qr에 등록한 나의 시작 정보가 있는지 확인한다.
		param.clear();
		MapUtils.put(param, "member_id", sessionId);
		MapUtils.put(param, "education_id", eduId);
		if (courseId == null) {
			MapUtils.put(param, "empty_course_id", true);	// CourseId가 없는 경우 
		} else {
			MapUtils.put(param, "course_id", courseId);
		}
		MemberEducationVo myEduVo = memberDao.selectMemberEducation(param);
		// 교육이 없음
		if (myEduVo == null) {
			throw new NotFoundEducationException();
		}
		
		if (myEduVo.getEnd_qr_id() == null) {
			// 종료를 하지 않아 작성 불가
			throw new InvalidEvalStateException();
		}
		
		myEduVo.setEval_star(star);
		myEduVo.setEval_body(body);
		if (myEduVo.getEval_reg_dt() == null) {
			// 최초 등록은 reg_dt
			myEduVo.setEval_reg_dt(new Date());			
		} else {
			// 이후 등록은 mod_dt
			myEduVo.setEval_mod_dt(new Date());
		}

		// 업데이트 하기 
		memberDao.updateMemberEducation(myEduVo);

		return resp;
	}

}
