package kr.ant.kpa.pharmcrew.resp.admin;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class AdminResp extends PcResp {
    /**
     * 관리자
     */
    @Description("관리자")
    private Admin admin;
}
