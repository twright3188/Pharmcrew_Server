package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

/**
* 사용자 => 회원 설문
 */
public class MemberSurveyVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * withMember<br>
	 *     회원명
	 */
	private String memberName;
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	회원 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	설문 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_survey-survey_id) &&  NOT NULL 
	**/
	private Long survey_id;

	/**
	*	문항 인덱스
	*	int, length : 2
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private Integer question_idx;

	/**
	*	답변 => 객관식-문항답변번호, 주관식- 답변주관식
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String answer;

	/**
	*	제출 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date survey_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	설문 ID
	public Long getSurvey_id() {
		return survey_id;
 	}
	public void setSurvey_id(Long survey_id) {
		this.survey_id = survey_id;
 	}

	//	문항 인덱스
	public Integer getQuestion_idx() {
		return question_idx;
 	}
	public void setQuestion_idx(Integer question_idx) {
		this.question_idx = question_idx;
 	}

	//	답변
	public String getAnswer() {
		return answer;
 	}
	public void setAnswer(String answer) {
		this.answer = answer;
 	}

	//	제출 일시
	public Date getSurvey_dt() {
		return survey_dt;
 	}
	public void setSurvey_dt(Date survey_dt) {
		this.survey_dt = survey_dt;
 	}

}
