package kr.ant.kpa.pharmcrew.db.vo.personal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * 근무처
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */
@Data
public class EmploymentInformationVo implements Serializable {
	
	private static final long serialVersionUID = -1;
	
	/**
	 * 취업순번
	**/
	private Integer ei_order;
	
	/**
	 * 신상신고신고넌도
	**/
	private String ry_year;
	
	/**
	 * 면허번호
	**/
	private String pi_license;
	
	/**
	 * 취업코드
	**/
	private String ec_code;
	
	/**
	 * 기타근무처입력
	**/
	private String ei_etc;
	
	/**
	 * 근무처명
	**/
	private String ei_office;
	
	/**
	 * 근무처우펀번호
	**/
	private String ei_postcode;
	
	/**
	 * 근무처주소
	**/
	private String ei_address;
	
	/**
	 * 근무처주소상세
	**/
	private String ei_address_details;
	
	/**
	 * 근무처전화번호
	**/
	private String ei_phone;
	
	/**
	 * 약국사업자번호
	**/
	private String ei_business_number;
	
	/**
	 * 요양기관기호
	**/
	private String ei_institution_number;
	
	/**
	 * 한약(첩약)취급 (Y:취급 N:취급안함)
	**/
	private String ei_herbal_deal;
	
	/**
	 * 동물약품취급 (Y:취급 N:취급안함)
	**/
	private String ei_animal_drug;
	
	/**
	 * 의약분업 지역구분 (Y:분업 N:분업예외)
	**/
	private String ei_division;
	
	/**
	 * 등록일시
	**/
	private Date ei_registration_date;
	
	/**
	 * 근무유형 / 형태 (상근:F, 비상근 :  P,  기타 : E)
	**/
	private String ei_work_type;
	
}
