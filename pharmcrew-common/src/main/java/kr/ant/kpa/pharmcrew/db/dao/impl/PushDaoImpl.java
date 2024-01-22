package kr.ant.kpa.pharmcrew.db.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.ant.kpa.pharmcrew.db.dao.PushDao;

import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushTokenVo;

@Repository
public class PushDaoImpl extends SqlSessionDaoSupport implements PushDao {

	private static final String PUSH = "kr.ant.kpa.pharmcrew.db.push.push-";
	private static final String PUSHTOKEN = "kr.ant.kpa.pharmcrew.db.push.pushtoken-";


	@Resource (name="sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	푸시
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPush(PushVo vo) { 
		getSqlSession().insert(PUSH + "insert", vo);
	}

	@Override
	public List<PushVo> listPush(Map<String, Object> param) { 
		return getSqlSession().selectList(PUSH + "select", param);
	}

	@Override
	public int countPush(Map<String, Object> param) { 
		return getSqlSession().selectOne(PUSH + "count", param);
	}

	@Override
	public PushVo selectPush(Map<String, Object> param) { 
		return getSqlSession().selectOne(PUSH + "select", param);
	}

	@Override
	public void updatePush(PushVo vo) { 
		getSqlSession().update(PUSH + "update", vo);
	}

	@Override
	public void deletePush(PushVo vo) { 
		getSqlSession().delete(PUSH + "delete", vo);
	}

	@Override
	public void updatePush(Map<String, Object> param) {
		getSqlSession().update(PUSH + "update-map", param);
	}

	@Override
	public void mergePush(PushVo vo) {
		getSqlSession().insert(PUSH + "merge", vo);
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	푸시 토큰
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void insertPushToken(PushTokenVo vo) { 
		getSqlSession().insert(PUSHTOKEN + "insert", vo);
	}

	@Override
	public List<PushTokenVo> listPushToken(Map<String, Object> param) { 
		return getSqlSession().selectList(PUSHTOKEN + "select", param);
	}

	@Override
	public int countPushToken(Map<String, Object> param) { 
		return getSqlSession().selectOne(PUSHTOKEN + "count", param);
	}

	@Override
	public PushTokenVo selectPushToken(Map<String, Object> param) { 
		return getSqlSession().selectOne(PUSHTOKEN + "select", param);
	}

	@Override
	public void updatePushToken(PushTokenVo vo) { 
		getSqlSession().update(PUSHTOKEN + "update", vo);
	}

	@Override
	public void deletePushToken(PushTokenVo vo) { 
		getSqlSession().delete(PUSHTOKEN + "delete", vo);
	}

	@Override
	public List<Long> listPushTokenMemberId(Map<String, Object> param) {
		return getSqlSession().selectList(PUSHTOKEN + "select-member-id", param);
	}

	@Override
	public List<String> listPushTokenToken(Map<String, Object> param) {
		return getSqlSession().selectList(PUSHTOKEN + "select-token", param);
	}

	@Override
	public void deletePushToken(Map<String, Object> param) {
		getSqlSession().delete(PUSHTOKEN + "delete-map", param);
	}
}

