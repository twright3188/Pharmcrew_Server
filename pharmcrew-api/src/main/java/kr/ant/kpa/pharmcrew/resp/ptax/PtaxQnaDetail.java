package kr.ant.kpa.pharmcrew.resp.ptax;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.news.NewsAttach;

import java.util.ArrayList;
import java.util.List;


/**
* 팜택스 1:1 문의 상세
 */
public class PtaxQnaDetail extends PtaxQna {

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


	@Description("질문 첨부파일 목록")
	private List<PtaxAttach> qAttach;


	@Description("답변 첨부파일 목록")
	private List<PtaxAttach> rAttach;


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

	public List<PtaxAttach> getQAttach() {
		return qAttach;
 	}
	public void setQAttach(List<PtaxAttach> qAttach) {
		this.qAttach = qAttach;
 	}
	public void addQAttach(PtaxAttach attach) {
		if (this.qAttach == null) {
			this.qAttach = new ArrayList<PtaxAttach>();
		}
		this.qAttach.add(attach);
	}

	public List<PtaxAttach> getRAttach() {
		return rAttach;
 	}
	public void setRAttach(List<PtaxAttach> rAttach) {
		this.rAttach = rAttach;
 	}
	public void addRAttach(PtaxAttach attach) {
		if (this.rAttach == null) {
			this.rAttach = new ArrayList<PtaxAttach>();
		}
		this.rAttach.add(attach);
	}

	public String getRDate() {
		return rDate;
 	}
	public void setRDate(String rDate) {
		this.rDate = rDate;
 	}

}
