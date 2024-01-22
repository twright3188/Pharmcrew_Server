package kr.ant.kpa.pharmcrew.resp.root;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 파트너 서비스 정보
 */
public class Partner implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("파트너 아이디")
	private Long id;


	@Description("노출 순서")
	private Integer idx;


	@Description("파트너 서비스 이름")
	private String name;


	@Description("파트너 서비스 부가 정보")
	private String detail;


	@Description("아이콘 이미지 URL")
	private String image;


	@Description("클릭시 이동할 연동 링크")
	private String link;


	@Description("파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)")
	private String isUse;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
 	}
	public void setId(Long id) {
		this.id = id;
 	}

	public Integer getIdx() {
		return idx;
 	}
	public void setIdx(Integer idx) {
		this.idx = idx;
 	}

	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	public String getDetail() {
		return detail;
 	}
	public void setDetail(String detail) {
		this.detail = detail;
 	}

	public String getImage() {
		return image;
 	}
	public void setImage(String image) {
		this.image = image;
 	}

	public String getLink() {
		return link;
 	}
	public void setLink(String link) {
		this.link = link;
 	}

	public String getIsUse() {
		return isUse;
 	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
 	}

}
