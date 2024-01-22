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
import kr.ant.kpa.pharmcrew.db.vo.dues.BankInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesItemVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableValueVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableVo;
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
import kr.ant.kpa.pharmcrew.service.DuesService;
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

@Controller("9.Dues")
public class DuesController {

	private static final Logger logger = LoggerFactory.getLogger(DuesController.class);

	@Autowired
	private DuesService duesService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1. 회비표요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/dues/table", method=RequestMethod.GET, name="01.회비표요청")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonResp<DuesTableVo> getDuesTable(
			@RequestParam(value="dt_year", required=true)  String dt_year,
			@RequestParam(value="dc_code", required=true)  String dc_code,
			@RequestParam(value="di_order", required=true) String di_order,
			@RequestParam(value="ac_code", required=true)  String ac_code) throws InvalidMemberException 
	{

		CommonResp<DuesTableVo> resp = 
					duesService.getDuesTable(dt_year, dc_code, di_order, ac_code);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	2. 회비항목요청
	////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/dues/item", method=RequestMethod.GET, name="02.회비항목요청")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-25", description="자동 구성")
	})
	public @ResponseBody CommonResp<DuesItemVo> getDuesItem(
			@RequestParam(value="di_order", required=true)  String di_order,
			@RequestParam(value="ac_code", required=true)  String ac_code) throws InvalidMemberException 
	{

		CommonResp<DuesItemVo> resp = 
					duesService.getDuesItem(di_order, ac_code);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 회비표요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/dues/table-list", method=RequestMethod.GET, name="03.회비표목록요청")
	@Session(required=true)
	@Histories({
			@History(date="2021-01-30", description="자동 구성")
	})
	public @ResponseBody CommonListResp<DuesTableValueVo> getDuesTableList(
			@RequestParam(value="dt_year", required=false)  String dt_year,
			@RequestParam(value="dc_code", required=false)  String dc_code,
			@RequestParam(value="ac_code", required=true)  String ac_code) throws InvalidMemberException 
	{

		CommonListResp<DuesTableValueVo> resp = 
					duesService.getDuesTableList(dt_year, dc_code, ac_code);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	4. 은행정보요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/dues/bankinformation", method=RequestMethod.GET, name="04.은행정보")
	@Session(required=true)
	@Histories({
			@History(date="2021-02-17", description="자동 구성")
	})
	public @ResponseBody CommonResp<BankInformationVo> getDuesTableList(
			@RequestParam(value="ac_code", required=true)  String ac_code) throws InvalidMemberException 
	{

		CommonResp<BankInformationVo> resp = 
					duesService.getBankInformation(ac_code);
		return resp;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	5. 은행정보요청
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/dues/bankinformation-list", method=RequestMethod.GET, name="05.은행정보")
	@Session(required=true)
	@Histories({
			@History(date="2021-02-17", description="자동 구성")
	})
	public @ResponseBody CommonListResp<BankInformationVo> getDuesTableList(
			@RequestParam(value="ac_code", required=false)  String ac_code,
			@RequestParam(value="bi_name", required=false)  String bi_name,
			@RequestParam(value="bi_owner", required=false)  String bi_owner,
			@RequestParam(value="bi_account", required=false)  String bi_account) throws InvalidMemberException
	{

		CommonListResp<BankInformationVo> resp = 
					duesService.listBankInformation(ac_code, bi_name, bi_owner, bi_account);
		return resp;
	}
}
