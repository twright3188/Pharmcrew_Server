package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.academy.Doc;

/**
* 학술정보
* 2. 문서 상세 조회
 */
public class DocDetailResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("학술 정보 PDF 정보")
	private Doc doc;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public DocDetailResp() {
		super();
	}
	public DocDetailResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Doc getDoc() {
		return doc;
 	}
	public void setDoc(Doc doc) {
		this.doc = doc;
 	}

}