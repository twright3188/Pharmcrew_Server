package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

/**
* 사용자 => 약사회 회원
 */
public class MemberVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 프로필 파일
	 */
	private FileVo profileFile;
	public FileVo getProfileFile() {
		return profileFile;
	}
	public void setProfileFile(FileVo profileFile) {
		this.profileFile = profileFile;
	}
	
	/**
	 * 조직 0 이름
	 */
	private String org_0_name;
	public String getOrg_0_name() {
		return org_0_name;
	}
	public void setOrg_0_name(String org_0_name) {
		this.org_0_name = org_0_name;
	}

	/**
	 * 조직 1 이름
	 */
	private String org_1_name;
	public String getOrg_1_name() {
		return org_1_name;
	}
	public void setOrg_1_name(String org_1_name) {
		this.org_1_name = org_1_name;
	}
	/**
	 * 조직 2 이름
	 */
	private String org_2_name;
	public String getOrg_2_name() {
		return org_2_name;
	}
	public void setOrg_2_name(String org_2_name) {
		this.org_2_name = org_2_name;
	}
	
	private Boolean isEmptyProfileId;
	public Boolean getIsEmptyProfileId() {
		return isEmptyProfileId;
	}
	public void setIsEmptyProfileId(Boolean isEmptyProfileId) {
		this.isEmptyProfileId = isEmptyProfileId;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	회원 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	조직 1 ID => 소속 조직 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) &&  NOT NULL 
	**/
	private Long organize_1_id;

	/**
	*	조직 2 ID => 소속 조직 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_2_id;

	/**
	*	면허번호 => 10자리로 예상됨
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL &&  UNIQUE 
	**/
	private String license_no;

	/**
	*	비밀번호 => 사용자 등록 시 비밀번호를 등록해야 함
	*	varchar, length : 100
	*	파라메터 옵션 : 
	**/
	private String password;

	/**
	*	이름
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String name;

	/**
	*	생년월일
	*	char, length : 6
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String birthday;

	/**
	*	프로필 이미지 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) 
	**/
	private Long profile_file_id;

	/**
	*	휴대전화 번호 => -없이
	*	varchar, length : 11
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String handphone;

	/**
	*	이메일
	*	varchar, length : 100
	*	파라메터 옵션 : 
	**/
	private String email;

	/**
	*	성별 => M(en): 남, W(omen): 여
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String sex_div;

	/**
	*	회원 등록 상태 => 회원등록 여부 : P(re): 등록 이전, N(ormal): 일반
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String state_div;

	/**
	*	일반 푸시 수신 동의 여부
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String push_yn;

	/**
	*	마케팅 sms 수신 동의 여부
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String ad_sms_yn;

	/**
	*	마케팅 푸시 수신 동의 여부 => Y(es): 수신동의, N(o):미동의
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String ad_push_yn;

	/**
	*	약관 동의 일시 => 약관 동의 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date agree_dt;

	/**
	*	알림 최종 요청 일시 => 알림 요청 시 업데이트 - 신규 알림 숫자 확인 용
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date noti_read_dt;

	/**
	*	마지막 로그인 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date last_login_dt;
	private String pharm_name;
	private String pharm_office;
	private String ptax_yn;

	/**
	*	등록 일시 => 초기 데이터 등록 일시가 아니라, 회원이 등록한 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	조직 1 ID
	public Long getOrganize_1_id() {
		return organize_1_id;
 	}
	public void setOrganize_1_id(Long organize_1_id) {
		this.organize_1_id = organize_1_id;
 	}

	//	조직 2 ID
	public Long getOrganize_2_id() {
		return organize_2_id;
 	}
	public void setOrganize_2_id(Long organize_2_id) {
		this.organize_2_id = organize_2_id;
 	}

	//	면허번호
	public String getLicense_no() {
		return license_no;
 	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
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

	//	생년월일
	public String getBirthday() {
		return birthday;
 	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
 	}

	//	프로필 이미지 파일 ID
	public Long getProfile_file_id() {
		return profile_file_id;
 	}
	public void setProfile_file_id(Long profile_file_id) {
		this.profile_file_id = profile_file_id;
 	}

	//	휴대전화 번호
	public String getHandphone() {
		return handphone;
 	}
	public void setHandphone(String handphone) {
		this.handphone = handphone;
 	}

	//	이메일
	public String getEmail() {
		return email;
 	}
	public void setEmail(String email) {
		this.email = email;
 	}

	//	성별
	public String getSex_div() {
		return sex_div;
 	}
	public void setSex_div(String sex_div) {
		this.sex_div = sex_div;
 	}

	//	회원 등록 상태
	public String getState_div() {
		return state_div;
 	}
	public void setState_div(String state_div) {
		this.state_div = state_div;
 	}

	//	일반 푸시 수신 동의 여부
	public String getPush_yn() {
		return push_yn;
 	}
	public void setPush_yn(String push_yn) {
		this.push_yn = push_yn;
 	}

	//	마케팅 sms 수신 동의 여부
	public String getAd_sms_yn() {
		return ad_sms_yn;
 	}
	public void setAd_sms_yn(String ad_sms_yn) {
		this.ad_sms_yn = ad_sms_yn;
 	}

	//	마케팅 푸시 수신 동의 여부
	public String getAd_push_yn() {
		return ad_push_yn;
 	}
	public void setAd_push_yn(String ad_push_yn) {
		this.ad_push_yn = ad_push_yn;
 	}

	//	약관 동의 일시
	public Date getAgree_dt() {
		return agree_dt;
 	}
	public void setAgree_dt(Date agree_dt) {
		this.agree_dt = agree_dt;
 	}

	//	알림 최종 요청 일시
	public Date getNoti_read_dt() {
		return noti_read_dt;
 	}
	public void setNoti_read_dt(Date noti_read_dt) {
		this.noti_read_dt = noti_read_dt;
 	}

	//	마지막 로그인 일시
	public Date getLast_login_dt() {
		return last_login_dt;
 	}
	public void setLast_login_dt(Date last_login_dt) {
		this.last_login_dt = last_login_dt;
 	}

	public String getPharm_name() {
		return pharm_name;
	}
	public void setPharm_name(String pharm_name) {
		this.pharm_name = pharm_name;
	}

	public String getPharm_office() {
		return pharm_office;
	}
	public void setPharm_office(String pharm_office) {
		this.pharm_office = pharm_office;
	}

	public String getPtax_yn() {
		return ptax_yn;
	}
	public void setPtax_yn(String ptax_yn) {
		this.ptax_yn = ptax_yn;
	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수정 일시
	public Date getMod_dt() {
		return mod_dt;
 	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
 	}

}
