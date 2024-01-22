package kr.ant.kpa.pharmcrew.db.vo.academy;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

/**
* 학술 정보 => 문서
 */
public class DocVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 문서 파일 FileVo
	 */
	private FileVo docFile;
	public FileVo getDocFile() {
		return docFile;
	}
	public void setDocFile(FileVo docFile) {
		this.docFile = docFile;
	}
	
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

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	*	문서 ID
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long doc_id;

	/**
	*	조직 ID => 조직이 없는 경우 : 전체 대상
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

	/**
	*	제목
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	내용
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	문서 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long doc_file_id;

	/**
	*	노출 여부
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String open_yn;

	/**
	*	조회 수
	*	long
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Long view_cnt;

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

	//	문서 ID
	public Long getDoc_id() {
		return doc_id;
 	}
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
 	}

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
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

	//	문서 파일 ID
	public Long getDoc_file_id() {
		return doc_file_id;
 	}
	public void setDoc_file_id(Long doc_file_id) {
		this.doc_file_id = doc_file_id;
 	}

	//	노출 여부
	public String getOpen_yn() {
		return open_yn;
 	}
	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
 	}

	//	조회 수
	public Long getView_cnt() {
		return view_cnt;
 	}
	public void setView_cnt(Long view_cnt) {
		this.view_cnt = view_cnt;
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
