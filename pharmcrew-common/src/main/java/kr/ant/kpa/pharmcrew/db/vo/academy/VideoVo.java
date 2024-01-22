package kr.ant.kpa.pharmcrew.db.vo.academy;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

/**
* 학술 정보 => 동영상
 */
public class VideoVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 섬네일 파일 FileVo
	 */
	private FileVo thumbFile;
	public FileVo getThumbFile() {
		return thumbFile;
	}
	public void setThumbFile(FileVo thumbFile) {
		this.thumbFile = thumbFile;
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
	*	비디오 ID
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long video_id;

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
	*	URL
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String url;

	/**
	*	썸네일 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long thumb_file_id;

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

	//	비디오 ID
	public Long getVideo_id() {
		return video_id;
 	}
	public void setVideo_id(Long video_id) {
		this.video_id = video_id;
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

	//	URL
	public String getUrl() {
		return url;
 	}
	public void setUrl(String url) {
		this.url = url;
 	}

	//	썸네일 파일 ID
	public Long getThumb_file_id() {
		return thumb_file_id;
 	}
	public void setThumb_file_id(Long thumb_file_id) {
		this.thumb_file_id = thumb_file_id;
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
