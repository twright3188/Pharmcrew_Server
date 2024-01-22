package kr.ant.kpa.pharmcrew.resp.common;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.io.Serializable;

@Data
public class Organize implements Serializable {
    /**
     * 조직 ID
     */
    @Description("조직 ID")
    private Long organizeId;
    /**
     * 조직 레벨1 ID
     */
    @Description("조직 레벨1 ID")
    private Long organizeD1Id;
    /**
     * 조직 레벨2 ID
     */
    @Description("조직 레벨2 ID")
    private Long organizeD2Id;
    /**
     * 조직 레벨3 ID
     */
    @Description("조직 레벨3 ID")
    private Long organizeD3Id;
    /**
     * 조직 레벨
     */
    @Description("조직 레벨")
    private Integer depth;
    /**
     * 조직명
     */
    @Description("조직명")
    private String name;
}
