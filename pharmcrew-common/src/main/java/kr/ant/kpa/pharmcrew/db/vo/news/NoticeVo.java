package kr.ant.kpa.pharmcrew.db.vo.news;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 소식 => 공지
 */
public class NoticeVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 첨부 파일 리스트
	 */
	private List<FileVo> attachFiles;
	public List<FileVo> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<FileVo> attachFiles) {
		this.attachFiles = attachFiles;
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

	/**
	 * 조직 이름 
	 */
	private String organizeName;
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	공지 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long notice_id;

	/**
	*	제목
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	내용
	*	mediumtext
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	조직 ID => 전체 공지는 조직 아이디가 없는 것인가? - 성백견
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

	/**
	*	푸시 발송 여부 => Y:푸시 발송완료, N:푸시 미발송
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_push;

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

	//	공지 ID
	public Long getNotice_id() {
		return notice_id;
 	}
	public void setNotice_id(Long notice_id) {
		this.notice_id = notice_id;
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

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	노출 여부
	public String getIs_open() {
		return is_open;
 	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
 	}

	//	푸시 발송 여부
	public String getIs_push() {
		return is_push;
 	}
	public void setIs_push(String is_push) {
		this.is_push = is_push;
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
