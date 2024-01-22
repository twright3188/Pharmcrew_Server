package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.io.Serializable;

@Data
public class SurveyAnswer implements Serializable {
    /**
     * 참여 회원명
     */
    @Description("참여 회원명")
    private String memberName;
    /**
     * 응답
     */
    @Description("응답")
    private String answer;
}
