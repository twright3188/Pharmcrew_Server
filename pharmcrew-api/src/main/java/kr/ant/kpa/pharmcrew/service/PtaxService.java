package kr.ant.kpa.pharmcrew.service;

import org.springframework.web.multipart.MultipartFile;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaListResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;

public interface PtaxService {

	/**
	*	1. 1:1 문의 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	PtaxQnaListResp getQnaList(Integer page, Integer count, Long sessionId );

	/**
	*	2. 1:1 문의 상세
	* @param		qnaId	질문 아이디
	**/
	PtaxQnaDetailResp getQnaDetal(Long qnaId, Long sessionId ) 
			throws NotFoundInfoException ;

	/**
	*	3. 1:1 문의 등록
	* @param		title	문의 타이틀
	* @param		body	문의 내용
	* @param		file1	첨부파일 1
	* @param		file2	첨부파일 2
	* @param		file3	첨부파일 3
	**/
	PcResp postUpdateQna(String title, String body, MultipartFile file1, MultipartFile file2, MultipartFile file3, Long sessionId )
			throws NotFoundInfoException, FailSaveFileException ;

	/**
	*	4. 공지 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	PtaxNewsListResp getNewsList(Integer page, Integer count, Long sessionId );

	/**
	*	5. 공지 상세
	* @param		id	팜텍스 공지 아이디
	**/
	PtaxNewsDetailResp getNewsDetail(Long id, Long sessionId )
			throws NotFoundInfoException ;

	/**
	 * 5-1 Web 소식방 상세 Body 요청 
	 * @param newsId
	 * @return
	 * @throws NotFoundNoticeException
	 */
	String getGetNewsDetailBody(Long newsId )
			throws NotFoundInfoException;
}
