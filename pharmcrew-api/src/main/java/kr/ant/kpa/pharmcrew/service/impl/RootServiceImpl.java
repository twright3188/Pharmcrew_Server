package kr.ant.kpa.pharmcrew.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.converter.MemberConverter;
import kr.ant.kpa.pharmcrew.converter.RootConverter;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.DuesDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.NewsDao;
import kr.ant.kpa.pharmcrew.db.dao.PartnersDao;
import kr.ant.kpa.pharmcrew.db.dao.PersonalDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.common.AppVersionVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberPartnerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushTokenVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.root.BannersResp;
import kr.ant.kpa.pharmcrew.resp.root.MainInfoResp;
import kr.ant.kpa.pharmcrew.resp.root.NoticesResp;
import kr.ant.kpa.pharmcrew.resp.root.PartnersResp;
import kr.ant.kpa.pharmcrew.resp.root.PopupsResp;
import kr.ant.kpa.pharmcrew.resp.root.UnreadResp;
import kr.ant.kpa.pharmcrew.resp.root.VersionResp;
import kr.ant.kpa.pharmcrew.service.RootService;

@Service("rootService")
public class RootServiceImpl implements RootService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommonDao commDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private PushDao pushDao;
	
	@Autowired
	private PartnersDao partnersDao;
	
	@Autowired
	private RootConverter converter;
	
	@Autowired
	private MemberConverter memberConverter;
	
	@Autowired
	private PersonalDao personalDao;
	
	@Autowired
	private DuesDao duesDao;


	@Transactional("transactionManager")

	/**
	*	1.버전 체크
	* @param		os - 요청 OS - A:android, I:iOS
	* @param		version - 현재 단말의 버전 정보 (1.1.1- 형식)
	**/
	public VersionResp getGetVersion(String os, String version ) {
		VersionResp resp =  new VersionResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "os_type", os);
		MapUtils.put(param, "open_yn", "Y");
		MapUtils.put(param, "req_version", version);
		MapUtils.put(param, "with_forced", true);			// forced_yn 정보 확인하기 위해서 
		MapUtils.put(param, "orderby", "ORIGIN.version desc");		// open_yn은  모두 "Y"로 해도 최후에 등록된 하나만 가지고 오기  
		MapUtils.pagingMySql(param, 1, 1);					// 최후의 1개 
		// 버전 정보 가져오기 
		AppVersionVo versionVo = commDao.selectAppVersion(param);
		
		// 정보 변환 하기 
		if (versionVo != null) {
			resp.setVersion(converter.convertVersion(versionVo));
			if ("A".equals(os)) {
				resp.getVersion().setLink("http://amytech.cafe24.com/pharmcrew.apk");
			}
		}
		
		return resp;
	}

	/**
	*	2.푸시 토큰 등록
	* @param		os - 요청 OS - A:android, I:iOS
	* @param		pushToken - 푸시 토큰
	**/
	public PcResp postTokenUpdate(String os, String pushToken, Long sessionId ) {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 토큰이 있는지 확인한다. 
		param.clear();
//		param.put("os_div", os);
		param.put("token", pushToken);
		PushTokenVo tokenVo = pushDao.selectPushToken(param);
		if (tokenVo == null) {
			// 저장된 토큰이 없는 경우 - 신규 추가 
			tokenVo = new PushTokenVo();
			tokenVo.setOs_div(os);
			tokenVo.setToken(pushToken);
			tokenVo.setUser_div("M");
			tokenVo.setUser_id(sessionId);
			pushDao.insertPushToken(tokenVo);
			
		} else {
			// OS 별로 1개만 허용?
//			param.clear();
//			param.put("os_div", os);
//			param.put("user_id", sessionId);
//			PushTokenVo userTokenVo = pushDao.selectPushToken(param);
//			
//			if (userTokenVo != null) {
//				pushDao.deletePushToken(userTokenVo);
//			}
			
			// 저장된 토큰이 있는 경우 - 사용자 정보 업데이트 하기 
			tokenVo.setOs_div(os);
			tokenVo.setUser_div("M");
			tokenVo.setUser_id(sessionId);
			pushDao.updatePushToken(tokenVo);
		}
		
		return resp;
	}

	/**
	*	3.메인 정보 요청
	**/
	public MainInfoResp getGetMain(Long sessionId ) {
		MainInfoResp resp =  new MainInfoResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 팝업 정보 가져오기 
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "IsProgress", true);
		MapUtils.put(param, "orderby", "ORIGIN.open_idx asc");
		List<PopupVo> popupVos = newsDao.listPopup(param);
		for (PopupVo popupVo : popupVos) {
			resp.addPopups(converter.convertAdvertise(popupVo));
		}
		
		// 배너 정보 가져오기 
		param.clear();
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "IsProgress", true);
		MapUtils.put(param, "orderby", "ORIGIN.open_idx asc");
		List<BannerVo> bannerVos = newsDao.listBanner(param);
		for (BannerVo bannerVo : bannerVos) {
			resp.addBanners(converter.convertAdvertise(bannerVo));
		}
		
		// 나에게 등록된 파트너 서비스 정보 가져오기 
		param.clear();
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt asc");
		MapUtils.put(param, "member_id", sessionId);
		List<MemberPartnerVo> partnersVos = memberDao.listMemberPartner(param);

		for (int i = 0; i < partnersVos.size(); i++) {
			MemberPartnerVo partnersVo = partnersVos.get(i);
			resp.addPartners(memberConverter.convertPartner(partnersVo, i));
		}
		
		// 1줄 공지 가져오기 - 현재는 3개로 회신 
		param.clear();
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");
		MapUtils.pagingMaridDb(param, 1, 3);
		List<NoticeVo> noticeVos = newsDao.listNotice(param);
		for (NoticeVo noticeVo : noticeVos) {
			resp.addNotices(converter.convertNotice(noticeVo));
		}
		
		// un-read Noti Count
		param.clear();
		MapUtils.put(param, "unread_member_id", sessionId);		// 읽지 않은 수 조회 아이디 
		int unReadCount = memberDao.countMemberNoti(param);
		resp.setUnReadNotiCount(unReadCount);
		
		return resp;
	}

	/**
	*	4.메인 팝업 정보 요청
	**/
	public PopupsResp getGetPopups(Long sessionId ) {
		PopupsResp resp =  new PopupsResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "IsProgress", true);
		MapUtils.put(param, "orderby", "ORIGIN.open_idx asc");
		List<PopupVo> popupVos = newsDao.listPopup(param);
		for (PopupVo popupVo : popupVos) {
			resp.addPopups(converter.convertAdvertise(popupVo));
		}

		return resp;
	}

	/**
	*	5.메인 배너 정보 요청
	**/
	public BannersResp getGetBanners(Long sessionId ) {
		BannersResp resp =  new BannersResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "IsProgress", true);
		MapUtils.put(param, "orderby", "ORIGIN.open_idx asc");
		List<BannerVo> bannerVos = newsDao.listBanner(param);
		for (BannerVo bannerVo : bannerVos) {
			resp.addBanners(converter.convertAdvertise(bannerVo));
		}
		
		return resp;
	}

	/**
	*	6.메인 파트너 서비스 요청
	**/
	public PartnersResp getGetPartners(Long sessionId ) {
		PartnersResp resp =  new PartnersResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 나에게 등록된 파트너 서비스 정보 가져오기 
		param.clear();
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt asc");
		MapUtils.put(param, "member_id", sessionId);
		List<MemberPartnerVo> partnersVos = memberDao.listMemberPartner(param);

		for (int i = 0; i < partnersVos.size(); i++) {
			MemberPartnerVo partnersVo = partnersVos.get(i);
			resp.addPartners(memberConverter.convertPartner(partnersVo, i));
		}

		return resp;
	}

	/**
	*	7.메인 한줄 공지 요청
	**/
	public NoticesResp getGetNotices(Long sessionId ) {
		NoticesResp resp =  new NoticesResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// 현재는 3개로 회신 
		MapUtils.put(param, "is_open", "Y");
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");
		MapUtils.pagingMaridDb(param, 1, 3);
		List<NoticeVo> noticeVos = newsDao.listNotice(param);
		for (NoticeVo noticeVo : noticeVos) {
			resp.addNotices(converter.convertNotice(noticeVo));
		}
		
		return resp;
	}

	/**
	*	8.읽지않은 알림숫자 요청
	**/
	public UnreadResp getGetUnReads(Long sessionId ) {
		UnreadResp resp =  new UnreadResp();
		Map<String, Object> param = new HashMap<String, Object>();

		// un-read Noti Count
		MapUtils.put(param, "unread_member_id", sessionId);		// 읽지 않은 수 조회 아이디 
		int unReadCount = memberDao.countMemberNoti(param);
		resp.setUnReadNotiCount(unReadCount);

		return resp;
	}

}
