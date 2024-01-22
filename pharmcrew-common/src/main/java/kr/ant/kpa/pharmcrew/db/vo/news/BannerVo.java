package kr.ant.kpa.pharmcrew.db.vo.news;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

import java.io.Serializable;

import java.util.Date;

/**
* 소식 => 배너
 */
public class BannerVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 이미지 파일
	 */
	private FileVo imgFile;
	public FileVo getImgFile() {
		return imgFile;
	}
	public void setImgFile(FileVo imgFile) {
		this.imgFile = imgFile;
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
	 * move_id를 null로 지정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.NewsDao#updateBanner(BannerVo)}
	 */
	private Boolean moveIdNull;
	public Boolean getMoveIdNull() {
		return moveIdNull;
	}
	public void setMoveIdNull(Boolean moveIdNull) {
		this.moveIdNull = moveIdNull;
	}

	/**
	 * move_url을 null로 지정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.NewsDao#updateBanner(BannerVo)}
	 */
	private Boolean moveUrlNull;
	public Boolean getMoveUrlNull() {
		return moveUrlNull;
	}
	public void setMoveUrlNull(Boolean moveUrlNull) {
		this.moveUrlNull = moveUrlNull;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	배너 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long banner_id;

	/**
	*	제목
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	이미지 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long img_file_id;

	/**
	*	이동 타입 => MOVE.java
	*	char, length : 2
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String move_div;

	/**
	*	이동 ID
	*	long
	*	파라메터 옵션 : 
	**/
	private Long move_id;

	/**
	*	이동 URL
	*	varchar, length : 200
	*	파라메터 옵션 : 
	**/
	private String move_url;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

	/**
	*	노출 시작 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date open_start_dt;

	/**
	*	노출 종료 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date open_end_dt;

	/**
	*	노출 순서
	*	int, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer open_idx;

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

	//	배너 ID
	public Long getBanner_id() {
		return banner_id;
 	}
	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
 	}

	//	제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	이미지 파일 ID
	public Long getImg_file_id() {
		return img_file_id;
 	}
	public void setImg_file_id(Long img_file_id) {
		this.img_file_id = img_file_id;
 	}

	//	이동 타입
	public String getMove_div() {
		return move_div;
 	}
	public void setMove_div(String move_div) {
		this.move_div = move_div;
 	}

	//	이동 ID
	public Long getMove_id() {
		return move_id;
 	}
	public void setMove_id(Long move_id) {
		this.move_id = move_id;
 	}

	//	이동 URL
	public String getMove_url() {
		return move_url;
 	}
	public void setMove_url(String move_url) {
		this.move_url = move_url;
 	}

	//	노출 여부
	public String getIs_open() {
		return is_open;
 	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
 	}

	//	노출 시작 일시
	public Date getOpen_start_dt() {
		return open_start_dt;
 	}
	public void setOpen_start_dt(Date open_start_dt) {
		this.open_start_dt = open_start_dt;
 	}

	//	노출 종료 일시
	public Date getOpen_end_dt() {
		return open_end_dt;
 	}
	public void setOpen_end_dt(Date open_end_dt) {
		this.open_end_dt = open_end_dt;
 	}

	//	노출 순서
	public Integer getOpen_idx() {
		return open_idx;
 	}
	public void setOpen_idx(Integer open_idx) {
		this.open_idx = open_idx;
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
