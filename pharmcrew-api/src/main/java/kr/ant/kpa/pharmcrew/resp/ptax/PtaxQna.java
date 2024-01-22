package kr.ant.kpa.pharmcrew.resp.ptax;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 팜택스 1:1 문의
 */
public class PtaxQna implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("질문 아이디")
	private Long id;


	@Description("질문 타이틀")
	private String title;


	@Description("답변 상태 - '답변대기', '답변완료'")
	private String state;


	@Description("첨부파일 유무 Y, N")
	private String existAttach;


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

	public String getState() {
		return state;
 	}
	public void setState(String state) {
		this.state = state;
 	}

	public String getExistAttach() {
		return existAttach;
 	}
	public void setExistAttach(String existAttach) {
		this.existAttach = existAttach;
 	}

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
