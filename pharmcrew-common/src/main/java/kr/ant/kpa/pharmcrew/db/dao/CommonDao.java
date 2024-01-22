package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.AffiliationCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.AppVersionVo;
import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;
import kr.ant.kpa.pharmcrew.db.vo.common.UniversityCodeVo;

public interface CommonDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	조직
	////////////////////////////////////////////////////////////////////////////////////////

	void insertOrganize(OrganizeVo vo); 
	List<OrganizeVo> listOrganize(Map<String, Object> param); 
	int countOrganize(Map<String, Object> param); 
	OrganizeVo selectOrganize(Map<String, Object> param); 
	void updateOrganize(OrganizeVo vo); 
	void deleteOrganize(OrganizeVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	파일
	////////////////////////////////////////////////////////////////////////////////////////

	void insertFile(FileVo vo); 
	List<FileVo> listFile(Map<String, Object> param); 
	int countFile(Map<String, Object> param); 
	FileVo selectFile(Map<String, Object> param); 
	void updateFile(FileVo vo); 
	void deleteFile(FileVo vo); 

	void deleteFile(Map<String, Object> param);

	////////////////////////////////////////////////////////////////////////////////////////
	//	버전
	////////////////////////////////////////////////////////////////////////////////////////

	void insertAppVersion(AppVersionVo vo); 
	List<AppVersionVo> listAppVersion(Map<String, Object> param); 
	int countAppVersion(Map<String, Object> param); 
	AppVersionVo selectAppVersion(Map<String, Object> param); 
	void updateAppVersion(AppVersionVo vo); 
	void deleteAppVersion(AppVersionVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	QR
	////////////////////////////////////////////////////////////////////////////////////////

	void insertQr(QrVo vo); 
	List<QrVo> listQr(Map<String, Object> param); 
	int countQr(Map<String, Object> param); 
	QrVo selectQr(Map<String, Object> param); 
	void updateQr(QrVo vo); 
	void deleteQr(QrVo vo); 
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	분회
	////////////////////////////////////////////////////////////////////////////////////////
	List<AffiliationCodeVo> listAffiliation(Map<String, Object> param);
	AffiliationCodeVo selectAffiliation(Map<String, Object> param);
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	대학교코드
	////////////////////////////////////////////////////////////////////////////////////////
	List<UniversityCodeVo> listUniversity(Map<String, Object> param);
	UniversityCodeVo selectUniversity(Map<String, Object> param);


}

