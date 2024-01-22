package kr.ant.kpa.pharmcrew.resp.education;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.survey.Choice;

import java.util.ArrayList;
import java.util.List;


/**
* 교육 상세 정보
 */
public class EduDetail extends Edu {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("오시는 길")
	private String guide;


	@Description("오시는 길 상세 정보")
	private String guideDetail;


	@Description("지도 위도 정보")
	private String latitude;


	@Description("지도 경도 정보")
	private String longitude;


	@Description("연락처 전화번호")
	private String telephone;


	@Description("약도 파일")
	private String mapFile;


	@Description("약도 파일 이름")
	private String mapFileName;


	@Description("시간표 파일 - pdf, png 등록")
	private String timeTableFile;


	@Description("시간표 파일 이름")
	private String timeTableFileName;


	@Description("교육 강의 시간표")
	private List<TimeTable> timeTables;


	////////////////////////////////////////////////////////////////////////////////////////
	//	  Member Functions	
	////////////////////////////////////////////////////////////////////////////////////////

	public String getGuide() {
		return guide;
 	}
	public void setGuide(String guide) {
		this.guide = guide;
 	}

	public String getGuideDetail() {
		return guideDetail;
 	}
	public void setGuideDetail(String guideDetail) {
		this.guideDetail = guideDetail;
 	}

	public String getLatitude() {
		return latitude;
 	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
 	}

	public String getLongitude() {
		return longitude;
 	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
 	}

	public String getTelephone() {
		return telephone;
 	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
 	}

	public String getMapFile() {
		return mapFile;
 	}
	public void setMapFile(String mapFile) {
		this.mapFile = mapFile;
 	}

	public String getMapFileName() {
		return mapFileName;
 	}
	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
 	}

	public String getTimeTableFile() {
		return timeTableFile;
 	}
	public void setTimeTableFile(String timeTableFile) {
		this.timeTableFile = timeTableFile;
 	}

	public String getTimeTableFileName() {
		return timeTableFileName;
 	}
	public void setTimeTableFileName(String timeTableFileName) {
		this.timeTableFileName = timeTableFileName;
 	}

	public List<TimeTable> getTimeTables() {
		return timeTables;
 	}
	public void setTimeTables(List<TimeTable> timeTables) {
		this.timeTables = timeTables;
 	}
	public void addTimeTables(TimeTable timeTable) {
			if (this.timeTables == null) {
				this.timeTables = new ArrayList<TimeTable>();
			}
			this.timeTables.add(timeTable);
	}

}
