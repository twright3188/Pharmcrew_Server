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
public class BankInformationVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 소속코드
	**/
	private String ac_code;
	
	/**
	 * 은행
	**/
	private String bi_name;
	
	/**
	 * 예금주 
	**/
	private String bi_owner;
	
	/**
	 * 계좌번호 
	**/
	private String bi_account;
	
	/**
	 * 공개여부(Y:공개, N:비공개) 
	**/
	private String bi_disclosure;
}
