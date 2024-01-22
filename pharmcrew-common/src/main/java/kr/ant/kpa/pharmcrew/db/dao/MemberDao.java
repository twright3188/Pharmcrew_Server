package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberPartnerVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberAuthVo;

public interface MemberDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	약사회 회원
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMember(MemberVo vo); 
	List<MemberVo> listMember(Map<String, Object> param); 
	int countMember(Map<String, Object> param); 
	MemberVo selectMember(Map<String, Object> param); 
	void updateMember(MemberVo vo); 
	void deleteMember(MemberVo vo);

	List<String> listMemberPharmName();


	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 알림
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMemberNoti(MemberNotiVo vo); 
	List<MemberNotiVo> listMemberNoti(Map<String, Object> param); 
	int countMemberNoti(Map<String, Object> param); 
	MemberNotiVo selectMemberNoti(Map<String, Object> param); 
	void updateMemberNoti(MemberNotiVo vo); 
	void deleteMemberNoti(MemberNotiVo vo);

	void insertMemberNotiList(Map<String, Object> param);


	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 교육
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMemberEducation(MemberEducationVo vo); 
	List<MemberEducationVo> listMemberEducation(Map<String, Object> param); 
	int countMemberEducation(Map<String, Object> param); 
	MemberEducationVo selectMemberEducation(Map<String, Object> param); 
	void updateMemberEducation(MemberEducationVo vo); 
	void deleteMemberEducation(MemberEducationVo vo); 

	Map selectMemberEducationEval(Map<String, Object> param);
	void updateMemberEducation(Map<String, Object> param);
	int selectMemberEduTime(Map<String, Object> param);

	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 설문
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMemberSurvey(MemberSurveyVo vo); 
	List<MemberSurveyVo> listMemberSurvey(Map<String, Object> param); 
	int countMemberSurvey(Map<String, Object> param); 
	MemberSurveyVo selectMemberSurvey(Map<String, Object> param); 
	void updateMemberSurvey(MemberSurveyVo vo); 
	void deleteMemberSurvey(MemberSurveyVo vo); 

	/**
	 * 설문 정답 등록을 한꺼번에 하기 
	 */
	void insertMemberSurveyList(Map<String, Object> param);

	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 파트너 서비스
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMemberPartner(MemberPartnerVo vo); 
	List<MemberPartnerVo> listMemberPartner(Map<String, Object> param); 
	int countMemberPartner(Map<String, Object> param); 
	MemberPartnerVo selectMemberPartner(Map<String, Object> param); 
	void updateMemberPartner(MemberPartnerVo vo); 
	void deleteMemberPartner(MemberPartnerVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 인증 번호
	////////////////////////////////////////////////////////////////////////////////////////

	void insertMemberAuth(MemberAuthVo vo); 
	List<MemberAuthVo> listMemberAuth(Map<String, Object> param); 
	int countMemberAuth(Map<String, Object> param); 
	MemberAuthVo selectMemberAuth(Map<String, Object> param); 
	void updateMemberAuth(MemberAuthVo vo); 
	void deleteMemberAuth(MemberAuthVo vo);


}

