package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Member implements Serializable {
    /**
     * 회원 ID
     */
    @Description("회원 ID")
    private Long memberId;
    /**
     * 조직 1
     */
    @Description("조직 1")
    private Organize2 organize1;
    /**
     * 조직 1
     */
    @Description("조직 2")
    private Organize2 organize2;
    /**
     * 면허 번호
     */
    @Description("면허 번호")
    private String licenseNo;
    /**
     * 이름
     */
    @Description("이름")
    private String name;
    /**
     * 생년월일<br>
     *     (SimpleDateFormat: yyyyMMdd)"
     */
    @Description("생년월일<br>" +
            "(SimpleDateFormat: yyyyMMdd)")
    private String birthday;
    /**
     * 휴대 전화번호
     */
    @Description("휴대 전화번호")
    private String phone;
    /**
     * 이메일
     */
    @Description("이메일")
    private String email;
    /**
     * 성별<br>
     *     M(en): 남, W(omen): 여
     */
    @Description("성별<br>" +
            "M(en): 남, W(omen): 여")
    private String sex;
    /**
     * 광고 이메일 수신 여부
     */
    @Description("푸시 수신 여부")
    private String pushYn;
    /**
     * 광고 문자 수신 여부
     */
    @Description("광고 문자 수신 여부")
    private String adSmsYn;
    /**
     * 광고 푸시 수신 여부
     */
    @Description("광고 푸시 수신 여부")
    private String adPushYn;
    /**
     * 마지막 로그인 일시
     */
    @Description("마지막 로그인 일시")
    private Long lastLoginDt;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;

}
