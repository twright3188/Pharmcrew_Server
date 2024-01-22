package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class DocListResp extends PcResp {
    /**
     * 문서 리스트
     */
    @Description("문서 리스트")
    private List<Doc> docs;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
