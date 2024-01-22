package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class PtaxqnaListResp extends PcResp {
    /**
     * QNA 리스트
     */
    @Description("QNA 리스트")
    private List<Ptaxqna> qnas;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
