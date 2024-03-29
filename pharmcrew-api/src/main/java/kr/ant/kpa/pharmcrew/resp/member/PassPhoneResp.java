package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 사용자
* 11. 임시비밀번호 발급 요청
 */
public class PassPhoneResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("휴대폰번호")
	private String phone;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PassPhoneResp() {
		super();
	}
	public PassPhoneResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getPhone() {
		return phone;
 	}
	public void setPhone(String phone) {
		this.phone = phone;
 	}

}
