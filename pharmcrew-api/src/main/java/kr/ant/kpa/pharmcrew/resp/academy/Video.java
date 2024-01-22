package kr.ant.kpa.pharmcrew.resp.academy;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 학술정보 동영상 정보
 */
public class Video implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("동영상 아이디")
	private Long id;


	@Description("문서 타이틀")
	private String title;


	@Description("Youtube 동영상 아이디")
	private String youtube;


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

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getYoutube() {
		return youtube;
 	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
