package kr.ant.kpa.pharmcrew.resp.session;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.member.Member;

/**
* 세션
* 1.로그인
 */
public class LoginResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("회원 정보")
	private Member member;


	@Description("교육 시작 시간 - 교육 진행 중인 경우")
	private Long eduStartTime;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public LoginResp() {
		super();
	}
	public LoginResp(VALIDATION validation) {
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

	public Long getEduStartTime() {
		return eduStartTime;
 	}
	public void setEduStartTime(Long eduStartTime) {
		this.eduStartTime = eduStartTime;
 	}

}
