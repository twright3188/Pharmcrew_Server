package kr.ant.kpa.pharmcrew.resp.ptax;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 팜택스 알림 상세
 */
public class PtaxNewsDetail extends PtaxNews {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("상세 호출 URL")
	private String detail;


	@Description("담장자 이름")
	private String person;


	@Description("첨부파일 목록")
	private List<PtaxAttach> attach;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	public String getPerson() {
		return person;
 	}
	public void setPerson(String person) {
		this.person = person;
 	}

	public List<PtaxAttach> getAttach() {
		return attach;
 	}
	public void setAttach(List<PtaxAttach> attach) {
		this.attach = attach;
 	}
	public void addAttach(PtaxAttach attach) {
		if (this.attach == null) {
			this.attach = new ArrayList<PtaxAttach>();
		}
		this.attach.add(attach);
	}
}
