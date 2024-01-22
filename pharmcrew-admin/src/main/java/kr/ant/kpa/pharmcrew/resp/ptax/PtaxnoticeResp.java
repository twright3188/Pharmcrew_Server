package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PtaxnoticeResp extends PcResp {
    /**
     * 공지
     */
    @Description("공지")
    private Ptaxnotice notice;
}
