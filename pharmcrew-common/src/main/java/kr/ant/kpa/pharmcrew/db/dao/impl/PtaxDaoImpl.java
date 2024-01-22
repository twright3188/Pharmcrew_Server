package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.PtaxDao;

import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaFileVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeFileVo;

@Repository
public class PtaxDaoImpl extends SqlSessionDaoSupport implements PtaxDao {

	private static final String PTAXQNA = "kr.ant.kpa.pharmcrew.db.ptax.ptaxqna-";
	private static final String PTAXQNAFILE = "kr.ant.kpa.pharmcrew.db.ptax.ptaxqnafile-";
	private static final String PTAXNOTICE = "kr.ant.kpa.pharmcrew.db.ptax.ptaxnotice-";
	private static final String PTAXNOTICEFILE = "kr.ant.kpa.pharmcrew.db.ptax.ptaxnoticefile-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	팜텍스 1:1 문의
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPtaxQna(PtaxQnaVo vo) { 
		getSqlSession().insert(PTAXQNA + "insert", vo);
	}

	@Override
	public List<PtaxQnaVo> listPtaxQna(Map<String, Object> param) { 
		return getSqlSession().selectList(PTAXQNA + "select", param);
	}

	@Override
	public int countPtaxQna(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXQNA + "count", param);
	}

	@Override
	public PtaxQnaVo selectPtaxQna(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXQNA + "select", param);
	}

	@Override
	public void updatePtaxQna(PtaxQnaVo vo) { 
		getSqlSession().update(PTAXQNA + "update", vo);
	}

	@Override
	public void deletePtaxQna(PtaxQnaVo vo) { 
		getSqlSession().delete(PTAXQNA + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	팜텍스 1:1 문의 답변 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPtaxQnaFile(PtaxQnaFileVo vo) { 
		getSqlSession().insert(PTAXQNAFILE + "insert", vo);
	}

	@Override
	public List<PtaxQnaFileVo> listPtaxQnaFile(Map<String, Object> param) { 
		return getSqlSession().selectList(PTAXQNAFILE + "select", param);
	}

	@Override
	public int countPtaxQnaFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXQNAFILE + "count", param);
	}

	@Override
	public PtaxQnaFileVo selectPtaxQnaFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXQNAFILE + "select", param);
	}

	@Override
	public void updatePtaxQnaFile(PtaxQnaFileVo vo) { 
		getSqlSession().update(PTAXQNAFILE + "update", vo);
	}

	@Override
	public void deletePtaxQnaFile(PtaxQnaFileVo vo) { 
		getSqlSession().delete(PTAXQNAFILE + "delete", vo);
	}

	@Override
	public void insertPTaxQnaFileList(Map<String, Object> param) {
		getSqlSession().insert(PTAXQNAFILE + "insert-list", param);
	}

	@Override
	public void deletePtaxQnaFile(Map<String, Object> param) {
		getSqlSession().delete(PTAXQNAFILE + "delete-map", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	팜택스 공지
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPtaxNotice(PtaxNoticeVo vo) { 
		getSqlSession().insert(PTAXNOTICE + "insert", vo);
	}

	@Override
	public List<PtaxNoticeVo> listPtaxNotice(Map<String, Object> param) { 
		return getSqlSession().selectList(PTAXNOTICE + "select", param);
	}

	@Override
	public int countPtaxNotice(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXNOTICE + "count", param);
	}

	@Override
	public PtaxNoticeVo selectPtaxNotice(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXNOTICE + "select", param);
	}

	@Override
	public void updatePtaxNotice(PtaxNoticeVo vo) { 
		getSqlSession().update(PTAXNOTICE + "update", vo);
	}

	@Override
	public void deletePtaxNotice(PtaxNoticeVo vo) { 
		getSqlSession().delete(PTAXNOTICE + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	팜택스 공지 첨부 파일
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPtaxNoticeFile(PtaxNoticeFileVo vo) { 
		getSqlSession().insert(PTAXNOTICEFILE + "insert", vo);
	}

	@Override
	public List<PtaxNoticeFileVo> listPtaxNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectList(PTAXNOTICEFILE + "select", param);
	}

	@Override
	public int countPtaxNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXNOTICEFILE + "count", param);
	}

	@Override
	public PtaxNoticeFileVo selectPtaxNoticeFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(PTAXNOTICEFILE + "select", param);
	}

	@Override
	public void updatePtaxNoticeFile(PtaxNoticeFileVo vo) { 
		getSqlSession().update(PTAXNOTICEFILE + "update", vo);
	}

	@Override
	public void deletePtaxNoticeFile(PtaxNoticeFileVo vo) { 
		getSqlSession().delete(PTAXNOTICEFILE + "delete", vo);
	}

	@Override
	public void insertPtaxNoticeFileList(Map<String, Object> param) {
		getSqlSession().insert(PTAXNOTICEFILE + "insert-list", param);
	}

	@Override
	public void deletePtaxNoticeFile(Map<String, Object> param) {
		getSqlSession().delete(PTAXNOTICEFILE + "delete-map", param);
	}
}

