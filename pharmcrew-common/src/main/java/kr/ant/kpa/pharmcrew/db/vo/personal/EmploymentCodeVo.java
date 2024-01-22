package kr.ant.kpa.pharmcrew.db.vo.personal;
import java.io.Serializable;
import lombok.Data;


/**
 * 근무처코드
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */
@Data
public class EmploymentCodeVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 취업코드
	**/
	private String ec_code;
	
	/**
	 * 취업코드명
	**/
	private String ec_name;
	
	/**
	 * 회비코드
	**/
	private String dc_code;
	
	/**
	 * 코드활성화여부(Y : Enabled , N: Disabled)
	**/
	private String ec_enabled;	
}
