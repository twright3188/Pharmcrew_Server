package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class SurveyResp extends PcResp {
    /**
     * 설문
     */
    @Description("설문")
    private Survey survey;
    /**
     * 문답 리스트
     */
    @Description("문답 리스트")
    private SurveyQuestions questions;
}
