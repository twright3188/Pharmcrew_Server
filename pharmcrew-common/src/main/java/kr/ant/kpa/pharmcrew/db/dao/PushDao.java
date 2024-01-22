package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushTokenVo;

public interface PushDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	푸시
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPush(PushVo vo); 
	List<PushVo> listPush(Map<String, Object> param); 
	int countPush(Map<String, Object> param); 
	PushVo selectPush(Map<String, Object> param); 
	void updatePush(PushVo vo); 
	void deletePush(PushVo vo);

	void updatePush(Map<String, Object> param);
	void mergePush(PushVo vo);


	////////////////////////////////////////////////////////////////////////////////////////
	//	푸시 토큰
	////////////////////////////////////////////////////////////////////////////////////////

	void insertPushToken(PushTokenVo vo); 
	List<PushTokenVo> listPushToken(Map<String, Object> param); 
	int countPushToken(Map<String, Object> param); 
	PushTokenVo selectPushToken(Map<String, Object> param); 
	void updatePushToken(PushTokenVo vo); 
	void deletePushToken(PushTokenVo vo);

	/**
	 * 조건에 맞는 푸시 토큰을 가진 회원 ID 리스트
	 * @param param	user_div: USER.java<br>
	 *              os_div: OS 구분, I(iOS), A(Android)<br>
	 *              pathDownOrganizeId: 조직 ID(하위 포함)<br>
	 *              push_yn: 푸시 수신 여부
	 * @return
	 * @see kr.ant.kpa.pharmcrew.type.user.USER
	 */
	List<Long> listPushTokenMemberId(Map<String, Object> param);
	/**
	 * 조건에 맞는 푸시 토큰
	 * @param param	user_div: USER.java<br>
	 *              userIds: 사용자 ID 리스트
	 * @return
	 * @see kr.ant.kpa.pharmcrew.type.user.USER
	 */
	List<String> listPushTokenToken(Map<String, Object> param);
	void deletePushToken(Map<String, Object> param);

}

