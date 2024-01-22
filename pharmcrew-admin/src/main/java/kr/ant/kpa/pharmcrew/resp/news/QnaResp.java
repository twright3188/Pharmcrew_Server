package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class QnaResp extends PcResp {
    /**
     * QNA
     */
    @Description("QNA")
    private Qna qna;
}
