package kr.ant.kpa.pharmcrew.resp.ptax;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 팜택스 알림
 */
public class PtaxNews implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("소식 아이디")
	private Long id;


	@Description("공지 타입(A:전체알림, M:나의 알림)")
	private String type;


	@Description("첨부파일 여부 ( Y, N )")
	private String existAttach;


	@Description("소식 타이틀")
	private String title;


	@Description("등록일  (yyyy.MM.dd)")
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

	public String getType() {
		return type;
 	}
	public void setType(String type) {
		this.type = type;
 	}

	public String getExistAttach() {
		return existAttach;
 	}
	public void setExistAttach(String existAttach) {
		this.existAttach = existAttach;
 	}

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
