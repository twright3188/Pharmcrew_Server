package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.PersonalDao;
import kr.ant.kpa.pharmcrew.db.vo.personal.AdditionalInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.EmploymentCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.EmploymentInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.PersonalInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.ReportYearVo;

@Repository
public class PersonalDaoImpl extends SqlSessionDaoSupport implements PersonalDao {

	private static final String PERSONAL = "kr.ant.kpa.pharmcrew.db.personal.personal-";
	private static final String ADDITIONAL = "kr.ant.kpa.pharmcrew.db.personal.additional-info-";
	private static final String EMPLOYMENT_INFO = "kr.ant.kpa.pharmcrew.db.personal.employment-info-";
	private static final String EMPLOYMENT_CODE = "kr.ant.kpa.pharmcrew.db.personal.employment-code-";
	private static final String REPORT = "kr.ant.kpa.pharmcrew.db.personal.report-";

	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	약사회 회원정보
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<PersonalInformationVo> listPersonalInformation(Map<String, Object> param) { 
		return getSqlSession().selectList(PERSONAL + "select", param);
	}

	@Override
	public PersonalInformationVo selectPersonalInformation(Map<String, Object> param) { 
		return getSqlSession().selectOne(PERSONAL + "select", param);
	}

	@Override
	public void updatePersonalInformation(PersonalInformationVo vo) { 
		getSqlSession().update(PERSONAL + "update", vo);
	}

	@Override
	public void deleteMember(String pi_license) { 
//		getSqlSession().delete(PERSONAL + "delete", pi_license);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	추가정보
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<AdditionalInformationVo> listAdditionalInformation(Map<String, Object> param) { 
		return getSqlSession().selectList(ADDITIONAL + "select", param);
	}

	@Override
	public AdditionalInformationVo selectAdditionalInformation(Map<String, Object> param) { 
		return getSqlSession().selectOne(ADDITIONAL + "select", param);
	}

	@Override
	public void updateAdditionalInformation(AdditionalInformationVo vo) { 
		getSqlSession().update(ADDITIONAL + "update", vo);
	}

	@Override
	public void insertAdditionalInformation(AdditionalInformationVo vo) { 
		getSqlSession().insert(ADDITIONAL + "insert", vo);
	}
	
	@Override
	public void deleteAdditional(String pi_license) { 
//		getSqlSession().delete(PERSONAL + "delete", pi_license);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	직업정보
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<EmploymentInformationVo> listEmploymentlInformation(Map<String, Object> param) { 
		return getSqlSession().selectList(EMPLOYMENT_INFO + "select", param);
	}

	@Override
	public EmploymentInformationVo selectEmploymentInformation(Map<String, Object> param) { 
		return getSqlSession().selectOne(EMPLOYMENT_INFO + "select", param);
	}

	@Override
	public void updateEmploymentInformation(EmploymentInformationVo vo) { 
		getSqlSession().update(EMPLOYMENT_INFO + "update", vo);
	}

	@Override
	public void insertEmploymentInformation(EmploymentInformationVo vo) { 
		getSqlSession().insert(EMPLOYMENT_INFO + "insert", vo);
	}
	
	@Override
	public void deleteEmploymentInformation(String pi_license) { 
//		getSqlSession().delete(PERSONAL + "delete", pi_license);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	직업코드
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<EmploymentCodeVo> listEmploymentlCode(Map<String, Object> param) { 
		return getSqlSession().selectList(EMPLOYMENT_CODE + "select", param);
	}

	@Override
	public EmploymentCodeVo selectEmploymentCode(Map<String, Object> param) { 
		return getSqlSession().selectOne(EMPLOYMENT_CODE + "select", param);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	신고서
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<ReportYearVo> listReportYear(Map<String, Object> param) { 
		return getSqlSession().selectList(REPORT + "select", param);
	}

	@Override
	public ReportYearVo selectReportYear(Map<String, Object> param) { 
		return getSqlSession().selectOne(REPORT + "select", param);
	}

	@Override
	public void updateReportYear(ReportYearVo vo) { 
		getSqlSession().update(REPORT + "update", vo);
	}

	@Override
	public void insertReportYear(ReportYearVo vo) { 
		getSqlSession().insert(REPORT + "insert", vo);
	}
	
	@Override
	public void deleteReportYear(String pi_license) { 
//		getSqlSession().delete(PERSONAL + "delete", pi_license);
	}

}

