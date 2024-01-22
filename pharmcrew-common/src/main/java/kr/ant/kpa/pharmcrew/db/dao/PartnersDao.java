package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;

public interface PartnersDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	파트너 서비스
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPartners(PartnersVo vo); 
	List<PartnersVo> listPartners(Map<String, Object> param); 
	int countPartners(Map<String, Object> param); 
	PartnersVo selectPartners(Map<String, Object> param); 
	void updatePartners(PartnersVo vo); 
	void deletePartners(PartnersVo vo); 


}

