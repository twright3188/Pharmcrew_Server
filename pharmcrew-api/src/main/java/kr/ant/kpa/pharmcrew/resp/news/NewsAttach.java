package kr.ant.kpa.pharmcrew.resp.news;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 소식방 상세 첨부파일
 */
public class NewsAttach implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("첨부 파일 이름")
	private String name;


	@Description("첨부 파일 다운로드 링크")
	private String link;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	public String getLink() {
		return link;
 	}
	public void setLink(String link) {
		this.link = link;
 	}

}
