package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.fcm.AbsFcmSender;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.PushConverter;
import kr.ant.kpa.pharmcrew.data.facade.education.EducationFacade;
import kr.ant.kpa.pharmcrew.data.facade.news.NoticeFacade;
import kr.ant.kpa.pharmcrew.data.facade.survey.SurveyFacade;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberNotiVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.fcm.FcmSender;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.push.PushListResp;
import kr.ant.kpa.pharmcrew.resp.push.PushResp;
import kr.ant.kpa.pharmcrew.service.PushService;
import kr.ant.kpa.pharmcrew.type.news.MOVE;
import kr.ant.kpa.pharmcrew.type.user.USER;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PushServiceImpl implements PushService {
    @Autowired
    private NoticeFacade noticeFacade;
    @Autowired
    private EducationFacade educationFacade;
    @Autowired
    private SurveyFacade surveyFacade;

    @Autowired
    private PushDao pushDao;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PushConverter pushConverter;

    @Autowired
    private FcmSender fcmSender;

    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;

    @Transactional
    @Override
    public PcResp registPush(String title, String body, MultipartFile imgFile, String reserveDate, String reserveHour, String reserveMinute, Long organizeId, String osType, String categoryType, String moveType, Long moveId, String moveUrl, Long regId) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException {
        if (moveType != null) {
            switch (MOVE.valueOf(moveType)) {
                case WU:
                    if (StringUtils.isEmpty(moveUrl))
                        throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                    break;
                case NO:
                    if (moveId == null)
                        throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'NO'");
                    if (noticeFacade.get(moveId) == null)
                        throw new NotFoundNoticeException();
                    break;
                case ED:
                    if (moveId == null)
                        throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'ED'");
                    if (educationFacade.get(moveId) == null)
                        throw new NotFoundEducationException();
                    break;
                case SU:
                    if (moveId == null)
                        throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
                    if (surveyFacade.get(moveId) == null)
                        throw new NotFoundSurveyException();
                    break;
            }
        }

        Long fileId = null;
        if (imgFile != null) {
            File file = pcUtils.save(imgFile);
            fileId = storage.save(file, imgFile.getOriginalFilename(), imgFile.getSize());
        }

        PushVo pushVo = new PushVo();
        pushVo.setTitle(title);
        pushVo.setBody(body);
        pushVo.setImg_file_id(fileId);
        pushVo.setOs_div(osType);
        pushVo.setOrganize_id(organizeId);
        pushVo.setCategory_div(categoryType);
        pushVo.setMove_div(moveType);
        pushVo.setMove_id(moveId);
        pushVo.setMove_url(moveUrl);
        if (reserveDate != null && reserveHour != null && reserveMinute != null) {
            try {
                // Controller에서 검증
                pushVo.setReserv_dt(pcUtils.dateHMFormat.parse(String.format("%s-%s-%s", reserveDate, reserveHour, reserveMinute)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        pushVo.setReg_id(regId);
        pushDao.insertPush(pushVo);

        // TODO exception 발생 시 imgFile 지워야 한다.

        return new PcResp();
    }

    @Override
    public PushListResp pushList(String reservDtStartDate, String reservDtEndDate, String keyword, Long organizeId, String categoryType, String sendType, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        if (!StringUtils.isEmpty(reservDtStartDate)) {
            try {
                // Controller에서 검증
//                MapUtils.put(param, "reservDtStartDate", pcUtils.dateFormat.parse(reservDtStartDate));
                MapUtils.put(param, "regDtStartDate", pcUtils.dateFormat.parse(reservDtStartDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(reservDtEndDate)) {
            try {
                // Controller에서 검증
//                MapUtils.put(param, "reservDtEndDate", pcUtils.dateFormat.parse(reservDtEndDate));
                MapUtils.put(param, "regDtEndDate", pcUtils.dateFormat.parse(reservDtEndDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        MapUtils.put(param, "keyword", keyword);
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "category_div", categoryType);
        if (!StringUtils.isEmpty(sendType)) {
            switch (sendType) {
                case "R":
                    MapUtils.put(param, "reservDtIsNotNull", true);
                    break;
                case "I":
                    MapUtils.put(param, "reservDtIsNull", true);
                    break;
            }
        }
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.put(param, "withSRCnt", true);
        List<PushVo> pushVos = pushDao.listPush(param);

        PushListResp resp = new PushListResp();
        if (ListUtils.isEmpty(pushVos)) {
            resp.setSearchCnt(0);
        } else {
            for (PushVo pushVo: pushVos) {
                if (resp.getPushes() == null) {
                    resp.setPushes(new ArrayList<>());
                }
                resp.getPushes().add(pushConverter.convert(pushVo));
            }
            resp.setSearchCnt(pushDao.countPush(param));
        }
        return resp;
    }

    @Override
    public PushResp push(Long pushId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "push_id", pushId);
        PushVo pushVo = pushDao.selectPush(param);

        PushResp resp = new PushResp();
        resp.setPush(pushConverter.convert(pushVo));
        return resp;
    }

    @Override
    public PcResp updatePush(Long pushId, String title, String body, Long delImgFileId, MultipartFile imgFile, String reserveDate, String reserveHour, String reserveMinute, Long organizeId, String osType, String categoryType, String moveType, Long moveId, String moveUrl, Long modId) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException {
        if (moveType != null) {
            switch (MOVE.valueOf(moveType)) {
                case WU:
                    if (StringUtils.isEmpty(moveUrl))
                        throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                    break;
                case NO:
                    if (moveId == null)
                        throw new BadRequestException(String.format("Required Long parameter 'moveId' is not present - 'moveType' is '{}'", moveType));
                    if (noticeFacade.get(moveId) == null)
                        throw new NotFoundNoticeException();
                    break;
                case ED:
                    if (moveId == null)
                        throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'ED'");
                    if (educationFacade.get(moveId) == null)
                        throw new NotFoundEducationException();
                    break;
                case SU:
                    if (moveId == null)
                        throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
                    if (surveyFacade.get(moveId) == null)
                        throw new NotFoundSurveyException();
                    break;
            }
        }

        File file = null;
        PushVo orgPushVo = null;
        if (imgFile != null) {
            file = pcUtils.save(imgFile);
        }
        if (imgFile != null || delImgFileId != null) {
            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "push_id", pushId);
            orgPushVo = pushDao.selectPush(param);
        }

        PushVo pushVo = new PushVo();
        pushVo.setPush_id(pushId);
        pushVo.setTitle(title);
        pushVo.setBody(body);
//        pushVo.setImg_file_id(fileId);
        if (delImgFileId != null && imgFile == null) {
            pushVo.setImgFileIdNull(true);
        }
        pushVo.setOs_div(osType);
        pushVo.setOrganize_id(organizeId);
        pushVo.setCategory_div(categoryType);
        pushVo.setMove_div(moveType);
        pushVo.setMove_id(moveId);
        pushVo.setMove_url(moveUrl);
        if (reserveDate != null && reserveHour != null && reserveMinute != null) {
            try {
                // Controller에서 검증
                pushVo.setReserv_dt(pcUtils.dateHMFormat.parse(String.format("%s-%s-%s", reserveDate, reserveHour, reserveMinute)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            pushVo.setReservDtNull(true);
        }
        pushVo.setMod_id(modId);
        pushDao.updatePush(pushVo);

        if (file != null) {
            storage.replace(orgPushVo.getImgFile(), file, imgFile.getOriginalFilename(), imgFile.getSize());
        } else if (delImgFileId != null) {
            storage.delete(orgPushVo.getImgFile());
        }

        // TODO exception 발생 시 imgFile 지워야 한다.

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deletePush(Long pushId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "push_id", pushId);
        PushVo pushVo = pushDao.selectPush(param);

        pushDao.deletePush(pushVo);

        if (pushVo.getImgFile() != null) {
            storage.delete(pushVo.getImgFile());
        }

        return new PcResp();
    }

    @Override
    public PcResp resendPush(Long pushId, String type) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "push_id", pushId);
        MapUtils.put(param, "recvDtIsNull", true);
        List<MemberNotiVo> notiVos = memberDao.listMemberNoti(param);
        if (!ListUtils.isEmpty(notiVos)) {
            // TODO SMS로 전송
            List<Long> memberIds = new ArrayList<>();
            for (MemberNotiVo notiVo: notiVos) {
                memberIds.add(notiVo.getMember_id());
            }
            param.clear();
            MapUtils.put(param, "user_div", USER.M.name());
            MapUtils.put(param, "userIds", memberIds);
            List<String> tokens = pushDao.listPushTokenToken(param);

            MemberNotiVo notiVo = notiVos.get(0);
            AbsFcmSender.FcmData fcmData = new AbsFcmSender.FcmData()
                    .addData("title", notiVo.getTitle())
                    .addData("body", notiVo.getBody())
                    .addData("push_id", notiVo.getPush_id().toString())
                    .addData("move_div", notiVo.getMove_div())
                    .addData("move_url", notiVo.getMove_url());

            if (notiVo.getMove_id() != null) {
                fcmData.addData("move_id", notiVo.getMove_id().toString());
            }
//            if (pushVo.getImgFile() != null) {
//                fcmData.addData("imgUrl", storage.fileUrl(pushVo.getImgFile()));
//            }
            fcmData.setRegistrationToken(tokens);
            fcmSender.send(fcmData);
        }

        return new PcResp();
    }
}
