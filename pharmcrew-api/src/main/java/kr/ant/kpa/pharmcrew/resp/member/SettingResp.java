package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Partner;

/**
* 사용자
* 5.설정 정보 요청
 */
public class SettingResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("사용자 파트너 설정 정보")
	private List<Partner> partners;


	@Description("현재 앱 사용 버전")
	private String version;


	@Description("푸시 설정 정보 - Y:수신, N:미수신")
	private String acceptPush;


	@Description("마케팅 sms 설정 정보")
	private String mSms;


	@Description("마케팅 푸시 설정 정보")
	private String mPush;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public SettingResp() {
		super();
	}
	public SettingResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Partner> getPartners() {
		return partners;
 	}
	public void setPartners(List<Partner> partners) {
		this.partners = partners;
 	}
	public void addPartners(Partner partner) {
		if (this.partners == null) {
			this.partners = new ArrayList<Partner>();
		}
		this.partners.add(partner);
 	}

	public String getVersion() {
		return version;
 	}
	public void setVersion(String version) {
		this.version = version;
 	}

	public String getAcceptPush() {
		return acceptPush;
 	}
	public void setAcceptPush(String acceptPush) {
		this.acceptPush = acceptPush;
 	}

	public String getMSms() {
		return mSms;
 	}
	public void setMSms(String mSms) {
		this.mSms = mSms;
 	}

	public String getMPush() {
		return mPush;
 	}
	public void setMPush(String mPush) {
		this.mPush = mPush;
 	}

}
