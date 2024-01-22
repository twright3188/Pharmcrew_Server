package kr.ant.kpa.pharmcrew.db.vo.news;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;

/**
* 소식 => 문의
 */
public class QnaVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 첨부 파일
	 */
	private FileVo attachFile;
	public FileVo getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(FileVo attachFile) {
		this.attachFile = attachFile;
	}

	/**
	 * 회원
	 */
	private MemberVo member;
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}

	/**
	 * 관리자
	 */
	private AdminVo admin;
	public AdminVo getAdmin() {
		return admin;
	}
	public void setAdmin(AdminVo admin) {
		this.admin = admin;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	문의 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long qna_id;

	/**
	*	조직 ID => 공지면 공지의, 교육이면 교육의, 일반이면 자신의...
	*	long
	*	파라메터 옵션 : 
	**/
	private Long organize_id;

	/**
	*	대상 타입 => QNA_TARGET,java
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String target_div;

	/**
	*	대상 ID
	*	long
	*	파라메터 옵션 : 
	**/
	private Long target_id;

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
	*	첨부 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) 
	**/
	private Long file_id;

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
	*	회원 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

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

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	대상 타입
	public String getTarget_div() {
		return target_div;
 	}
	public void setTarget_div(String target_div) {
		this.target_div = target_div;
 	}

	//	대상 ID
	public Long getTarget_id() {
		return target_id;
 	}
	public void setTarget_id(Long target_id) {
		this.target_id = target_id;
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

	//	첨부 파일 ID
	public Long getFile_id() {
		return file_id;
 	}
	public void setFile_id(Long file_id) {
		this.file_id = file_id;
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

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
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
