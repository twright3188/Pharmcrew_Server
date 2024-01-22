package kr.ant.kpa.pharmcrew.resp.education;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 교육 정보
 */
public class Edu implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("교육 아이디")
	private Long id;


	@Description("주관 부서")
	private String organize;


	@Description("교육명")
	private String title;


	@Description("시작일 (yyyy.MM.dd)")
	private String startDate;


	@Description("종료일 (yyyy.MM.dd)")
	private String endDate;


	@Description("시작 시간 (HH:mm)")
	private String startTime;


	@Description("종료 시간 (HH:mm)")
	private String endTime;


	@Description("장소")
	private String address;


	@Description("문의처")
	private String contact;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
 	}
	public void setId(Long id) {
		this.id = id;
 	}

	public String getOrganize() {
		return organize;
 	}
	public void setOrganize(String organize) {
		this.organize = organize;
 	}

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getStartDate() {
		return startDate;
 	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
 	}

	public String getEndDate() {
		return endDate;
 	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
 	}

	public String getStartTime() {
		return startTime;
 	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
 	}

	public String getEndTime() {
		return endTime;
 	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
 	}

	public String getAddress() {
		return address;
 	}
	public void setAddress(String address) {
		this.address = address;
 	}

	public String getContact() {
		return contact;
 	}
	public void setContact(String contact) {
		this.contact = contact;
 	}

}
