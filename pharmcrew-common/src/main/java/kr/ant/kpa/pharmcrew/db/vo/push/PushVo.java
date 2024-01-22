package kr.ant.kpa.pharmcrew.db.vo.push;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

import java.io.Serializable;

import java.util.Date;

/**
* 푸시 => 푸시
 */
public class PushVo implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	/**
	 * push-update에서 img_file_id를 null로 설정
	 */
	private Boolean imgFileIdNull;
	public boolean isImgFileIdNull() {
		return imgFileIdNull;
	}
	public void setImgFileIdNull(boolean imgFileIdNull) {
		this.imgFileIdNull = imgFileIdNull;
	}

	/**
	 * push-update에서 reserv_dt를 null로 설정
	 */
	private Boolean reservDtNull;
	public Boolean isReservDtNull() {
		return reservDtNull;
	}
	public void setReservDtNull(Boolean reservDtNull) {
		this.reservDtNull = reservDtNull;
	}

	/**
	 * push-update에서 sended_dt를 now()로 설정
	 */
	private Boolean sendedDtNow;
	public Boolean getSendedDtNow() {
		return sendedDtNow;
	}
	public void setSendedDtNow(Boolean sendedDtNow) {
		this.sendedDtNow = sendedDtNow;
	}

	/**
	 * 이미지 파일
	 */
	private FileVo imgFile;
	public FileVo getImgFile() {
		return imgFile;
	}
	public void setImgFile(FileVo imgFile) {
		this.imgFile = imgFile;
	}

	/**
	 * 작성자
	 */
	private AdminVo regAdmin;
	public AdminVo getRegAdmin() {
		return regAdmin;
	}
	public void setRegAdmin(AdminVo regAdmin) {
		this.regAdmin = regAdmin;
	}

	/**
	 * 전송 수<br>
	 *     withSRCnt
	 */
	private Integer sendCnt;
	public Integer getSendCnt() {
		return sendCnt;
	}
	public void setSendCnt(Integer sendCnt) {
		this.sendCnt = sendCnt;
	}
	/**
	 * 전송 응답 수<br>
	 *     withSRCnt
	 */
	private Integer recvCnt;
	public Integer getRecvCnt() {
		return recvCnt;
	}
	public void setRecvCnt(Integer recvCnt) {
		this.recvCnt = recvCnt;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	 기본 컬럼
	////////////////////////////////////////////////////////////////////////////////////////

	/**
	*	푸시 ID => 고유 아이디
	*	long
	*	파라메터 옵션 :  Auto-Increment &&  Primary Key &&  NOT NULL &&  UNIQUE 
	**/
	private Long push_id;

	/**
	*	제목
	*	varchar, length : 100
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String title;

	/**
	*	본문
	*	varchar, length : 1500
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String body;

	/**
	*	이미지 파일 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_file-file_id) 
	**/
	private Long img_file_id;

	/**
	*	OS 구분 => null: 전체 => I: iOS, A: Android
	*	char, length : 1
	*	파라메터 옵션 : 
	**/
	private String os_div;

	/**
	*	조직 ID
	*	long
	*	파라메터 옵션 :  Foreign Key = (t_organize-organize_id) 
	**/
	private Long organize_id;

//	/**
//	*	개별 전송 여부 => 조직 전송 여부 - Y(조직 전송), N(개별전송,팜택스전송)
//	*	char, length : 1
//	*	파라메터 옵션 :  NOT NULL
//	**/
//	private String public_yn;

	/**
	*	이동 카테고리 => PUSH_CATEGORY.java => AD: 광고, NT: 알림, PT: 팜택스
	*	char, length : 2
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String category_div;

	/**
	*	이동 타입 => MOVE.java -  WU("웹", "Web URL"), NO("공지사항", "공지사항 상세화면 ID"), ED("교육", "교육 상세화면 ID"), PT("팜택스", "팜택스 공지 ID")
	*	char, length : 2
	*	파라메터 옵션 : 
	**/
	private String move_div;

	/**
	*	이동 ID
	*	long
	*	파라메터 옵션 : 
	**/
	private Long move_id;

	/**
	*	이동 URL
	*	varchar, length : 200
	*	파라메터 옵션 : 
	**/
	private String move_url;

	/**
	*	상태 => PUSH_STATE.java => R(eady): 준비, S(ended): 완료
	*	char, length : 1
	*	파라메터 옵션 :  NOT NULL 
	**/
	private String state_div;

	/**
	*	예약 일시 => null: 즉시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date reserv_dt;

	/**
	*	전송 일시
	*	datetime
	*	파라메터 옵션 : 
	**/
	private Date sended_dt;

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

	//	푸시 ID
	public Long getPush_id() {
		return push_id;
 	}
	public void setPush_id(Long push_id) {
		this.push_id = push_id;
 	}

	//	제목
	public String getTitle() {
		return title;
 	}
	public void setTitle(String title) {
		this.title = title;
 	}

	//	본문
	public String getBody() {
		return body;
 	}
	public void setBody(String body) {
		this.body = body;
 	}

	//	이미지 파일 ID
	public Long getImg_file_id() {
		return img_file_id;
 	}
	public void setImg_file_id(Long img_file_id) {
		this.img_file_id = img_file_id;
 	}

	//	OS 구분
	public String getOs_div() {
		return os_div;
 	}
	public void setOs_div(String os_div) {
		this.os_div = os_div;
 	}

	//	조직 ID
	public Long getOrganize_id() {
		return organize_id;
 	}
	public void setOrganize_id(Long organize_id) {
		this.organize_id = organize_id;
 	}

//	//	개별 전송 여부
//	public String getPublic_yn() {
//		return public_yn;
// 	}
//	public void setPublic_yn(String public_yn) {
//		this.public_yn = public_yn;
// 	}

	//	이동 카테고리
	public String getCategory_div() {
		return category_div;
 	}
	public void setCategory_div(String category_div) {
		this.category_div = category_div;
 	}

	//	이동 타입
	public String getMove_div() {
		return move_div;
 	}
	public void setMove_div(String move_div) {
		this.move_div = move_div;
 	}

	//	이동 ID
	public Long getMove_id() {
		return move_id;
 	}
	public void setMove_id(Long move_id) {
		this.move_id = move_id;
 	}

	//	이동 URL
	public String getMove_url() {
		return move_url;
 	}
	public void setMove_url(String move_url) {
		this.move_url = move_url;
 	}

	//	상태
	public String getState_div() {
		return state_div;
 	}
	public void setState_div(String state_div) {
		this.state_div = state_div;
 	}

	//	예약 일시
	public Date getReserv_dt() {
		return reserv_dt;
 	}
	public void setReserv_dt(Date reserv_dt) {
		this.reserv_dt = reserv_dt;
 	}

	//	전송 일시
	public Date getSended_dt() {
		return sended_dt;
 	}
	public void setSended_dt(Date sended_dt) {
		this.sended_dt = sended_dt;
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
