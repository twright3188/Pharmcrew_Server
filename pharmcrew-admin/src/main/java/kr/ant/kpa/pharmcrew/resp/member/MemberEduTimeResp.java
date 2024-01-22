package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class MemberEduTimeResp extends PcResp {
    /**
     * 교육 이수 시간(분)
     */
    @Description("교육 이수 시간(분)")
    private Integer minute;
    /**
     * 필요 이수 시간(분)
     */
    @Description("필요 이수 시간(분)")
    private Integer maxMinute;
}
