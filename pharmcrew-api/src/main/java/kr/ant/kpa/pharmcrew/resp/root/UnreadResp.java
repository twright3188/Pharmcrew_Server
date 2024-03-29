package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;


/**
* 메인
* 8.읽지않은 알림숫자 요청
 */
public class UnreadResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("읽지 않은 알림 숫자")
	private Integer unReadNotiCount;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public UnreadResp() {
		super();
	}
	public UnreadResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Integer getUnReadNotiCount() {
		return unReadNotiCount;
 	}
	public void setUnReadNotiCount(Integer unReadNotiCount) {
		this.unReadNotiCount = unReadNotiCount;
 	}

}
