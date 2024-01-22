package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

@Data
public class SurveyAnswerChoice {
    /**
     * 번호
     */
    @Description("번호")
    private Integer answerNo;
    /**
     * 답변
     */
    @Description("답변")
    private String answer;
    /**
     * 응답자 수
     */
    @Description("응답자 수")
    private Integer answerCnt;
}
