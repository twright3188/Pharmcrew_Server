package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PtaxqnaResp extends PcResp {
    /**
     * QNA
     */
    @Description("QNA")
    private Ptaxqna qna;
}
