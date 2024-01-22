package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Partner;

/**
* 메인
* 6.메인 파트너 서비스 요청
 */
public class PartnersResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("null")
	private List<Partner> partners;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PartnersResp() {
		super();
	}
	public PartnersResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Partner> getPartners() {
		return partners;
 	}
	public void setPartners(List<Partner> partners) {
		this.partners = partners;
 	}
	public void addPartners(Partner partner) {
		if (this.partners == null) {
			this.partners = new ArrayList<Partner>();
		}
		this.partners.add(partner);
 	}

}
