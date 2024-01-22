package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.NewsDao;

import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeFileVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;

@Repository
public class NewsDaoImpl extends SqlSessionDaoSupport implements NewsDao {

	private static final String NOTICE = "kr.ant.kpa.pharmcrew.db.news.notice-";
	private static final String NOTICEFILE = "kr.ant.kpa.pharmcrew.db.news.noticefile-";
	private static final String POPUP = "kr.ant.kpa.pharmcrew.db.news.popup-";
	private static final String BANNER = "kr.ant.kpa.pharmcrew.db.news.banner-";
	private static final String QNA = "kr.ant.kpa.pharmcrew.db.news.qna-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	공지
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertNotice(NoticeVo vo) { 
		getSqlSession().insert(NOTICE + "insert", vo);
	}

	@Override
	public List<NoticeVo> listNotice(Map<String, Object> param) { 
		return getSqlSession().selectList(NOTICE + "select", param);
	}

	@Override
	public int countNotice(Map<String, Object> param) { 
		return getSqlSession().selectOne(NOTICE + "count", param);
	}

	@Override
	public NoticeVo selectNotice(Map<String, Object> param) { 
		return getSqlSession().selectOne(NOTICE + "select", param);
	}

	@Override
	public void updateNotice(NoticeVo vo) { 
		getSqlSession().update(NOTICE + "update", vo);
	}

	@Override
	public void deleteNotice(NoticeVo vo) { 
		getSqlSession().delete(NOTICE + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	공지 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertNoticeFile(NoticeFileVo vo) { 
		getSqlSession().insert(NOTICEFILE + "insert", vo);
	}

	@Override
	public List<NoticeFileVo> listNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectList(NOTICEFILE + "select", param);
	}

	@Override
	public int countNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(NOTICEFILE + "count", param);
	}

	@Override
	public NoticeFileVo selectNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(NOTICEFILE + "select", param);
	}

	@Override
	public void updateNoticeFile(NoticeFileVo vo) { 
		getSqlSession().update(NOTICEFILE + "update", vo);
	}

	@Override
	public void deleteNoticeFile(NoticeFileVo vo) { 
		getSqlSession().delete(NOTICEFILE + "delete", vo);
	}

	@Override
	public void insertNoticeFileList(Map<String, Object> param) {
		getSqlSession().insert(NOTICEFILE + "insert-list", param);
	}

	@Override
	public void deleteNoticeFile(Map<String, Object> param) {
		getSqlSession().delete(NOTICEFILE + "delete-map", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	팝업
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPopup(PopupVo vo) { 
		getSqlSession().insert(POPUP + "insert", vo);
	}

	@Override
	public List<PopupVo> listPopup(Map<String, Object> param) { 
		return getSqlSession().selectList(POPUP + "select", param);
	}

	@Override
	public int countPopup(Map<String, Object> param) { 
		return getSqlSession().selectOne(POPUP + "count", param);
	}

	@Override
	public PopupVo selectPopup(Map<String, Object> param) { 
		return getSqlSession().selectOne(POPUP + "select", param);
	}

	@Override
	public void updatePopup(PopupVo vo) { 
		getSqlSession().update(POPUP + "update", vo);
	}

	@Override
	public void deletePopup(PopupVo vo) { 
		getSqlSession().delete(POPUP + "delete", vo);
	}

	@Override
	public int selectMaxOpenIdxFromActivePopup() {
		return getSqlSession().selectOne(POPUP + "select-max-open-idx-from-active");
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	배너
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertBanner(BannerVo vo) { 
		getSqlSession().insert(BANNER + "insert", vo);
	}

	@Override
	public List<BannerVo> listBanner(Map<String, Object> param) { 
		return getSqlSession().selectList(BANNER + "select", param);
	}

	@Override
	public int countBanner(Map<String, Object> param) { 
		return getSqlSession().selectOne(BANNER + "count", param);
	}

	@Override
	public BannerVo selectBanner(Map<String, Object> param) { 
		return getSqlSession().selectOne(BANNER + "select", param);
	}

	@Override
	public void updateBanner(BannerVo vo) { 
		getSqlSession().update(BANNER + "update", vo);
	}

	@Override
	public void deleteBanner(BannerVo vo) { 
		getSqlSession().delete(BANNER + "delete", vo);
	}

	@Override
	public int selectMaxOpenIdxFromActiveBanner() {
		return getSqlSession().selectOne(BANNER + "select-max-open-idx-from-active");
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	문의
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertQna(QnaVo vo) { 
		getSqlSession().insert(QNA + "insert", vo);
	}

	@Override
	public List<QnaVo> listQna(Map<String, Object> param) { 
		return getSqlSession().selectList(QNA + "select", param);
	}

	@Override
	public int countQna(Map<String, Object> param) { 
		return getSqlSession().selectOne(QNA + "count", param);
	}

	@Override
	public QnaVo selectQna(Map<String, Object> param) { 
		return getSqlSession().selectOne(QNA + "select", param);
	}

	@Override
	public void updateQna(QnaVo vo) { 
		getSqlSession().update(QNA + "update", vo);
	}

	@Override
	public void deleteQna(QnaVo vo) { 
		getSqlSession().delete(QNA + "delete", vo);
	}


}

