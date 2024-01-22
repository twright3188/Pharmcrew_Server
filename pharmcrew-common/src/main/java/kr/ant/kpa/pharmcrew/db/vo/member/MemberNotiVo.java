package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

/**
* 사용자 => 회원 알림
 */
public class MemberNotiVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	회원 알림 아이디 => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long noti_id;

	/**
	*	회원 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	푸시 ID => t_push-push_id
	*	long
	*	파라메터 옵션 :
	**/
	private Long push_id;

	/**
	*	제목
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	내용
	*	varchar, length : 500
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	타입 => PUSH_CATEGORY.java => AD: 광고, NT: 약사회 알림
	*	char, length : 2
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String cartegory_div;

	/**
	*	이동 타입 => PUSH_MOVE.java
	*	char, length : 2
	*	파라메터 옵션 :
	**/
	private String move_div;

	/**
	*	이동 URL => 클릭 시 이동할 URL
	*	varchar, length : 100
	*	파라메터 옵션 : 
	**/
	private String move_url;

	/**
	*	이동 아이디 => 클릭 시 이동할 상세 ID
	*	long
	*	파라메터 옵션 : 
	**/
	private Long move_id;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수신 일시 => 푸시를 받았을 때 클라이언트가 서버에 올려주는 시간
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date recv_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	회원 알림 아이디
	public Long getNoti_id() {
		return noti_id;
 	}
	public void setNoti_id(Long noti_id) {
		this.noti_id = noti_id;
 	}

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	푸시 ID
	public Long getPush_id() {
		return push_id;
 	}
	public void setPush_id(Long push_id) {
		this.push_id = push_id;
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

	//	타입
	public String getCartegory_div() {
		return cartegory_div;
 	}
	public void setCartegory_div(String cartegory_div) {
		this.cartegory_div = cartegory_div;
 	}

	//	이동 타입
	public String getMove_div() {
		return move_div;
 	}
	public void setMove_div(String move_div) {
		this.move_div = move_div;
 	}

	//	이동 URL
	public String getMove_url() {
		return move_url;
 	}
	public void setMove_url(String move_url) {
		this.move_url = move_url;
 	}

	//	이동 아이디
	public Long getMove_id() {
		return move_id;
 	}
	public void setMove_id(Long move_id) {
		this.move_id = move_id;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수신 일시
	public Date getRecv_dt() {
		return recv_dt;
 	}
	public void setRecv_dt(Date recv_dt) {
		this.recv_dt = recv_dt;
 	}

}
