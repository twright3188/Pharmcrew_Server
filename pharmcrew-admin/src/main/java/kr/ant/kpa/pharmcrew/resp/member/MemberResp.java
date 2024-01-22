package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class MemberResp extends PcResp {
    /**
     * 회원
     */
    @Description("회원")
    private Member member;
}
