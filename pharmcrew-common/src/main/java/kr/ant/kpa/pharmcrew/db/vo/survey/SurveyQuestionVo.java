package kr.ant.kpa.pharmcrew.db.vo.survey;

import java.io.Serializable;
import java.util.List;

/**
* 설문 => 설문 문항
 */
public class SurveyQuestionVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 답안 리스트
	 */
	private List<SurveyAnswerChoiceVo> answers;
	public List<SurveyAnswerChoiceVo> getAnswers() {
		return answers;
	}
	public void setAnswers(List<SurveyAnswerChoiceVo> answers) {
		this.answers = answers;
	}

	/**
	 * 나의 정답 정보 
	 */
	private String myAnswer;
	public String getMyAnswer() {
		return myAnswer;
	}
	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	
	/**
	*	설문 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_survey-survey_id) &&  NOT NULL 
	**/
	private Long survey_id;

	/**
	*	문항 인덱스 => 99 번까지 유효하게 2자리로 구성
	*	int, length : 2
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private Integer question_idx;

	/**
	*	질문
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String question;

	/**
	*	답변 타입 => SURVEY_ANSWER.java => S(ubjective)주관식, O(bjective)객관식
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String answer_div;

	/**
	*	답변 수
	*	int, length : 11
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer answer_cnt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	설문 ID
	public Long getSurvey_id() {
		return survey_id;
 	}
	public void setSurvey_id(Long survey_id) {
		this.survey_id = survey_id;
 	}

	//	문항 인덱스
	public Integer getQuestion_idx() {
		return question_idx;
 	}
	public void setQuestion_idx(Integer question_idx) {
		this.question_idx = question_idx;
 	}

	//	질문
	public String getQuestion() {
		return question;
 	}
	public void setQuestion(String question) {
		this.question = question;
 	}

	//	답변 타입
	public String getAnswer_div() {
		return answer_div;
 	}
	public void setAnswer_div(String answer_div) {
		this.answer_div = answer_div;
 	}

	//	답변 수
	public Integer getAnswer_cnt() {
		return answer_cnt;
 	}
	public void setAnswer_cnt(Integer answer_cnt) {
		this.answer_cnt = answer_cnt;
 	}

}
