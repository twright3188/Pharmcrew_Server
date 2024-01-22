package kr.ant.kpa.pharmcrew.resp.partner;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PartnerResp extends PcResp {
    /**
     * 파트너
     */
    @Description("파트너")
    private Partner partner;
}
