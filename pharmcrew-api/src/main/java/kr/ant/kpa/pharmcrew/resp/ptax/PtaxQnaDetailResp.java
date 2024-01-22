package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaDetail;

/**
* 팜택스
* 2. 1:1 문의 상세
 */
public class PtaxQnaDetailResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("팜택스 1:1 문의 답변 상세")
	private PtaxQnaDetail detail;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxQnaDetailResp() {
		super();
	}
	public PtaxQnaDetailResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxQnaDetail getDetail() {
		return detail;
 	}
	public void setDetail(PtaxQnaDetail detail) {
		this.detail = detail;
 	}

}
