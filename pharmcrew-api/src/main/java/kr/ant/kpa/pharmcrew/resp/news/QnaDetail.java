package kr.ant.kpa.pharmcrew.resp.news;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 나의 질문 상세
 */
public class QnaDetail extends Qna {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("질문 상세")
	private String qBody;


	@Description("답변 타이틀")
	private String rTitle;


	@Description("답변 상세 내용")
	private String rBody;


	@Description("첨부파일 이름")
	private String attachName;


	@Description("첨부파일 다운로드 경로")
	private String attachPath;


	@Description("답변 등록일  (yyyy.MM.dd)")
	private String rDate;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getQBody() {
		return qBody;
 	}
	public void setQBody(String qBody) {
		this.qBody = qBody;
 	}

	public String getRTitle() {
		return rTitle;
 	}
	public void setRTitle(String rTitle) {
		this.rTitle = rTitle;
 	}

	public String getRBody() {
		return rBody;
 	}
	public void setRBody(String rBody) {
		this.rBody = rBody;
 	}

	public String getAttachName() {
		return attachName;
 	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
 	}

	public String getAttachPath() {
		return attachPath;
 	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
 	}

	public String getRDate() {
		return rDate;
 	}
	public void setRDate(String rDate) {
		this.rDate = rDate;
 	}

}
