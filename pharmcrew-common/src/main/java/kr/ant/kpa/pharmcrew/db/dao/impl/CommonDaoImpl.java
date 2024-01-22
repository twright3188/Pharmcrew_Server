package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.AffiliationCodeVo;
import kr.ant.kpa.pharmcrew.db.vo.common.AppVersionVo;
import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;
import kr.ant.kpa.pharmcrew.db.vo.common.UniversityCodeVo;

@Repository
public class CommonDaoImpl extends SqlSessionDaoSupport implements CommonDao {

	private static final String ORGANIZE = "kr.ant.kpa.pharmcrew.db.common.organize-";
	private static final String FILE = "kr.ant.kpa.pharmcrew.db.common.file-";
	private static final String APPVERSION = "kr.ant.kpa.pharmcrew.db.common.appversion-";
	private static final String QR = "kr.ant.kpa.pharmcrew.db.common.qr-";
	private static final String AFFILIATION = "kr.ant.kpa.pharmcrew.db.common.affiliation-";
	private static final String UNINVERSITY = "kr.ant.kpa.pharmcrew.db.common.university-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	조직
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertOrganize(OrganizeVo vo) { 
		getSqlSession().insert(ORGANIZE + "insert", vo);
	}

	@Override
	public List<OrganizeVo> listOrganize(Map<String, Object> param) { 
		return getSqlSession().selectList(ORGANIZE + "select", param);
	}

	@Override
	public int countOrganize(Map<String, Object> param) { 
		return getSqlSession().selectOne(ORGANIZE + "count", param);
	}

	@Override
	public OrganizeVo selectOrganize(Map<String, Object> param) { 
		return getSqlSession().selectOne(ORGANIZE + "select", param);
	}

	@Override
	public void updateOrganize(OrganizeVo vo) { 
		getSqlSession().update(ORGANIZE + "update", vo);
	}

	@Override
	public void deleteOrganize(OrganizeVo vo) { 
		getSqlSession().delete(ORGANIZE + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	파일
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertFile(FileVo vo) { 
		getSqlSession().insert(FILE + "insert", vo);
	}

	@Override
	public List<FileVo> listFile(Map<String, Object> param) { 
		return getSqlSession().selectList(FILE + "select", param);
	}

	@Override
	public int countFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(FILE + "count", param);
	}

	@Override
	public FileVo selectFile(Map<String, Object> param) { 
		return getSqlSession().selectOne(FILE + "select", param);
	}

	@Override
	public void updateFile(FileVo vo) { 
		getSqlSession().update(FILE + "update", vo);
	}

	@Override
	public void deleteFile(FileVo vo) { 
		getSqlSession().delete(FILE + "delete", vo);
	}

	@Override
	public void deleteFile(Map<String, Object> param) {
		getSqlSession().delete(FILE + "delete-map", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	버전
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertAppVersion(AppVersionVo vo) { 
		getSqlSession().insert(APPVERSION + "insert", vo);
	}

	@Override
	public List<AppVersionVo> listAppVersion(Map<String, Object> param) { 
		return getSqlSession().selectList(APPVERSION + "select", param);
	}

	@Override
	public int countAppVersion(Map<String, Object> param) { 
		return getSqlSession().selectOne(APPVERSION + "count", param);
	}

	@Override
	public AppVersionVo selectAppVersion(Map<String, Object> param) { 
		return getSqlSession().selectOne(APPVERSION + "select", param);
	}

	@Override
	public void updateAppVersion(AppVersionVo vo) { 
		getSqlSession().update(APPVERSION + "update", vo);
	}

	@Override
	public void deleteAppVersion(AppVersionVo vo) { 
		getSqlSession().delete(APPVERSION + "delete", vo);
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	QR
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertQr(QrVo vo) { 
		getSqlSession().insert(QR + "insert", vo);
	}

	@Override
	public List<QrVo> listQr(Map<String, Object> param) { 
		return getSqlSession().selectList(QR + "select", param);
	}

	@Override
	public int countQr(Map<String, Object> param) { 
		return getSqlSession().selectOne(QR + "count", param);
	}

	@Override
	public QrVo selectQr(Map<String, Object> param) { 
		return getSqlSession().selectOne(QR + "select", param);
	}

	@Override
	public void updateQr(QrVo vo) { 
		getSqlSession().update(QR + "update", vo);
	}

	@Override
	public void deleteQr(QrVo vo) { 
		getSqlSession().delete(QR + "delete", vo);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	분회
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<AffiliationCodeVo> listAffiliation(Map<String, Object> param) { 
		return getSqlSession().selectList(AFFILIATION + "select", param);
	}

	@Override
	public AffiliationCodeVo selectAffiliation(Map<String, Object> param) { 
		return getSqlSession().selectOne(AFFILIATION + "select", param);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	대학교코드
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<UniversityCodeVo> listUniversity(Map<String, Object> param) { 
		return getSqlSession().selectList(UNINVERSITY + "select", param);
	}

	@Override
	public UniversityCodeVo selectUniversity(Map<String, Object> param) { 
		return getSqlSession().selectOne(UNINVERSITY + "select", param);
	}

}

