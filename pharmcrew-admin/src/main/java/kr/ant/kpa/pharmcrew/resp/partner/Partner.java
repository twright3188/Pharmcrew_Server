package kr.ant.kpa.pharmcrew.resp.partner;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import lombok.Data;

@Data
public class Partner {
    /**
     * 파트너 ID
     */
    @Description("파트너 ID")
    private Long partnerId;
    /**
     * 이름
     */
    @Description("이름")
    private String name;
    /**
     * 설명
     */
    @Description("설명")
    private String detail;
    /**
     * 아이콘 파일
     */
    @Description("아이콘 파일")
    private AttachFile iconFile;
    /**
     * 연동 URL
     */
    @Description("연동 URL")
    private String moveUrl;
    /**
     * 노출 여부<br>
     *     Y(es): 노출, N(o): 비노출
     */
    @Description("노출 여부<br>" +
            "Y(es): 노출, N(o): 비노출")
    private String isOpen;
    /**
     * 사용 중인 회원 수
     */
    @Description("사용 중인 회원 수")
    private Integer memberCount;
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
