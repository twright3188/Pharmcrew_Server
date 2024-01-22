package kr.ant.kpa.pharmcrew.db.vo.education;

import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;

import java.io.Serializable;
import java.util.List;

/**
* 교육 => 강의
 */
public class EducationCourseVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	private List<QrVo> qrs;
	public List<QrVo> getQrs() {
		return qrs;
	}
	public void setQrs(List<QrVo> qrs) {
		this.qrs = qrs;
	}

	/**
	 * withAttendCnt<br>
	 *     참여 수
	 */
	private Integer attendCnt;
	public Integer getAttendCnt() {
		return attendCnt;
	}
	public void setAttendCnt(Integer attendCnt) {
		this.attendCnt = attendCnt;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	강의 ID
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long course_id;

	/**
	*	교육 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_education-education_id) &&  NOT NULL 
	**/
	private Long education_id;

	/**
	*	교육 일 => 2일 이상인 경우 - 강의 교육 일
	*	int, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer days;

	/**
	*	인덱스 => 시간표 노출 인덱스 번호
	*	int, length : 2
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer idx;

	/**
	*	강의 제목
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	강사명
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String teacher;

	/**
	*	구분
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String type;

	/**
	*	강의실 => 강의실 직접 입력
	*	varchar, length : 20
	*	파라메터 옵션 : 
	**/
	private String room;

	/**
	*	시작 시간 => HHmm
	*	char, length : 4
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String start_time;

	/**
	*	종료 시간 => HHmm
	*	char, length : 4
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String end_time;

	/**
	*	교육 이수 시간(인증시간) => 분단위 이수시간
	*	int, length : 4
	*	파라메터 옵션 : 
	**/
	private Integer take_min;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	강의 ID
	public Long getCourse_id() {
		return course_id;
 	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
 	}

	//	교육 ID
	public Long getEducation_id() {
		return education_id;
 	}
	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
 	}

	//	교육 일
	public Integer getDays() {
		return days;
 	}
	public void setDays(Integer days) {
		this.days = days;
 	}

	//	인덱스
	public Integer getIdx() {
		return idx;
 	}
	public void setIdx(Integer idx) {
		this.idx = idx;
 	}

	//	강의 제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	강사명
	public String getTeacher() {
		return teacher;
 	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
 	}

	//	구분
	public String getType() {
		return type;
 	}
	public void setType(String type) {
		this.type = type;
 	}

	//	강의실
	public String getRoom() {
		return room;
 	}
	public void setRoom(String room) {
		this.room = room;
 	}

	//	시작 시간
	public String getStart_time() {
		return start_time;
 	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
 	}

	//	종료 시간
	public String getEnd_time() {
		return end_time;
 	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
 	}

	//	교육 이수 시간(인증시간)
	public Integer getTake_min() {
		return take_min;
 	}
	public void setTake_min(Integer take_min) {
		this.take_min = take_min;
 	}

}
