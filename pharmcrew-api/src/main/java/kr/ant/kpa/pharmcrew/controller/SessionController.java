package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.service.SessionService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserLoginFailException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.UserNewLoginFailException;

@Controller("1.Session")
@RestController
public class SessionController {
	

	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	private SessionService sessionService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.로그인
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/sessions", method=RequestMethod.POST, name="1.로그인")
	@Session(required=false)
	@Description("Page=null , 로그인을 수행하여 세션을 획득한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody LoginResp postSession(
			@RequestParam(value="licenseNo", required=true) @Description("면허번호") String licenseNo,
			@RequestParam(value="password", required=false) @Description("비밀번호- 회원인증 시 회신되는 정보") String password,
			 HttpServletRequest request) 
					 throws UserLoginFailException {

		HttpSession session = request.getSession(true);
		LoginResp resp = sessionService.postSession(licenseNo, password, session);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.로그아웃
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/session", method=RequestMethod.DELETE, name="2.로그아웃")
	@Session(required=false)
	@Description("Page=null , 로그아웃을 수행하여 세션을 만료시킨다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PcResp deleteSession(HttpSession session ) {

		session.invalidate();
		return new PcResp();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.새로운 로그인
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/sessions-new", method=RequestMethod.POST, name="1.로그인")
	@Session(required=false)
	@Description("Page=null , 로그인을 수행하여 세션을 획득한다.")
	@Histories({
			@History(date="2021-01-23", description="자동 구성")
	})
	public @ResponseBody LoginResp postSessionNew(
			@RequestParam(value="licenseNo", required=true) @Description("면허번호") String licenseNo,
			@RequestParam(value="birth", required=true) @Description("생년월일") String birth,
			@RequestParam(value="name", required=true) @Description("멤버네임") String name,
			 HttpServletRequest request) 
					 throws UserNewLoginFailException {

		HttpSession session = request.getSession(true);
		LoginResp resp = sessionService.postSessionNew(licenseNo, birth, name, session);

		return resp;
	}


//    @RequestMapping(value = "/sessions", method = RequestMethod.POST, name="01. 로그인")
//    @Description("로그인")
//	@Histories({
//			@History(date="2020-05-15", description="자동 구성"),
//	})
//    public @ResponseBody PostSessionResp postSession(
//            @RequestParam(value="licenseNo", required=true)  @Description("면허번호") String licenseNo,
//            @RequestParam(value="password", required=true)  @Description("비밀번호") String password,
//            HttpServletRequest request
//    ) {
//        HttpSession session = request.getSession(true);
//        session.setAttribute("memberId", 1L);
//        return new PostSessionResp();
//    }
//
//    @RequestMapping(value = "/session", method = RequestMethod.DELETE, name="02. 로그아웃")
//    @Description("로그아웃")
//  	@Histories({
//  			@History(date="2020-05-15", description="자동 구성"),
//  	})
//    public @ResponseBody PcResp deleteSession(
//            HttpSession session
//    ) {
//        session.invalidate();
//        return new PcResp();
//    }
}
