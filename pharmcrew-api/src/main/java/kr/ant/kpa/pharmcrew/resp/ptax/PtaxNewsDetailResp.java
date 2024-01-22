package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsDetail;

/**
* 팜택스
* 5. 공지 상세
 */
public class PtaxNewsDetailResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("팜택스 공지 상세")
	private PtaxNewsDetail detail;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxNewsDetailResp() {
		super();
	}
	public PtaxNewsDetailResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxNewsDetail getDetail() {
		return detail;
 	}
	public void setDetail(PtaxNewsDetail detail) {
		this.detail = detail;
 	}

}
