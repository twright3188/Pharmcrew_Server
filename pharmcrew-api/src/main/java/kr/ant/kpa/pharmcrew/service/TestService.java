package kr.ant.kpa.pharmcrew.service;

import org.springframework.web.multipart.MultipartFile;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.test.FileUploadResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;

public interface TestService {

	/**
	*	1. 파일 등
	* @param		image	이미지 파일
	**/
	FileUploadResp postUploadFile(MultipartFile image )
			throws FailSaveFileException ;

	/**
	*	100. 사용자 푸시 전송
	* @param		os	요청 OS - A:android, I:iOS - 없으면 전체
	* @param		title	전송할 메시지 타이틀
	* @param		body	전송할 메시 내용
	* @param		imageUrl	푸시에 보여질 이미지 링크
	* @param		imageFile	푸시에 보여질 이미지 파일
	* @param		category	AD: 광고, NT: 알림
	* @param		moveType	WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId	이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl	이동할 URL
	**/
	PcResp postSendPushMember(String os, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl, Long sessionId )
			throws FailSaveFileException ;

	/**
	*	101. 토큰으로 푸시 전송
	* @param		token	푸시 수신할 토큰
	* @param		os	요청 OS - A:android, I:iOS
	* @param		title	전송할 메시지 타이틀
	* @param		body	전송할 메시 내용
	* @param		imageUrl	푸시에 보여질 이미지 링크
	* @param		imageFile	푸시에 보여질 이미지 파일
	* @param		category	AD: 광고, NT: 알림
	* @param		moveType	WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId	이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl	이동할 URL
	**/
	PcResp postSendPushToken(String token, String os, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl, Long sessionId )
			throws FailSaveFileException ;

	/**
	*	103. 면허번호로 전송
	* @param		licenseNo	전송할 멤버 라이선스 번호
	* @param		title	전송할 메시지 타이틀
	* @param		body	전송할 메시 내용
	* @param		imageUrl	푸시에 보여질 이미지 링크
	* @param		imageFile	푸시에 보여질 이미지 파일
	* @param		category	AD: 광고, NT: 알림
	* @param		moveType	WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId	이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl	이동할 URL
	**/
	PcResp postSendPushLicense(String licenseNo, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl )
			throws FailSaveFileException ;

}

