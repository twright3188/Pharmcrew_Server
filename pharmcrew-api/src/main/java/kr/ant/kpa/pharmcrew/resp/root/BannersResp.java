package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Advertise;

/**
* 메인
* 5.메인 배너 정보 요청
 */
public class BannersResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("null")
	private List<Advertise> banners;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public BannersResp() {
		super();
	}
	public BannersResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

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

}
