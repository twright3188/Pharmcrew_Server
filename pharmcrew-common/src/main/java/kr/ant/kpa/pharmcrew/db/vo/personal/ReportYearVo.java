package kr.ant.kpa.pharmcrew.db.vo.personal;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 * 
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */
@Data
public class ReportYearVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 신상신고신고넌도
	**/
	private String ry_year;
	
	/**
	 * 면허번호
	**/
	private String pi_license;
	
	/**
	 * 회비납부구분 (N:미납 Y:납부 E:면제)
	**/
	private String ry_payment_flag;
	
	/**
	 * 신고일자
	**/
	private Date ry_report_date;
	
	/**
	 * 신고경로 (W:웹, M:모바일, A:관리자등록)
	**/
	private String ry_report_path;
	
	/**
	 * 승인일자
	**/
	private Date ry_approval_date;
	
	/**
	 * 승인ID
	**/
	private String ui_id;
	
	/**
	 * 회비납부일시
	**/
	private Date ry_payment_date;
	
}
