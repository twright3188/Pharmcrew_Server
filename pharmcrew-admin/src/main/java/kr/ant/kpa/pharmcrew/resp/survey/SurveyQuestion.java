package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SurveyQuestion implements Serializable {
    /**
     * 질문
     */
    @Description("질문")
    private String question;
    /**
     * 인덱스
     */
    @Description("인덱스")
    private Integer idx;
    /**
     * 질문 형식<br>
     *     SURVEY_ANSWER.java<br>
     *     S(ubjective)주관식, O(bjective)객관식
     * @see kr.ant.kpa.pharmcrew.type.survey.SURVEY_ANSWER
     */
    @Description("질문 형식<br>" +
            "S(ubjective)주관식, O(bjective)객관식")
    private String answerType;
    /**
     * 응답자 수
     */
    @Description("응답자 수")
    private Integer answerCnt;
    /**
     * 객관식 응답 항목 수
     */
    @Description("객관식 응답 항목 수")
    private Integer oAnswerCnt;
    /**
     * 객관식 답변 리스트<br>
     *     answerType이 O일 때
     */
    @Description("객관식 답변 리스트<br>" +
            "answerType이 O일 때")
    private List<SurveyAnswerChoice> answerChoices;
}
