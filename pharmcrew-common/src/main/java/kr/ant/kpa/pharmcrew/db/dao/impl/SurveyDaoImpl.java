package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.SurveyDao;

import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;

@Repository
public class SurveyDaoImpl extends SqlSessionDaoSupport implements SurveyDao {

	private static final String SURVEY = "kr.ant.kpa.pharmcrew.db.survey.survey-";
	private static final String SURVEYQUESTION = "kr.ant.kpa.pharmcrew.db.survey.surveyquestion-";
	private static final String SURVEYANSWERCHOICE = "kr.ant.kpa.pharmcrew.db.survey.surveyanswerchoice-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	설문
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertSurvey(SurveyVo vo) { 
		getSqlSession().insert(SURVEY + "insert", vo);
	}

	@Override
	public List<SurveyVo> listSurvey(Map<String, Object> param) { 
		return getSqlSession().selectList(SURVEY + "select", param);
	}

	@Override
	public int countSurvey(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEY + "count", param);
	}

	@Override
	public SurveyVo selectSurvey(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEY + "select", param);
	}

	@Override
	public void updateSurvey(SurveyVo vo) { 
		getSqlSession().update(SURVEY + "update", vo);
	}

	@Override
	public void deleteSurvey(SurveyVo vo) { 
		getSqlSession().delete(SURVEY + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	설문 문항
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertSurveyQuestion(SurveyQuestionVo vo) { 
		getSqlSession().insert(SURVEYQUESTION + "insert", vo);
	}

	@Override
	public List<SurveyQuestionVo> listSurveyQuestion(Map<String, Object> param) { 
		return getSqlSession().selectList(SURVEYQUESTION + "select", param);
	}

	@Override
	public int countSurveyQuestion(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEYQUESTION + "count", param);
	}

	@Override
	public SurveyQuestionVo selectSurveyQuestion(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEYQUESTION + "select", param);
	}

	@Override
	public void updateSurveyQuestion(SurveyQuestionVo vo) { 
		getSqlSession().update(SURVEYQUESTION + "update", vo);
	}

	@Override
	public void deleteSurveyQuestion(SurveyQuestionVo vo) { 
		getSqlSession().delete(SURVEYQUESTION + "delete", vo);
	}

	@Override
	public void mergeSurveyQuestionList(Map<String, Object> param) {
		getSqlSession().insert(SURVEYQUESTION + "merge-list", param);
	}

	/**
	 * 질문에 대한 답변 일괄적으로 등록하기 
	 */
	@Override
	public void updateSurveyQuestionAnswer(Map<String, Object> param) {
		getSqlSession().update(SURVEYQUESTION + "list-update", param);
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	설문 답변(객관식)
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertSurveyAnswerChoice(SurveyAnswerChoiceVo vo) { 
		getSqlSession().insert(SURVEYANSWERCHOICE + "insert", vo);
	}

	@Override
	public List<SurveyAnswerChoiceVo> listSurveyAnswerChoice(Map<String, Object> param) { 
		return getSqlSession().selectList(SURVEYANSWERCHOICE + "select", param);
	}

	@Override
	public int countSurveyAnswerChoice(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEYANSWERCHOICE + "count", param);
	}

	@Override
	public SurveyAnswerChoiceVo selectSurveyAnswerChoice(Map<String, Object> param) { 
		return getSqlSession().selectOne(SURVEYANSWERCHOICE + "select", param);
	}

	@Override
	public void updateSurveyAnswerChoice(SurveyAnswerChoiceVo vo) { 
		getSqlSession().update(SURVEYANSWERCHOICE + "update", vo);
	}

	@Override
	public void deleteSurveyAnswerChoice(SurveyAnswerChoiceVo vo) { 
		getSqlSession().delete(SURVEYANSWERCHOICE + "delete", vo);
	}

	@Override
	public void mergeSurveyAnswerChoiceList(Map<String, Object> param) {
		getSqlSession().insert(SURVEYANSWERCHOICE + "merge-list", param);
	}
	
	/**
	 * 질문에 대한 답변 일괄적으로 등록하기 
	 */
	@Override
	public void updateSurveyAnswerChoice(Map<String, Object> param) {
		getSqlSession().update(SURVEYANSWERCHOICE + "list-update", param);
	}
}

