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

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.QuestionsResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveysResp;
import kr.ant.kpa.pharmcrew.service.SurveyService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;

@Controller("5.Survey")
public class SurveyController {

	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

	@Autowired
	private SurveyService surveyService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.설문 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/survey/list", method=RequestMethod.GET, name="1.설문 목록")
	@Session(required=true)
	@Description("Page=null , 설문 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody SurveysResp getGetSurveyList(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		SurveysResp resp = surveyService.getGetSurveyList(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.설문 문항 목록 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/survey/{surveyId}", method=RequestMethod.GET, name="2.설문 문항 목록 요청")
	@Session(required=false)
	@Description("Page=null , 각 설문의 문항 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody QuestionsResp getGetQuestions(@PathVariable("surveyId") @Description("설문 아이디") Long surveyId,
			HttpSession session ) 
					throws NotFoundSurveyException {

		Long sessionId = null;
		if (session.getAttribute("memberId") != null) {
			sessionId = (Long) session.getAttribute("memberId");
		}
		QuestionsResp resp = surveyService.getGetQuestions(surveyId, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3.설문 응답 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/survey/{surveyId}", method=RequestMethod.POST, name="3.설문 응답 등록")
	@Session(required=true)
	@Description("Page=null , 설문 응답을 등록한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PcResp postPutSurvey(@PathVariable("surveyId") @Description("설문 아이디") Long surveyId,
			@RequestParam(value="questNo", required=false) @Description("질문 번호 목록") Integer[] questNo,
			@RequestParam(value="answer", required=false) @Description("답변 목록") String[] answer,
			HttpSession session ) 
					throws NotFoundSurveyException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = surveyService.postPutSurvey(surveyId, questNo, answer, sessionId);

		return resp;
	}

}
