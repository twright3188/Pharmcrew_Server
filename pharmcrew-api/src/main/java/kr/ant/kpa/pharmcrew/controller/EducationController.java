package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.RangeValidation;

import kr.ant.kpa.pharmcrew.resp.PcResp;
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

@Controller("6.Education")
public class EducationController {

	private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

	@Autowired
	private EducationService educationService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.교육 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/edu/list", method=RequestMethod.GET, name="1.교육 목록")
	@Session(required=true)
	@Description("Page=null , 교육 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-05-26", description="1차 완료")
	})
	public @ResponseBody EduListResp getGetEduList(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		EduListResp resp = educationService.getGetEduList(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.교육 상세
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/edu/{eduId}", method=RequestMethod.GET, name="2.교육 상세")
	@Session(required=true)
	@Description("Page=null , 교육 상세 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-05-26", description="1차 완료")
	})
	public @ResponseBody EduDetailResp getGetEduDetail(@PathVariable("eduId") @Description("교육 아이디") Long eduId,
			HttpSession session ) 
					throws NotFoundEducationException {

		Long sessionId = (Long) session.getAttribute("memberId");

		EduDetailResp resp = educationService.getGetEduDetail(eduId, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3.나의 교육 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/edu/mine", method=RequestMethod.GET, name="3.나의 교육 목록")
	@Session(required=true)
	@Description("Page=null , 나의 교육 이수 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-05-26", description="1차 완료")
	})
	public @ResponseBody EduMineListResp getGetEduMine(@RequestParam(value="year", required=true) @Description("검색할 연도") Integer year,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		EduMineListResp resp = educationService.getGetEduMine(year, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.출석, 퇴실 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/edu/attend", method=RequestMethod.POST, name="4.출석, 퇴실 등록")
	@Session(required=true)
	@Description("Page=null , 출석 참여, 퇴실을 등록한다.")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-05-26", description="1차 완료")
	})
	public @ResponseBody MyEduResp postPutEduAttend(@RequestParam(value="qrcode", required=true) @Description("촬영한 QR 코드 정보") String qrcode,
			HttpSession session ) 
					throws NotFoundQrException, InvalidQrStartException, AlreadyQrStartException, NotEduStartTimeException, AlreadyFinishEduException, AlreadyProgressEduException {

		Long sessionId = (Long) session.getAttribute("memberId");

		MyEduResp resp = educationService.postPutEduAttend(qrcode, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	6.교육 평가, 수정하기
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/edu/{eduId}/eval", method=RequestMethod.POST, name="5.교육 평가, 수정하기")
	@Session(required=true)
	@Description("Page=null , 교육을 평가, 수정한다.")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-05-26", description="1차 완료")
	})
	public @ResponseBody PcResp postEvalEdu(@PathVariable("eduId") @Description("교육 아이디") Long eduId,
			@RequestParam(value="courseId", required=false) @Description("강의 아이디 - 나의 교육에서 주는 정보") Long courseId,
			@RequestParam(value="star", required=true) @RangeValidation(min = 1, max = 5) @Description("교육 평가 별점 (1~5)") Integer star,
			@RequestParam(value="body", required=true) @Description("교육 평가 내용") String body,
			HttpSession session ) 
					throws NotFoundEducationException, InvalidEvalStateException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = educationService.postEvalEdu(eduId, courseId, star, body, sessionId);

		return resp;
	}

}
