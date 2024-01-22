package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.regex.RegexValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.me.FindLoginIdResp;
import kr.ant.kpa.pharmcrew.resp.me.MeResp;
import kr.ant.kpa.pharmcrew.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("내 정보")
public class MeController {
    @Autowired
    private MeService meService;

    @RequestMapping(value = "/loginId", method = RequestMethod.GET, name = "1. 아이디 찾기")
    @ResponseBody
    @Session(required = false)
    @Description("아이디를 찾는다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-06-08", description = "완료"),
    })
    public FindLoginIdResp findLoginId(
            @RequestParam("name") @Description("이름") String name,
            @RequestParam("email") @Description("이메일") String email
    ) {
        return meService.findLoginId(name, email);
    }

    @RequestMapping(value = "/password/reset", method = RequestMethod.PUT, name = "2. 비밀번호 찾기")
    @ResponseBody
    @Session(required = false)
    @Description("임시 비밀번호를 이메일로 발송한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
    })
    public PcResp resetPassword(
            @RequestParam("name") @Description("이름") String name,
            @RequestParam("loginId") @Description("로그인 아이디") String loginId,
            @RequestParam("email") @Description("이메일") String email
    ) {
        return meService.resetPassword(name, loginId, email);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET, name = "3. 내 정보")
    @ResponseBody
    @Session
    @Description("내 정보를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-24", description = "완료"),
    })
    public MeResp me(
            HttpSession session
    ) {
        long myId = (long) session.getAttribute("admin_id");

        return meService.me(myId);
    }

    @RequestMapping(value = "/me", method = RequestMethod.PUT, name = "4. 내 정보 수정")
    @ResponseBody
    @Session
    @Description("내 정보를 수정한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-24", description = "완료"),
    })
    public PcResp updateMe(
            @RequestParam(value = "password", required = false) @Description("비밀번호, 수정 시에만 입력") String password,
            @RequestParam("name") @Description("이름") String name,
            @RequestParam("email") @Description("이메일") String email,
            @RequestParam(value = "tel", required = false) @Description("전화번호") String tel,
            @RequestParam(value = "phone", required = false) @Description("휴대 전화번호") String phone,
            HttpSession session
    ) {
        long myId = (long) session.getAttribute("admin_id");

        return meService.updateMe(myId, password, name, email, tel, phone);
    }
}
