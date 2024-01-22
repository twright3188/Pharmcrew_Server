package kr.ant.kpa.pharmcrew.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.NewsConverter;
import kr.ant.kpa.pharmcrew.db.dao.EducationDao;
import kr.ant.kpa.pharmcrew.db.dao.NewsDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsDetail;
import kr.ant.kpa.pharmcrew.resp.news.NewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsListResp;
import kr.ant.kpa.pharmcrew.service.NewsService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundQnaException;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private EducationDao eduDao;
	
	@Autowired
	private NewsConverter converter;

	@Autowired
	private Storage storage;
	@Autowired
	private PcUtils pcUtils;
	
	
	@Transactional("transactionManager")

	/**
	*	1.소식방 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public NewsListResp getGetNewsList(Integer page, Integer count, Long sessionId ) {
		NewsListResp resp =  new NewsListResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);
		
		// 사용자 조직에 따른 교육 확인 용
		MapUtils.put(param, "organize_member_id", sessionId);	
		
		// open 인 것 
		MapUtils.put(param, "is_open", "Y");
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");
		
		List<NoticeVo> noticeVos = newsDao.listNotice(param);
		
		for (NoticeVo noticeVo : noticeVos) {
			resp.addNews(converter.convertNews(noticeVo));
		}
		return resp;
	}

	/**
	*	2.소식방 상세
	* @param		newsId - 소식 아이디
	 * @throws NotFoundNoticeException 
	**/
	public NewsDetailResp getGetNewsDetail(Long newsId, Long sessionId )
			throws NotFoundNoticeException {
		NewsDetailResp resp =  new NewsDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "notice_id", newsId);
		NoticeVo noticeVo = newsDao.selectNotice(param);
		if (noticeVo == null) {
			throw new NotFoundNoticeException();
		}
		// 상세 정보 구성
		NewsDetail detail = converter.convertNewsDetail(noticeVo);
		
		// 상세 조회 카운트 업데이트 
		noticeVo.setView_cnt(noticeVo.getView_cnt()+1);
		newsDao.updateNotice(noticeVo);

		// 정보 회신 
		resp.setDetail(detail);
		
		return resp;
	}

	
	/**
	 * 2-1 Web 소식방 상세 Body 요청 
	 * @param newsId
	 * @return
	 * @throws NotFoundNoticeException
	 */
	public String getGetNewsDetailBody(Long newsId )
			throws NotFoundNoticeException {
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "notice_id", newsId);
		NoticeVo noticeVo = newsDao.selectNotice(param);
		if (noticeVo == null) {
			throw new NotFoundNoticeException();
		}
				
		return noticeVo.getBody();
	}
	
	/**
	*	3.문의하기 등록
	* @param		id - 소식 or 교육 아이디
	* @param		category - E: 교육, N : 소식
	* @param		title - 문의 타이틀
	* @param		body - 문의 내용
	* @param		file - 첨부파일
	 * @throws FailSaveFileException 
	 * @throws NotFoundNoticeException 
	 * @throws NotFoundEducationException 
	**/
	public PcResp postUpdateNewsQna(Long id, String category, String title, String body, MultipartFile file, Long sessionId ) 
			throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();
	
		// 정보 등록 
		QnaVo qnaVo = new QnaVo();
		
		if ("E".equals(category.trim())) {
			MapUtils.put(param, "education_id", id);
			EducationVo educationVo = eduDao.selectEducation(param);
			if (educationVo == null) {
				throw new NotFoundEducationException();
			}
			qnaVo.setTarget_div("E");
			qnaVo.setTarget_id(educationVo.getEducation_id());
			qnaVo.setOrganize_id(educationVo.getOrganize_id());
			
		} else {
			// 공지 아이디 확인 
			MapUtils.put(param, "notice_id", id);
			NoticeVo noticeVo = newsDao.selectNotice(param);
			if (noticeVo == null) {
				throw new NotFoundNoticeException();
			}
			qnaVo.setTarget_div("N");
			qnaVo.setTarget_id(noticeVo.getNotice_id());
			qnaVo.setOrganize_id(noticeVo.getOrganize_id());
		}
	
		qnaVo.setMember_id(sessionId);
		qnaVo.setTitle(title);
		qnaVo.setBody(body);
		if (file != null && file.getSize() != 0) {
			File attach = pcUtils.save(file);
			long fileId = storage.save(attach, file.getOriginalFilename(), file.getSize()/*, FILE.P*/);
			qnaVo.setFile_id(fileId);				
		}
		
		newsDao.insertQna(qnaVo);
		
		return resp;
	}

	/**
	*	4.나의 문의 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public MyQnaResp getGetMyQna(Integer page, Integer count, Long sessionId ) {
		MyQnaResp resp =  new MyQnaResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);
		// 사용자 아이디
		MapUtils.put(param, "member_id", sessionId);
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.question_dt desc");
		List<QnaVo> qnaVos = newsDao.listQna(param);
		
		for (QnaVo qnaVo : qnaVos) {
			resp.addQnas(converter.convertQna(qnaVo));
		}
		
		return resp;
	}

	/**
	*	5.나의 문의 상세
	* @param		qnaId - 질문 아이디
	 * @throws NotFoundQnaException 
	**/
	public MyQnaDetailResp getGetMyQnaDetail(Long qnaId, Long sessionId ) 
			throws NotFoundQnaException {
		MyQnaDetailResp resp =  new MyQnaDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "qna_id", qnaId);
		QnaVo qnaVo = newsDao.selectQna(param);
		if (qnaVo == null) {
			throw new NotFoundQnaException();
		}
		
		resp.setDetail(converter.convertQnaDetail(qnaVo));

		return resp;
	}

}
