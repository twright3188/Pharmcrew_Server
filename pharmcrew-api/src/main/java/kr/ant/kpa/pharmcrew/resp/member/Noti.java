package kr.ant.kpa.pharmcrew.resp.member;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 멤버 알림 정보
 */
public class Noti implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("회원 알림 아이디")
	private Long id;


	@Description("제목")
	private String title;


	@Description("내용")
	private String body;


	@Description("타입")
	private String category;


	@Description("이동 타입")
	private String moveType;


	@Description("이동 URL")
	private String moveUrl;


	@Description("이동 아이디")
	private Long moveId;


	@Description("등록 일시")
	private String date;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
 	}
	public void setId(Long id) {
		this.id = id;
 	}

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getBody() {
		return body;
 	}
	public void setBody(String body) {
		this.body = body;
 	}

	public String getCategory() {
		return category;
 	}
	public void setCategory(String category) {
		this.category = category;
 	}

	public String getMoveType() {
		return moveType;
 	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
 	}

	public String getMoveUrl() {
		return moveUrl;
 	}
	public void setMoveUrl(String moveUrl) {
		this.moveUrl = moveUrl;
 	}

	public Long getMoveId() {
		return moveId;
 	}
	public void setMoveId(Long moveId) {
		this.moveId = moveId;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
