package kr.ant.kpa.pharmcrew.db.vo.survey;

import java.io.Serializable;

/**
* 설문 => 설문 답변(객관식)
 */
public class SurveyAnswerChoiceVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

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
	*	문항 인덱스 => 1번 문제
	*	int, length : 2
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private Integer question_idx;

	/**
	*	문항 답변 번호 => 예시 번호 - 객관식 답변 번호
	*	int, length : 1
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private Integer answer_no;

	/**
	*	문항 답변 내용
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String answer;

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

	//	문항 답변 번호
	public Integer getAnswer_no() {
		return answer_no;
 	}
	public void setAnswer_no(Integer answer_no) {
		this.answer_no = answer_no;
 	}

	//	문항 답변 내용
	public String getAnswer() {
		return answer;
 	}
	public void setAnswer(String answer) {
		this.answer = answer;
 	}

	//	답변 수
	public Integer getAnswer_cnt() {
		return answer_cnt;
 	}
	public void setAnswer_cnt(Integer answer_cnt) {
		this.answer_cnt = answer_cnt;
 	}

}
