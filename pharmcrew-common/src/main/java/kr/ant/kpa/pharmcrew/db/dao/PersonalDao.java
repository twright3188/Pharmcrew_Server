package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.AdditionalInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.EmploymentCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.EmploymentInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.PersonalInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.ReportYearVo;

public interface PersonalDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	약사회 회원
	////////////////////////////////////////////////////////////////////////////////////////

	List<PersonalInformationVo> listPersonalInformation(Map<String, Object> param); 
	PersonalInformationVo 		selectPersonalInformation(Map<String, Object> param); 
	void 						updatePersonalInformation(PersonalInformationVo vo); 
	void 						deleteMember(String pi_license);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	추가정보
	////////////////////////////////////////////////////////////////////////////////////////

	List<AdditionalInformationVo> listAdditionalInformation(Map<String, Object> param); 
	AdditionalInformationVo 	selectAdditionalInformation(Map<String, Object> param); 
	void 						updateAdditionalInformation(AdditionalInformationVo vo);
	void 						insertAdditionalInformation(AdditionalInformationVo vo);
	void 						deleteAdditional(String pi_license);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	직업정보
	////////////////////////////////////////////////////////////////////////////////////////
	List<EmploymentInformationVo> listEmploymentlInformation(Map<String, Object> param); 
	EmploymentInformationVo 	selectEmploymentInformation(Map<String, Object> param); 
	void 						updateEmploymentInformation(EmploymentInformationVo vo); 
	void 						insertEmploymentInformation(EmploymentInformationVo vo);
	void 						deleteEmploymentInformation(String pi_license);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	직업코드
	////////////////////////////////////////////////////////////////////////////////////////
	List<EmploymentCodeVo> 		listEmploymentlCode(Map<String, Object> param); 
	EmploymentCodeVo 			selectEmploymentCode(Map<String, Object> param); 
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	신고서
	////////////////////////////////////////////////////////////////////////////////////////
	List<ReportYearVo> 			listReportYear(Map<String, Object> param); 
	ReportYearVo 				selectReportYear(Map<String, Object> param); 
	void 						updateReportYear(ReportYearVo vo); 
	void 						insertReportYear(ReportYearVo vo);
	void 						deleteReportYear(String pi_license);
}

