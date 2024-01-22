package kr.ant.kpa.pharmcrew.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumdori.spring.BLogger;
import com.bumdori.util.DateUtils;
import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.converter.EducationConverter;
import kr.ant.kpa.pharmcrew.converter.MemberConverter;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.member.Member;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.service.SessionService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserLoginFailException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserNewLoginFailException;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

	private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberConverter	converter;
	
	@Autowired
	private EducationConverter	eduConverter;

	@Autowired
	private PcUtils pcUtils;

	@Transactional("transactionManager")

	/**
	*	1.로그인
	* @param		licenseNo - 면허번호
	* @param		password - 비밀번호- 회원인증 시 회신되는 정보
	**/
	public LoginResp postSession(String licenseNo, String password, HttpSession session ) 
			throws UserLoginFailException {
		LoginResp resp =  new LoginResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "license_no", licenseNo);
		MapUtils.put(param, "password", pcUtils.encryptPassword(password));
		
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new UserLoginFailException();
		}
		
		Member member = converter.convertMember(memberVo);
		
		// 교육이 진행 중인지 확인 
		// 교육을 시작했고, 종료가 없는 것 : 시간이 너무 지나지 않은 것 : 기본 1시간 이내
		param.clear();
		MapUtils.put(param, "member_id", memberVo.getMember_id());
		MapUtils.put(param, "nowProgress", true);	// 현재 진행 중인 교육 확인 	
		MapUtils.put(param, "approval", 	"R");						// 승인대기 중인 것 
		// 페이징  
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
			// 종료된 교육인지 확인 => 교육 1시간 이후에 접근 시 
//			Calendar calEnd = DateUtils.getCalendarFromString(DateUtils.getStringFromDate(educationVo.getEnd_date(),  "yyyyMMdd") + endTime, "yyyyMMddHHmm");
//			BLogger.debug(logger, "calEnd : {}", DateUtils.getStringFromCalendar(calEnd, "yyyyMMdd HHmm"));
//			Long diffTime = DateUtils.diffNow(calEnd);
//			if (diffTime > 60*60*1000) {
//				member.setIsIngEdu("N");
//			} else {
//
//				resp.setEduStartTime(myEduVo.getStart_dt().getTime());
//				// 내 교육 정보를 회신해야 함
//				member.setMyEdu(eduConverter.convertMyEdu(myEduVo));
//				member.setIsIngEdu("Y");
//			}
		} else {
			member.setIsIngEdu("N");
		}
		// 멤버 정보 회신 
		resp.setMember(member);
		
		// 마지막 로그인 시간 업데이트 
		memberVo.setLast_login_dt(new Date());
		memberDao.updateMember(memberVo);

		 session.setAttribute("memberId", memberVo.getMember_id());
		 session.setAttribute("license", memberVo.getLicense_no());
		// session.setMaxInactiveInterval(3);


		return resp;
	}

	/**
	*	2.로그아웃
	**/
	public PcResp deleteSession(Long sessionId ) {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		return resp;
	}
	
	/**
	*	3.로그인
	* @param		licenseNo	면허번호
	* @param		birth		생년월일
	* @param 		name		이름
	**/
	public LoginResp postSessionNew(String licenseNo, String birth, String name, HttpSession session ) 
			throws UserNewLoginFailException {
		LoginResp resp =  new LoginResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "license_no", licenseNo);
		MapUtils.put(param, "birthday", birth);
		MapUtils.put(param, "name", name);
		
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new UserNewLoginFailException();
		}
		
		Member member = converter.convertMember(memberVo);
		
		// 교육이 진행 중인지 확인 
		// 교육을 시작했고, 종료가 없는 것 : 시간이 너무 지나지 않은 것 : 기본 1시간 이내
		param.clear();
		MapUtils.put(param, "member_id", memberVo.getMember_id());
		MapUtils.put(param, "nowProgress", true);	// 현재 진행 중인 교육 확인 	
		MapUtils.put(param, "approval", 	"R");						// 승인대기 중인 것 
		// 페이징  
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

		} else {
			member.setIsIngEdu("N");
		}
		// 멤버 정보 회신 
		resp.setMember(member);
		
		// 마지막 로그인 시간 업데이트 
		memberVo.setLast_login_dt(new Date());
		memberDao.updateMember(memberVo);

		 session.setAttribute("memberId", memberVo.getMember_id());
		 session.setAttribute("license", memberVo.getLicense_no());
		// session.setMaxInactiveInterval(3);

		return resp;
	}


}
