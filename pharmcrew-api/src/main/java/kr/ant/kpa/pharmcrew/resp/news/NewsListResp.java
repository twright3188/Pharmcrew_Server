package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.news.News;

/**
* 소식방
* 1.소식방 목록
 */
public class NewsListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("소식방 목록")
	private List<News> news;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public NewsListResp() {
		super();
	}
	public NewsListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<News> getNews() {
		return news;
 	}
	public void setNews(List<News> news) {
		this.news = news;
 	}
	public void addNews(News news) {
		if (this.news == null) {
			this.news = new ArrayList<News>();
		}
		this.news.add(news);
 	}

}
