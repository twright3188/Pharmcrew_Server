package kr.ant.kpa.pharmcrew.resp.education;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.education.MyEdu;

/**
* 교육
* 3.나의 교육 목록
 */
public class EduMineListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("나의 교육 정보 목록")
	private List<MyEdu> educations;


	@Description("현재 진행 중인 교육 정보")
	private MyEdu nowEdu;


	@Description("검색 년도의 교육 이수시간 (분단위)")
	private Integer eduTime;


	@Description("겸색 년도의 교육 미이수 시간 (분단위)")
	private Integer totalTime;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public EduMineListResp() {
		super();
	}
	public EduMineListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<MyEdu> getEducations() {
		return educations;
 	}
	public void setEducations(List<MyEdu> educations) {
		this.educations = educations;
 	}
	public void addEducations(MyEdu myedu) {
		if (this.educations == null) {
			this.educations = new ArrayList<MyEdu>();
		}
		this.educations.add(myedu);
 	}

	public MyEdu getNowEdu() {
		return nowEdu;
 	}
	public void setNowEdu(MyEdu nowEdu) {
		this.nowEdu = nowEdu;
 	}

	public Integer getEduTime() {
		return eduTime;
 	}
	public void setEduTime(Integer eduTime) {
		this.eduTime = eduTime;
 	}

	public Integer getTotalTime() {
		return totalTime;
 	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
 	}

}
