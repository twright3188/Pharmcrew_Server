package kr.ant.kpa.pharmcrew.resp.survey;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 객관식 답변 문항
 */
public class Choice implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("답변 문항 번호")
	private Integer no;


	@Description("답변 예시")
	private String answer;


	@Description("질문 답변 비율 - 결과 노출 시 결과를 보여주는 것 ( 완료 한 사람에게 보여줄 정보)=>p39")
	private Integer result;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Integer getNo() {
		return no;
 	}
	public void setNo(Integer no) {
		this.no = no;
 	}

	public String getAnswer() {
		return answer;
 	}
	public void setAnswer(String answer) {
		this.answer = answer;
 	}

	public Integer getResult() {
		return result;
 	}
	public void setResult(Integer result) {
		this.result = result;
 	}

}
