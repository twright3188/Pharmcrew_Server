package kr.ant.kpa.pharmcrew.resp.root;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 광고 정보
 */
public class Advertise implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("배너 아이디")
	private Long id;


	@Description("노출 순서")
	private Integer idx;


	@Description("이미지 URL")
	private String image;


	@Description("이동 타입")
	private String moveType;


	@Description("이동 URL")
	private String moveUrl;


	@Description("이동 아이디")
	private Long moveId;


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

	public String getImage() {
		return image;
 	}
	public void setImage(String image) {
		this.image = image;
 	}

	public String getMoveType() {
		return moveType;
 	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
 	}

	public String getMoveUrl() {
		return moveUrl;
 	}
	public void setMoveUrl(String moveUrl) {
		this.moveUrl = moveUrl;
 	}

	public Long getMoveId() {
		return moveId;
 	}
	public void setMoveId(Long moveId) {
		this.moveId = moveId;
 	}

}
