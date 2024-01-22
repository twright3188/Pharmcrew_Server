package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.AcademyDao;

import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;

@Repository
public class AcademyDaoImpl extends SqlSessionDaoSupport implements AcademyDao {

	private static final String DOC = "kr.ant.kpa.pharmcrew.db.academy.doc-";
	private static final String VIDEO = "kr.ant.kpa.pharmcrew.db.academy.video-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	문서
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertDoc(DocVo vo) { 
		getSqlSession().insert(DOC + "insert", vo);
	}

	@Override
	public List<DocVo> listDoc(Map<String, Object> param) { 
		return getSqlSession().selectList(DOC + "select", param);
	}

	@Override
	public int countDoc(Map<String, Object> param) { 
		return getSqlSession().selectOne(DOC + "count", param);
	}

	@Override
	public DocVo selectDoc(Map<String, Object> param) { 
		return getSqlSession().selectOne(DOC + "select", param);
	}

	@Override
	public void updateDoc(DocVo vo) { 
		getSqlSession().update(DOC + "update", vo);
	}

	@Override
	public void deleteDoc(DocVo vo) { 
		getSqlSession().delete(DOC + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	동영상
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertVideo(VideoVo vo) { 
		getSqlSession().insert(VIDEO + "insert", vo);
	}

	@Override
	public List<VideoVo> listVideo(Map<String, Object> param) { 
		return getSqlSession().selectList(VIDEO + "select", param);
	}

	@Override
	public int countVideo(Map<String, Object> param) { 
		return getSqlSession().selectOne(VIDEO + "count", param);
	}

	@Override
	public VideoVo selectVideo(Map<String, Object> param) { 
		return getSqlSession().selectOne(VIDEO + "select", param);
	}

	@Override
	public void updateVideo(VideoVo vo) { 
		getSqlSession().update(VIDEO + "update", vo);
	}

	@Override
	public void deleteVideo(VideoVo vo) { 
		getSqlSession().delete(VIDEO + "delete", vo);
	}


}

