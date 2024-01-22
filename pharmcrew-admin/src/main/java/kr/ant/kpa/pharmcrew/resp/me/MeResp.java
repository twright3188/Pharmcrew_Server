package kr.ant.kpa.pharmcrew.resp.me;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class MeResp extends PcResp {
    /**
     * 로그인 ID
     */
    @Description("로그인 ID")
    private String loginId;
    /**
     * 이름
     */
    @Description("이름")
    private String name;
    /**
     * 조직 ID 리스트
     */
    @Description("조직 ID 리스트")
    private List<Long> organizeIds;
    /**
     * 이메일
     */
    @Description("이메일")
    private String email;
    /**
     * 전화번호
     */
    @Description("전화번호")
    private String tel;
    /**
     * 휴대 전화번호
     */
    @Description("휴대 전화번호")
    private String phone;
}
