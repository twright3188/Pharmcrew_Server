package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaFileVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeFileVo;

public interface PtaxDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	팜텍스 1:1 문의
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPtaxQna(PtaxQnaVo vo); 
	List<PtaxQnaVo> listPtaxQna(Map<String, Object> param); 
	int countPtaxQna(Map<String, Object> param); 
	PtaxQnaVo selectPtaxQna(Map<String, Object> param); 
	void updatePtaxQna(PtaxQnaVo vo); 
	void deletePtaxQna(PtaxQnaVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	팜텍스 1:1 문의 답변 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPtaxQnaFile(PtaxQnaFileVo vo); 
	List<PtaxQnaFileVo> listPtaxQnaFile(Map<String, Object> param); 
	int countPtaxQnaFile(Map<String, Object> param); 
	PtaxQnaFileVo selectPtaxQnaFile(Map<String, Object> param); 
	void updatePtaxQnaFile(PtaxQnaFileVo vo); 
	void deletePtaxQnaFile(PtaxQnaFileVo vo);

	void insertPTaxQnaFileList(Map<String, Object> param);
	void deletePtaxQnaFile(Map<String, Object> param);


	////////////////////////////////////////////////////////////////////////////////////////
	//	팜택스 공지
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPtaxNotice(PtaxNoticeVo vo); 
	List<PtaxNoticeVo> listPtaxNotice(Map<String, Object> param); 
	int countPtaxNotice(Map<String, Object> param); 
	PtaxNoticeVo selectPtaxNotice(Map<String, Object> param); 
	void updatePtaxNotice(PtaxNoticeVo vo); 
	void deletePtaxNotice(PtaxNoticeVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	팜택스 공지 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPtaxNoticeFile(PtaxNoticeFileVo vo); 
	List<PtaxNoticeFileVo> listPtaxNoticeFile(Map<String, Object> param); 
	int countPtaxNoticeFile(Map<String, Object> param); 
	PtaxNoticeFileVo selectPtaxNoticeFile(Map<String, Object> param); 
	void updatePtaxNoticeFile(PtaxNoticeFileVo vo); 
	void deletePtaxNoticeFile(PtaxNoticeFileVo vo);

	void insertPtaxNoticeFileList(Map<String, Object> param);
	void deletePtaxNoticeFile(Map<String, Object> param);


}

