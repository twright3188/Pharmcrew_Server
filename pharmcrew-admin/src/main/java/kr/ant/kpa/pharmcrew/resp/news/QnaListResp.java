package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class QnaListResp extends PcResp {
    /**
     * QNA 리스트
     */
    @Description("QNA 리스트")
    private List<Qna> qnas;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
