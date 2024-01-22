package kr.ant.kpa.pharmcrew.db.vo.common;
import java.io.Serializable;
import lombok.Data;

/**
 * 대학교코드
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class UniversityCodeVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 대학교코드
	**/
	private String uc_code;
	
	/**
	 * 대학교명
	**/
	private String uc_name;
}
