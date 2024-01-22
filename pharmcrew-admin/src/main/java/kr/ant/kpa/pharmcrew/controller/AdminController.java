package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import com.bumdori.spring.annotation.validation.regex.EmailValidation;
import com.bumdori.spring.annotation.validation.regex.HandphoneValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminListResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminResp;
import kr.ant.kpa.pharmcrew.service.AdminService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotUseableLoginidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller("관리자")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admins", method = RequestMethod.POST, name = "1. 관리자 등록")
    @ResponseBody
    @Session
    @Description("관리자를 등록한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public PcResp registAdmin(
            @RequestParam("loginId") @Description("로그인 아이디") @LengthValidation(max = 10) String loginId,
            @RequestParam("password") @Description("비밀번호") @LengthValidation(max = 20) String password,
            @RequestParam("name") @Description("이름") @LengthValidation(max = 10) String name,
            @RequestParam("email") @Description("이메일") @LengthValidation(max = 100) @EmailValidation String email,
            @RequestParam("authority") @Description("관리자 타입<br>" +
                    "ADMIN_AUTHORITY.java") @EnumValidation({"S", "A", "O"}) String authority,
            @RequestParam(value = "tel", required = false) @LengthValidation(max = 11) @Description("전화번호") String tel,
            @RequestParam(value = "phone", required = false) @LengthValidation(max = 11) @HandphoneValidation @Description("휴대 전화번호") String phone,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("state") @Description("상태, N(ormal): 일반, S(top): 중지") @EnumValidation({"N", "S"}) String state,
            HttpSession session
    ) {
        long regId = (long) session.getAttribute("admin_id");

        return adminService.registAdmin(loginId, password, name, email, authority, tel, phone, organizeId, state, regId);
    }

    @RequestMapping(value = "/admin/loginId", method = RequestMethod.GET, name = "2. 로그인 아이디 중복 확인")
    @ResponseBody
    @Session
    @Description("로그인 아이디의 중복 여부를 확인한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public PcResp checkLoginId(
            @RequestParam("loginId") @Description("로그인 아이디") @LengthValidation(max = 10)  String loginId
    ) throws NotUseableLoginidException {
        return adminService.checkLoginId(loginId);
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET, name = "3. 관리자 리스트")
    @ResponseBody
    @Session
    @Description("관리자 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public AdminListResp adminList(
            @RequestParam(value = "keyword", required = false) @Description("검색어(아이디, 이름)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage,
            HttpSession session
    ) {
        Long organizeId = (Long) session.getAttribute("organize_id");

        return adminService.adminList(keyword, page, cntPerPage, organizeId);
    }

    @RequestMapping(value = "/admins/{adminId}", method = RequestMethod.GET, name = "4. 관리자")
    @ResponseBody
    @Session
    @Description("관리자를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public AdminResp admin(
            @PathVariable("adminId") @Description("관리자 ID") Long adminId
    ) {
        return adminService.admin(adminId);
    }

    @RequestMapping(value = "/admins/{adminId}", method = RequestMethod.PUT, name = "5. 관리자 수정")
    @ResponseBody
    @Session
    @Description("관리자를 수정한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public PcResp updateAdmin(
            @PathVariable("adminId") @Description("관리자 ID") Long adminId,
            @RequestParam(value = "password", required = false) @Description("비밀번호") @LengthValidation(max = 20) String password,
            @RequestParam("name") @Description("이름") @LengthValidation(max = 10) String name,
            @RequestParam("email") @Description("이메일") @LengthValidation(max = 100) String email,
            @RequestParam("authority") @Description("관리자 타입<br>" +
                    "ADMIN_AUTHORITY.java") @EnumValidation({"S", "A", "O"}) String authority,
            @RequestParam(value = "tel", required = false) @LengthValidation(max = 11) @Description("전화번호") String tel,
            @RequestParam(value = "phone", required = false) @LengthValidation(max = 11) @HandphoneValidation @Description("휴대 전화번호") String phone,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("state") @Description("상태, N(ormal): 일반, S(top): 중지") @EnumValidation({"N", "S"}) String state,
            HttpSession session
    ) {
        long modId = (long) session.getAttribute("admin_id");

        return adminService.updateAdmin(adminId, password, name, email, authority, tel, phone, organizeId, state, modId);
    }

//    // XXX 안하는게?
//    @RequestMapping(value = "/admins/{adminId}", method = RequestMethod.DELETE, name = "6. 관리자 삭제")
//    @ResponseBody
//    @Session
//    @Description("관리자를 삭제한다.")
//    @Histories({
//            @History(date = "2020-05-14", description = "설계"),
//    })
//    public AdminResp deleteAdmin(
//            @PathVariable("adminId") @Description("관리자 ID") Long adminId
//    ) {
//        return adminService.deleteAdmin(adminId);
//    }
}
