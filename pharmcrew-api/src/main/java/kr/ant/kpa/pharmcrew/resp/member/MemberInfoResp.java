package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.member.Member;

/**
* 사용자
* 4.내 정보 요청
 */
public class MemberInfoResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("회원 정보")
	private Member member;


	@Description("교육 이수시간 (분단위)")
	private Integer eduTime;


	@Description("교육 미이수 시간 (분단위)")
	private Integer totalTime;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public MemberInfoResp() {
		super();
	}
	public MemberInfoResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Member getMember() {
		return member;
 	}
	public void setMember(Member member) {
		this.member = member;
 	}

	public Integer getEduTime() {
		return eduTime;
 	}
	public void setEduTime(Integer eduTime) {
		this.eduTime = eduTime;
 	}

	public Integer getTotalTime() {
		return totalTime;
 	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
 	}

}
