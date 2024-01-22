package kr.ant.kpa.pharmcrew.resp.common;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class OrganizeListResp extends PcResp {
    /**
     * depth
     */
    @Description("depth")
    private Integer depth;
    /**
     * 조직 리스트
     */
    @Description("조직 리스트")
    private List<Organize> organizes;
}
