package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.EducationDao;

import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;

@Repository
public class EducationDaoImpl extends SqlSessionDaoSupport implements EducationDao {

	private static final String EDUCATION = "kr.ant.kpa.pharmcrew.db.education.education-";
	private static final String EDUCATIONCOURSE = "kr.ant.kpa.pharmcrew.db.education.educationcourse-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	교육
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertEducation(EducationVo vo) { 
		getSqlSession().insert(EDUCATION + "insert", vo);
	}

	@Override
	public List<EducationVo> listEducation(Map<String, Object> param) { 
		return getSqlSession().selectList(EDUCATION + "select", param);
	}

	@Override
	public int countEducation(Map<String, Object> param) { 
		return getSqlSession().selectOne(EDUCATION + "count", param);
	}

	@Override
	public EducationVo selectEducation(Map<String, Object> param) { 
		return getSqlSession().selectOne(EDUCATION + "select", param);
	}

	@Override
	public void updateEducation(EducationVo vo) { 
		getSqlSession().update(EDUCATION + "update", vo);
	}

	@Override
	public void deleteEducation(EducationVo vo) { 
		getSqlSession().delete(EDUCATION + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	강의
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertEducationCourse(EducationCourseVo vo) { 
		getSqlSession().insert(EDUCATIONCOURSE + "insert", vo);
	}

	@Override
	public List<EducationCourseVo> listEducationCourse(Map<String, Object> param) { 
		return getSqlSession().selectList(EDUCATIONCOURSE + "select", param);
	}

	@Override
	public int countEducationCourse(Map<String, Object> param) { 
		return getSqlSession().selectOne(EDUCATIONCOURSE + "count", param);
	}

	@Override
	public EducationCourseVo selectEducationCourse(Map<String, Object> param) { 
		return getSqlSession().selectOne(EDUCATIONCOURSE + "select", param);
	}

	@Override
	public void updateEducationCourse(EducationCourseVo vo) { 
		getSqlSession().update(EDUCATIONCOURSE + "update", vo);
	}

	@Override
	public void deleteEducationCourse(EducationCourseVo vo) { 
		getSqlSession().delete(EDUCATIONCOURSE + "delete", vo);
	}

	@Override
	public void mergeEducationCourseList(Map<String, Object> param) {
		getSqlSession().insert(EDUCATIONCOURSE + "merge-list", param);
	}

	@Override
	public void deleteEducationCourseMap(Map<String, Object> param) {
		getSqlSession().delete(EDUCATIONCOURSE + "delete-map", param);
	}
}

