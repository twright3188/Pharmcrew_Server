package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.news.Qna;

/**
* 소식방
* 4.나의 문의 목록
 */
public class MyQnaResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("문의 목록")
	private List<Qna> qnas;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public MyQnaResp() {
		super();
	}
	public MyQnaResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Qna> getQnas() {
		return qnas;
 	}
	public void setQnas(List<Qna> qnas) {
		this.qnas = qnas;
 	}
	public void addQnas(Qna qna) {
		if (this.qnas == null) {
			this.qnas = new ArrayList<Qna>();
		}
		this.qnas.add(qna);
 	}

}
