package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountStateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SessionService {
    LoginResp login(String loginId, String password, HttpServletRequest request) throws InvalidAccountException, InvalidAccountStateException;
    PcResp logout(HttpSession session);
}
