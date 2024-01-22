package kr.ant.kpa.pharmcrew.db.vo.common;

import java.io.Serializable;

import java.util.Date;

/**
* 기본 => 조직
 */
public class OrganizeVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * withDepthNames
	 */
	private String d1Name;
	public String getD1Name() {
		return d1Name;
	}
	public void setD1Name(String d1Name) {
		this.d1Name = d1Name;
	}
	/**
	 * withDepthNames
	 */
	private String d2Name;
	public String getD2Name() {
		return d2Name;
	}
	public void setD2Name(String d2Name) {
		this.d2Name = d2Name;
	}
	/**
	 * withDepthNames
	 */
	private String d3Name;
	public String getD3Name() {
		return d3Name;
	}
	public void setD3Name(String d3Name) {
		this.d3Name = d3Name;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	조직 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long organize_id;

	/**
	*	조직 레벨1 ID => 1단계 레벨 조직 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) &&  NOT NULL 
	**/
	private Long organize_d1_id;

	/**
	*	조직 레벨2 ID => 2단계 레벨 조직 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_d2_id;

	/**
	*	조직 레벨3 ID => 3단계 레벨 조직 아이디
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_d3_id;

	/**
	*	조직 레벨
	*	int, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Integer depth;

	/**
	*	조직명
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String name;

	/**
	*	등록자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) &&  NOT NULL 
	**/
	private Long reg_id;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수정자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) 
	**/
	private Long mod_id;

	/**
	*	수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	조직 레벨1 ID
	public Long getOrganize_d1_id() {
		return organize_d1_id;
 	}
	public void setOrganize_d1_id(Long organize_d1_id) {
		this.organize_d1_id = organize_d1_id;
 	}

	//	조직 레벨2 ID
	public Long getOrganize_d2_id() {
		return organize_d2_id;
 	}
	public void setOrganize_d2_id(Long organize_d2_id) {
		this.organize_d2_id = organize_d2_id;
 	}

	//	조직 레벨3 ID
	public Long getOrganize_d3_id() {
		return organize_d3_id;
 	}
	public void setOrganize_d3_id(Long organize_d3_id) {
		this.organize_d3_id = organize_d3_id;
 	}

	//	조직 레벨
	public Integer getDepth() {
		return depth;
 	}
	public void setDepth(Integer depth) {
		this.depth = depth;
 	}

	//	조직명
	public String getName() {
		return name;
 	}
	public void setName(String name) {
		this.name = name;
 	}

	//	등록자 ID
	public Long getReg_id() {
		return reg_id;
 	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수정자 ID
	public Long getMod_id() {
		return mod_id;
 	}
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
 	}

	//	수정 일시
	public Date getMod_dt() {
		return mod_dt;
 	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
 	}

}
