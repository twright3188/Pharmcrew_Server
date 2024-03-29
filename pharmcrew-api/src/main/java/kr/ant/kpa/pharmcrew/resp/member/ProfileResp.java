package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 사용자
* 8.프로필 이미지 등록
 */
public class ProfileResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("변경된 이미지 파일 url")
	private String profileImgUrl;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public ProfileResp() {
		super();
	}
	public ProfileResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getProfileImgUrl() {
		return profileImgUrl;
 	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
 	}

}
