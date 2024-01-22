package kr.ant.kpa.pharmcrew.db.vo.common;

import java.io.Serializable;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;

@Builder
@Data
public class FileVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	파일 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long file_id;

	/**
	*	이름
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String name;

	/**
	*	원본 파일명 => 등록한 파일 이름
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String org_name;

	/**
	*	사이즈
	*	long
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Long size;

	/**
	*	저장 경로
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String path;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	파일 ID
	public Long getFile_id() {
		return file_id;
 	}
	public void setFile_id(Long file_id) {
		this.file_id = file_id;
 	}

	//	이름
	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	//	원본 파일명
	public String getOrg_name() {
		return org_name;
 	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
 	}

	//	사이즈
	public Long getSize() {
		return size;
 	}
	public void setSize(Long size) {
		this.size = size;
 	}

	//	저장 경로
	public String getPath() {
		return path;
 	}
	public void setPath(String path) {
		this.path = path;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수정 일시
	public Date getMod_dt() {
		return mod_dt;
 	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
 	}

}
