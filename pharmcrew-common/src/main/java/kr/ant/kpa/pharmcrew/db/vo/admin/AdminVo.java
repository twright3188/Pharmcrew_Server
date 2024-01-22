package kr.ant.kpa.pharmcrew.db.vo.admin;

import java.io.Serializable;

import java.util.Date;

/**
* 관리자 => 관리자
 */
public class AdminVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * tel을 null로 설정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.AdminDao#updateAdmin(AdminVo)}
	 */
	private Boolean telNull;
	public Boolean getTelNull() {
		return telNull;
	}
	public void setTelNull(Boolean telNull) {
		this.telNull = telNull;
	}

	/**
	 * phone을 null로 설정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.AdminDao#updateAdmin(AdminVo)}
	 */
	private Boolean phoneNull;
	public Boolean getPhoneNull() {
		return phoneNull;
	}
	public void setPhoneNull(Boolean phoneNull) {
		this.phoneNull = phoneNull;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	관리자 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long admin_id;

	/**
	*	조직 ID => 조직에 따라 접근 권한이 있다.
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

	/**
	*	권한 => S(uper Admin), A(Admin), O(조직관리)
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String authority;

	/**
	*	아이디
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL &&  UNIQUE 
	**/
	private String account;

	/**
	*	비밀번호
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String password;

	/**
	*	이름
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String name;

	/**
	*	이메일
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String email;

	/**
	*	휴대 전화번호 => 휴대폰 번호 (-제외)
	*	varchar, length : 11
	*	파라메터 옵션 : 
	**/
	private String phone;

	/**
	*	일반전화 => 일반 전화 번호
	*	varchar, length : 11
	*	파라메터 옵션 : 
	**/
	private String tel;

	/**
	*	상태 => ADMIN_STATE.java => N(ormal): 일반, S(top): 중지
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String state_div;

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

	//	관리자 ID
	public Long getAdmin_id() {
		return admin_id;
 	}
	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
 	}

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	권한
	public String getAuthority() {
		return authority;
 	}
	public void setAuthority(String authority) {
		this.authority = authority;
 	}

	//	아이디
	public String getAccount() {
		return account;
 	}
	public void setAccount(String account) {
		this.account = account;
 	}

	//	비밀번호
	public String getPassword() {
		return password;
 	}
	public void setPassword(String password) {
		this.password = password;
 	}

	//	이름
	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	//	이메일
	public String getEmail() {
		return email;
 	}
	public void setEmail(String email) {
		this.email = email;
 	}

	//	휴대 전화번호
	public String getPhone() {
		return phone;
 	}
	public void setPhone(String phone) {
		this.phone = phone;
 	}

	//	일반전화
	public String getTel() {
		return tel;
 	}
	public void setTel(String tel) {
		this.tel = tel;
 	}

	//	상태
	public String getState_div() {
		return state_div;
 	}
	public void setState_div(String state_div) {
		this.state_div = state_div;
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
