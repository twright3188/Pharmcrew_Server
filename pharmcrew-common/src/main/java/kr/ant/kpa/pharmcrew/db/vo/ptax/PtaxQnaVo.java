package kr.ant.kpa.pharmcrew.db.vo.ptax;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;

import java.io.Serializable;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;

/**
* 팜택스 => 팜텍스 1:1 문의
 */
public class PtaxQnaVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 질문 회원
	 */
	private MemberVo member;
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}

	/**
	 * 답변 관리자
	 */
	private AdminVo admin;
	public AdminVo getAdmin() {
		return admin;
	}
	public void setAdmin(AdminVo admin) {
		this.admin = admin;
	}

	/**
	 * 질문 첨부파일 리스트
	 */
	private List<FileVo> qAttachFiles;
	public List<FileVo> getqAttachFiles() {
		return qAttachFiles;
	}
	public void setqAttachFiles(List<FileVo> qAttachFiles) {
		this.qAttachFiles = qAttachFiles;
	}

	/**
	 * 답변 첨부파일 리스트
	 */
	private List<FileVo> aAttachFiles;
	public List<FileVo> getaAttachFiles() {
		return aAttachFiles;
	}
	public void setaAttachFiles(List<FileVo> aAttachFiles) {
		this.aAttachFiles = aAttachFiles;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	문의 ID
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long qna_id;

	/**
	*	회원 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	약국명 => 팜택스 연동이 필요한 정보
	*	varchar, length : 20
	*	파라메터 옵션 :
	**/
	private String pharm_name;

	/**
	*	제목
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	내용
	*	varchar, length : 200
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	상태 => Y:답변완료, N:문의 중
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_answerd;

	/**
	*	답변 타이틀 => 관리자 답변 타이틀
	*	varchar, length : 50
	*	파라메터 옵션 : 
	**/
	private String ans_title;

	/**
	*	답변 내용 => 관리자 답변 내용
	*	varchar, length : 200
	*	파라메터 옵션 : 
	**/
	private String ans_body;

	/**
	*	문의 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date question_dt;

	/**
	*	관리자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) 
	**/
	private Long admin_id;

	/**
	*	답변 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date answer_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	문의 ID
	public Long getQna_id() {
		return qna_id;
 	}
	public void setQna_id(Long qna_id) {
		this.qna_id = qna_id;
 	}

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	약국명
	public String getPharm_name() {
		return pharm_name;
 	}
	public void setPharm_name(String pharm_name) {
		this.pharm_name = pharm_name;
 	}

	//	제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	내용
	public String getBody() {
		return body;
 	}
	public void setBody(String body) {
		this.body = body;
 	}

	//	상태
	public String getIs_answerd() {
		return is_answerd;
 	}
	public void setIs_answerd(String is_answerd) {
		this.is_answerd = is_answerd;
 	}

	//	답변 타이틀
	public String getAns_title() {
		return ans_title;
 	}
	public void setAns_title(String ans_title) {
		this.ans_title = ans_title;
 	}

	//	답변 내용
	public String getAns_body() {
		return ans_body;
 	}
	public void setAns_body(String ans_body) {
		this.ans_body = ans_body;
 	}

	//	문의 일시
	public Date getQuestion_dt() {
		return question_dt;
 	}
	public void setQuestion_dt(Date question_dt) {
		this.question_dt = question_dt;
 	}

	//	관리자 ID
	public Long getAdmin_id() {
		return admin_id;
 	}
	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
 	}

	//	답변 일시
	public Date getAnswer_dt() {
		return answer_dt;
 	}
	public void setAnswer_dt(Date answer_dt) {
		this.answer_dt = answer_dt;
 	}

}
