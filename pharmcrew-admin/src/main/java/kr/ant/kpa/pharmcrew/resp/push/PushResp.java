package kr.ant.kpa.pharmcrew.resp.push;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PushResp extends PcResp {
    /**
     * 푸시
     */
    @Description("푸시")
    private Push push;
}
