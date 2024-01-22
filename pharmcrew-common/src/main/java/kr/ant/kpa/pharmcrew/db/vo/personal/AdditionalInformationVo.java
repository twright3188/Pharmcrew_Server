package kr.ant.kpa.pharmcrew.db.vo.personal;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @since 2021-01-23
 * @version 0.0.1
 * @author Zhang
 */

@Data
public class AdditionalInformationVo implements Serializable {
	
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
	 * 약국입지1(H:2,3차 의료기관인근, U:지하, G:1층, A:2층이상, E:기타)
	 */
	private String ai_situation;
	
	/**
	 * 약국입지2 (S:대항마트,백화점내, T:터미널역사공항내, O:그외)  
	**/
	private String ai_location;
	
	/**
	 * 체인가입여부 (Y:가입, N:미가입)
	**/
	private String ai_chain_join;
	
	/**
	 * 현재약국개설년도  
	**/
	private String ai_current_open_year;
	
	/**
	 * 최초약국개설년도  
	**/
	private String ai_initial_open_year;
	
	/**
	 * 개설 / 근무 / 취업 외 경력
	**/
	private String ai_other_career;
	
	/**
	 * ATC보유여부 (Y:보유, N:미보유)
	**/
	private String ai_atc_have;
	
	/**
	 * 평일개문시간  
	**/
	private String ai_weekday_open;
	
	/**
	 * 평일폐문
	**/
	private String ai_weekday_close;
	
	/**
	 * 토요일개문시간 
	**/
	private String ai_saturday_open;
	
	/**
	 * 토요일폐문시간
	**/
	private String ai_saturday_close;
	
	/**
	 * 토요일개문횟수
	**/
	private Integer ai_saturday_times;
	
	/**
	 * 일요일개문시간
	**/
	private String ai_sunday_open;
	
	/**
	 * 일요일폐문시간
	**/
	private String ai_sunday_close;
	
	/**
	 * 일요일개문횟수
	**/
	private Integer ai_sunday_times;
	
	/**
	 * 공휴일개문시간
	**/
	private String ai_holiday_open;
	
	/**
	 * 공휴일폐문시간
	**/
	private String ai_holiday_close;
	
	/**
	 * 약사(상근)
	**/
	private Integer ai_pharmacist_fulltime;
	
	/**
	 * 약사(비상근)
	**/
	private Integer ai_pharmacist_parttime;
	
	/**
	 * 약사(기타)
	**/
	private Integer ai_pharmacist_other;
	
	/**
	 * 종업원(상근) 
	**/
	private Integer ai_staff_fulltime;
	
	/**
	 * 종업원(비상근)
	**/
	private Integer ai_staff_parttime;
	
	/**
	 * 종업원(기타) 
	**/
	private Integer ai_staff_other;
	
	/**
	 * 약국개설경력보유여부(Y:보유, N:미보유)
	**/
	private String ai_career_open;
	
	/**
	 * 약국근무경력보유여부 (Y:보유, N:미보유)
	**/
	private String ai_career_work;
	
	/**
	 * 의료기관 경력보유여부 (Y:보유,  N:미보유)
	**/
	private String ai_career_medical;
	
	/**
	 * 제약유통경력보유여부 (Y:보유, N:미보유)
	**/
	private String ai_career_pharmaceutical;
	
	/**
	 * 정부공공기관경력보유여부(Y:보유, N:미보유)
	**/
	private String ai_career_public;
	
	/**
	 * 학교경력보유여부(Y:보유, N:미보유)
	**/
	private String ai_career_school;
	
	/**
	 * 기타경력보유여부(Y:보유, N:미보유)
	**/
	private String ai_career_etc;
	
	/**
	 * 약국면적 (평) 
	**/
	private String ai_pharmacy_area;
	
	/**
	 * 점포구분(O:소유, R:임대)
	**/
	private String ai_store_classification;
	
	/**
	 * 현재약국근무시작년도
	**/
	private String ai_current_work_year;
	
	/**
	 * 근무약사최초활동년도
	**/
	private String ai_initial_work_year;
	
	/**
	 * 부서
	**/
	private String ai_department;
	
	/**
	 * 직위
	**/
	private String ai_position;
	
	/**
	 * 현재근무시관입사년도
	**/
	private String ai_current_join_year;
	
	/**
	 * 해당 취업구분 최초 입사년도
	**/
	private String ai_initial_join_year;
}
