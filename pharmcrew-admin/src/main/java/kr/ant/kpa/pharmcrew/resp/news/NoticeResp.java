package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class NoticeResp extends PcResp {
    /**
     * 공지
     */
    @Description("공지")
    private Notice notice;
}
