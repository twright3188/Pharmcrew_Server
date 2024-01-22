package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;

public interface AdminDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	관리자
	////////////////////////////////////////////////////////////////////////////////////////

	void insertAdmin(AdminVo vo); 
	List<AdminVo> listAdmin(Map<String, Object> param); 
	int countAdmin(Map<String, Object> param); 
	AdminVo selectAdmin(Map<String, Object> param); 
	void updateAdmin(AdminVo vo); 
	void deleteAdmin(AdminVo vo); 


}

