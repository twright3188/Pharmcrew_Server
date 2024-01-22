package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.util.FileUtils;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.root.BannersResp;
import kr.ant.kpa.pharmcrew.resp.root.MainInfoResp;
import kr.ant.kpa.pharmcrew.resp.root.NoticesResp;
import kr.ant.kpa.pharmcrew.resp.root.PartnersResp;
import kr.ant.kpa.pharmcrew.resp.root.PopupsResp;
import kr.ant.kpa.pharmcrew.resp.root.UnreadResp;
import kr.ant.kpa.pharmcrew.resp.root.VersionResp;
import kr.ant.kpa.pharmcrew.service.RootService;

@Controller("1.Root")
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);

	@Autowired
	private RootService rootService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.버전 체크
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/versions", method=RequestMethod.GET, name="1.버전 체크")
	@Session(required=false)
	@Description("Page=null , 앱 버전을 체크 한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody VersionResp getGetVersion(@RequestParam(value="os", required=true) @EnumValidation({"A", "I"})  @Description("요청 OS - A:android, I:iOS") String os,
			@RequestParam(value="version", required=true) @Description("현재 단말의 버전 정보 (1.1.1- 형식)") String version ) {

		VersionResp resp = rootService.getGetVersion(os, version);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.푸시 토큰 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/pushtoken", method=RequestMethod.POST, name="2.푸시 토큰 등록")
	@Session(required=true)
	@Description("Page=null , 푸시 토큰을 등록한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PcResp postTokenUpdate(@RequestParam(value="os", required=true) @EnumValidation({"A", "I"})  @Description("요청 OS - A:android, I:iOS") String os,
			@RequestParam(value="pushToken", required=true) @Description("푸시 토큰") String pushToken,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = rootService.postTokenUpdate(os, pushToken, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3.메인 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/maininfo", method=RequestMethod.GET, name="3.메인 정보 요청")
	@Session(required=true)
	@Description("Page=null , 메인 팝업/배너, 한줄 공지, 파트너 서비스 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody MainInfoResp getGetMain(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		MainInfoResp resp = rootService.getGetMain(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.메인 팝업 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/popups", method=RequestMethod.GET, name="4.메인 팝업 정보 요청")
	@Session(required=true)
	@Description("Page=null , 메인 팝업 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PopupsResp getGetPopups(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		PopupsResp resp = rootService.getGetPopups(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5.메인 배너 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/banners", method=RequestMethod.GET, name="5.메인 배너 정보 요청")
	@Session(required=true)
	@Description("Page=null , 메인 배너 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody BannersResp getGetBanners(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		BannersResp resp = rootService.getGetBanners(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	6.메인 파트너 서비스 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/partners", method=RequestMethod.GET, name="6.메인 파트너 서비스 요청")
	@Session(required=true)
	@Description("Page=null , 메인 파트너 서비스 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PartnersResp getGetPartners(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		PartnersResp resp = rootService.getGetPartners(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	7.메인 한줄 공지 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/notices", method=RequestMethod.GET, name="7.메인 한줄 공지 요청")
	@Session(required=true)
	@Description("Page=null , 메인 한줄 공지 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody NoticesResp getGetNotices(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		NoticesResp resp = rootService.getGetNotices(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	8. 모바일 이용 약관 WEB
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/web/eula", method=RequestMethod.GET)
	@Session(required=false)
	public ModelAndView getWebGuideDetail(@RequestParam(value="type", required=true) @EnumValidation({"E", "P", "T"})  @Description("약관 정보 - E(Eula):이용약관, P(Privacy):개인정보취급방침, T(Third):제3자정보제공") String type)  {
		
		String eula = FileUtils.getFileBody("kr/ant/kpa/pharmcrew/eula.html");
		if ("E".equals(type)) {
			eula = FileUtils.getFileBody("kr/ant/kpa/pharmcrew/eula.html");
		} else if ("P".equals(type)) {
			eula = FileUtils.getFileBody("kr/ant/kpa/pharmcrew/privacy.html");
		} else if ("T".equals(type)) {
			eula = FileUtils.getFileBody("kr/ant/kpa/pharmcrew/third.html");
		}
		
		ModelAndView mav = new ModelAndView("/detail/board");
		
		mav.addObject("detail", eula);
		
		return mav;
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	8.읽지않은 알림숫자 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/root/unreads", method=RequestMethod.GET, name="8.읽지않은 알림숫자 요청")
	@Session(required=true)
	@Description("Page=null , 읽지않은 알림 숫자를 요청한다.")
	@Histories({
			@History(date="2020-07-16", description="자동 구성")
	})
	public @ResponseBody UnreadResp getGetUnReads(HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		UnreadResp resp = rootService.getGetUnReads(sessionId);

		return resp;
	}
}
