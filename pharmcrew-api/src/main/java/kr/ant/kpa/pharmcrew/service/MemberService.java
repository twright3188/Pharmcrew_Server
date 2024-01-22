package kr.ant.kpa.pharmcrew.service;

import org.springframework.web.multipart.MultipartFile;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberInfoResp;
import kr.ant.kpa.pharmcrew.resp.member.NotiResp;
import kr.ant.kpa.pharmcrew.resp.member.PassPhoneResp;
import kr.ant.kpa.pharmcrew.resp.member.ProfileResp;
import kr.ant.kpa.pharmcrew.resp.member.SettingResp;
import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyRegMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundAuthCodeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberNotiException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundPartnersException;

public interface MemberService {

	/**
	*	1.사용자 인증 및 인증코드 요청
	* @param		licenseNo	면허번호
	* @param		birthDay	생년월일
	* @param		name	이름
	* @param		phone	휴대전화번호
	**/
	PcResp postAuthMember(String licenseNo, String birthDay, String name, String phone )
			throws NotFoundMemberInfoException, AlreadyRegMemberException, SmsException ;

	/**
	*	2.문자 인증하기
	* @param		authCode	인증번호
	**/
	PcResp postAuthPhone(String authCode )
			throws NotFoundAuthCodeException ;

	/**
	*	3. 회원정보 등록
	* @param		licenseNo	면허번호
	* @param		authCode	인증번호
	* @param		agreeThird	개인정보 제 3자 제공동의
	* @param		expireDate	개인정보 유효기간제도 설정
	* @param		agreeSms	sms 마케팅 수신 동의
	* @param		agreePush	푸시 마케팅 수신 동의
	* @param		password	패스워드
	**/
	PcResp postUpdateInfo(String licenseNo, String authCode, String agreeThird, 
			String expireDate, String agreeSms, String agreePush, String password )
			throws NotFoundMemberInfoException, AlreadyRegMemberException, NotFoundAuthCodeException ;

	/**
	*	4.내 정보 요청
	**/
	MemberInfoResp getGetMemberInfo(Long sessionId )
			throws InvalidMemberException ;

	/**
	*	5.설정 정보 요청
	* @param		os	요청 OS - A:android, I:iOS
	**/
	SettingResp getGetSetting(String os, Long sessionId )
			throws InvalidMemberException ;

	/**
	*	6.파트너서비스 업데이트
	* @param		partnerId	설정 On/Off 파트너 서비스 목록
	* @param		useYn	on : Y, off : N
	**/
	PcResp postUpdatePartners(Long partnerId, String useYn, Long sessionId )
			throws NotFoundPartnersException ;

	/**
	*	7.푸시 설정 업데이트
	* @param		category	PU : 푸시, MS : 마케팅 SMS, MP : 마케팅 푸시
	* @param		agree	설정정보 - Y : 수신, N : 미수신
	**/
	PcResp postAcceptPush(String category, String agree, Long sessionId )
			throws InvalidMemberException;

	/**
	*	8.프로필 이미지 등록
	* @param		image	이미지 파일
	**/
	ProfileResp postUploadProfile(MultipartFile image, Long sessionId )
			throws FailSaveFileException, InvalidMemberException ;

	/**
	*	9. 프로필 이미지 삭제
	**/
	PcResp deleteDeleteProfile(Long sessionId )
			throws InvalidMemberException ;


	/**
	*	11. 임시비밀번호 발급 요청
	* @param		name	이름
	* @param		licenseNo	면허번호
	**/
	PassPhoneResp postRenewPassword(String name, String licenseNo )
			throws NotFoundMemberInfoException, SmsException ;

	/**
	*	12. 비밀번호 변경
	* @param		password	패스워드
	**/
	PcResp postChangePassword(String password, Long sessionId )
			throws InvalidMemberException ;

	/**
	*	13. 푸시 수신 등록
	* @param		licenseNo	사용자의 면허번호 - 로그인 하지 않을 수 있어서
	* @param		pushId	수신한 푸시 아이디
	**/
	PcResp postReceivePush(String licenseNo, Long pushId )
			throws NotFoundMemberNotiException ;

	/**
	*	14.알림 목록 요청
	* @param		type	요청 타입-AD: 광고, NT: 약사회 알림, PT:파트너서비스 알림
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	NotiResp getGetNotiList(String type, Integer page, Integer count, Long sessionId );

}
