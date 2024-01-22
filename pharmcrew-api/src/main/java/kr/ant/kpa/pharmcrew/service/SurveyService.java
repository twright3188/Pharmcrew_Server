package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.QuestionsResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveysResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;

public interface SurveyService {

	/**
	*	1.설문 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	SurveysResp getGetSurveyList(Integer page, Integer count, Long sessionId );

	/**
	*	2.설문 문항 목록 요청
	* @param		surveyId	설문 아이디
	**/
	QuestionsResp getGetQuestions(Long surveyId, Long sessionId )
			throws NotFoundSurveyException ;

	/**
	*	3.설문 응답 등록
	* @param		surveyId	설문 아이디
	* @param		questNo	질문 번호 목록
	* @param		answer	답변 목록
	**/
	PcResp postPutSurvey(Long surveyId, Integer[] questNo, String[] answer, Long sessionId )
			throws NotFoundSurveyException ;

}
