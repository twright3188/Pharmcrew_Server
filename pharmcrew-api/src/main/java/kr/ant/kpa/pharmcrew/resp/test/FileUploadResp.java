package kr.ant.kpa.pharmcrew.resp.test;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 테스트
* 파일 등록하기 
 */
public class FileUploadResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("파일 등록 후 파일 아이디 확인 ")
	private Long fildId;

	@Description("변경된 이미지 파일 url")
	private String imgUrl;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public FileUploadResp() {
		super();
	}
	public FileUploadResp(VALIDATION validation) {
		super(validation);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getFildId() {
		return fildId;
	}
	public void setFildId(Long fildId) {
		this.fildId = fildId;
	}

	public String getImgUrl() {
		return imgUrl;
 	}
	public void setImgUrl(String ImgUrl) {
		this.imgUrl = ImgUrl;
 	}
	
}
