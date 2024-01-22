package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;

public interface SurveyDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	설문
	////////////////////////////////////////////////////////////////////////////////////////

	void insertSurvey(SurveyVo vo); 
	List<SurveyVo> listSurvey(Map<String, Object> param); 
	int countSurvey(Map<String, Object> param); 
	SurveyVo selectSurvey(Map<String, Object> param); 
	void updateSurvey(SurveyVo vo); 
	void deleteSurvey(SurveyVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	설문 문항
	////////////////////////////////////////////////////////////////////////////////////////

	void insertSurveyQuestion(SurveyQuestionVo vo); 
	List<SurveyQuestionVo> listSurveyQuestion(Map<String, Object> param); 
	int countSurveyQuestion(Map<String, Object> param); 
	SurveyQuestionVo selectSurveyQuestion(Map<String, Object> param); 
	void updateSurveyQuestion(SurveyQuestionVo vo); 
	void deleteSurveyQuestion(SurveyQuestionVo vo);

	void mergeSurveyQuestionList(Map<String, Object> param);

	/**
	 * 질문에 대한 답변 일괄적으로 등록하기 
	 */
	void updateSurveyQuestionAnswer(Map<String, Object> param); 
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	설문 답변(객관식)
	////////////////////////////////////////////////////////////////////////////////////////

	void insertSurveyAnswerChoice(SurveyAnswerChoiceVo vo); 
	List<SurveyAnswerChoiceVo> listSurveyAnswerChoice(Map<String, Object> param); 
	int countSurveyAnswerChoice(Map<String, Object> param); 
	SurveyAnswerChoiceVo selectSurveyAnswerChoice(Map<String, Object> param); 
	void updateSurveyAnswerChoice(SurveyAnswerChoiceVo vo); 
	void deleteSurveyAnswerChoice(SurveyAnswerChoiceVo vo); 

	void mergeSurveyAnswerChoiceList(Map<String, Object> param);

	/**
	 * 질문에 대한 답변 일괄적으로 등록하기 
	 */
	void updateSurveyAnswerChoice(Map<String, Object> param); 
}

