package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.academy.DocDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;

public interface AcademyService {

	/**
	*	1. 문서 목록 조회
	* @param		searchKey	검색어
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	DocListResp getDocList(String searchKey, Integer page, Integer count, Long sessionId );

	/**
	*	2. 문서 상세 조회
	* @param		id	문서 아이디
	**/
	DocDetailResp getDocDetail(Long id )
			throws NotFoundInfoException;

	/**
	*	3. 동영상 목록 조회
	* @param		searchKey	검색어
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	VideoListResp getVideoList(String searchKey, Integer page, Integer count, Long sessionId );

	/**
	*	4. 동영상 상세 조회
	* @param		id	동영상 아이디
	**/
	VideoDetailResp getVideoDetail(Long id )
			throws NotFoundInfoException;

}
