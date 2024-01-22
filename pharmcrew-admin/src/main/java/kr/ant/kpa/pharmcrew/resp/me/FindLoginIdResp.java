package kr.ant.kpa.pharmcrew.resp.me;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class FindLoginIdResp extends PcResp {
    /**
     * 로그인 아이디
     */
    @Description("로그인 아이디")
    private String loginId;
}
