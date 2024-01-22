package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.root.BannersResp;
import kr.ant.kpa.pharmcrew.resp.root.MainInfoResp;
import kr.ant.kpa.pharmcrew.resp.root.NoticesResp;
import kr.ant.kpa.pharmcrew.resp.root.PartnersResp;
import kr.ant.kpa.pharmcrew.resp.root.PopupsResp;
import kr.ant.kpa.pharmcrew.resp.root.UnreadResp;
import kr.ant.kpa.pharmcrew.resp.root.VersionResp;

public interface RootService {

	/**
	*	1.버전 체크
	* @param		os	요청 OS - A:android, I:iOS
	* @param		version	현재 단말의 버전 정보 (1.1.1- 형식)
	**/
	VersionResp getGetVersion(String os, String version );

	/**
	*	2.푸시 토큰 등록
	* @param		os	요청 OS - A:android, I:iOS
	* @param		pushToken	푸시 토큰
	**/
	PcResp postTokenUpdate(String os, String pushToken, Long sessionId );

	/**
	*	3.메인 정보 요청
	**/
	MainInfoResp getGetMain(Long sessionId );

	/**
	*	4.메인 팝업 정보 요청
	**/
	PopupsResp getGetPopups(Long sessionId );

	/**
	*	5.메인 배너 정보 요청
	**/
	BannersResp getGetBanners(Long sessionId );

	/**
	*	6.메인 파트너 서비스 요청
	**/
	PartnersResp getGetPartners(Long sessionId );

	/**
	*	7.메인 한줄 공지 요청
	**/
	NoticesResp getGetNotices(Long sessionId );

	/**
	*	8.읽지않은 알림숫자 요청
	**/
	UnreadResp getGetUnReads(Long sessionId );

}
