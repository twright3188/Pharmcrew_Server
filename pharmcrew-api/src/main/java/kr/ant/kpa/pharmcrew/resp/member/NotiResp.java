package kr.ant.kpa.pharmcrew.resp.member;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.member.Noti;

/**
* 사용자
* 3.알림 목록 요청
 */
public class NotiResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("사용자 알림 목록")
	private List<Noti> notis;


	@Description("전체 알림 숫자")
	private Integer totalCount;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public NotiResp() {
		super();
	}
	public NotiResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Noti> getNotis() {
		return notis;
 	}
	public void setNotis(List<Noti> notis) {
		this.notis = notis;
 	}
	public void addNotis(Noti noti) {
		if (this.notis == null) {
			this.notis = new ArrayList<Noti>();
		}
		this.notis.add(noti);
 	}

	public Integer getTotalCount() {
		return totalCount;
 	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
 	}

}
