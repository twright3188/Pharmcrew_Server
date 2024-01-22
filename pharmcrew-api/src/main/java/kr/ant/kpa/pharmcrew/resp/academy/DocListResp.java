package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.academy.Doc;

/**
* 학술정보
* 1. 문서 목록 조회
 */
public class DocListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("학술정보 PDF 목록")
	private List<Doc> docs;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public DocListResp() {
		super();
	}
	public DocListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Doc> getDocs() {
		return docs;
 	}
	public void setDocs(List<Doc> docs) {
		this.docs = docs;
 	}
	public void addDocs(Doc doc) {
		if (this.docs == null) {
			this.docs = new ArrayList<Doc>();
		}
		this.docs.add(doc);
 	}

}
