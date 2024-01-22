package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class BannerResp extends PcResp {
    /**
     * 배너
     */
    @Description("배너")
    private Banner banner;
}
