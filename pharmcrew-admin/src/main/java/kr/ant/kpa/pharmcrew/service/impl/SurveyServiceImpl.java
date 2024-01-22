package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import com.google.gson.Gson;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.converter.SurveyConverter;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.SurveyDao;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.*;
import kr.ant.kpa.pharmcrew.service.SurveyService;
import kr.ant.kpa.pharmcrew.type.survey.SURVEY_ANSWER;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletableSurveyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDao surveyDao;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private SurveyConverter surveyConverter;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private PcUtils pcUtils;
    @Autowired
    private Gson gson;

    @Transactional
    @Override
    public PcResp registSurvey(String startDate, String endDate, String isOpen, String openResult, Long organizeId, String title, String detail, String questionsJson, long regId) {
        SurveyQuestions questions = gson.fromJson(questionsJson, SurveyQuestions.class);

        SurveyVo surveyVo = new SurveyVo();
        surveyVo.setTitle(title);
        surveyVo.setDetail(detail);
        surveyVo.setIs_open(isOpen);
        surveyVo.setOpen_result(openResult);
        try {
            // Controller에서 검증
            surveyVo.setStart_date(pcUtils.dateFormat.parse(startDate));
            if (endDate != null) {
                surveyVo.setEnd_date(pcUtils.dateFormat.parse(endDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        surveyVo.setOrganize_id(organizeId);
        surveyVo.setReg_id(regId);
        surveyDao.insertSurvey(surveyVo);

        List<SurveyQuestionVo> questionVos = new ArrayList<>();
        List<SurveyAnswerChoiceVo> answerVos = null;
        for (SurveyQuestion question: questions.getSurveyQuestions()) {
            SurveyQuestionVo questionVo = new SurveyQuestionVo();
//            questionVo.setSurvey_id(surveyVo.getSurvey_id());
            questionVo.setQuestion_idx(question.getIdx());
            questionVo.setQuestion(question.getQuestion());
            questionVo.setAnswer_div(question.getAnswerType());
            questionVos.add(questionVo);
            switch (SURVEY_ANSWER.valueOf(question.getAnswerType())) {
                case O:
                    for (SurveyAnswerChoice answer: question.getAnswerChoices()) {
                        if (answerVos == null) {
                            answerVos = new ArrayList<>();
                        }
                        SurveyAnswerChoiceVo answerVo = new SurveyAnswerChoiceVo();
//                        answerVo.setSurvey_id(surveyVo.getSurvey_id());
                        answerVo.setQuestion_idx(question.getIdx());
                        answerVo.setAnswer_no(answer.getAnswerNo());
                        answerVo.setAnswer(answer.getAnswer());
                        answerVos.add(answerVo);
                    }
                    break;
            }
        }

        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "survey_id", surveyVo.getSurvey_id());
        MapUtils.put(param, "questions", questionVos);
        surveyDao.mergeSurveyQuestionList(param);

        if (answerVos != null) {
            param.clear();
            MapUtils.put(param, "survey_id", surveyVo.getSurvey_id());
            MapUtils.put(param, "answers", answerVos);
            surveyDao.mergeSurveyAnswerChoiceList(param);
        }

        return new PcResp();
    }

    @Override
    public SurveyListResp surveyList(String startDate, String endDate, Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "startDate", startDate);
        MapUtils.put(param, "endDate", endDate);
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        if (!StringUtils.isEmpty(isOpen)) {
            switch (isOpen) {
                case "Y":
                case "N":
                    MapUtils.put(param, "is_open", isOpen);
                    break;
            }
        }
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.put(param, "withJoinMemberCnt", true);
        List<SurveyVo> surveyVos = surveyDao.listSurvey(param);

        SurveyListResp resp = new SurveyListResp();
        if (ListUtils.isEmpty(surveyVos)) {
            resp.setSearchCnt(0);
        } else {
            for (SurveyVo surveyVo: surveyVos) {
                if (resp.getSurveys() == null) {
                    resp.setSurveys(new ArrayList<>());
                }
                resp.getSurveys().add(surveyConverter.convert(surveyVo));
            }
            resp.setSearchCnt(surveyDao.countSurvey(param));
        }
        return resp;
    }

    @Override
    public SurveyResp survey(Long surveyId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "survey_id", surveyId);
        MapUtils.put(param, "withJoinMemberCnt", true);
        SurveyVo surveyVo = surveyDao.selectSurvey(param);
        if (surveyVo == null)   throw new NotFoundException();

        MapUtils.put(param, "orderby", "question_idx");
        List<SurveyQuestionVo> questionVos = surveyDao.listSurveyQuestion(param);

        SurveyResp resp = new SurveyResp();
        resp.setSurvey(surveyConverter.convert(surveyVo));
        resp.setQuestions(new SurveyQuestions());
        resp.getQuestions().setSurveyQuestions(new ArrayList<>());
        for (SurveyQuestionVo questionVo: questionVos) {
            resp.getQuestions().getSurveyQuestions().add(surveyConverter.convert(questionVo));
        }
        return resp;
    }

    @Override
    public PcResp updateSurvey(Long surveyId, String startDate, String endDate, String isOpen, String openResult, Long organizeId, String title, String questionsJson, long modId) {
        SurveyQuestions questions = gson.fromJson(questionsJson, SurveyQuestions.class);

        SurveyVo surveyVo = new SurveyVo();
        surveyVo.setSurvey_id(surveyId);
        surveyVo.setTitle(title);
        surveyVo.setIs_open(isOpen);
        surveyVo.setOpen_result(openResult);
        try {
            // Controller에서 검증
            surveyVo.setStart_date(pcUtils.dateFormat.parse(startDate));
            if (endDate != null) {
                surveyVo.setEnd_date(pcUtils.dateFormat.parse(endDate));
            } else {
                surveyVo.setEndDateNull(true);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (organizeId != null) {
            surveyVo.setOrganize_id(organizeId);
        } else {
            surveyVo.setOrganizeIdSetNull(true);
        }
        surveyVo.setMod_id(modId);
        surveyDao.updateSurvey(surveyVo);

        List<SurveyQuestionVo> questionVos = new ArrayList<>();
        List<SurveyAnswerChoiceVo> answerVos = null;
        for (SurveyQuestion question: questions.getSurveyQuestions()) {
            SurveyQuestionVo questionVo = new SurveyQuestionVo();
//            questionVo.setSurvey_id(surveyVo.getSurvey_id());
            questionVo.setQuestion_idx(question.getIdx());
            questionVo.setQuestion(question.getQuestion());
            questionVo.setAnswer_div(question.getAnswerType());
            questionVos.add(questionVo);
            switch (SURVEY_ANSWER.valueOf(question.getAnswerType())) {
                case O:
                    for (SurveyAnswerChoice answer: question.getAnswerChoices()) {
                        if (answerVos == null) {
                            answerVos = new ArrayList<>();
                        }
                        SurveyAnswerChoiceVo answerVo = new SurveyAnswerChoiceVo();
//                        answerVo.setSurvey_id(surveyVo.getSurvey_id());
                        answerVo.setQuestion_idx(question.getIdx());
                        answerVo.setAnswer_no(answer.getAnswerNo());
                        answerVo.setAnswer(answer.getAnswer());
                        answerVos.add(answerVo);
                    }
                    break;
            }
        }

        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "survey_id", surveyVo.getSurvey_id());
        MapUtils.put(param, "questions", questionVos);
        surveyDao.mergeSurveyQuestionList(param);

        if (answerVos != null) {
            param.clear();
            MapUtils.put(param, "survey_id", surveyVo.getSurvey_id());
            MapUtils.put(param, "answers", answerVos);
            surveyDao.mergeSurveyAnswerChoiceList(param);
        }

        cacheManager.delete(CACHE.SURVEY, surveyId);

        return new PcResp();
    }

    @Override
    public PcResp deleteSurvey(Long surveyId) throws NotDeletableSurveyException {
        try {
            SurveyVo surveyVo = new SurveyVo();
            surveyVo.setSurvey_id(surveyId);
            surveyDao.deleteSurvey(surveyVo);

            return new PcResp();
        } catch (DataIntegrityViolationException e) {
            throw new NotDeletableSurveyException();
        }
    }

    @Override
    public SurveyAnswerListResp answerList(Long surveyId, Long questionIdx, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "survey_id", surveyId);
        MapUtils.put(param, "question_idx", questionIdx);
        MapUtils.put(param, "withMember", true);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<MemberSurveyVo> memberSurveyVos = memberDao.listMemberSurvey(param);

        SurveyAnswerListResp resp = new SurveyAnswerListResp();
        if (ListUtils.isEmpty(memberSurveyVos)) {
            resp.setSearchCnt(0);
        } else {
            for (MemberSurveyVo memberSurveyVo: memberSurveyVos) {
                if (resp.getSurveyAnswers() == null) {
                    resp.setSurveyAnswers(new ArrayList<>());
                }
                resp.getSurveyAnswers().add(surveyConverter.convert(memberSurveyVo));

                resp.setSearchCnt(memberDao.countMemberSurvey(param));
            }
        }
        return resp;
    }
}
