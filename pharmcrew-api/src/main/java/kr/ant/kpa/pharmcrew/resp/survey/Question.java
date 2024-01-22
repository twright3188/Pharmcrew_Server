package kr.ant.kpa.pharmcrew.resp.survey;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 설문 질문 정보
 */
public class Question implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("설문 아이디")
	private Long id;


	@Description("문항 순서")
	private Integer idx;


	@Description("질문 내용")
	private String question;


	@Description("S(ubjective)주관식, O(bjective)객관식")
	private String type;


	@Description("참여한 전체 사용자 수")
	private Integer totalCount;


	@Description("내가 참여한 정답 정보")
	private String answer;


	@Description("객관식 답변 문항")
	private List<Choice> examples;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
 	}
	public void setId(Long id) {
		this.id = id;
 	}

	public Integer getIdx() {
		return idx;
 	}
	public void setIdx(Integer idx) {
		this.idx = idx;
 	}

	public String getQuestion() {
		return question;
 	}
	public void setQuestion(String question) {
		this.question = question;
 	}

	public String getType() {
		return type;
 	}
	public void setType(String type) {
		this.type = type;
 	}

	public Integer getTotalCount() {
		return totalCount;
 	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
 	}

	public String getAnswer() {
		return answer;
 	}
	public void setAnswer(String answer) {
		this.answer = answer;
 	}

	public List<Choice> getExamples() {
		return examples;
 	}
	public void setExamples(List<Choice> examples) {
		this.examples = examples;
 	}
	public void addExamples(Choice examples) {
		if (this.examples == null) {
			this.examples = new ArrayList<Choice>();
		}
		this.examples.add(examples);
 	}
}
