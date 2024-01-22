package kr.ant.kpa.pharmcrew.resp.root;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 한줄 공지 정보
 */
public class Notice implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("한줄 공지 아이디 - 소식방 아이디")
	private Long id;


	@Description("노출할 텍스트 정보 - 소식방 타이틀")
	private String title;


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

}
