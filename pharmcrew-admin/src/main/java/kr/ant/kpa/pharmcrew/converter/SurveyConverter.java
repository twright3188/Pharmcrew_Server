package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.resp.survey.Survey;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyAnswer;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyAnswerChoice;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyQuestion;
import kr.ant.kpa.pharmcrew.type.survey.SURVEY_ANSWER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyConverter {
//    @Autowired
//    private OrganizeFacade organizeFacade;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private PcUtils pcUtils;

    public Survey convert(SurveyVo s) {
        Survey o = new Survey();
        o.setSurveyId(s.getSurvey_id());
        o.setTitle(s.getTitle());
        o.setDetail(s.getDetail());
        o.setIsOpen(s.getIs_open());
        o.setOpenResult(s.getOpen_result());
        o.setStartDate(pcUtils.dateFormat.format(s.getStart_date()));
        if (s.getEnd_date() != null) {
            o.setEndDate(pcUtils.dateFormat.format(s.getEnd_date().getTime()));
        }
//        if (s.getOrganize_id() != null) {
//            OrganizeVo organize = organizeFacade.get(s.getOrganize_id());
//            List<Long> ids = new ArrayList<>();
//            ids.add(organize.getOrganize_d1_id());
//            if (organize.getOrganize_d2_id() != null) {
//                ids.add(organize.getOrganize_d2_id());
//                if (organize.getOrganize_d3_id() != null) {
//                    ids.add(organize.getOrganize_d3_id());
//                }
//            }
//            o.setOrganizeIds(ids);
//            o.setOrganizeName(organize.getName());
//        } else {
//            o.setOrganizeName("전체");
//        }
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setJoinMemberCnt(s.getJoinMemberCnt());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }

    public SurveyQuestion convert(SurveyQuestionVo s) {
        SurveyQuestion o = new SurveyQuestion();
        o.setIdx(s.getQuestion_idx());
        o.setQuestion(s.getQuestion());
        o.setAnswerType(s.getAnswer_div());
        o.setAnswerCnt(s.getAnswer_cnt());
        switch (SURVEY_ANSWER.valueOf(s.getAnswer_div())) {
            case O:
                o.setOAnswerCnt(s.getAnswers().size());
                for (SurveyAnswerChoiceVo answerVo: s.getAnswers()) {
                    if (o.getAnswerChoices() == null) {
                        o.setAnswerChoices(new ArrayList<>());
                    }
                    o.getAnswerChoices().add(convert(answerVo));
                }
                break;
        }
        return o;
    }

    private SurveyAnswerChoice convert(SurveyAnswerChoiceVo s) {
        SurveyAnswerChoice o = new SurveyAnswerChoice();
        o.setAnswerNo(s.getAnswer_no());
        o.setAnswer(s.getAnswer());
        o.setAnswerCnt(s.getAnswer_cnt());
        return o;
    }

    public SurveyAnswer convert(MemberSurveyVo s) {
        SurveyAnswer o = new SurveyAnswer();
        o.setMemberName(s.getMemberName());
        o.setAnswer(s.getAnswer());
        return o;
    }
}
