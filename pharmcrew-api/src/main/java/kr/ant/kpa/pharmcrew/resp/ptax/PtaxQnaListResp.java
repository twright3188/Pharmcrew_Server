package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQna;

/**
* 팜택스
* 1. 1:1 문의 목록
 */
public class PtaxQnaListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("팜택스 1:1 문의 목록")
	private List<PtaxQna> qnas;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxQnaListResp() {
		super();
	}
	public PtaxQnaListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<PtaxQna> getQnas() {
		return qnas;
 	}
	public void setQnas(List<PtaxQna> qnas) {
		this.qnas = qnas;
 	}
	public void addQnas(PtaxQna ptaxqna) {
		if (this.qnas == null) {
			this.qnas = new ArrayList<PtaxQna>();
		}
		this.qnas.add(ptaxqna);
 	}

}
