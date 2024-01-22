package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Survey implements Serializable {
    /**
     * 설문 ID
     */
    @Description("설문 ID")
    private Long surveyId;
    /**
     * 제목
     */
    @Description("제목")
    private String title;
    /**
     * 상세
     */
    @Description("상세")
    private String detail;
    /**
     * 노출 여부<br>
     *     Y: 노출, N: 비노출
     */
    @Description("노출 여부<br>" +
            "Y: 노출, N: 비노출")
    private String isOpen;
    /**
     * 결과 공개 여부<br>
     *     Y: 노출, N: 비노출
     */
    @Description("결과 공개 여부<br>" +
            "Y: 노출, N: 비노출")
    private String openResult;
    /**
     * 설문 시작일<br>
     *     (SimpleDateFormat: yyyy.MM.dd)
     */
    @Description("설문 시작일<br>" +
            "(SimpleDateFormat: yyyy.MM.dd)")
    private String startDate;
    /**
     * 설문 종료일<br>
     *     (SimpleDateFormat: yyyy.MM.dd)
     */
    @Description("설문 종료일<br>" +
            "(SimpleDateFormat: yyyy.MM.dd)")
    private String endDate;
//    /**
//     * 조직 ID 리스트
//     */
//    @Description("조직 ID 리스트")
//    private List<Long> organizeIds;
//    /**
//     * 조직명
//     */
//    @Description("조직명")
//    private String organizeName;
    /**
     * 조직
     */
    @Description("조직")
    private Organize2 organize;
    /**
     * 참여 회원 수
     */
    @Description("참여 회원 수")
    private Integer joinMemberCnt;
    /**
     * 등록자명
     */
    @Description("등록자명")
    private String regName;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
