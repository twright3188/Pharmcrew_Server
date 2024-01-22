package kr.ant.kpa.pharmcrew.resp.root;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 버전정보
 */
public class Version implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("버전네임 (1.0.1)")
	private String version;


	@Description("강제 여부 (Y:강제, N:일반, X: 업데이트 없음)")
	private String forced;


	@Description("다운로드 링크")
	private String link;


	@Description("업데이트 내용 설명")
	private String desc;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getVersion() {
		return version;
 	}
	public void setVersion(String version) {
		this.version = version;
 	}

	public String getForced() {
		return forced;
 	}
	public void setForced(String forced) {
		this.forced = forced;
 	}

	public String getLink() {
		return link;
 	}
	public void setLink(String link) {
		this.link = link;
 	}

	public String getDesc() {
		return desc;
 	}
	public void setDesc(String desc) {
		this.desc = desc;
 	}

}
