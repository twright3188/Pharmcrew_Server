package kr.ant.kpa.pharmcrew.db.vo.member;

import java.io.Serializable;

import java.util.Date;

import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;

/**
* 사용자 => 회원 파트너 서비스
 */
public class MemberPartnerVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 아이콘 이미지 파일
	 */
	private FileVo iconFile;
	public FileVo getIconFile() {
		return iconFile;
	}
	public void setIconFile(FileVo iconFile) {
		this.iconFile = iconFile;
	}
	
	/**
	 * 파트너 정보 
	 */
	private PartnersVo partnersVo;
	public PartnersVo getPartnersVo() {
		return partnersVo;
	}
	public void setPartnersVo(PartnersVo partnersVo) {
		this.partnersVo = partnersVo;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	회원 ID => 회원 아이디
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_member-member_id) &&  NOT NULL 
	**/
	private Long member_id;

	/**
	*	파트너 아이디 => 파트너 아이디
	*	long
	*	파라메터 옵션 :  Primary Key &&  Foreign Key = (t_partners-partner_id) &&  NOT NULL 
	**/
	private Long partner_id;

	/**
	*	등록일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	회원 ID
	public Long getMember_id() {
		return member_id;
 	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
 	}

	//	파트너 아이디
	public Long getPartner_id() {
		return partner_id;
 	}
	public void setPartner_id(Long partner_id) {
		this.partner_id = partner_id;
 	}

	//	등록일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

}
