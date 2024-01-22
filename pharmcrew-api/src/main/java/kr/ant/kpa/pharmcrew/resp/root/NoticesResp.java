package kr.ant.kpa.pharmcrew.resp.root;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.root.Notice;

/**
* 메인
* 7.메인 한줄 공지 요청
 */
public class NoticesResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("null")
	private List<Notice> notices;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public NoticesResp() {
		super();
	}
	public NoticesResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

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

}
