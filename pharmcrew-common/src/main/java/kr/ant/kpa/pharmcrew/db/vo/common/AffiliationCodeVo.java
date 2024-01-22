package kr.ant.kpa.pharmcrew.db.vo.common;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class AffiliationCodeVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 소속코드
	**/
	private String ac_code;
	
	/**
	 * 소속명
	**/
	private String ac_name;
	
	/**
	 * 지부명
	 */
	private String ac_branch;
	
	/**
	 * 분회명  
	**/
	private String ac_chapter;

}
