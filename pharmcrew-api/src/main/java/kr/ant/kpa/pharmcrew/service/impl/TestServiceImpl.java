package kr.ant.kpa.pharmcrew.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushTokenVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.notify.NotifyHelper;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.test.FileUploadResp;
import kr.ant.kpa.pharmcrew.service.TestService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommonDao commDao;
	
	@Autowired
	private PushDao pushDao;

	@Autowired
	private Storage storage;
	@Autowired
	private PcUtils pcUtils;
	
	@Autowired
	private NotifyHelper notifyHelper;


	@Transactional("transactionManager")

	/**
	*	1.파일 등록
	* @param		file - 이미지 파일
	 * @throws FailSaveFileException 
	**/
	public FileUploadResp postUploadFile(MultipartFile image) 
			throws FailSaveFileException {
		FileUploadResp resp =  new FileUploadResp();
		Map<String, Object> param = new HashMap<String, Object>();

		File file = pcUtils.save(image);
		
		long fileId = storage.save(file, image.getOriginalFilename(), image.getSize()/*, FILE.P*/);
		
		
		// 파일 정보 확인해서 response 
		param.clear();
		MapUtils.put(param, "file_id", fileId);
		FileVo fileVo = commDao.selectFile(param);
		if (fileVo != null) {
			resp.setFildId(fileVo.getFile_id());
			resp.setImgUrl(storage.fileUrl(fileVo));
		}
				
		return resp;
	}


	/**
	*	100. 사용자 푸시 전송
	* @param		os - 요청 OS - A:android, I:iOS - 없으면 전체
	* @param		title - 전송할 메시지 타이틀
	* @param		body - 전송할 메시 내용
	* @param		imageUrl - 푸시에 보여질 이미지 링크
	* @param		imageFile - 푸시에 보여질 이미지 파일
	* @param		category - AD: 광고, NT: 알림
	* @param		moveType - WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId - 이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl - 이동할 URL
	 * @throws FailSaveFileException 
	**/
	public PcResp postSendPushMember(String os, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl, Long sessionId ) 
			throws FailSaveFileException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		PushVo pushVo = new PushVo();
		pushVo.setOs_div(os);
		pushVo.setTitle(title);
		pushVo.setBody(body);

		Long fileId = null;
		if (imageFile != null && imageFile.getSize() != 0) {
			File file = pcUtils.save(imageFile);
			fileId = storage.save(file, imageFile.getOriginalFilename(), imageFile.getSize());
			pushVo.setImg_file_id(fileId);
		}

		pushVo.setCategory_div(category);
		pushVo.setMove_div(moveType);
		pushVo.setMove_id(moveId);
		pushVo.setMove_url(moveUrl);
		
		pushVo.setState_div("S");

		pushVo.setReg_id(1L);
		
		// 푸시 등록
		pushDao.insertPush(pushVo);

		// 여기서 바로 전송 호출
		List<Long> memberIds = new ArrayList<Long>();
		memberIds.add(sessionId);
		
		List<String> tokens = new ArrayList<String>(); 
		MapUtils.put(param, "user_id", sessionId);
		List<PushTokenVo> tokenVos = pushDao.listPushToken(param);
		for (PushTokenVo tokenVo : tokenVos) {
			tokens.add(tokenVo.getToken());
		}
		
		notifyHelper.sendDirect(pushVo, memberIds, tokens, imageUrl);
		
		return resp;
	}

	/**
	*	101. 토큰으로 푸시 전송
	* @param		token - 푸시 수신할 토큰
	* @param		os - 요청 OS - A:android, I:iOS
	* @param		title - 전송할 메시지 타이틀
	* @param		body - 전송할 메시 내용
	* @param		imageUrl - 푸시에 보여질 이미지 링크
	* @param		imageFile - 푸시에 보여질 이미지 파일
	* @param		category - AD: 광고, NT: 알림
	* @param		moveType - WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId - 이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl - 이동할 URL
	 * @throws FailSaveFileException 
	**/
	public PcResp postSendPushToken(String token, String os, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl, Long sessi1onId )
			throws FailSaveFileException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		PushVo pushVo = new PushVo();
		pushVo.setOs_div(os);
		pushVo.setTitle(title);
		pushVo.setBody(body);

		Long fileId = null;
		if (imageFile != null && imageFile.getSize() != 0) {
			File file = pcUtils.save(imageFile);
			fileId = storage.save(file, imageFile.getOriginalFilename(), imageFile.getSize());
			pushVo.setImg_file_id(fileId);
		}

		pushVo.setCategory_div(category);
		pushVo.setMove_div(moveType);
		pushVo.setMove_id(moveId);
		pushVo.setMove_url(moveUrl);
		
		pushVo.setState_div("R");

		pushVo.setReg_id(1L);
		
		// 푸시 등록
		pushDao.insertPush(pushVo);

		// 여기서 바로 전송 호출
//		
//		
//		List<String> tokens = new ArrayList<String>(); 
//		tokens.add(token);
//		
//		notifyHelper.sendDirect(pushVo, null, tokens, imageUrl);
		
		return resp;
	}

	/**
	*	103. 면허번호로 전송
	* @param		licenseNo - 전송할 멤버 라이선스 번호
	* @param		title - 전송할 메시지 타이틀
	* @param		body - 전송할 메시 내용
	* @param		imageUrl - 푸시에 보여질 이미지 링크
	* @param		imageFile - 푸시에 보여질 이미지 파일
	* @param		category - AD: 광고, NT: 알림
	* @param		moveType - WU(웹), NO(공지사항), ED(교육), SU(설문)
	* @param		moveId - 이동할 ID(공지사항ID, 교육ID, 설문ID)
	* @param		moveUrl - 이동할 URL
	 * @throws FailSaveFileException 
	**/
	public PcResp postSendPushLicense(String licenseNo, String title, String body, String imageUrl, MultipartFile imageFile, String category, String moveType, Long moveId, String moveUrl ) 
			throws FailSaveFileException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "license_no", licenseNo);
		MemberVo memberVo = memberDao.selectMember(param);
		if (memberVo == null) {
			throw new NotFoundException();
		}
		
		PushVo pushVo = new PushVo();

		pushVo.setTitle(title);
		pushVo.setBody(body);

		Long fileId = null;
		if (imageFile != null && imageFile.getSize() != 0) {
			File file = pcUtils.save(imageFile);
			fileId = storage.save(file, imageFile.getOriginalFilename(), imageFile.getSize());
			pushVo.setImg_file_id(fileId);
		}

		pushVo.setCategory_div(category);
		pushVo.setMove_div(moveType);
		pushVo.setMove_id(moveId);
		pushVo.setMove_url(moveUrl);
		
		pushVo.setState_div("S");

		pushVo.setReg_id(1L);
		
		// 푸시 등록
		pushDao.insertPush(pushVo);

		// 여기서 바로 전송 호출
		List<Long> memberIds = new ArrayList<Long>();
		memberIds.add(memberVo.getMember_id());
		
		List<String> tokens = new ArrayList<String>(); 
		MapUtils.put(param, "user_id", memberVo.getMember_id());
		List<PushTokenVo> tokenVos = pushDao.listPushToken(param);
		for (PushTokenVo tokenVo : tokenVos) {
			tokens.add(tokenVo.getToken());
		}
		
		notifyHelper.sendDirect(pushVo, memberIds, tokens, imageUrl);
		
		return resp;
	}

}
