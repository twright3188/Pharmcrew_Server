package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class PopupListResp extends PcResp {
    /**
     * 팝업 리스트
     */
    @Description("팝업 리스트")
    private List<Popup> popups;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
