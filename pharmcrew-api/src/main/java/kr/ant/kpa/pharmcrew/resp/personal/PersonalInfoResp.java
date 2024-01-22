package kr.ant.kpa.pharmcrew.resp.personal;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.db.vo.personal.PersonalInformationVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

/**
* 사용자
* 4.내 정보 요청
 */
public class PersonalInfoResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("회원 정보")
	private PersonalInformationVo personal;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PersonalInfoResp() {
		super();
	}
	public PersonalInfoResp(VALIDATION validation) {
		super(validation);
	}
	
	public void setPersonal(PersonalInformationVo info) {
		this.personal = info;
	}
	
	public PersonalInformationVo getPersonal() {
		return this.personal;
	}

}
