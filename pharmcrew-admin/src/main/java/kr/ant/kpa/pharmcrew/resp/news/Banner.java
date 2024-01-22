package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import lombok.Data;

@Data
public class Banner {
    /**
     * 배너 ID
     */
    @Description("배너 ID")
    private Long bannerId;
    /**
     * 제목
     */
    @Description("제목")
    private String title;
    /**
     * 이미지 파일
     */
    @Description("이미지 파일")
    private AttachFile imgFile;
    /**
     * 노출 순서
     */
    @Description("노출 순서")
    private Integer idx;
    /**
     * 이동 타입<br>
     * @see kr.ant.kpa.pharmcrew.type.news.MOVE
     */
    @Description("이동 타입<br>")
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
     * 노출 여부<br>
     *     Y(es): 노출, N(o): 비노출
     */
    @Description("노출 여부<br>" +
            "Y(es): 노출, N(o): 비노출")
    private String isOpen;
    /**
     * 노출 시작 일(SimpleDateFormat: yyyy.MM.dd)
     */
    @Description("노출 시작 일<br>" +
            "SimpleDateFormat: yyyy.MM.dd")
    private String openStartDate;
    /**
     * 노출 시작 시<br>
     *     Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("노출 시작 시<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String openStartHour;
    /**
     * 노출 종료 일<br>
     *     SimpleDateFormat: yyyy.MM.dd
     */
    @Description("노출 종료 일<br>" +
            "SimpleDateFormat: yyyy.MM.dd")
    private String openEndDate;
    /**
     * 노출 종료 시<br>
     *     Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("노출 종료 시<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String openEndHour;
    /**
     * 등록자 명
     */
    @Description("등로자 명")
    private String regName;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
