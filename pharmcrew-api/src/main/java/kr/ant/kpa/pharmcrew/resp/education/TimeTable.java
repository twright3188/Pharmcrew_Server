package kr.ant.kpa.pharmcrew.resp.education;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import java.util.ArrayList;
import java.util.List;


/**
* 교육 시간표(강의) 정보
 */
public class TimeTable implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("강의 아이디")
	private Long id;


	@Description("강의 날짜")
	private Integer days;


	@Description("시간표 순서")
	private Integer idx;


	@Description("강의 제목")
	private String title;


	@Description("강사명")
	private String teacher;


	@Description("구분")
	private String type;


	@Description("강의실")
	private String room;


	@Description("시작 시간 (HH:mm)")
	private String startTime;


	@Description("종료 시간 (HH:mm)")
	private String endTime;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Long getId() {
		return id;
 	}
	public void setId(Long id) {
		this.id = id;
 	}

	public Integer getDays() {
		return days;
 	}
	public void setDays(Integer days) {
		this.days = days;
 	}

	public Integer getIdx() {
		return idx;
 	}
	public void setIdx(Integer idx) {
		this.idx = idx;
 	}

	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	public String getTeacher() {
		return teacher;
 	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
 	}

	public String getType() {
		return type;
 	}
	public void setType(String type) {
		this.type = type;
 	}

	public String getRoom() {
		return room;
 	}
	public void setRoom(String room) {
		this.room = room;
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

}
