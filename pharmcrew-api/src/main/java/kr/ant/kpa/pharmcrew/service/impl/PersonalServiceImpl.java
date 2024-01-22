package kr.ant.kpa.pharmcrew.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.bytecode.Descriptor.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PersonalDao;
import kr.ant.kpa.pharmcrew.db.vo.common.AffiliationCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.UniversityCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.personal.*;
import kr.ant.kpa.pharmcrew.resp.CommonListResp;
import kr.ant.kpa.pharmcrew.resp.CommonResp;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.service.PersonalService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;

@Service("personalService")
public class PersonalServiceImpl implements PersonalService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalServiceImpl.class);
	
	@Autowired
	private PersonalDao personalDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private PcUtils pcUtils;
	
	@Autowired
	private MemberDao memberDao;
	
	@Transactional("transactionManager")

	/**
	 * 1.내 정보 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<PersonalInformationVo> getPersonalInfo(String license) throws InvalidMemberException {
		CommonResp<PersonalInformationVo> resp = new CommonResp<PersonalInformationVo>();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "pi_license", license);
		PersonalInformationVo personalInfo = personalDao.selectPersonalInformation(param);
		if (personalInfo == null) {
			resp.setData(new PersonalInformationVo());
		} else {
			resp.setData(personalInfo);
		}
		return resp;
	}
	
	/**
	 * 2.내 정보 저장
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp savePersonalInfo(PersonalInformationVo personalInfo) throws InvalidMemberException {
		PcResp resp = new PcResp();
		personalDao.updatePersonalInformation(personalInfo);
		return resp;
	}
	
	/**
	 * 3.추가정보 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<AdditionalInformationVo> getAdditionalInfo(String license, String ry_year) throws InvalidMemberException {
		CommonResp<AdditionalInformationVo> resp = new CommonResp<AdditionalInformationVo>();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "pi_license", license);
		MapUtils.put(param, "ry_year", ry_year);
		AdditionalInformationVo additionaInfo = personalDao.selectAdditionalInformation(param);
		if (additionaInfo == null) {
			resp.setData(new AdditionalInformationVo());
		} else {
			resp.setData(additionaInfo);
		}
		return resp;
	}
	
	/**
	 * 4.추가정보 저장
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp saveAdditionalInfo(AdditionalInformationVo additionalInfo) throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();
		MapUtils.put(param, "pi_license", additionalInfo.getPi_license());
		MapUtils.put(param, "ry_year", additionalInfo.getRy_year());

		AdditionalInformationVo vo = personalDao.selectAdditionalInformation(param);
		
		if (vo != null && vo.getPi_license() != null) {
			personalDao.updateAdditionalInformation(additionalInfo);
		} else {
			checkAndinsertReportYear(additionalInfo.getPi_license(), additionalInfo.getRy_year());
			personalDao.insertAdditionalInformation(additionalInfo);
		}

		return resp;
	}
	
	/**
	 * 5. 분회목록을 요청.
	 */
	public CommonListResp<AffiliationCodeVo> getAffiliationList() throws InvalidMemberException
	{
		CommonListResp<AffiliationCodeVo> resp = new CommonListResp<AffiliationCodeVo>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		List<AffiliationCodeVo> affiliationList = commonDao.listAffiliation(param);
		resp.setData(affiliationList);
		return resp;
	}
	
	/**
	 * 6. 대학교목록을 요청.
	 */
	public CommonListResp<UniversityCodeVo> getUniversityList() throws InvalidMemberException
	{
		CommonListResp<UniversityCodeVo> resp = new CommonListResp<UniversityCodeVo>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		List<UniversityCodeVo> universityList = commonDao.listUniversity(param);
		resp.setData(universityList);
		return resp;
	}
	
	/**
	 * 7. 직업코드목록 요청.
	 */
	public CommonListResp<EmploymentCodeVo> getEmploymentCodeList() throws InvalidMemberException
	{
		CommonListResp<EmploymentCodeVo> resp = new CommonListResp<EmploymentCodeVo>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		List<EmploymentCodeVo> employmentCodeList = personalDao.listEmploymentlCode(param);
		resp.setData(employmentCodeList);
		return resp;
	}
	
	/**
	 * 8.직업정보 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<EmploymentInformationVo> getEmploymentInfo(String license, String ry_year) throws InvalidMemberException {
		CommonResp<EmploymentInformationVo> resp = new CommonResp<EmploymentInformationVo>();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "pi_license", license);
		MapUtils.put(param, "ry_year", ry_year);
		List<EmploymentInformationVo> infoList = personalDao.listEmploymentlInformation(param);
		
		if (infoList != null && infoList.size() > 0) {
			resp.setData(infoList.get(0));
		} else {
			resp.setData(new EmploymentInformationVo());
		}
		
		return resp;
	}
	
	/**
	 * 9.직업정보 저장
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp saveEmploymentInfo(EmploymentInformationVo info) throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();
		MapUtils.put(param, "pi_license", info.getPi_license());
		MapUtils.put(param, "ry_year", info.getRy_year());
		
		if (info.getEi_order() == null) {
			info.setEi_order(1);
		}
		
		MapUtils.put(param, "ei_order", info.getEi_order());
		
		EmploymentInformationVo vo = personalDao.selectEmploymentInformation(param);
		
		if (vo != null && vo.getPi_license() != null) {
			personalDao.updateEmploymentInformation(info);
		} else {
			checkAndinsertReportYear(info.getPi_license(), info.getRy_year());
			personalDao.insertEmploymentInformation(info);
		}

		return resp;
	}
	
	private void checkAndinsertReportYear(String pi_license, String ry_year)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		MapUtils.put(param, "pi_license", pi_license);
		MapUtils.put(param, "ry_year", ry_year);
		//. check report_year
		ReportYearVo ry_vo = personalDao.selectReportYear(param);
		if (ry_vo == null) {
			ry_vo = new ReportYearVo();
			ry_vo.setPi_license(pi_license);
			ry_vo.setRy_year(ry_year);
			ry_vo.setRy_payment_flag("N");
			ry_vo.setRy_report_path("A");
			personalDao.insertReportYear(ry_vo);
		}		
	}
	
	/**
	 * 10.일요일개붐시간 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<ReportYearVo> getReportYear(String license, String ry_year) throws InvalidMemberException {
		CommonResp<ReportYearVo> resp = new CommonResp<ReportYearVo>();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "pi_license", license);
		MapUtils.put(param, "ry_year", ry_year);
		ReportYearVo info = personalDao.selectReportYear(param);
		if (info == null) {
			resp.setData(new ReportYearVo());
		} else {
			resp.setData(info);
		}
		
		return resp;
	}
	
	/**
	 * 11.일요일개붐시간 저장
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp saveReportYear(ReportYearVo info) throws InvalidMemberException {
		PcResp resp = new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();
		MapUtils.put(param, "pi_license", info.getPi_license());
		MapUtils.put(param, "ry_year", info.getRy_year());
		
		ReportYearVo vo = personalDao.selectReportYear(param);
		
		if (vo != null && vo.getPi_license() != null) {
			personalDao.updateReportYear(info);
		} else {
			personalDao.insertReportYear(info);
		}
		return resp;
	}
	
	public PcResp generateMember() throws InvalidMemberException {		
		boolean isContinue = true;
		int start = 0, limit = 100;
		while (isContinue){
			Map<String, Object> param = new HashMap<String, Object>();
			MapUtils.put(param, "start", start);
			MapUtils.put(param, "limit", limit);
			List<PersonalInformationVo> infoList = personalDao.listPersonalInformation(param);
			
			if (infoList.size() == 0) {
				isContinue = false;
			}
			
			for (PersonalInformationVo info : infoList)
			{
				MemberVo member = new MemberVo();
				member.setLicense_no(info.getPi_license());
				member.setOrganize_1_id(8L);
				member.setPassword(pcUtils.encryptPassword("1234"));
				member.setName(info.getPi_name());
				member.setBirthday(info.getPi_birth_date());
				member.setHandphone(info.getPi_phone());
				member.setEmail(info.getPi_email());
				member.setSex_div(info.getPi_gender());
				member.setState_div("P");
				member.setPush_yn("Y");
				member.setPtax_yn("N");
				member.setReg_dt(new Date());
				
				Map<String, Object> memberParam = new HashMap<String, Object>();
				MapUtils.put(memberParam, "license_no", info.getPi_license());
				MemberVo existMember = memberDao.selectMember(memberParam);
				
				if (existMember == null) {
					try {
						memberDao.insertMember(member);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}			
			start += limit;
		}		
		return  new PcResp();
	}
	
	

}
