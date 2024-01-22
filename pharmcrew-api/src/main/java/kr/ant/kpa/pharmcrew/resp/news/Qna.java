package kr.ant.kpa.pharmcrew.resp.news;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 나의 질문 정보
 */
public class Qna implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("질문 아이디")
	private Long id;


	@Description("질문 등록 카테고리 - E: 교육, N : 소식")
	private String category;


	@Description("교육이나 소식 타겟 아이디 -> 향후 교육이나 소식으로 이동 시 필요할까 해서 추가해 봄")
	private Long target;


	@Description("질문 타이틀")
	private String title;


	@Description("답변 상태 - '답변대기', '답변완료'")
	private String state;


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

	public String getCategory() {
		return category;
 	}
	public void setCategory(String category) {
		this.category = category;
 	}

	public Long getTarget() {
		return target;
 	}
	public void setTarget(Long target) {
		this.target = target;
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

	public String getDate() {
		return date;
 	}
	public void setDate(String date) {
		this.date = date;
 	}

}
