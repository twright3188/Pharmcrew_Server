package kr.ant.kpa.pharmcrew.resp.news;

import java.util.ArrayList;
import java.util.List;

import com.bumdori.spring.annotation.Description;


/**
* 소식 상세 정보
 */
public class NewsDetail extends News {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("상세 호출 URL")
	private String detail;


	@Description("첨부파일 목록")
	private List<NewsAttach> attach;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	public List<NewsAttach> getAttach() {
		return attach;
 	}
	public void setAttach(List<NewsAttach> attach) {
		this.attach = attach;
 	}
	public void addAttach(NewsAttach attach) {
		if (this.attach == null) {
			this.attach = new ArrayList<NewsAttach>();
		}
		this.attach.add(attach);
	}

}
