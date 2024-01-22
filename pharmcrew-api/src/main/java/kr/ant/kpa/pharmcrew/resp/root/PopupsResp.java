package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Advertise;

/**
* 메인
* 4.메인 팝업 정보 요청
 */
public class PopupsResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("null")
	private List<Advertise> popups;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PopupsResp() {
		super();
	}
	public PopupsResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Advertise> getPopups() {
		return popups;
 	}
	public void setPopups(List<Advertise> popups) {
		this.popups = popups;
 	}
	public void addPopups(Advertise advertise) {
		if (this.popups == null) {
			this.popups = new ArrayList<Advertise>();
		}
		this.popups.add(advertise);
 	}

}
