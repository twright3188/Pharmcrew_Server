package kr.ant.kpa.pharmcrew.db.vo.ptax;

import java.io.Serializable;

/**
* 팜택스 => 팜택스 공지 첨부 파일
 */
public class PtaxNoticeFileVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	공지 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_ptax_notice-notice_id) &&  NOT NULL 
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
