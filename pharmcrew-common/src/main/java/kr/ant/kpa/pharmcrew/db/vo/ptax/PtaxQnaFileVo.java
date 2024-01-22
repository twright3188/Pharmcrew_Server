package kr.ant.kpa.pharmcrew.db.vo.ptax;

import java.io.Serializable;

/**
* 팜택스 => 팜텍스 1:1 문의 답변 첨부 파일
 */
public class PtaxQnaFileVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	문의 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_ptax_qna-qna_id) &&  NOT NULL 
	**/
	private Long qna_id;

	/**
	*	등록 타입 => Q:질문파일, A:답변 파일 - 질문3,답변3 으로 수정 되어서
	*	char, length : 1
	*	파라메터 옵션 :  Primary Key &&  NOT NULL 
	**/
	private String type_div;

	/**
	*	파일 ID
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_file-file_id) &&  NOT NULL 
	**/
	private Long file_id;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	문의 ID
	public Long getQna_id() {
		return qna_id;
 	}
	public void setQna_id(Long qna_id) {
		this.qna_id = qna_id;
 	}

	//	등록 타입
	public String getType_div() {
		return type_div;
 	}
	public void setType_div(String type_div) {
		this.type_div = type_div;
 	}

	//	파일 ID
	public Long getFile_id() {
		return file_id;
 	}
	public void setFile_id(Long file_id) {
		this.file_id = file_id;
 	}

}
