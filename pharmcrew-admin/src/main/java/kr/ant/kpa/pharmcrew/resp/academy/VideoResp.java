package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class VideoResp extends PcResp {
    /**
     * 비디오
     */
    @Description("비디오")
    private Video video;
}
