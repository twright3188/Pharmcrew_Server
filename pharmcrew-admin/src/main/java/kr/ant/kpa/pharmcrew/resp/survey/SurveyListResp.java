package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class SurveyListResp extends PcResp {
    /**
     * 설문 리스트
     */
    @Description("설문 리스트")
    private List<Survey> surveys;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
