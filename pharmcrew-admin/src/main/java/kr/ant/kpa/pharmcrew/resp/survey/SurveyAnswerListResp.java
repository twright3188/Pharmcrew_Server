package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class SurveyAnswerListResp extends PcResp {
    /**
     * 응답 리스트
     */
    @Description("응답 리스트")
    private List<SurveyAnswer> surveyAnswers;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
