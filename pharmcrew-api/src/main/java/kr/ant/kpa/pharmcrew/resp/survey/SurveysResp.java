package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.survey.Survey;

/**
* 설문
* 1.설문 목록
 */
public class SurveysResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("설문 목록")
	private List<Survey> surveys;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public SurveysResp() {
		super();
	}
	public SurveysResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Survey> getSurveys() {
		return surveys;
 	}
	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
 	}
	public void addSurveys(Survey survey) {
		if (this.surveys == null) {
			this.surveys = new ArrayList<Survey>();
		}
		this.surveys.add(survey);
 	}

}
