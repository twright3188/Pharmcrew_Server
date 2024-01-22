package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.education.Edu;

/**
* 교육
* 1.교육 목록
 */
public class EduListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("교육 목록 정보")
	private List<Edu> educations;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public EduListResp() {
		super();
	}
	public EduListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Edu> getEducations() {
		return educations;
 	}
	public void setEducations(List<Edu> educations) {
		this.educations = educations;
 	}
	public void addEducations(Edu edu) {
		if (this.educations == null) {
			this.educations = new ArrayList<Edu>();
		}
		this.educations.add(edu);
 	}

}
