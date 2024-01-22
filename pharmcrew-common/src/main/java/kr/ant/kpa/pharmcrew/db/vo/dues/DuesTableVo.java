package kr.ant.kpa.pharmcrew.db.vo.dues;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class DuesTableVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 년도
	**/
	private String dt_year;
	
	/**
	 * 회비코드
	**/
	private String dc_code;
	
	/**
	 * 회비항목아이디 
	**/
	private Integer di_order;
	
	/**
	 * 소속코드 
	**/
	private String ac_code;
	
	/**
	 * 회비 
	**/
	private Long dt_dues;
}
