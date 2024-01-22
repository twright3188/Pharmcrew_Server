package kr.ant.kpa.pharmcrew.resp.survey;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.survey.Question;
import kr.ant.kpa.pharmcrew.resp.survey.Survey;

/**
* 설문
* 2.설문 문항 목록 요청
 */
public class QuestionsResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("설문 문항 목록")
	private List<Question> questions;


	@Description("설문정보")
	private Survey survey;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public QuestionsResp() {
		super();
	}
	public QuestionsResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Question> getQuestions() {
		return questions;
 	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
 	}
	public void addQuestions(Question question) {
		if (this.questions == null) {
			this.questions = new ArrayList<Question>();
		}
		this.questions.add(question);
 	}

	public Survey getSurvey() {
		return survey;
 	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
 	}

}
