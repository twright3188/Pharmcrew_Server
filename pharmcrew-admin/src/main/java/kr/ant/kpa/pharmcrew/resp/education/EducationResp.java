package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class EducationResp extends PcResp {
    /**
     * 교육
     */
    @Description("교육")
    private Education education;
    /**
     * 강의 리스트
     */
    @Description("강의 리스트")
    private EducationCoursess coursess;
}
