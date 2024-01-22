package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;

/**
* 사용자 => 회원 교육
 */
public class MemberEducationVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	// 교육정보 추출
	private EducationVo educationVo;
	public EducationVo getEducationVo() {
		return educationVo;
	}
	public void setEducationVo(EducationVo educationVo) {
		this.educationVo = educationVo;
	}
	
	// 교육정보 추출
	private EducationCourseVo courseVo;
	public EducationCourseVo getCourseVo() {
		return courseVo;
	}
	public void setCourseVo(EducationCourseVo courseVo) {
		this.courseVo = courseVo;
	}

	private MemberVo memberVo;
	public MemberVo getMemberVo() {
		return memberVo;
	}
	public void setMemberVo(MemberVo memberVo) {
		this.memberVo = memberVo;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	회원 교육 참석 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long attend_id;

	/**
	*	회원 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	교육 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_education-education_id) &&  NOT NULL 
	**/
	private Long education_id;

	/**
	*	강의 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_education_course-course_id) 
	**/
	private Long course_id;

	/**
	*	시작 인증 QR ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_qr-qr_id) &&  NOT NULL 
	**/
	private Long start_qr_id;

	/**
	*	시작 인증 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date start_dt;

	/**
	*	종료 인증 QR ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_qr-qr_id) 
	**/
	private Long end_qr_id;

	/**
	*	종료 인증 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date end_dt;

	/**
	*	인정 시간(분) => 교육에서 설정한 교육 이수시간 - 분단위
	*	int, length : 4
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer take_min;

	/**
	*	인증 여부 => 관리자 페이지 체크 여부 :  Y: 승인, N: 미승인, R: 승인대기
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String approval;

	/**
	*	평가 별점 => x/5점
	*	int, length : 1
	*	파라메터 옵션 : 
	**/
	private Integer eval_star;

	/**
	*	평가 내용
	*	varchar, length : 100
	*	파라메터 옵션 : 
	**/
	private String eval_body;

	/**
	*	평가 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date eval_reg_dt;

	/**
	*	평가 수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date eval_mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	회원 교육 참석 ID
	public Long getAttend_id() {
		return attend_id;
 	}
	public void setAttend_id(Long attend_id) {
		this.attend_id = attend_id;
 	}

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	교육 ID
	public Long getEducation_id() {
		return education_id;
 	}
	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
 	}

	//	강의 ID
	public Long getCourse_id() {
		return course_id;
 	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
 	}

	//	시작 인증 QR ID
	public Long getStart_qr_id() {
		return start_qr_id;
 	}
	public void setStart_qr_id(Long start_qr_id) {
		this.start_qr_id = start_qr_id;
 	}

	//	시작 인증 일시
	public Date getStart_dt() {
		return start_dt;
 	}
	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
 	}

	//	종료 인증 QR ID
	public Long getEnd_qr_id() {
		return end_qr_id;
 	}
	public void setEnd_qr_id(Long end_qr_id) {
		this.end_qr_id = end_qr_id;
 	}

	//	종료 인증 일시
	public Date getEnd_dt() {
		return end_dt;
 	}
	public void setEnd_dt(Date end_dt) {
		this.end_dt = end_dt;
 	}

	//	인정 시간(분)
	public Integer getTake_min() {
		return take_min;
 	}
	public void setTake_min(Integer take_min) {
		this.take_min = take_min;
 	}

	//	인증 여부
	public String getApproval() {
		return approval;
 	}
	public void setApproval(String approval) {
		this.approval = approval;
 	}

	//	평가 별점
	public Integer getEval_star() {
		return eval_star;
 	}
	public void setEval_star(Integer eval_star) {
		this.eval_star = eval_star;
 	}

	//	평가 내용
	public String getEval_body() {
		return eval_body;
 	}
	public void setEval_body(String eval_body) {
		this.eval_body = eval_body;
 	}

	//	평가 일시
	public Date getEval_reg_dt() {
		return eval_reg_dt;
 	}
	public void setEval_reg_dt(Date eval_reg_dt) {
		this.eval_reg_dt = eval_reg_dt;
 	}

	//	평가 수정 일시
	public Date getEval_mod_dt() {
		return eval_mod_dt;
 	}
	public void setEval_mod_dt(Date eval_mod_dt) {
		this.eval_mod_dt = eval_mod_dt;
 	}

}
