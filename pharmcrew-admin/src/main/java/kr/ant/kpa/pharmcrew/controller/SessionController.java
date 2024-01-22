package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.service.SessionService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("세션")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/sessions", method = RequestMethod.POST, name = "1. 로그인")
    @ResponseBody
    @Session(required = false)
    @Description("로그인을 수행하여 세션을 획득한다." +
            "(v0.2_2020.05.11 - 5p)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-15", description = "완료"),
    })
    public LoginResp login(
            @RequestParam(value = "loginId") @Description("로그인 아이디") String loginId,
            @RequestParam(value = "password") @Description("비밀번호") String password,
            HttpServletRequest request
    ) throws InvalidAccountException, InvalidAccountStateException {
        LoginResp resp = sessionService.login(loginId, password, request);
        return resp;
    }

    @RequestMapping(value = "/session", method = RequestMethod.DELETE, name = "2. 로그아웃")
    @ResponseBody
    @Session(required = false)
    @Description("로그아웃을 수행하여 세션을 만료시킨다." +
            "(v0.2_2020.05.11 - 5p)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-15", description = "완료"),
    })
    public PcResp logout(
            HttpSession session
    ) {
        PcResp resp = sessionService.logout(session);
        return resp;
    }
}
