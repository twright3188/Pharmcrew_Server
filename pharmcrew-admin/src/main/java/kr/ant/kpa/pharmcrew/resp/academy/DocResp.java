package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class DocResp extends PcResp {
    /**
     * 문서
     */
    @Description("문서")
    private Doc doc;
}
