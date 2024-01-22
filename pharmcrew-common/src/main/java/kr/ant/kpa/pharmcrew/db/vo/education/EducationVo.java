package kr.ant.kpa.pharmcrew.db.vo.education;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;

/**
* 교육 => 교육
 */
public class EducationVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * 지도 파일 삭제
	 */
	private Boolean mapFileIdNull;
	public Boolean getMapFileIdNull() {
		return mapFileIdNull;
	}
	public void setMapFileIdNull(Boolean mapFileIdNull) {
		this.mapFileIdNull = mapFileIdNull;
	}

	/**
	 * 시간표 파일 삭제
	 */
	private Boolean timetableFileIdNull;
	public Boolean getTimetableFileIdNull() {
		return timetableFileIdNull;
	}
	public void setTimetableFileIdNull(Boolean timetableFileIdNull) {
		this.timetableFileIdNull = timetableFileIdNull;
	}

	/**
	 * 지도 파일
	 */
	private FileVo mapFile;
	public FileVo getMapFile() {
		return mapFile;
	}
	public void setMapFile(FileVo mapFile) {
		this.mapFile = mapFile;
	}

	/**
	 * 시간표 파일
	 */
	private FileVo timetableFile;
	public FileVo getTimetableFile() {
		return timetableFile;
	}
	public void setTimetableFile(FileVo timetableFile) {
		this.timetableFile = timetableFile;
	}

	/**
	 * 등록자
	 */
	private AdminVo regAdmin;
	public AdminVo getRegAdmin() {
		return regAdmin;
	}
	public void setRegAdmin(AdminVo regAdmin) {
		this.regAdmin = regAdmin;
	}

	/**
	 * QR 리스트
	 */
	private List<QrVo> qrs;
	public List<QrVo> getQrs() {
		return qrs;
	}
	public void setQrs(List<QrVo> qrs) {
		this.qrs = qrs;
	}

	/**
	 * 관리자에 의해 인정된 참석자 수<br>
	 *     withApprovalCnt
	 *
	 */
	private Integer approvalCnt;
	public Integer getApprovalCnt() {
		return approvalCnt;
	}
	public void setApprovalCnt(Integer approvalCnt) {
		this.approvalCnt = approvalCnt;
	}

	/**
	 * 조직 이름 
	 */
	private String organizeName;
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////


	/**
	*	교육 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL 
	**/
	private Long education_id;

	/**
	*	조직 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

	/**
	*	노출 여부 => Y:노출, N:비노출
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String is_open;

	/**
	*	교육명
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	시작 일
	*	date
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date start_date;

	/**
	*	종료 일
	*	date
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date end_date;

	/**
	*	시작 시간 => HHmm
	*	char, length : 4
	*	파라메터 옵션 : 
	**/
	private String start_time;

	/**
	*	종료 시간 => HHmm
	*	char, length : 4
	*	파라메터 옵션 : 
	**/
	private String end_time;

	/**
	*	교육 인정 시간 => 분단위 이수시간 - 강의전체 인증 타입인 경우
	*	int, length : 4
	*	파라메터 옵션 : 
	**/
	private Integer take_min;

	/**
	*	주소
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String address;

	/**
	*	주소 상세
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String address_detail;

	/**
	*	오시는 길 (요약) => 오시는 길 직접 입력 텍스트
	*	varchar, length : 50
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String way_guide;

	/**
	*	오시는 길 (상세) => 오시는 길 상세 정보
	*	varchar, length : 150
	*	파라메터 옵션 : 
	**/
	private String way_detail;

	/**
	*	약도 파일 (오시는길)
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) 
	**/
	private Long map_file_id;

	/**
	*	담당자 => 문의처 정보 직접 입력 - 담당자 등
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String master;

	/**
	*	담당자 연락처 => 전화걸기 전화번호
	*	varchar, length : 15
	*	파라메터 옵션 : 
	**/
	private String master_phone;

	/**
	*	인증 타입 - 전체인증, 개별 인증 => A:강의전체 인증, P:개별 인증
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String auth_div;

	/**
	*	시간표 파일 - pdf, png 등록
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) 
	**/
	private Long timetable_file_id;

	/**
	*	지도 위도 정보
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String latitude;

	/**
	*	지도 경도 정보
	*	varchar, length : 20
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String longitude;

	/**
	*	조회수
	*	long
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Long view_cnt;

	/**
	*	등록자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) &&  NOT NULL 
	**/
	private Long reg_id;

	/**
	*	등록 일시
	*	datetime
	*	파라메터 옵션 :  NOT NULL 
	**/
	private Date reg_dt;

	/**
	*	수정자 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_admin-admin_id) 
	**/
	private Long mod_id;

	/**
	*	수정 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date mod_dt;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 getter/setter
	////////////////////////////////////////////////////////////////////////////////////////

	//	교육 ID
	public Long getEducation_id() {
		return education_id;
 	}
	public void setEducation_id(Long education_id) {
		this.education_id = education_id;
 	}

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

	//	노출 여부
	public String getIs_open() {
		return is_open;
 	}
	public void setIs_open(String is_open) {
		this.is_open = is_open;
 	}

	//	교육명
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	시작 일
	public Date getStart_date() {
		return start_date;
 	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
 	}

	//	종료 일
	public Date getEnd_date() {
		return end_date;
 	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
 	}

	//	시작 시간
	public String getStart_time() {
		return start_time;
 	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
 	}

	//	종료 시간
	public String getEnd_time() {
		return end_time;
 	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
 	}

	//	교육 인정 시간
	public Integer getTake_min() {
		return take_min;
 	}
	public void setTake_min(Integer take_min) {
		this.take_min = take_min;
 	}

	//	주소
	public String getAddress() {
		return address;
 	}
	public void setAddress(String address) {
		this.address = address;
 	}

	//	주소 상세
	public String getAddress_detail() {
		return address_detail;
 	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
 	}

	//	오시는 길 (요약)
	public String getWay_guide() {
		return way_guide;
 	}
	public void setWay_guide(String way_guide) {
		this.way_guide = way_guide;
 	}

	//	오시는 길 (상세)
	public String getWay_detail() {
		return way_detail;
 	}
	public void setWay_detail(String way_detail) {
		this.way_detail = way_detail;
 	}

	//	약도 파일 (오시는길)
	public Long getMap_file_id() {
		return map_file_id;
 	}
	public void setMap_file_id(Long map_file_id) {
		this.map_file_id = map_file_id;
 	}

	//	담당자
	public String getMaster() {
		return master;
 	}
	public void setMaster(String master) {
		this.master = master;
 	}

	//	담당자 연락처
	public String getMaster_phone() {
		return master_phone;
 	}
	public void setMaster_phone(String master_phone) {
		this.master_phone = master_phone;
 	}

	//	인증 타입 - 전체인증, 개별 인증
	public String getAuth_div() {
		return auth_div;
 	}
	public void setAuth_div(String auth_div) {
		this.auth_div = auth_div;
 	}

	//	시간표 파일 - pdf, png 등록
	public Long getTimetable_file_id() {
		return timetable_file_id;
 	}
	public void setTimetable_file_id(Long timetable_file_id) {
		this.timetable_file_id = timetable_file_id;
 	}

	//	지도 위도 정보
	public String getLatitude() {
		return latitude;
 	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
 	}

	//	지도 경도 정보
	public String getLongitude() {
		return longitude;
 	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
 	}

	//	조회수
	public Long getView_cnt() {
		return view_cnt;
 	}
	public void setView_cnt(Long view_cnt) {
		this.view_cnt = view_cnt;
 	}

	//	등록자 ID
	public Long getReg_id() {
		return reg_id;
 	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
 	}

	//	등록 일시
	public Date getReg_dt() {
		return reg_dt;
 	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
 	}

	//	수정자 ID
	public Long getMod_id() {
		return mod_id;
 	}
	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
 	}

	//	수정 일시
	public Date getMod_dt() {
		return mod_dt;
 	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
 	}

}
