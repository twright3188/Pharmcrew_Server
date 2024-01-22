package kr.ant.kpa.pharmcrew.resp.session;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.admin.Admin;
import lombok.Data;

@Data
public class LoginResp extends PcResp {
    /**
     * 관리자
     */
    @Description("관리자")
    private Admin admin;
}
