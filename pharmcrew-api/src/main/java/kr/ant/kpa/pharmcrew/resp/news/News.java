package kr.ant.kpa.pharmcrew.resp.news;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 소식 리스트 정보
 */
public class News implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("소식 아이디")
	private Long id;


	@Description("공지 타입(일반약사회,지부공지,분회공지)")
	private String type;


	@Description("소식 타이틀")
	private String title;


	@Description("상단고정 여부")
	private String isTop;


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

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getIsTop() {
		return isTop;
 	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
