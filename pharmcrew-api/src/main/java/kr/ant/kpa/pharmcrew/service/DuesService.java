package kr.ant.kpa.pharmcrew.service;

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
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidMemberException;

public interface DuesService {

	/**
	*	1. 회비표 요청
	**/
	CommonResp<DuesTableVo> getDuesTable(String dt_year, String dc_code,  String di_order, String ac_code)
			throws InvalidMemberException ;
	/**
	*	2. 회비표 수정
	**/
	PcResp saveDuesTable(DuesTableVo duesTable)
			throws InvalidMemberException ;
	
	/**
	*	3. 회비항목 요청
	**/
	CommonResp<DuesItemVo> getDuesItem(String di_order, String ac_code  )
			throws InvalidMemberException ;
	/**
	*	4. 회비항목 수정
	**/
	PcResp saveDuesItem(DuesItemVo duesItem)
			throws InvalidMemberException ;
	/**
	*	5. 회비표목록 요청
	**/
	CommonListResp<DuesTableValueVo> getDuesTableList(String dt_year, String dc_code,  String ac_code)
			throws InvalidMemberException ;
	/**
	 * 	6. 은행정보 요청
	 */
	CommonResp<BankInformationVo> getBankInformation(String ac_code)
			throws InvalidMemberException ;

	/**
	 * 	7. 은행정보 목록
	 */
	CommonListResp<BankInformationVo> listBankInformation(String ac_code, String bi_name, String bi_owner, String bi_account)
			throws InvalidMemberException;
}
