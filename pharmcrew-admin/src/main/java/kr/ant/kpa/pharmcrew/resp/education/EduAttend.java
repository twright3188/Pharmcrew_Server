package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.io.Serializable;

@Data
public class EduAttend implements Serializable {
    /**
     * 참여 ID
     */
    @Description("참여 ID")
    private Long attendId;
    /**
     * 회원명
     */
    @Description("회원명")
    private String memberName;
    /**
     * 출석 일시
     */
    @Description("출석 일시")
    private Long startDt;
    /**
     * 종료 일시
     */
    @Description("종료 일시")
    private Long endDt;
    /**
     * 승인 상태
     */
    @Description("승인 상태")
    private String approval;
    /**
     * 평가 별점
     */
    @Description("평가 별점")
    private Integer evalStar;
    /**
     * 평가 내용
     */
    @Description("평가 내용")
    private String evalBody;
}
