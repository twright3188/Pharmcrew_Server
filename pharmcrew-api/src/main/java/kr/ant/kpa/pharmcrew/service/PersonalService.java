package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.db.vo.common.AffiliationCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.UniversityCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.*;
import kr.ant.kpa.pharmcrew.resp.CommonListResp;
import kr.ant.kpa.pharmcrew.resp.CommonResp;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;

public interface PersonalService {

	/**
	*	1.내 정보 요청
	**/
	CommonResp<PersonalInformationVo> getPersonalInfo(String license )
			throws InvalidMemberException ;
	/**
	*	2.내 정보 수정
	**/
	PcResp savePersonalInfo(PersonalInformationVo personalInfo)
			throws InvalidMemberException ;
	
	/**
	*	3.추가정보 요청
	**/
	CommonResp<AdditionalInformationVo> getAdditionalInfo(String license, String ry_year )
			throws InvalidMemberException ;
	/**
	*	4.추가정보 수정
	**/
	PcResp saveAdditionalInfo(AdditionalInformationVo additionalInfo)
			throws InvalidMemberException ;
	
	/** 5. 분회목록 **/
	CommonListResp<AffiliationCodeVo> getAffiliationList()
			throws InvalidMemberException ;
	
	/** 6. 대학교목록 **/
	CommonListResp<UniversityCodeVo> getUniversityList()
			throws InvalidMemberException ;
	
	/** 7. 직업코드목록 **/
	CommonListResp<EmploymentCodeVo> getEmploymentCodeList()
			throws InvalidMemberException ;
	
	/**
	*	8.직업정보 요청
	**/
	CommonResp<EmploymentInformationVo> getEmploymentInfo(String license, String ry_year )
			throws InvalidMemberException ;
	/**
	*	9.직업정보 저장
	**/
	PcResp saveEmploymentInfo(EmploymentInformationVo personalInfo)
			throws InvalidMemberException ;
	
	/**
	*	9.신고서 요청
	**/
	CommonResp<ReportYearVo> getReportYear(String license_no, String ry_year)
			throws InvalidMemberException ;
	/**
	*	10.신고서 저장
	**/
	PcResp saveReportYear(ReportYearVo info)
			throws InvalidMemberException ;

	/**
	*	11.멤버생성
	**/
	PcResp generateMember()
			throws InvalidMemberException ;

}
