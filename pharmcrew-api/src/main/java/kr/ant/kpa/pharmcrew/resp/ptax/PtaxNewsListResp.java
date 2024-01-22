package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNews;

/**
* 팜택스
* 4. 공지 목록
 */
public class PtaxNewsListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("팜택스 공지 목록")
	private List<PtaxNews> news;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxNewsListResp() {
		super();
	}
	public PtaxNewsListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<PtaxNews> getNews() {
		return news;
 	}
	public void setNews(List<PtaxNews> news) {
		this.news = news;
 	}
	public void addNews(PtaxNews ptaxnews) {
		if (this.news == null) {
			this.news = new ArrayList<PtaxNews>();
		}
		this.news.add(ptaxnews);
 	}

}
