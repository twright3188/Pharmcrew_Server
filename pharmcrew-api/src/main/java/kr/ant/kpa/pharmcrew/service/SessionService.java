package kr.ant.kpa.pharmcrew.service;

import javax.servlet.http.HttpSession;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserLoginFailException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserNewLoginFailException;

public interface SessionService {

	/**
	*	1.로그인
	* @param		licenseNo	면허번호
	* @param		password	비밀번호- 회원인증 시 회신되는 정보
	**/
	LoginResp postSession(String licenseNo, String password, HttpSession session )
			throws UserLoginFailException ;

	/**
	*	2.로그아웃
	**/
	PcResp deleteSession(Long sessionId );
	
	/**
	*	3.로그인
	* @param		licenseNo	면허번호
	* @param		birth		생년월일
	* @param 		name		이름
	**/
	LoginResp postSessionNew(String licenseNo, String birth, String name, HttpSession session )
			throws UserNewLoginFailException ;

}
