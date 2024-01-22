package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;

public interface EducationDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	교육
	////////////////////////////////////////////////////////////////////////////////////////

	void insertEducation(EducationVo vo); 
	List<EducationVo> listEducation(Map<String, Object> param); 
	int countEducation(Map<String, Object> param); 
	EducationVo selectEducation(Map<String, Object> param); 
	void updateEducation(EducationVo vo); 
	void deleteEducation(EducationVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	강의
	////////////////////////////////////////////////////////////////////////////////////////

	void insertEducationCourse(EducationCourseVo vo); 
	List<EducationCourseVo> listEducationCourse(Map<String, Object> param); 
	int countEducationCourse(Map<String, Object> param); 
	EducationCourseVo selectEducationCourse(Map<String, Object> param); 
	void updateEducationCourse(EducationCourseVo vo); 
	void deleteEducationCourse(EducationCourseVo vo); 

	void mergeEducationCourseList(Map<String, Object> param);
	void deleteEducationCourseMap(Map<String, Object> param);

}

