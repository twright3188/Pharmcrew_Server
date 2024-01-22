package kr.ant.kpa.pharmcrew.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.converter.AcademyConverter;
import kr.ant.kpa.pharmcrew.db.dao.AcademyDao;
import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;
import kr.ant.kpa.pharmcrew.resp.academy.DocDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.service.AcademyService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;

@Service("academyService")
public class AcademyServiceImpl implements AcademyService {

	@Autowired
	private AcademyDao academyDao;
	@Autowired
	private AcademyConverter converter;

	@Transactional("transactionManager")

	/**
	*	1. 문서 목록 조회
	* @param		searchKey - 검색어
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public DocListResp getDocList(String searchKey, Integer page, Integer count, Long sessionId ) {
		DocListResp resp =  new DocListResp();
		Map<String, Object> param = new HashMap<String, Object>();
		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 검색어
		if (!StringUtils.isEmpty(searchKey)) {
			MapUtils.put(param, "keyword", searchKey);
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);

		// 사용자 조직에 따른 교육 확인 용
		MapUtils.put(param, "organize_member_id", sessionId);	

		// open 인 것 
		MapUtils.put(param, "is_open", "Y");
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");
		
		List<DocVo> docVos = academyDao.listDoc(param);
		for (DocVo docVo : docVos) {
			resp.addDocs(converter.convertDoc(docVo));
		}
		
		return resp;
	}

	/**
	*	2. 문서 상세 조회
	* @param		id - 문서 아이디
	 * @throws NotFoundInfoException 
	**/
	public DocDetailResp getDocDetail(Long id ) 
			throws NotFoundInfoException {
		DocDetailResp resp =  new DocDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "doc_id", id);
		DocVo docVo = academyDao.selectDoc(param);
		if (docVo == null) {
			// 에러
			throw new NotFoundInfoException();
		}
		
		docVo.setView_cnt(docVo.getView_cnt()+1);
		academyDao.updateDoc(docVo);
		
		resp.setDoc(converter.convertDoc(docVo));
		
		return resp;
	}

	/**
	*	3. 동영상 목록 조회
	* @param		searchKey - 검색어
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public VideoListResp getVideoList(String searchKey, Integer page, Integer count, Long sessionId ) {
		VideoListResp resp =  new VideoListResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		
		// 검색어
		if (!StringUtils.isEmpty(searchKey)) {
			MapUtils.put(param, "keyword", searchKey);
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);

		// 사용자 조직에 따른 교육 확인 용
		MapUtils.put(param, "organize_member_id", sessionId);	

		// open 인 것 
		MapUtils.put(param, "is_open", "Y");
		// 정렬은 시간 역순
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");

		List<VideoVo> videoVos = academyDao.listVideo(param);
		for (VideoVo videoVo : videoVos) {
			resp.addVideos(converter.convertVideo(videoVo));
		}
		return resp;
	}

	/**
	*	4. 동영상 상세 조회
	* @param		id - 동영상 아이디
	 * @throws NotFoundInfoException 
	**/
	public VideoDetailResp getVideoDetail(Long id ) 
			throws NotFoundInfoException {
		VideoDetailResp resp =  new VideoDetailResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "video_id", id);
		VideoVo videoVo = academyDao.selectVideo(param);
		if (videoVo == null) {
			// 에러
			throw new NotFoundInfoException();
		}
		
		videoVo.setView_cnt(videoVo.getView_cnt()+1);
		academyDao.updateVideo(videoVo);
		
		resp.setVideo(converter.convertVideo(videoVo));
		
		return resp;
	}

}
