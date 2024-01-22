package kr.ant.kpa.pharmcrew.service;

import org.springframework.web.multipart.MultipartFile;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsListResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundQnaException;

public interface NewsService {

	/**
	*	1.소식방 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	NewsListResp getGetNewsList(Integer page, Integer count, Long sessionId );

	/**
	*	2.소식방 상세
	* @param		newsId	소식 아이디
	**/
	NewsDetailResp getGetNewsDetail(Long newsId, Long sessionId )
			throws NotFoundNoticeException;

	/**
	 * 2-1 Web 소식방 상세 Body 요청 
	 * @param newsId
	 * @return
	 * @throws NotFoundNoticeException
	 */
	String getGetNewsDetailBody(Long newsId )
			throws NotFoundNoticeException;
	
	/**
	*	3.문의하기 등록
	* @param		id	소식 or 교육 아이디
	* @param		category	E: 교육, N : 소식
	* @param		title	문의 타이틀
	* @param		body	문의 내용
	* @param		file	첨부파일
	**/
	PcResp postUpdateNewsQna(Long id, String category, String title, String body, MultipartFile file, Long sessionId )
			throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException;

	/**
	*	4.나의 문의 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	MyQnaResp getGetMyQna(Integer page, Integer count, Long sessionId );

	/**
	*	5.나의 문의 상세
	* @param		qnaId	질문 아이디
	**/
	MyQnaDetailResp getGetMyQnaDetail(Long qnaId, Long sessionId )
			throws NotFoundQnaException ;

}
