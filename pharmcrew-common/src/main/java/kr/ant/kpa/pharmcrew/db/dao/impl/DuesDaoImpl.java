package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.DuesDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;

import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberPartnerVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.BankInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesItemVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableValueVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberAuthVo;

@Repository
public class DuesDaoImpl extends SqlSessionDaoSupport implements DuesDao {

	private static final String DUESTABLE = "kr.ant.kpa.pharmcrew.db.dues.duestable-";
	private static final String DUESITEM = "kr.ant.kpa.pharmcrew.db.dues.duesitem-";
	private static final String BANKINFO = "kr.ant.kpa.pharmcrew.db.dues.bi-";

	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	회비표
	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<DuesTableVo> listDuesTable(Map<String, Object> param) { 
		return getSqlSession().selectList(DUESTABLE + "select", param);
	}

	@Override
	public DuesTableVo selectDuesTable(Map<String, Object> param) { 
		return getSqlSession().selectOne(DUESTABLE + "select", param);
	}

	@Override
	public void updateDuesTable(DuesTableVo vo) { 
		getSqlSession().update(DUESTABLE + "update", vo);
	}

	@Override
	public void insertDuesTable(DuesTableVo vo) { 
//		getSqlSession().update(DUESTABLE + "insert", vo);
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	dues_item
	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<DuesItemVo> listDuesItem(Map<String, Object> param) { 
		return getSqlSession().selectList(DUESITEM + "select", param);
	}

	@Override
	public DuesItemVo selectDuesItem(Map<String, Object> param) { 
		return getSqlSession().selectOne(DUESITEM + "select", param);
	}

	@Override
	public void updateDuesItem(DuesItemVo vo) { 
		getSqlSession().update(DUESITEM + "update", vo);
	}
	
	@Override
	public void insertDuesItem(DuesItemVo vo) { 
//		getSqlSession().update(DUESITEM + "update", vo);
	}
	
	@Override
	public List<DuesTableValueVo> listDuesTableByCode(Map<String, Object> param) { 
		return getSqlSession().selectList(DUESTABLE + "value", param);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	bank_information
	////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<BankInformationVo> listBankInformationTable(Map<String, Object> param) { 
		return getSqlSession().selectList(BANKINFO + "select", param);
	}

	@Override
	public BankInformationVo selectBankInformationTable(Map<String, Object> param) { 
		return getSqlSession().selectOne(BANKINFO + "select", param);
	}

}

