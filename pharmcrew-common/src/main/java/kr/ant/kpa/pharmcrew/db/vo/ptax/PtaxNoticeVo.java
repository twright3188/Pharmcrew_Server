package kr.ant.kpa.pharmcrew.db.vo.ptax;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
* 팜택스 => 팜택스 공지
 */
public class PtaxNoticeVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * target_pharm_name를 null로 설정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.PtaxDao#updatePtaxNotice(PtaxNoticeVo)}
	 */
	private Boolean targetPharmNameNull;
	public Boolean getTargetPharmNameNull() {
		return targetPharmNameNull;
	}
	public void setTargetPharmNameNull(Boolean targetPharmNameNull) {
		this.targetPharmNameNull = targetPharmNameNull;
	}

	/**
	 * target_member_id를 null로 설정
	 * {@link kr.ant.kpa.pharmcrew.db.dao.PtaxDao#updatePtaxNotice(PtaxNoticeVo)}
	 */
	private Boolean targetMemberIdNull;
	public Boolean getTargetMemberIdNull() {
		return targetMemberIdNull;
	}
	public void setTargetMemberIdNull(Boolean targetMemberIdNull) {
		this.targetMemberIdNull = targetMemberIdNull;
	}

	private MemberVo member;
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}

	private PushVo push;
	public PushVo getPush() {
		return push;
	}
	public void setPush(PushVo push) {
		this.push = push;
	}

	private AdminVo regAdmin;
	public AdminVo getRegAdmin() {
		return regAdmin;
	}
	public void setRegAdmin(AdminVo regAdmin) {
		this.regAdmin = regAdmin;
	}

	private List<FileVo> attachFiles;
	public List<FileVo> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<FileVo> attachFiles) {
		this.attachFiles = attachFiles;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	공지 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long notice_id;

	/**
	*	제목
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	내용
	*	mediumtext
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	수신 대상 타입 => null: 전체, M(ember): 회원, G(uest): 비회원
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String target_div;

	/**
	*	수신 대상 약국 => 수신 대상이 약국일 경우 약국의 이름(?)
	*	varchar, length : 20
	*	파라메터 옵션 : 
	**/
	private String target_pharm_name;
	private Long target_member_id;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

	/**
	*	조회 수
	*	long
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Long view_cnt;

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

	//	공지 ID
	public Long getNotice_id() {
		return notice_id;
 	}
	public void setNotice_id(Long notice_id) {
		this.notice_id = notice_id;
 	}

	//	제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	내용
	public String getBody() {
		return body;
 	}
	public void setBody(String body) {
		this.body = body;
 	}

	//	수신 대상 타입
	public String getTarget_div() {
		return target_div;
	}
	public void setTarget_div(String target_div) {
		this.target_div = target_div;
	}

	//	수신 대상 약국
	public String getTarget_pharm_name() {
		return target_pharm_name;
	}
	public void setTarget_pharm_name(String target_pharm_name) {
		this.target_pharm_name = target_pharm_name;
	}

	public Long getTarget_member_id() {
		return target_member_id;
	}
	public void setTarget_member_id(Long target_member_id) {
		this.target_member_id = target_member_id;
	}

	//	노출 여부
	public String getIs_open() {
		return is_open;
 	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
 	}

	//	조회 수
	public Long getView_cnt() {
		return view_cnt;
 	}
	public void setView_cnt(Long view_cnt) {
		this.view_cnt = view_cnt;
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
