package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class EduAttendListResp extends PcResp {
    /**
     * 후기 평점 평균
     */
    @Description("후기 평점 평균")
    private Integer evalStarAvg;
    /**
     * 후기 수
     */
    @Description("후기 수")
    private Integer evalCnt;
    /**
     * 참여 리스트
     */
    @Description("참여 리스트")
    private List<EduAttend> attends;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
