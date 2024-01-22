package kr.ant.kpa.pharmcrew.db.vo.common;

import java.io.Serializable;

/**
* 기본 => QR
 */
public class QrVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	QR ID
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long qr_id;

	/**
	*	타입 => QR.java - O: 하나, S: 시작, E: 종료
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String type;

	/**
	*	교육 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_education-education_id) &&  NOT NULL 
	**/
	private Long education_id;

	/**
	*	강의 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_education_course-course_id) 
	**/
	private Long course_id;

	/**
	*	코드
	*	char, length : 36
	*	파라메터 옵션 :  NOT NULL &&  UNIQUE 
	**/
	private String code;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	QR ID
	public Long getQr_id() {
		return qr_id;
 	}
	public void setQr_id(Long qr_id) {
		this.qr_id = qr_id;
 	}

	//	타입
	public String getType() {
		return type;
 	}
	public void setType(String type) {
		this.type = type;
 	}

	//	교육 ID
	public Long getEducation_id() {
		return education_id;
 	}
	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
 	}

	//	강의 ID
	public Long getCourse_id() {
		return course_id;
 	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
 	}

	//	코드
	public String getCode() {
		return code;
 	}
	public void setCode(String code) {
		this.code = code;
 	}

}
