package kr.ant.kpa.pharmcrew.db.vo.push;

import java.io.Serializable;

import java.util.Date;

/**
* 푸시 => 푸시 토큰
 */
public class PushTokenVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	사용자 구분 => M(t_member): 약사
	*	char
	*	파라메터 옵션 :  Primary Key 
	**/
	private String user_div;

	/**
	*	사용자 ID => user_div가 M일 때 t_member-member_id
	*	long
	*	파라메터 옵션 :  Primary Key 
	**/
	private Long user_id;

	/**
	*	푸시 토큰
	*	varchar, length : 300
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private String token;

	/**
	*	운영체제 구분 => I: iOS, A: Android
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String os_div;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	사용자 구분
	public String getUser_div() {
		return user_div;
 	}
	public void setUser_div(String user_div) {
		this.user_div = user_div;
 	}

	//	사용자 ID
	public Long getUser_id() {
		return user_id;
 	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
 	}

	//	푸시 토큰
	public String getToken() {
		return token;
 	}
	public void setToken(String token) {
		this.token = token;
 	}

	//	운영체제 구분
	public String getOs_div() {
		return os_div;
 	}
	public void setOs_div(String os_div) {
		this.os_div = os_div;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

}
