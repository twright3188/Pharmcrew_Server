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
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.PtaxConverter;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PtaxDao;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaFileVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaListResp;
import kr.ant.kpa.pharmcrew.service.PtaxService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;

@Service("ptaxService")
public class PtaxServiceImpl implements PtaxService {

	@Autowired
	private PtaxDao ptaxDao;
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PtaxConverter converter;

	@Autowired
	private Storage storage;
	@Autowired
	private PcUtils pcUtils;
	
	@Transactional("transactionManager")

	/**
	*	1. 1:1 문의 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public PtaxQnaListResp getQnaList(Integer page, Integer count, Long sessionId ) {
		PtaxQnaListResp resp =  new PtaxQnaListResp();
		Map<String, Object> param = new HashMap<String, Object>();
		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);
		// 멤버의 정보
		MapUtils.put(param, "member_id", sessionId);
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.question_dt desc");
		
		List<PtaxQnaVo> qnaVos = ptaxDao.listPtaxQna(param);
		for (PtaxQnaVo qnaVo : qnaVos) {
			resp.addQnas(converter.convertPtaxQna(qnaVo));
		}
		
		return resp;
	}

	/**
	*	2. 1:1 문의 상세
	* @param		qnaId - 질문 아이디
	 * @throws NotFoundInfoException 
	**/
	public PtaxQnaDetailResp getQnaDetal(Long qnaId, Long sessionId ) 
			throws NotFoundInfoException {
		PtaxQnaDetailResp resp =  new PtaxQnaDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "qna_id", qnaId);
		PtaxQnaVo qnaVo = ptaxDao.selectPtaxQna(param);
		if (qnaVo == null) {
			// 에러
			throw new NotFoundInfoException();
		}
		
		resp.setDetail(converter.convertPtaxQnaDetail(qnaVo));
		
		return resp;
	}

	/**
	*	3. 1:1 문의 등록
	* @param		title - 문의 타이틀
	* @param		body - 문의 내용
	* @param		file1 - 첨부파일 1
	* @param		file2 - 첨부파일 2
	* @param		file3 - 첨부파일 3
	 * @throws NotFoundInfoException 
	 * @throws FailSaveFileException 
	**/
	public PcResp postUpdateQna(String title, String body, MultipartFile file1, MultipartFile file2, MultipartFile file3, Long sessionId ) 
			throws NotFoundInfoException, FailSaveFileException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		if (StringUtils.isEmpty(title.trim()) || StringUtils.isEmpty(body.trim())) {
			// 에러
			throw new NotFoundInfoException();
		}
		
		PtaxQnaVo qnaVo = new PtaxQnaVo();
		qnaVo.setMember_id(sessionId);
		qnaVo.setTitle(title);
		qnaVo.setBody(body);
		
		// 약국 이름 업데이트
		MapUtils.put(param, "member_id", sessionId);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo != null && !StringUtils.isEmpty(memberVo.getPharm_name())) {
			qnaVo.setPharm_name(memberVo.getPharm_name());			
		}
		
		ptaxDao.insertPtaxQna(qnaVo);
		
		if (file1 != null && file1.getSize() != 0) {
			File attach = pcUtils.save(file1);
			long fileId = storage.save(attach, file1.getOriginalFilename(), file1.getSize());
			PtaxQnaFileVo fileVo = new PtaxQnaFileVo();
			fileVo.setQna_id(qnaVo.getQna_id());
			fileVo.setType_div("Q");
			fileVo.setFile_id(fileId);
			ptaxDao.insertPtaxQnaFile(fileVo);
		}
		
		if (file2 != null && file2.getSize() != 0) {
			File attach = pcUtils.save(file2);
			long fileId = storage.save(attach, file2.getOriginalFilename(), file2.getSize());
			PtaxQnaFileVo fileVo = new PtaxQnaFileVo();
			fileVo.setQna_id(qnaVo.getQna_id());
			fileVo.setType_div("Q");
			fileVo.setFile_id(fileId);
			ptaxDao.insertPtaxQnaFile(fileVo);
		}
		
		if (file3 != null && file3.getSize() != 0) {
			File attach = pcUtils.save(file3);
			long fileId = storage.save(attach, file3.getOriginalFilename(), file3.getSize());
			PtaxQnaFileVo fileVo = new PtaxQnaFileVo();
			fileVo.setQna_id(qnaVo.getQna_id());
			fileVo.setType_div("Q");
			fileVo.setFile_id(fileId);
			ptaxDao.insertPtaxQnaFile(fileVo);
		}
		
		return resp;
	}

	/**
	*	4. 공지 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public PtaxNewsListResp getNewsList(Integer page, Integer count, Long sessionId ) {
		PtaxNewsListResp resp =  new PtaxNewsListResp();
		Map<String, Object> param = new HashMap<String, Object>();
		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);
		// 멤버의 정보
		MapUtils.put(param, "member_id", sessionId);
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");

		List<PtaxNoticeVo> noticeVos = ptaxDao.listPtaxNotice(param);
		for (PtaxNoticeVo noticeVo : noticeVos) {
			resp.addNews(converter.convertPtaxNews(noticeVo));
		}
		
		return resp;
	}

	/**
	*	5. 공지 상세
	* @param		id - 팜텍스 공지 아이디
	 * @throws NotFoundInfoException 
	**/
	public PtaxNewsDetailResp getNewsDetail(Long id, Long sessionId ) 
			throws NotFoundInfoException {
		PtaxNewsDetailResp resp =  new PtaxNewsDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "notice_id", id);
		PtaxNoticeVo noticeVo = ptaxDao.selectPtaxNotice(param);
		if (noticeVo == null) {
			// 에러
			throw new NotFoundInfoException();
		}
		
		resp.setDetail(converter.convertPtaxNewsDetail(noticeVo));
		
		return resp;
	}

	/**
	 * 5-1 Web 공 상세 Body 요청 
	 * @param newsId
	 * @return
	 * @throws NotFoundNoticeException
	 */
	public String getGetNewsDetailBody(Long newsId )
			throws NotFoundInfoException {
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "notice_id", newsId);
		PtaxNoticeVo noticeVo = ptaxDao.selectPtaxNotice(param);
		if (noticeVo == null) {
			throw new NotFoundInfoException();
		}
		
		// 상세 조회 카운트 업데이트 
		noticeVo.setView_cnt(noticeVo.getView_cnt()+1);
		ptaxDao.updatePtaxNotice(noticeVo);
		
		return noticeVo.getBody();
	}
}
