package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.MemberDao;

import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberPartnerVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberAuthVo;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	private static final String MEMBER = "kr.ant.kpa.pharmcrew.db.member.member-";
	private static final String MEMBERNOTI = "kr.ant.kpa.pharmcrew.db.member.membernoti-";
	private static final String MEMBEREDUCATION = "kr.ant.kpa.pharmcrew.db.member.membereducation-";
	private static final String MEMBERSURVEY = "kr.ant.kpa.pharmcrew.db.member.membersurvey-";
	private static final String MEMBERPARTNER = "kr.ant.kpa.pharmcrew.db.member.memberpartner-";
	private static final String MEMBERAUTH = "kr.ant.kpa.pharmcrew.db.member.memberauth-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	약사회 회원
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMember(MemberVo vo) { 
		getSqlSession().insert(MEMBER + "insert", vo);
	}

	@Override
	public List<MemberVo> listMember(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBER + "select", param);
	}

	@Override
	public int countMember(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBER + "count", param);
	}

	@Override
	public MemberVo selectMember(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBER + "select", param);
	}

	@Override
	public void updateMember(MemberVo vo) { 
		getSqlSession().update(MEMBER + "update", vo);
	}

	@Override
	public void deleteMember(MemberVo vo) { 
		getSqlSession().delete(MEMBER + "delete", vo);
	}

	@Override
	public List<String> listMemberPharmName() {
		return getSqlSession().selectList(MEMBER + "select-pharm-name");
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 알림
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberNoti(MemberNotiVo vo) { 
		getSqlSession().insert(MEMBERNOTI + "insert", vo);
	}

	@Override
	public List<MemberNotiVo> listMemberNoti(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBERNOTI + "select", param);
	}

	@Override
	public int countMemberNoti(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERNOTI + "count", param);
	}

	@Override
	public MemberNotiVo selectMemberNoti(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERNOTI + "select", param);
	}

	@Override
	public void updateMemberNoti(MemberNotiVo vo) { 
		getSqlSession().update(MEMBERNOTI + "update", vo);
	}

	@Override
	public void deleteMemberNoti(MemberNotiVo vo) { 
		getSqlSession().delete(MEMBERNOTI + "delete", vo);
	}

	@Override
	public void insertMemberNotiList(Map<String, Object> param) {
		getSqlSession().insert(MEMBERNOTI + "insert-list", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 교육
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberEducation(MemberEducationVo vo) { 
		getSqlSession().insert(MEMBEREDUCATION + "insert", vo);
	}

	@Override
	public List<MemberEducationVo> listMemberEducation(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBEREDUCATION + "select", param);
	}

	@Override
	public int countMemberEducation(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBEREDUCATION + "count", param);
	}

	@Override
	public MemberEducationVo selectMemberEducation(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBEREDUCATION + "select", param);
	}

	@Override
	public void updateMemberEducation(MemberEducationVo vo) { 
		getSqlSession().update(MEMBEREDUCATION + "update", vo);
	}

	@Override
	public void deleteMemberEducation(MemberEducationVo vo) { 
		getSqlSession().delete(MEMBEREDUCATION + "delete", vo);
	}

	@Override
	public Map selectMemberEducationEval(Map<String, Object> param) {
		return getSqlSession().selectOne(MEMBEREDUCATION + "select-eval", param);
	}

	@Override
	public void updateMemberEducation(Map<String, Object> param) {
		getSqlSession().update(MEMBEREDUCATION + "update-map", param);
	}

	@Override
	public int selectMemberEduTime(Map<String, Object> param) {
		return getSqlSession().selectOne(MEMBEREDUCATION + "select-take-min-sum", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 설문
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberSurvey(MemberSurveyVo vo) { 
		getSqlSession().insert(MEMBERSURVEY + "insert", vo);
	}

	@Override
	public List<MemberSurveyVo> listMemberSurvey(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBERSURVEY + "select", param);
	}

	@Override
	public int countMemberSurvey(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERSURVEY + "count", param);
	}

	@Override
	public MemberSurveyVo selectMemberSurvey(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERSURVEY + "select", param);
	}

	@Override
	public void updateMemberSurvey(MemberSurveyVo vo) { 
		getSqlSession().update(MEMBERSURVEY + "update", vo);
	}

	@Override
	public void deleteMemberSurvey(MemberSurveyVo vo) { 
		getSqlSession().delete(MEMBERSURVEY + "delete", vo);
	}

	/**
	 * 설문 정답 등록을 한꺼번에 하기 
	 */
	@Override
	public void insertMemberSurveyList(Map<String, Object> param) {

		getSqlSession().delete(MEMBERSURVEY + "merge-list", param);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 파트너 서비스
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberPartner(MemberPartnerVo vo) { 
		getSqlSession().insert(MEMBERPARTNER + "insert", vo);
	}

	@Override
	public List<MemberPartnerVo> listMemberPartner(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBERPARTNER + "select", param);
	}

	@Override
	public int countMemberPartner(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERPARTNER + "count", param);
	}

	@Override
	public MemberPartnerVo selectMemberPartner(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERPARTNER + "select", param);
	}

	@Override
	public void updateMemberPartner(MemberPartnerVo vo) { 
		getSqlSession().update(MEMBERPARTNER + "update", vo);
	}

	@Override
	public void deleteMemberPartner(MemberPartnerVo vo) { 
		getSqlSession().delete(MEMBERPARTNER + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	회원 인증 번호
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertMemberAuth(MemberAuthVo vo) { 
		getSqlSession().insert(MEMBERAUTH + "insert", vo);
	}

	@Override
	public List<MemberAuthVo> listMemberAuth(Map<String, Object> param) { 
		return getSqlSession().selectList(MEMBERAUTH + "select", param);
	}

	@Override
	public int countMemberAuth(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERAUTH + "count", param);
	}

	@Override
	public MemberAuthVo selectMemberAuth(Map<String, Object> param) { 
		return getSqlSession().selectOne(MEMBERAUTH + "select", param);
	}

	@Override
	public void updateMemberAuth(MemberAuthVo vo) { 
		getSqlSession().update(MEMBERAUTH + "update", vo);
	}

	@Override
	public void deleteMemberAuth(MemberAuthVo vo) { 
		getSqlSession().delete(MEMBERAUTH + "delete", vo);
	}


}

