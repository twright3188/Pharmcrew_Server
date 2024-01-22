package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;

import java.util.ArrayList;
import java.util.List;

import kr.ant.kpa.pharmcrew.resp.academy.Video;

/**
* 학술정보
* 3. 동영상 목록 조회
 */
public class VideoListResp extends PcResp {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("학술정보 동영상 목록")
	private List<Video> videos;


	////////////////////////////////////////////////////////////////////////////////////////
	//	 Base Functions 
	////////////////////////////////////////////////////////////////////////////////////////

	public VideoListResp() {
		super();
	}
	public VideoListResp(VALIDATION validation) {
		super(validation);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public List<Video> getVideos() {
		return videos;
 	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
 	}
	public void addVideos(Video video) {
		if (this.videos == null) {
			this.videos = new ArrayList<Video>();
		}
		this.videos.add(video);
 	}

}
