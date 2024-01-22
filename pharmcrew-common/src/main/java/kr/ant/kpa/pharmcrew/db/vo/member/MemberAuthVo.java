package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

/**
* 사용자 => 회원 인증 번호
 */
public class MemberAuthVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	멤버 아이디
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	인증 코드 => 난수 코드 4자리
	*	varchar, length : 10
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String auth_code;

	/**
	*	등록일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	멤버 아이디
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	인증 코드
	public String getAuth_code() {
		return auth_code;
 	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
 	}

	//	등록일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

}
