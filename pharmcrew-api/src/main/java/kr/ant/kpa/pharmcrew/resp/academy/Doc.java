package kr.ant.kpa.pharmcrew.resp.academy;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 학술정보 PDF 정보
 */
public class Doc implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("문서 아이디")
	private Long id;


	@Description("문서 타이틀")
	private String title;


	@Description("문서 다운로드 경로")
	private String link;


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

	public String getLink() {
		return link;
 	}
	public void setLink(String link) {
		this.link = link;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
