package kr.ant.kpa.pharmcrew.resp.survey;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 설문 기본 정보
 */
public class Survey implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("설문 아이디")
	private Long id;


	@Description("제목")
	private String title;


	@Description("설문 설명")
	private String detail;


	@Description("답변 노출 여부 - Y:결과 노출, N:결과 미노출")
	private String showResult;


	@Description("설문 참여 여부 - Y:설문 참여, N:설문 미참여")
	private String isComplete;


	@Description("설문 시작일  (yyyy.MM.dd)")
	private String startDate;


	@Description("설문 종료일  (yyyy.MM.dd)")
	private String endDate;


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

	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	public String getShowResult() {
		return showResult;
 	}
	public void setShowResult(String showResult) {
		this.showResult = showResult;
 	}

	public String getIsComplete() {
		return isComplete;
 	}
	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
 	}

	public String getStartDate() {
		return startDate;
 	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
 	}

	public String getEndDate() {
		return endDate;
 	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
 	}

}
