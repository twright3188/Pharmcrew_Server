package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class MemberListResp extends PcResp {
    /**
     * 회원 리스트
     */
    @Description("회원 리스트")
    private List<Member> members;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
