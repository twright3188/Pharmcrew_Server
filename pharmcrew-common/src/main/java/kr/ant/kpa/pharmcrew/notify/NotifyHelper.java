package kr.ant.kpa.pharmcrew.notify;

import com.bumdori.fcm.AbsFcmSender;
import com.bumdori.spring.BLogger;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PtaxDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.fcm.FcmSender;
import kr.ant.kpa.pharmcrew.type.news.MOVE;
import kr.ant.kpa.pharmcrew.type.notify.PUSH_CATEGORY;
import kr.ant.kpa.pharmcrew.type.notify.PUSH_STATE;
import kr.ant.kpa.pharmcrew.type.user.USER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotifyHelper {
    private static final Logger logger = LoggerFactory.getLogger(NotifyHelper.class);

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PushDao pushDao;
    @Autowired
    private PtaxDao ptaxDao;

    @Autowired
    private FcmSender fcmSender;

    @Autowired
    private Storage storage;

    @Transactional
    public void send(PushVo pushVo) {
        send(pushVo.getPush_id(),
                pushVo.getTitle(), pushVo.getBody(), pushVo.getImgFile() != null ? storage.fileUrl(pushVo.getImgFile()) : null,
                PUSH_CATEGORY.valueOf(pushVo.getCategory_div()), pushVo.getMove_div(), pushVo.getMove_url(), pushVo.getMove_id(),
                pushVo.getOrganize_id(), pushVo.getOs_div(), null);

        pushVo.setState_div(PUSH_STATE.S.name());
        pushVo.setSendedDtNow(true);
        pushDao.updatePush(pushVo);
    }

    /**
     *
     * 호출 하는 곳에서 @Transational
     * @param pushId
     * @param title
     * @param body
     * @param imgUrl
     * @param category
     * @param move
     * @param moveUrl
     * @param moveId
     * @param organizeId
     * @param os
     * @param memberIds
     */
//    @Transactional
    public void send(Long pushId,
                     String title, String body, String imgUrl,
                     PUSH_CATEGORY category, String moveDiv, String moveUrl, Long moveId,
                     Long organizeId, String os, List<Long> memberIds) {
        Map<String, Object> param = new HashMap<>();

//        PUSH_CATEGORY category = PUSH_CATEGORY.valueOf(pushVo.getCategory_div());
//        List<Long> memberIds = null;
        if (memberIds == null) {
            switch (category) {
                case PT:
//                    MapUtils.put(param, "notice_id", pushVo.getMove_id());
                    MapUtils.put(param, "notice_id", moveId);
                    PtaxNoticeVo noticeVo = ptaxDao.selectPtaxNotice(param);

                    param.clear();
                    MapUtils.put(param, "user_div", USER.M.name());
//                    MapUtils.put(param, "os_div", pushVo.getOs_div());  // os
                    MapUtils.put(param, "os_div", os);  // os
                    if (noticeVo.getTarget_div() != null) {
                        switch (noticeVo.getTarget_div()) {
                            case "M":
                                MapUtils.put(param, "ptax_yn", 'Y');
                                break;
                            case "G":
                                MapUtils.put(param, "ptax_yn", 'N');
                                break;
                        }
                    }
                    MapUtils.put(param, "pharm_name", noticeVo.getTarget_pharm_name());
                    MapUtils.put(param, "member_id", noticeVo.getTarget_member_id());
                    MapUtils.put(param, "push_yn", "Y");
                    break;
                default:
                    // 대상 member_id
                    param.clear();
                    MapUtils.put(param, "user_div", USER.M.name());
//                    MapUtils.put(param, "os_div", pushVo.getOs_div());  // os
                    MapUtils.put(param, "os_div", os);  // os
//                    MapUtils.put(param, "pathDownOrganizeId", pushVo.getOrganize_id()); // 조직(하위 포함)
                    MapUtils.put(param, "pathDownOrganizeId", organizeId); // 조직(하위 포함)
                    MapUtils.put(param, "push_yn", "Y");
                    if (category == PUSH_CATEGORY.AD) {
                        MapUtils.put(param, "ad_push_yn", "Y");
                    }
                    break;
            }
            memberIds = pushDao.listPushTokenMemberId(param);
            BLogger.debug(logger, "memberIds: {}", memberIds);
        }
        List<Long> tmp = new ArrayList<>();
        for (Long memberId: memberIds) {
            if (!tmp.contains(memberId))    tmp.add(memberId);
        }
        BLogger.debug(logger, "tmp: {}", tmp);
        memberIds = tmp;

        if (!ListUtils.isEmpty(memberIds)) {
            // 대상 member에 대한 push token
            param.clear();
            MapUtils.put(param, "user_div", USER.M.name());
            MapUtils.put(param, "userIds", memberIds);
            MapUtils.put(param, "os_div", os);  // os
            List<String> tokens = pushDao.listPushTokenToken(param);

            // t_member_noti
            param.clear();
            MapUtils.put(param, "memberIds", memberIds);
//            MapUtils.put(param, "push_id", pushVo.getPush_id());
            MapUtils.put(param, "push_id", pushId);
//            MapUtils.put(param, "title", pushVo.getTitle());
            MapUtils.put(param, "title", title);
//            MapUtils.put(param, "body", pushVo.getBody());
            MapUtils.put(param, "body", body);
//            MapUtils.put(param, "category_div", pushVo.getCategory_div());
            MapUtils.put(param, "category_div", category.name());
//            MapUtils.put(param, "move_div", pushVo.getMove_div());
            MapUtils.put(param, "move_div", moveDiv);
//            MapUtils.put(param, "move_url", pushVo.getMove_url());
            MapUtils.put(param, "move_url", moveUrl);
//            MapUtils.put(param, "move_id", pushVo.getMove_id());
            MapUtils.put(param, "move_id", moveId);
            MapUtils.put(param, "reg_dt", new Date());
            memberDao.insertMemberNotiList(param);

            AbsFcmSender.FcmData fcmData = new AbsFcmSender.FcmData()
//                    .setTitle(pushVo.getTitle())
//                    .setBody(pushVo.getBody())
//                    .addData("title", pushVo.getTitle())
                    .addData("title", title)
//                    .addData("body", pushVo.getBody())
                    .addData("body", body)
//                    .addData("push_id", pushVo.getPush_id().toString())
                    .addData("push_id", pushId == null ? null : pushId.toString())
//                    .addData("move_div", pushVo.getMove_div())
                    .addData("move_div", moveDiv)
//                    .addData("move_url", pushVo.getMove_url());
                    .addData("move_url", moveUrl)
                    .addData("move_id", moveId == null ? null : moveId.toString())
                    .addData("imgUrl", StringUtils.isEmpty(imgUrl)? (Config.Inst.localStorageUrl()+"ios_none_empty.png"):imgUrl);	// 아이폰 푸시 처리를 위한 테스트 이미지 url 추가 
                    ;

            fcmData.setRegistrationToken(tokens);
            fcmSender.send(fcmData);
        }
    }
    
    /**
     * 푸시 직접 전송하기 위한 함수 
     * @param pushVo
     * @param memberIds
     * @param tokens
     */
    @Transactional
    public void sendDirect(PushVo pushVo, List<Long> memberIds, List<String> tokens, String imageUrl) {
    	Map<String, Object> param = new HashMap<>();

    	if (memberIds != null && memberIds.size()>0) {
    		// t_member_noti insert
    		param.clear();
    		MapUtils.put(param, "memberIds", memberIds);
    		for (Long memberId: memberIds) {
    			MapUtils.put(param, "member_id", memberId);
    			MapUtils.put(param, "push_id", pushVo.getPush_id());
    			MapUtils.put(param, "title", pushVo.getTitle());
    			MapUtils.put(param, "body", pushVo.getBody());
    			MapUtils.put(param, "category_div", pushVo.getCategory_div());
    			MapUtils.put(param, "move_div", pushVo.getMove_div());
    			MapUtils.put(param, "move_url", pushVo.getMove_url());
    			MapUtils.put(param, "move_id", pushVo.getMove_id());
    			MapUtils.put(param, "reg_dt", new Date());
    		}
    		memberDao.insertMemberNotiList(param);
    	}
    	AbsFcmSender.FcmData fcmData = new AbsFcmSender.FcmData()
    			.addData("title", pushVo.getTitle())
    			.addData("body", pushVo.getBody())
    			.addData("push_id", pushVo.getPush_id().toString())
    			.addData("move_div", pushVo.getMove_div())
    			.addData("move_url", pushVo.getMove_url());

    	if (pushVo.getMove_id() != null) {
    		fcmData.addData("move_id", pushVo.getMove_id().toString());
    	}
    	if (pushVo.getImgFile() != null) {
    		fcmData.addData("imgUrl", storage.fileUrl(pushVo.getImgFile()));
    	} else if (!StringUtils.isEmpty(imageUrl)) {
    		fcmData.addData("imgUrl", imageUrl);
    	} else {
    		fcmData.addData("imgUrl", Config.Inst.localStorageUrl()+"ios_none_empty.png");	// 아이폰 푸시 처리를 위한 테스트 이미지 url 추가
    	}

    	fcmData.setRegistrationToken(tokens);
    	fcmSender.send(fcmData);
    }
}
