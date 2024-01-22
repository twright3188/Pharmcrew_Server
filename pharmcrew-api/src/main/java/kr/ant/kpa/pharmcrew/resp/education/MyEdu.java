package kr.ant.kpa.pharmcrew.resp.education;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 나의 교육 정보
 */
public class MyEdu implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("시작 인증 시간  (yyyy.MM.dd HH:mm)")
	private String certStartTime;


	@Description("종료 인증 시간   (yyyy.MM.dd HH:mm)")
	private String certEndTime;


	@Description("인정 시간(분)")
	private Integer certTime;


	@Description("교육 정보")
	private Edu education;


	@Description("강의 정보 - 교육을 강의 별로 들을 경우")
	private TimeTable course;


	@Description("승인대기, 미승인 등 상태값 - Y: 승인, N: 미승인, R: 승인대기")
	private String state;


	@Description("평가 별점")
	private Integer evalStar;


	@Description("평가 내용")
	private String evalBody;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getCertStartTime() {
		return certStartTime;
 	}
	public void setCertStartTime(String certStartTime) {
		this.certStartTime = certStartTime;
 	}

	public String getCertEndTime() {
		return certEndTime;
 	}
	public void setCertEndTime(String certEndTime) {
		this.certEndTime = certEndTime;
 	}

	public Integer getCertTime() {
		return certTime;
 	}
	public void setCertTime(Integer certTime) {
		this.certTime = certTime;
 	}

	public Edu getEducation() {
		return education;
 	}
	public void setEducation(Edu education) {
		this.education = education;
 	}

	public TimeTable getCourse() {
		return course;
 	}
	public void setCourse(TimeTable course) {
		this.course = course;
 	}

	public String getState() {
		return state;
 	}
	public void setState(String state) {
		this.state = state;
 	}

	public Integer getEvalStar() {
		return evalStar;
 	}
	public void setEvalStar(Integer evalStar) {
		this.evalStar = evalStar;
 	}

	public String getEvalBody() {
		return evalBody;
 	}
	public void setEvalBody(String evalBody) {
		this.evalBody = evalBody;
 	}

}
