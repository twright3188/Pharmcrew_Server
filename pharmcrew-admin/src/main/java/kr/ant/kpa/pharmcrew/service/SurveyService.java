package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyAnswerListResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyListResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletableSurveyException;

public interface SurveyService {
    PcResp registSurvey(String startDate, String endDate, String isOpen, String openResult, Long organizeId, String title, String detail, String questionsJson, long regId);
    SurveyListResp surveyList(String startDate, String endDate, Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage);
    SurveyResp survey(Long surveyId);
    PcResp updateSurvey(Long surveyId, String startDate, String endDate, String isOpen, String openResult, Long organizeId, String title, String questionsJson, long modId);
    PcResp deleteSurvey(Long surveyId) throws NotDeletableSurveyException;
    SurveyAnswerListResp answerList(Long surveyId, Long questionIdx, Integer page, Integer cntPerPage);
}
