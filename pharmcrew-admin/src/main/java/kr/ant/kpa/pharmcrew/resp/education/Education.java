package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Education implements Serializable {
    /**
     * 교육 ID
     */
    @Description("교육 ID")
    private Long educationId;
    /**
     * 조직
     */
    @Description("조직")
    private Organize2 organize;
    /**
     * 노출 여부<br>
     *     Y: 노출, N: 비노출
     */
    @Description("노출 여부<br>" +
            "Y: 노출, N: 비노출")
    private String isOpen;
    /**
     * 제목
     */
    @Description("제목")
    private String title;
    /**
     * 교육 시작일<br>
     *     SimpleDateFormat: yyyy.MM.dd
     */
    @Description("교육 시작일<br>" +
            "SimpleDateFormat: yyyy.MM.dd")
    private String startDate;
    /**
     * 교육 종료일<br>
     *     SimpleDateFormat: yyyy.MM.dd
     */
    @Description("교육 종료일<br>" +
            "SimpleDateFormat: yyyy.MM.dd")
    private String endDate;
    /**
     * 교육 시작 시<br>
     *     Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("교육 시작 시<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String startHour;
    /**
     * 교육 시작 분<br>
     *     Regex: ([0-5][0-9])
     */
    @Description("교육 시작 분<br>" +
            "Regex: ([0-5][0-9])")
    private String startMinute;
    /**
     * 교육 종료 시<br>
     *     Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("교육 종료 시<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String endHour;
    /**
     * 교육 종료 분<br>
     *     Regex: ([0-5][0-9])
     */
    @Description("교육 종료 분<br>" +
            "Regex: ([0-5][0-9])")
    private String endMinute;
    /**
     * 교육 인정 시간(시)<br>
     *      Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("교육 인정 시간(시)<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String takeHour;
    /**
     * 교육 인정 시간(분)<br>
     *     Regex: ([0-5][0-9])
     */
    @Description("교육 인정 시간(분)<br>" +
            "Regex: ([0-5][0-9])")
    private String takeMinute;
    /**
     * 인증 타입<br>
     * @see kr.ant.kpa.pharmcrew.type.education.EDUCATION_AUTH
     */
    @Description("인증 타입")
    private String authType;
    /**
     * 주소
     */
    @Description("주소")
    private String address;
    /**
     * 주소 상세
     */
    @Description("주소 상세")
    private String addressDetail;
    /**
     * 위도
     */
    @Description("위도")
    private String longitude;
    /**
     * 경도
     */
    @Description("경도")
    private String latitude;
    /**
     * 오시는 길(요약)
     */
    @Description("오시는 길(요약)")
    private String wayGuide;
    /**
     * 오시는 길(상세)
     */
    @Description("오시는 길(상세)")
    private String wayDetail;
    /**
     * 약도 파일
     */
    @Description("약도 파일")
    private AttachFile mapFile;
    /**
     * 시간표 파일
     */
    @Description("시간표 파일")
    private AttachFile timetableFile;
    /**
     * 담당자 명
     */
    @Description("담당자 명")
    private String master;
    /**
     * 담당자 휴대 전화번호
     */
    @Description("담당자 휴대 전화번호")
    private String masterPhone;
    /**
     * QR 1
     */
    @Description("QR 1")
    private String qr1;
    /**
     * QR 2
     */
    @Description("QR 2")
    private String qr2;
    /**
     * 조회 수
     */
    @Description("조회 수")
    private Long viewCnt;
    /**
     * 회원 교육 인증 완료 수
     */
    @Description("회원 교육 인증 완료 수")
    private Integer approvalCnt;
    /**
     * 등록자 명
     */
    @Description("담당자 명")
    private String regName;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
