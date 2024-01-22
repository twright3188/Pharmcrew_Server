package kr.ant.kpa.pharmcrew.db.vo.dues;
import java.io.Serializable;
import lombok.Data;

/**
 * 회비코드
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class DuesCodeVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 회비코드
	**/
	private String dc_code;
	
	/**
	 * 회비코드명
	**/
	private String dc_name;
	
	/**
	 * 코드활성화여부(Y : Enabled, N: Disabled) 
	**/
	private String dc_enabled;
}
