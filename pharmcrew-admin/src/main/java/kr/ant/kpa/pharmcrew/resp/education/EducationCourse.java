package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import lombok.Data;

@Data
public class EducationCourse {
    /**
     * 강의 ID
     */
    @Description("강의 ID")
    private Long courseId;
    /**
     * 일차
     */
    private Integer day;
    /**
     * 강의 인덱스
     */
    @Description("강의 인덱스")
    private Integer idx;
    /**
     * 강의명
     */
    @Description("강의명")
    private String title;
    /**
     * 강의실
     */
    @Description("강의실")
    private String room;
    /**
     * 강사명
     */
    @Description("강사명")
    private String teacher;
    /**
     * 구분
     */
    @Description("구분")
    private String type;
    /**
     * 교육 시작 시<br>
     *     Regex: ([1-9]|[01][0-9]|2[0-3])
     */
    @Description("교육 시작 시<br>" +
            "Regex: ([1-9]|[01][0-9]|2[0-3])")
    private String startHour;
    /**
     * 교육 시작 분<br>
     *     Regex: ([0-5][0-9]))
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
     *     education의 authType이 P인 경우 필수
     */
    @Description("교육 인정 시간(시)<br>" +
            "education의 authType이 P인 경우 필수")
    private String takeHour;
    /**
     * 교육 인정 시간(분)<br>
     *     education의 authType이 P인 경우 필수
     */
    @Description("교육 인정 시간(분)<br>" +
            "education의 authType이 P인 경우 필수")
    private String takeMinute;
    /**
     * REQ only<br>
     *     QR 생성 타입<br>
     *     education의 authType이 P인 경우 필수<br>
     *         O: 하나, M: 각각(시작, 종료)
     */
    @Description("QR 생성 타입<br>" +
            "education의 authType이 P인 경우 필수<br>" +
            "O: 하나, M: 각각(시작, 종료)")
    private String qrType;
    /**
     * RESP only<br>
     *     QR 1 - authType이 P일 때
     */
    @Description("QR 1")
    private String qr1;
    /**
     * RESP only<br>
     *     QR 2 - authType이 P일 때
     */
    @Description("QR 2")
    private String qr2;
    /**
     * RESP only<br>
     *     참여 수 - authType이 P일 때
     */
    @Description("참여 수")
    private Integer attendCnt;

}
