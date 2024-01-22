package kr.ant.kpa.pharmcrew.resp.partner;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class PartnerListResp extends PcResp {
    /**
     * 파트너 리스트
     */
    @Description("파트너 리스트")
    private List<Partner> partners;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
