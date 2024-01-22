package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import lombok.Data;

import java.util.List;

@Data
public class Ptaxnotice {
    private Long noticeId;
    private String title;
    private String body;
    private String openYn;
    /**
     * 첨부 파일 리스트
     */
    @Description("첨부 파일 리스트")
    private List<AttachFile> attachFiles;
    private String sendType;
    private String target;
    private String targetPharmName;
    private Long targetMemberId;
    private String targetMemberName;

    // t_push
    private String os;
    private String reserveDate;
    private String reserveHour;
    private String reserveMinute;
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
     * 등록자 명
     */
    @Description("등록자 명")
    private String regName;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
