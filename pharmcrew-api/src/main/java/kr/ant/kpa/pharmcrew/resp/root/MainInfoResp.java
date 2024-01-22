package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Advertise;
import kr.ant.kpa.pharmcrew.resp.root.Advertise;
import kr.ant.kpa.pharmcrew.resp.root.Partner;
import kr.ant.kpa.pharmcrew.resp.root.Notice;

/**
* 메인
* 3.메인 정보 요청
 */
public class MainInfoResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("팝업 광고 목록")
	private List<Advertise> popups;


	@Description("배너 광고 목록")
	private List<Advertise> banners;


	@Description("파트너서비스 목록")
	private List<Partner> partners;


	@Description("한줄 공지 목록")
	private List<Notice> notices;


	@Description("읽지 않은 알림 숫자")
	private Integer unReadNotiCount;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public MainInfoResp() {
		super();
	}
	public MainInfoResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Advertise> getPopups() {
		return popups;
 	}
	public void setPopups(List<Advertise> popups) {
		this.popups = popups;
 	}
	public void addPopups(Advertise advertise) {
		if (this.popups == null) {
			this.popups = new ArrayList<Advertise>();
		}
		this.popups.add(advertise);
 	}

	public List<Advertise> getBanners() {
		return banners;
 	}
	public void setBanners(List<Advertise> banners) {
		this.banners = banners;
 	}
	public void addBanners(Advertise advertise) {
		if (this.banners == null) {
			this.banners = new ArrayList<Advertise>();
		}
		this.banners.add(advertise);
 	}

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

	public List<Notice> getNotices() {
		return notices;
 	}
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
 	}
	public void addNotices(Notice notice) {
		if (this.notices == null) {
			this.notices = new ArrayList<Notice>();
		}
		this.notices.add(notice);
 	}

	public Integer getUnReadNotiCount() {
		return unReadNotiCount;
 	}
	public void setUnReadNotiCount(Integer unReadNotiCount) {
		this.unReadNotiCount = unReadNotiCount;
 	}

}
