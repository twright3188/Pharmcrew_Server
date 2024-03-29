package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import kr.ant.kpa.pharmcrew.resp.academy.Video;

/**
* 학술정보
* 4. 동영상 상세 조회
 */
public class VideoDetailResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("학술 정보 동영상 정보")
	private Video video;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public VideoDetailResp() {
		super();
	}
	public VideoDetailResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public Video getVideo() {
		return video;
 	}
	public void setVideo(Video video) {
		this.video = video;
 	}

}
