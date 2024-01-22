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
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import com.bumdori.spring.annotation.validation.regex.HandphoneValidation;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberInfoResp;
import kr.ant.kpa.pharmcrew.resp.member.NotiResp;
import kr.ant.kpa.pharmcrew.resp.member.PassPhoneResp;
import kr.ant.kpa.pharmcrew.resp.member.ProfileResp;
import kr.ant.kpa.pharmcrew.resp.member.SettingResp;
import kr.ant.kpa.pharmcrew.service.MemberService;
import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyRegMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundAuthCodeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberNotiException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundPartnersException;

@Controller("3.Member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.사용자 인증 및 인증코드 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/auth", method=RequestMethod.POST, name="01.사용자 인증 및 인증코드 요청")
	@Session(required=false)
	@Description("Page=null , 사용자 정보 입력 후 인증을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PcResp postAuthMember(@RequestParam(value="licenseNo", required=true) @Description("면허번호") String licenseNo,
			@RequestParam(value="birthDay", required=true) @LengthValidation(min=6,max=8) @Description("생년월일") String birthDay,
			@RequestParam(value="name", required=true) @Description("이름") String name,
			@RequestParam(value="phone", required=true) @HandphoneValidation @Description("휴대전화번호") String phone )
					throws NotFoundMemberInfoException, AlreadyRegMemberException, SmsException {

		PcResp resp = memberService.postAuthMember(licenseNo, birthDay, name, phone);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.문자 인증하기
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/auth/phone", method=RequestMethod.POST, name="02.문자 인증하기")
	@Session(required=false)
	@Description("Page=null , 인증 문자에 대해 인증을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")			
	})
	public @ResponseBody PcResp postAuthPhone(@RequestParam(value="authCode", required=true) @LengthValidation(min=4,max=4)  @Description("인증번호") String authCode ) 
			throws NotFoundAuthCodeException {

		PcResp resp = memberService.postAuthPhone(authCode);

		return resp;
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 회원정보 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/info/update", method=RequestMethod.POST, name="03. 회원정보 등록")
	@Session(required=false)
	@Description("Page=null , 회원 정보 등록")
	@Histories({
			@History(date="2020-06-08", description="자동 구성")
	})
	public @ResponseBody PcResp postUpdateInfo(@RequestParam(value="licenseNo", required=true) @Description("면허번호") String licenseNo,
			@RequestParam(value="authCode", required=true) @Description("인증번호") String authCode,
			@RequestParam(value="agreeThird", required=true) @EnumValidation({"Y", "N"}) @Description("개인정보 제 3자 제공동의") String agreeThird,
			@RequestParam(value="expireDate", required=true) @EnumValidation({"Y", "N"}) @Description("개인정보 유효기간제도 설정") String expireDate,
			@RequestParam(value="agreeSms", required=true) @EnumValidation({"Y", "N"}) @Description("sms 마케팅 수신 동의") String agreeSms,
			@RequestParam(value="agreePush", required=true) @EnumValidation({"Y", "N"}) @Description("푸시 마케팅 수신 동의") String agreePush,
			@RequestParam(value="password", required=true) @Description("패스워드") String password ) 
					throws NotFoundMemberInfoException, AlreadyRegMemberException, NotFoundAuthCodeException {

		PcResp resp = memberService.postUpdateInfo(licenseNo, authCode, agreeThird, expireDate, agreeSms, agreePush, password);

		return resp;
	}

	
	////////////////////////////////////////////////////////////////////////////////////////
	//	4.내 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/Info", method=RequestMethod.GET, name="04.내 정보 요청")
	@Session(required=true)
	@Description("Page=null , 내 정보 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="작업중 - 교육")	
	})
	public @ResponseBody MemberInfoResp getGetMemberInfo(HttpSession session ) 
			throws InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		MemberInfoResp resp = memberService.getGetMemberInfo(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5.설정 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/setting", method=RequestMethod.GET, name="05.설정 정보 요청")
	@Session(required=true)
	@Description("Page=50.0 , 설정 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")	
	})
	public @ResponseBody SettingResp getGetSetting(@RequestParam(value="os", required=false) @Description("요청 OS - A:android, I:iOS") String os,
	HttpSession session ) 
			throws InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		SettingResp resp = memberService.getGetSetting(os, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	6.파트너서비스 업데이트
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/partners", method=RequestMethod.POST, name="06.파트너서비스 업데이트")
	@Session(required=true)
	@Description("Page=50.0 , 파트너 서비스를 업데이트 한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")	
	})
	public @ResponseBody PcResp postUpdatePartners(@RequestParam(value="partnerId", required=true) @Description("설정 On/Off 파트너 서비스 목록") Long partnerId,
			@RequestParam(value="useYn", required=true) @EnumValidation({"Y", "N"}) @Description("on : Y, off : N") String useYn,
			HttpSession session ) 
					throws NotFoundPartnersException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = memberService.postUpdatePartners(partnerId, useYn, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	7.푸시 설정 업데이트
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/push", method=RequestMethod.POST, name="07.푸시 설정 업데이트")
	@Session(required=true)
	@Description("Page=null , 푸시 설정을 업데이트 한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")	
	})
	public @ResponseBody PcResp postAcceptPush(@RequestParam(value="category", required=true) @EnumValidation({"PU", "MS", "MP"}) @Description("PU : 푸시, MS : 마케팅 SMS, MP : 마케팅 푸시") String category,
			@RequestParam(value="agree", required=true) @EnumValidation({"Y", "N"}) @Description("설정 정보 - Y:수신, N:미수신") String agree,
			HttpSession session ) 
					throws InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = memberService.postAcceptPush(category, agree, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	8.프로필 이미지 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/uploadProfile", method=RequestMethod.POST, name="08.프로필 이미지 등록")
	@Session(required=true)
	@Description("Page=null , 프로필 이미지를 등록한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody ProfileResp postUploadProfile(@RequestParam(value="image", required=true) @Description("이미지 파일") MultipartFile image,
			HttpSession session ) 
					throws FailSaveFileException, InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		ProfileResp resp = memberService.postUploadProfile(image, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	9. 프로필 이미지 삭제
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/deleteProfile", method=RequestMethod.DELETE, name="09. 프로필 이미지 삭제")
	@Session(required=true)
	@Description("Page=null , 프로필 이미지를 삭제한다")
	@Histories({
			@History(date="2020-05-25", description="자동 구성"),
			@History(date="2020-06-08", description="1차 구성 완료")
	})
	public @ResponseBody PcResp deleteDeleteProfile(HttpSession session ) 
			throws InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = memberService.deleteDeleteProfile(sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	11. 임시비밀번호 발급 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/password/renew", method=RequestMethod.POST, name="11. 임시비밀번호 발급 요청")
	@Session(required=false)
	@Description("Page=null , 임시비밀번호 발급 요청")
	@Histories({
			@History(date="2020-06-08", description="자동 구성")
	})
	public @ResponseBody PassPhoneResp postRenewPassword(@RequestParam(value="name", required=true) @Description("이름") String name,
			@RequestParam(value="licenseNo", required=true) @Description("면허번호") String licenseNo ) 
					throws NotFoundMemberInfoException, SmsException {

		PassPhoneResp resp = memberService.postRenewPassword(name, licenseNo);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	12. 비밀번호 변경
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/password", method=RequestMethod.POST, name="12. 비밀번호 변경")
	@Session(required=true)
	@Description("Page=null , 비밀번호 변경 요청")
	@Histories({
			@History(date="2020-06-08", description="자동 구성")
	})
	public @ResponseBody PcResp postChangePassword(@RequestParam(value="password", required=true) @Description("패스워드") String password,
			HttpSession session )
					throws InvalidMemberException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = memberService.postChangePassword(password, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	13. 푸시 수신 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/push/receive", method=RequestMethod.POST, name="13. 푸시 수신 등록")
	@Session(required=false)
	@Description("Page=null , 푸시 수신 시 서버로 수신 여부를 등록한다.")
	@Histories({
			@History(date="2020-06-08", description="자동 구성")
	})
	public @ResponseBody PcResp postReceivePush(@RequestParam(value="licenseNo", required=true) @Description("사용자의 면허번호 - 로그인 하지 않을 수 있어서") String licenseNo,
			@RequestParam(value="pushId", required=true) @Description("수신한 푸시 아이디") Long pushId ) 
					throws NotFoundMemberNotiException {

		PcResp resp = memberService.postReceivePush(licenseNo, pushId);

		return resp;
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	14.알림 목록 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/member/notifys", method=RequestMethod.GET, name="14.알림 목록 요청")
	@Session(required=true)
	@Description("Page=null , 광고알림,약사회알림 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성")
	})
	public @ResponseBody NotiResp getGetNotiList(@RequestParam(value="type", required=true) @EnumValidation({"AD", "NT", "PT"}) @Description("요청 타입-AD: 광고, NT: 약사회 알림, PT:파트너서비스 알림") String type,
			@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		NotiResp resp = memberService.getGetNotiList(type, page, count, sessionId);

		return resp;
	}

}
