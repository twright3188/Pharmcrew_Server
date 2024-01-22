package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.dues.BankInformationVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesItemVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableValueVo;
import kr.ant.kpa.pharmcrew.db.vo.dues.DuesTableVo;

public interface DuesDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	회비표
	////////////////////////////////////////////////////////////////////////////////////////

	List<DuesTableVo> 	listDuesTable(Map<String, Object> param); 
	DuesTableVo  		selectDuesTable(Map<String, Object> param); 
	void 				updateDuesTable(DuesTableVo vo);
	void 				insertDuesTable(DuesTableVo vo);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	회비항목들
	////////////////////////////////////////////////////////////////////////////////////////

	List<DuesItemVo> 	listDuesItem(Map<String, Object> param); 
	DuesItemVo 			selectDuesItem(Map<String, Object> param); 
	void 				updateDuesItem(DuesItemVo vo);
	void 				insertDuesItem(DuesItemVo vo);
	
	List<DuesTableValueVo>	listDuesTableByCode(Map<String, Object> param);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	은행정보
	////////////////////////////////////////////////////////////////////////////////////////
	List<BankInformationVo> 	listBankInformationTable(Map<String, Object> param); 
	BankInformationVo  		selectBankInformationTable(Map<String, Object> param); 
}

