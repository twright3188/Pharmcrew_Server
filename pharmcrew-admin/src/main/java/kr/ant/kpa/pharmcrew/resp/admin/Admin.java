package kr.ant.kpa.pharmcrew.resp.admin;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.Organize;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.util.List;

@Data
public class Admin {
    /**
     * 관리자 ID
     */
    @Description("관리자 ID")
    private Long adminId;
    /**
     * 로그인 아이디
     */
    @Description("로그인 아이디")
    private String loginId;
    /**
     * 이름
     */
    @Description("이름")
    private String name;
    /**
     * 이메일
     */
    @Description("이메일")
    private String email;
    /**
     * 전화번호
     */
    @Description("전화번호")
    private String tel;
    /**
     * 휴대 전화번호
     */
    @Description("휴대 전화번호")
    private String phone;
    /**
     * 권한
     * @see kr.ant.kpa.pharmcrew.type.admin.ADMIN_AUTHORITY
     */
    @Description("권한")
    private String authority;
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
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
    /**
     * 상태
     * @see kr.ant.kpa.pharmcrew.type.admin.ADMIN_STATE
     */
    @Description("상태")
    private String state;
}
