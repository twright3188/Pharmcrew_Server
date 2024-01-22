package kr.ant.kpa.pharmcrew.db.vo.partners;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

/**
* 파트너 서비스 => 파트너 서비스
 */
public class PartnersVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * withMine<br>
	 * 사용자가 사용중 여부 
	 */
	private String isUsing;
	public String getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(String isUsing) {
		this.isUsing = isUsing;
	}

	/**
	 * withMemberCount<br>
	 *     사용중인 회원 수
	 */
	private Integer memberCount;
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	/**
	 * 아이콘 이미지 파일
	 */
	private FileVo iconFile;
	public FileVo getIconFile() {
		return iconFile;
	}
	public void setIconFile(FileVo iconFile) {
		this.iconFile = iconFile;
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
	*	파트너 아이디 => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long partner_id;

	/**
	*	파트너 이름
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String name;

	/**
	*	파트너 설명 => 파트너 설명 - 메인화면에 노출
	*	varchar, length : 50
	*	파라메터 옵션 : 
	**/
	private String detail;

	/**
	*	파트너 아이콘 file 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long icon_file_id;

	/**
	*	이동 URL
	*	varchar, length : 200
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String move_url;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

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

	//	파트너 아이디
	public Long getPartner_id() {
		return partner_id;
 	}
	public void setPartner_id(Long partner_id) {
		this.partner_id = partner_id;
 	}

	//	파트너 이름
	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	//	파트너 설명
	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	//	파트너 아이콘 file 아이디
	public Long getIcon_file_id() {
		return icon_file_id;
 	}
	public void setIcon_file_id(Long icon_file_id) {
		this.icon_file_id = icon_file_id;
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
