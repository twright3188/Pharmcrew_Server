package kr.ant.kpa.pharmcrew.db.vo.personal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 개인정보
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class PersonalInformationVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 면허번호
	**/
	private String pi_license;
	
	/**
	 * 이름
	**/
	private String pi_name;
	
	/**
	 * 성별(M:남 F:여) 
	 */
	private String pi_gender;
	
	/**
	 * 생년월일  
	**/
	private String pi_birth_date;
	
	/**
	 * 면허취득년도
	**/
	private String pi_acquisition_year;
	
	/**
	 * 일반전화
	**/
	private String pi_phone;
	
	/**
	 * 휴대전화
	**/
	private String pi_cellphone;
	
	/**
	 * 우편번호
	**/
	private String pi_postcode;
	
	/**
	 * 주소
	**/
	private String pi_address;
	
	/**
	 * 주소상세  
	**/
	private String pi_address_details;
	
	/**
	 * 이메일
	**/
	private String pi_email;
	
	/**
	 * 대학교코드
	**/
	private String uc_code;
	
	/**
	 * 졸업년도
	**/
	private String pi_graduation_year;
	
	/**
	 * 소속코드
	**/
	private String ac_code;
	
	/**
	 * 한약조제자격 (Y:유 N:무)
	**/
	private String pi_herbal_manufacture;
	
	/**
	 * 최종학위 학사 : Bachelor 석사 : Master 박사 : Doctor 
	**/
	private String pi_final_degree;
	
	/**
	 * 전공
	**/
	private String pi_major;
	
	/**
	 * 약사공론 우편물 수신처 (O:근무지 H:거주지 R:수신거부+사유)
	**/
	private String pi_news_destination;
	
	/**
	 * 기타 우편물 수신처 (O:근무지 H:거주지 R:수신거부+사유)  
	**/
	private String pi_mail_destination;
	
	/**
	 * 성화여부(Y : Enabled, N: Disabled) 
	**/
	private String pi_enabled;
	
	/**
	 * 수집∙이용동의 (Y:동의, N:미동의)
	**/
	private String pi_user_consent;
	
	/**
	 * 수정일시
	**/
	private Date mod_at;
	
}
