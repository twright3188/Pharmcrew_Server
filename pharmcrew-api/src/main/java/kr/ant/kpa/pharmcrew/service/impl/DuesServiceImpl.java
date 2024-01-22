package kr.ant.kpa.pharmcrew.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.DuesDao;
import kr.ant.kpa.pharmcrew.db.dao.PersonalDao;
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
import kr.ant.kpa.pharmcrew.service.DuesService;
import kr.ant.kpa.pharmcrew.service.PersonalService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;

@Service("duesService")
public class DuesServiceImpl implements DuesService {

	private static final Logger logger = LoggerFactory.getLogger(DuesServiceImpl.class);

	@Autowired
	private DuesDao duesDao;

	@Transactional("transactionManager")

	/**
	 * 1. 회비표 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<DuesTableVo> getDuesTable(String dt_year, String dc_code, String di_order, String ac_code)
			throws InvalidMemberException {
		CommonResp<DuesTableVo> resp = new CommonResp<DuesTableVo>();

		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "dt_year", dt_year);
		MapUtils.put(param, "dc_code", dc_code);
		MapUtils.put(param, "di_order", di_order);
		MapUtils.put(param, "ac_code", ac_code);

		DuesTableVo info = duesDao.selectDuesTable(param);
		if (info == null) {
			throw new InvalidMemberException();
		}

		resp.setData(info);
		return resp;
	}

	/**
	 * 2. 회비표 수정
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp saveDuesTable(DuesTableVo info) throws InvalidMemberException {
		PcResp resp = new PcResp();
		duesDao.updateDuesTable(info);
		return resp;
	}

	/**
	 * 3. 회비항목 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<DuesItemVo> getDuesItem(String di_order, String ac_code) throws InvalidMemberException {
		CommonResp<DuesItemVo> resp = new CommonResp<DuesItemVo>();

		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "di_order", di_order);
		MapUtils.put(param, "ac_code", ac_code);

		DuesItemVo info = duesDao.selectDuesItem(param);
		if (info == null) {
			throw new InvalidMemberException();
		}

		resp.setData(info);
		return resp;
	}

	/**
	 * 4. 회비항목 수정
	 * 
	 * @throws InvalidMemberException
	 **/
	public PcResp saveDuesItem(DuesItemVo info) throws InvalidMemberException {
		PcResp resp = new PcResp();
		duesDao.updateDuesItem(info);
		return resp;
	}
	
	/**
	 * 5. 회비표목록 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonListResp<DuesTableValueVo> getDuesTableList(String dt_year, String dc_code, String ac_code)
			throws InvalidMemberException {
		CommonListResp<DuesTableValueVo> resp = new CommonListResp<DuesTableValueVo>();

		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "dt_year", dt_year);
		MapUtils.put(param, "dc_code", dc_code);
		MapUtils.put(param, "ac_code", ac_code);
		if (!ac_code.isEmpty()) {
			MapUtils.put(param, "ac_code_up", ac_code.substring(0, 1) + "00");
		}		

		List<DuesTableValueVo> list = duesDao.listDuesTableByCode(param);

		resp.setData(list);
		return resp;
	}
	
	/**
	 * 6. 은행정보 요청
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonResp<BankInformationVo> getBankInformation(String ac_code)
			throws InvalidMemberException {
		CommonResp<BankInformationVo> resp = new CommonResp<BankInformationVo>();

		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "ac_code", ac_code);

		BankInformationVo info = duesDao.selectBankInformationTable(param);
		if (info == null) {
			throw new InvalidMemberException();
		}
		resp.setData(info);
		return resp;
	}
	
	/**
	 * 7. 은행정보 목록
	 * 
	 * @throws InvalidMemberException
	 **/
	public CommonListResp<BankInformationVo> listBankInformation(
			String ac_code, String bi_name, String bi_owner, String bi_account)
			throws InvalidMemberException {
		CommonListResp<BankInformationVo> resp = new CommonListResp<BankInformationVo>();

		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "ac_code", ac_code);
		MapUtils.put(param, "bi_owner", bi_owner);
		MapUtils.put(param, "bi_name", bi_name);
		MapUtils.put(param, "bi_account", bi_account);

		List<BankInformationVo> info = duesDao.listBankInformationTable(param);

		resp.setData(info);
		return resp;
	}

}
