package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.AdminDao;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;

@Repository
public class AdminDaoImpl extends SqlSessionDaoSupport implements AdminDao {

	private static final String ADMIN = "kr.ant.kpa.pharmcrew.db.admin.admin-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	관리자
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertAdmin(AdminVo vo) { 
		getSqlSession().insert(ADMIN + "insert", vo);
	}

	@Override
	public List<AdminVo> listAdmin(Map<String, Object> param) { 
		return getSqlSession().selectList(ADMIN + "select", param);
	}

	@Override
	public int countAdmin(Map<String, Object> param) { 
		return getSqlSession().selectOne(ADMIN + "count", param);
	}

	@Override
	public AdminVo selectAdmin(Map<String, Object> param) { 
		return getSqlSession().selectOne(ADMIN + "select", param);
	}

	@Override
	public void updateAdmin(AdminVo vo) { 
		getSqlSession().update(ADMIN + "update", vo);
	}

	@Override
	public void deleteAdmin(AdminVo vo) { 
		getSqlSession().delete(ADMIN + "delete", vo);
	}


}

