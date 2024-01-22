package kr.ant.kpa.pharmcrew.controller;

import java.awt.List;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import com.bumdori.spring.annotation.validation.regex.HandphoneValidation;

import kr.ant.kpa.pharmcrew.db.vo.common.AffiliationCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.UniversityCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.*;
import kr.ant.kpa.pharmcrew.resp.CommonListResp;
import kr.ant.kpa.pharmcrew.resp.CommonResp;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberInfoResp;
import kr.ant.kpa.pharmcrew.resp.member.NotiResp;
import kr.ant.kpa.pharmcrew.resp.member.PassPhoneResp;
import kr.ant.kpa.pharmcrew.resp.member.ProfileResp;
import kr.ant.kpa.pharmcrew.resp.member.SettingResp;
import kr.ant.kpa.pharmcrew.resp.personal.PersonalInfoResp;
import kr.ant.kpa.pharmcrew.service.MemberService;
import kr.ant.kpa.pharmcrew.service.PersonalService;
import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyRegMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundAuthCodeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundMemberNotiException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundPartnersException;

@Controller("8.Personal")
public class PersonalController {

	private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);

	@Autowired
	private PersonalService personalService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.내 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/personal/info", method=RequestMethod.GET, name="01.내 정보 요청")
	@Session(required=true)
	@Description("Page=null , 내 정보 요청한다.")
	@Histories({
			@History(date="2021-01-24", description="자동 구성")
	})
	public @ResponseBody CommonResp<PersonalInformationVo> getGetMemberInfo(HttpSession session ) 
			throws InvalidMemberException {

		String license_no = (String) session.getAttribute("license");
		CommonResp<PersonalInformationVo> resp = personalService.getPersonalInfo(license_no);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	2.내 정보 갱신
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/info", method=RequestMethod.POST, name="02.내 정보 수정")
	@Session(required=true)
	@Description("Page=null , 내 정보 갱신한다.")
	@Histories({
			@History(date="2021-01-24", description="자동 구성")
	})
	public @ResponseBody PcResp putPersonalInfo(
			HttpSession session, 
			@RequestBody @Description("개인정보갱신") PersonalInformationVo personalInfo ) 
			throws InvalidMemberException 
	{

		String license_no = (String) session.getAttribute("license");
		personalInfo.setPi_license(license_no);
		PcResp resp = personalService.savePersonalInfo(personalInfo);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.추가정보요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/personal/additional", method=RequestMethod.GET, name="03.추가정보 요청")
	@Session(required=true)
	@Description("Page=null , 추가정보 요청한다.")
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonResp<AdditionalInformationVo> getAdditionalInfo(HttpSession session ) 
			throws InvalidMemberException {

		String license_no = (String) session.getAttribute("license");
		String ry_year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		CommonResp<AdditionalInformationVo> resp = personalService.getAdditionalInfo(license_no, ry_year);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	04.추가정보 갱신
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/personal/additional", method=RequestMethod.POST, name="04.추가정보 갱신")
	@Session(required=true)
	@Description("Page=null , 추가정보 갱신한다.")
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody PcResp postAdditionalInfo(
			HttpSession session, 
			@RequestBody @Description("추가정보갱신") AdditionalInformationVo additionalInfo ) 
			throws InvalidMemberException 
	{

		String license_no = (String) session.getAttribute("license");
		additionalInfo.setPi_license(license_no);
		if (additionalInfo.getRy_year() == null || additionalInfo.getRy_year().isEmpty()) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			additionalInfo.setRy_year(Integer.toString(year));
		}
		PcResp resp = personalService.saveAdditionalInfo(additionalInfo);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	5. 분회목록
	////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/personal/affiliation-list", method=RequestMethod.GET, name="05. 분회목록")
	@Session(required=true)
	@Description("Page=null , 분회들을 얻어온다.")
	@Histories({
			@History(date="2021-01-24", description="자동 구성")
	})
	public @ResponseBody CommonListResp<AffiliationCodeVo> getGetAffiliationList(HttpSession session ) 
			throws InvalidMemberException {

		CommonListResp<AffiliationCodeVo>  resp = personalService.getAffiliationList();
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	06. 대학교목록
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/university-list", method=RequestMethod.GET, name="06. 대학교목록")
	@Session(required=true)
	@Description("Page=null , 대학교들을 얻어온다.")
	@Histories({
			@History(date="2021-01-24", description="자동 구성")
	})
	public @ResponseBody CommonListResp<UniversityCodeVo> getGetUniversityList(HttpSession session ) 
			throws InvalidMemberException {

		CommonListResp<UniversityCodeVo>  resp = personalService.getUniversityList();
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	07. 직업코드목록
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/employment-codes", method=RequestMethod.GET, name="07. 직업코드목록")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonListResp<EmploymentCodeVo> getEmploymentCodeList(HttpSession session ) 
			throws InvalidMemberException {

		CommonListResp<EmploymentCodeVo>  resp = personalService.getEmploymentCodeList();
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	8.직업정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/personal/employment-info", method=RequestMethod.GET, name="8.직업정보 요청")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonResp<EmploymentInformationVo> getEmploymentInfo(HttpSession session ) 
			throws InvalidMemberException {

		String license_no = (String) session.getAttribute("license");
		String ry_year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		CommonResp<EmploymentInformationVo> resp = personalService.getEmploymentInfo(license_no, ry_year);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	9.직업정보 저장
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/employment-info", method=RequestMethod.POST, name="9.직업정보 저장")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody PcResp postEmploymentInfo(
			HttpSession session, 
			@RequestBody EmploymentInformationVo employmentInfo ) 
			throws InvalidMemberException 
	{

		String license_no = (String) session.getAttribute("license");
		employmentInfo.setPi_license(license_no);
		if (employmentInfo.getRy_year() == null || employmentInfo.getRy_year().isEmpty()) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			employmentInfo.setRy_year(Integer.toString(year));
		}
		
		PcResp resp = personalService.saveEmploymentInfo(employmentInfo);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	9.신고서 요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/personal/report", method=RequestMethod.GET, name="9.신고서 요청")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonResp<ReportYearVo> getReport(
			HttpSession session,
			@RequestParam(value="ry_year")  String ry_year ) throws InvalidMemberException {

		String license_no = (String) session.getAttribute("license");
		if (ry_year == null || ry_year.isEmpty()) {
			ry_year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		}
		CommonResp<ReportYearVo> resp = personalService.getReportYear(license_no, ry_year);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	10.신고서 저장
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/report", method=RequestMethod.POST, name="10.신고서 저장")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody PcResp postReportInfo(
			HttpSession session, 
			@RequestBody ReportYearVo reportInfo ) 
			throws InvalidMemberException 
	{

		String license_no = (String) session.getAttribute("license");
		reportInfo.setPi_license(license_no);
		if (reportInfo.getRy_year() == null || reportInfo.getRy_year().isEmpty()) {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			reportInfo.setRy_year(Integer.toString(year));
		}
		
		PcResp resp = personalService.saveReportYear(reportInfo);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	11.멤버표로 변환
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/personal/generate-members", method=RequestMethod.GET, name="11.멤버표로 변환")
	@Histories({
			@History(date="2021-01-26", description="자동 구성")
	})
	public @ResponseBody PcResp convertMemberTable() 
			throws InvalidMemberException 
	{		
		PcResp resp = personalService.generateMember();
		return resp;
	}
}
