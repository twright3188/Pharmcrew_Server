package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.PartnersDao;

import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;

@Repository
public class PartnersDaoImpl extends SqlSessionDaoSupport implements PartnersDao {

	private static final String PARTNERS = "kr.ant.kpa.pharmcrew.db.partners.partners-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	파트너 서비스
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPartners(PartnersVo vo) { 
		getSqlSession().insert(PARTNERS + "insert", vo);
	}

	@Override
	public List<PartnersVo> listPartners(Map<String, Object> param) { 
		return getSqlSession().selectList(PARTNERS + "select", param);
	}

	@Override
	public int countPartners(Map<String, Object> param) { 
		return getSqlSession().selectOne(PARTNERS + "count", param);
	}

	@Override
	public PartnersVo selectPartners(Map<String, Object> param) { 
		return getSqlSession().selectOne(PARTNERS + "select", param);
	}

	@Override
	public void updatePartners(PartnersVo vo) { 
		getSqlSession().update(PARTNERS + "update", vo);
	}

	@Override
	public void deletePartners(PartnersVo vo) { 
		getSqlSession().delete(PARTNERS + "delete", vo);
	}


}

