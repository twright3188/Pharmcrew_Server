package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PopupResp extends PcResp {
    /**
     * 팝업
     */
    @Description("팝업")
    private Popup popup;
}
