package kr.ant.kpa.pharmcrew.resp.push;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Push implements Serializable {
    /**
     * 푸시 ID
     */
    @Description("푸시 ID")
    private Long pushId;
    /**
     * OS<br>
     *     ALL(모두), I(iOS), A(Android)
     */
    @Description("OS<br>" +
            "ALL(모두), I(iOS), A(Android)")
    private String osType;
    /**
     * 제목
     */
    @Description("제목")
    private String title;
    /**
     * 내용
     */
    @Description("내용")
    private String body;
    /**
     * 이미지 파일
     */
    @Description("이미지 파일")
    private AttachFile imgFile;
    /**
     * 전송 예약일
     */
    @Description("전송 예약일")
    private String reserveDate;
    /**
     * 전송 예약 시
     */
    @Description("전송 예약 시")
    private String reserveHour;
    /**
     * 전송 예약 분
     */
    @Description("전송 예약 분")
    private String reserveMinute;
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
     * 이동 카테고리
     * @see kr.ant.kpa.pharmcrew.type.notify.PUSH_CATEGORY
     */
    @Description("이동 카테고리<br>" +
            "PUSH_CATEGORY.java")
    private String categoryType;
    /**
     * 이동 타입<br>
     * @see kr.ant.kpa.pharmcrew.type.news.MOVE
     */
    @Description("이동 타입<br>" +
            "MOVE.java")
    private String moveType;
    /**
     * 이동 ID
     */
    @Description("이동 ID")
    private Long moveId;
    /**
     * 이동 URL
     */
    @Description("이동 URL")
    private String moveUrl;
    /**
     * 상태
     * @see kr.ant.kpa.pharmcrew.type.notify.PUSH_STATE
     */
    @Description("상태<br>" +
            "PUSH_STATE.java")
    private String state;
    /**
     * 전송 일시
     */
    @Description("전송 일시")
    private Long sendDt;
    /**
     * 전송 수
     */
    @Description("전송 수")
    private Integer sendCnt;
    /**
     * 전송 응답 수
     */
    @Description("전송 응답 수")
    private Integer recvCnt;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
