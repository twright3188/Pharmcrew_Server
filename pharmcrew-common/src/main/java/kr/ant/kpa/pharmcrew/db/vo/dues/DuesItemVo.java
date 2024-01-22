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
public class DuesItemVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 회비항목순서
	**/
	private Integer di_order;
	
	/**
	 * 소속코드
	**/
	private String ac_code;
	
	/**
	 * 회비항목명 
	**/
	private String di_name;
	
	/**
	 * 노출우선순위 
	**/
	private Integer di_priority;
	
	/**
	 * 코드활성화여부(Y : Enabled, N: Disabled) 
	**/
	private String di_enabled;
}
