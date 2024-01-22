package kr.ant.kpa.pharmcrew.db.vo.survey;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;

import java.io.Serializable;

import java.util.Date;

/**
* 설문 => 설문
 */
public class SurveyVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 작성자
	 */
	private AdminVo regAdmin;
	public AdminVo getRegAdmin() {
		return regAdmin;
	}
	public void setRegAdmin(AdminVo regAdmin) {
		this.regAdmin = regAdmin;
	}

	/**
	 * 사용자의 완료 여부 확인용
	 */
	private String isComplete;
	public String getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * withJoinMemberCnt<br>
	 *     참여 회원 수
	 */
	private Integer joinMemberCnt;
	public Integer getJoinMemberCnt() {
		return joinMemberCnt;
	}
	public void setJoinMemberCnt(Integer joinMemberCnt) {
		this.joinMemberCnt = joinMemberCnt;
	}

	/**
	 * 업데이트 시 end_date를 null로 설정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.SurveyDao#updateSurvey(SurveyVo)}
	 */
	private Boolean endDateNull;
	public Boolean getEndDateNull() {
		return endDateNull;
	}
	public void setEndDateNull(Boolean endDateNull) {
		this.endDateNull = endDateNull;
	}

	/**
	 * 업데이트 시 organize_id를 null로 변경
	 * {@link kr.ant.kpa.pharmcrew.db.dao.SurveyDao#updateSurvey(SurveyVo)}
	 */
	private Boolean organizeIdSetNull;
	public Boolean getOrganizeIdSetNull() {
		return organizeIdSetNull;
	}
	public void setOrganizeIdSetNull(Boolean organizeIdSetNull) {
		this.organizeIdSetNull = organizeIdSetNull;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	설문 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long survey_id;

	/**
	*	제목
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	설문 상세 설명 => 설문 조사를 위한상세 설명
	*	varchar, length : 200
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String detail;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

	/**
	*	결과 공개 여부 => Y:공개, N:비공개
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String open_result;

	/**
	*	설문 시작일
	*	date
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date start_date;

	/**
	*	설문 종료일 => 완료시까지라는 게 있음....9999.12.31로 하면 어떨지?
	*	date
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date end_date;

	/**
	*	조직 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

	/**
	*	등록자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) &&  NOT NULL 
	**/
	private Long reg_id;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수정자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) 
	**/
	private Long mod_id;

	/**
	*	수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	설문 ID
	public Long getSurvey_id() {
		return survey_id;
 	}
	public void setSurvey_id(Long survey_id) {
		this.survey_id = survey_id;
 	}

	//	제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	설문 상세 설명
	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	//	노출 여부
	public String getIs_open() {
		return is_open;
 	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
 	}

	//	결과 공개 여부
	public String getOpen_result() {
		return open_result;
 	}
	public void setOpen_result(String open_result) {
		this.open_result = open_result;
 	}

	//	설문 시작일
	public Date getStart_date() {
		return start_date;
 	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
 	}

	//	설문 종료일
	public Date getEnd_date() {
		return end_date;
 	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
 	}

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	등록자 ID
	public Long getReg_id() {
		return reg_id;
 	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수정자 ID
	public Long getMod_id() {
		return mod_id;
 	}
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
 	}

	//	수정 일시
	public Date getMod_dt() {
		return mod_dt;
 	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
 	}

}
