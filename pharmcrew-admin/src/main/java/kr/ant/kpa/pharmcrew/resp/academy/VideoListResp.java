package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class VideoListResp extends PcResp {
    /**
     * 비디오 리스트
     */
    @Description("비디오 리스트")
    private List<Video> videos;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
