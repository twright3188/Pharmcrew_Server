package kr.ant.kpa.pharmcrew.db.vo.news;

import java.io.Serializable;

/**
* 소식 => 공지 첨부 파일
 */
public class NoticeFileVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	*	이름
	**/
	private String attach_name;
	public String getAttach_name() {
		return attach_name;
	}
	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}
	
	/**
	*	원본 파일명 => 등록한 파일 이름
	**/
	private String attach_org_name;
	public String getAttach_org_name() {
		return attach_org_name;
	}
	public void setAttach_org_name(String attach_org_name) {
		this.attach_org_name = attach_org_name;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	공지 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_notice-notice_id) &&  NOT NULL 
	**/
	private Long notice_id;

	/**
	*	파일 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long file_id;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	공지 ID
	public Long getNotice_id() {
		return notice_id;
 	}
	public void setNotice_id(Long notice_id) {
		this.notice_id = notice_id;
 	}

	//	파일 ID
	public Long getFile_id() {
		return file_id;
 	}
	public void setFile_id(Long file_id) {
		this.file_id = file_id;
 	}

}
