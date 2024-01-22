package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class NoticeListResp extends PcResp {
    /**
     * 공지 리스트
     */
    @Description("공지 리스트")
    private List<Notice> notices;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
