package kr.ant.kpa.pharmcrew.db.vo.common;

import java.io.Serializable;

import java.util.Date;

/**
* 기본 => 버전
 */
public class AppVersionVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	// 쿼리에서 forced_yn이 있는지 여부 
	private Integer force_count;
	// 강제 여부 숫자  
	public Integer getForce_count() {
		return force_count;
	}
	public void setForce_count(Integer force_count) {
		this.force_count = force_count;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	버전 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long version_id;

	/**
	*	OS 타입 => OS 타입 - A(Android), I(iOS)
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String os_type;

	/**
	*	버전 정보 => 등록한 app 버전
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String version;

	/**
	*	업데이트 설명 => 업데이트 간단 설명
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String detail;

	/**
	*	강제 업데이트 여부 => Y(강제업데이트), N(일반업데이트)
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String forced_yn;

	/**
	*	노출 여부 => Y(노출), N(비노출)
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String open_yn;

	/**
	*	등록일
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	버전 ID
	public Long getVersion_id() {
		return version_id;
 	}
	public void setVersion_id(Long version_id) {
		this.version_id = version_id;
 	}

	//	OS 타입
	public String getOs_type() {
		return os_type;
 	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
 	}

	//	버전 정보
	public String getVersion() {
		return version;
 	}
	public void setVersion(String version) {
		this.version = version;
 	}

	//	업데이트 설명
	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	//	강제 업데이트 여부
	public String getForced_yn() {
		return forced_yn;
 	}
	public void setForced_yn(String forced_yn) {
		this.forced_yn = forced_yn;
 	}

	//	노출 여부
	public String getOpen_yn() {
		return open_yn;
 	}
	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
 	}

	//	등록일
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

}
