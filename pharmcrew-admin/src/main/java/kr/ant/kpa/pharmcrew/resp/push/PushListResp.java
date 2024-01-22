package kr.ant.kpa.pharmcrew.resp.push;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class PushListResp extends PcResp {
    /**
     * 푸시 리스트
     */
    @Description("푸시 리스트")
    private List<Push> pushes;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
