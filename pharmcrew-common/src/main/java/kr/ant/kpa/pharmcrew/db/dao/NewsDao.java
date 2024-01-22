package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeFileVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;

public interface NewsDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	공지
	////////////////////////////////////////////////////////////////////////////////////////

	void insertNotice(NoticeVo vo); 
	List<NoticeVo> listNotice(Map<String, Object> param); 
	int countNotice(Map<String, Object> param); 
	NoticeVo selectNotice(Map<String, Object> param); 
	void updateNotice(NoticeVo vo); 
	void deleteNotice(NoticeVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	공지 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	void insertNoticeFile(NoticeFileVo vo); 
	List<NoticeFileVo> listNoticeFile(Map<String, Object> param); 
	int countNoticeFile(Map<String, Object> param); 
	NoticeFileVo selectNoticeFile(Map<String, Object> param); 
	void updateNoticeFile(NoticeFileVo vo); 
	void deleteNoticeFile(NoticeFileVo vo); 

	void insertNoticeFileList(Map<String, Object> param);
	void deleteNoticeFile(Map<String, Object> param);


	////////////////////////////////////////////////////////////////////////////////////////
	//	팝업
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPopup(PopupVo vo); 
	List<PopupVo> listPopup(Map<String, Object> param); 
	int countPopup(Map<String, Object> param); 
	PopupVo selectPopup(Map<String, Object> param); 
	void updatePopup(PopupVo vo); 
	void deletePopup(PopupVo vo); 

	/**
	 * 노출 중(is_open이 Y)인 팝업들 중의 open_idx 최대 값
	 * @return
	 */
	int selectMaxOpenIdxFromActivePopup();


	////////////////////////////////////////////////////////////////////////////////////////
	//	배너
	////////////////////////////////////////////////////////////////////////////////////////

	void insertBanner(BannerVo vo); 
	List<BannerVo> listBanner(Map<String, Object> param); 
	int countBanner(Map<String, Object> param); 
	BannerVo selectBanner(Map<String, Object> param); 
	void updateBanner(BannerVo vo); 
	void deleteBanner(BannerVo vo);

	/**
	 * 노출 중(is_open이 Y)인 배너들 중의 open_idx 최대 값
	 * @return
	 */
	int selectMaxOpenIdxFromActiveBanner();


	////////////////////////////////////////////////////////////////////////////////////////
	//	문의
	////////////////////////////////////////////////////////////////////////////////////////

	void insertQna(QnaVo vo); 
	List<QnaVo> listQna(Map<String, Object> param); 
	int countQna(Map<String, Object> param); 
	QnaVo selectQna(Map<String, Object> param); 
	void updateQna(QnaVo vo); 
	void deleteQna(QnaVo vo); 


}

