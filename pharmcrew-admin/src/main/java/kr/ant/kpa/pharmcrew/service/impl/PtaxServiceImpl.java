package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ArrayUtils;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.PtaxConverter;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.PtaxDao;
import kr.ant.kpa.pharmcrew.db.dao.PushDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.notify.NotifyHelper;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.*;
import kr.ant.kpa.pharmcrew.service.PtaxService;
import kr.ant.kpa.pharmcrew.type.news.MOVE;
import kr.ant.kpa.pharmcrew.type.news.QNA_TARGET;
import kr.ant.kpa.pharmcrew.type.notify.PUSH_CATEGORY;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePtaxnoticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class PtaxServiceImpl implements PtaxService {
    @Autowired
    private PtaxDao ptaxDao;
    @Autowired
    private PushDao pushDao;
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private PtaxConverter ptaxConverter;

    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;

    @Autowired
    private NotifyHelper notifyHelper;

    @Override
    public PtaxqnaListResp qnaList(String regDtStartDate, String regDtEndDate, String target, String pharmName, String answerYn, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        if (!StringUtils.isEmpty(regDtStartDate)) {
            try {
                MapUtils.put(param, "questionDtStartDate", pcUtils.dateFormat.parse(regDtStartDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(regDtEndDate)) {
            try {
                MapUtils.put(param, "questionDtEndDate", pcUtils.dateFormat.parse(regDtEndDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        switch (target) {
            case "G":
                MapUtils.put(param, "pharmNameIsNull", true);
                break;
            case "M":
                MapUtils.put(param, "pharmNameKeyword", pharmName);
                break;
        }
        MapUtils.put(param, "is_answerd", answerYn);
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "question_dt desc");
        List<PtaxQnaVo> qnaVos = ptaxDao.listPtaxQna(param);

        PtaxqnaListResp resp = new PtaxqnaListResp();
        if (ListUtils.isEmpty(qnaVos)) {
            resp.setSearchCnt(0);
        } else {
            for (PtaxQnaVo qnaVo: qnaVos) {
                if (resp.getQnas() == null) {
                    resp.setQnas(new ArrayList<>());
                }
                resp.getQnas().add(ptaxConverter.convert(qnaVo));
            }
            resp.setSearchCnt(ptaxDao.countPtaxQna(param));
        }

        return resp;
    }

    @Override
    public PtaxqnaResp qna(Long qnaId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "qna_id", qnaId);
        PtaxQnaVo qnaVo = ptaxDao.selectPtaxQna(param);

        PtaxqnaResp resp = new PtaxqnaResp();
        resp.setQna(ptaxConverter.convert(qnaVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp answerQna(Long qnaId, String answerTitle, String answerBody, Long[] delAttachFileIds, MultipartFile[] attachFiles, long answerId) throws FailSaveFileException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "qna_id", qnaId);
        PtaxQnaVo orgQnaVo = ptaxDao.selectPtaxQna(param);
        if (orgQnaVo == null)  throw new NotFoundException();

        if ("Y".equals(orgQnaVo.getMember().getPush_yn())) {
            notifyHelper.send(null,
                    "팜텍스공지 문의 답변", "답변이 등록되었습니다.", null,
                    PUSH_CATEGORY.PT, MOVE.PQ.name(), null, qnaId,
                    null, null, Arrays.asList(orgQnaVo.getMember().getMember_id()));
        }

        PtaxQnaVo qnaVo = new PtaxQnaVo();
        qnaVo.setQna_id(qnaId);
        qnaVo.setIs_answerd("Y");
        qnaVo.setAns_title(answerTitle);
        qnaVo.setAns_body(answerBody);
        qnaVo.setAdmin_id(answerId);
        qnaVo.setAnswer_dt(new Date());
        ptaxDao.updatePtaxQna(qnaVo);

        if (!ArrayUtils.isEmpty(delAttachFileIds)) {
            param.clear();
            MapUtils.put(param, "fileIds", delAttachFileIds);
            List<FileVo> fileVos = commonDao.listFile(param);
            ptaxDao.deletePtaxQnaFile(param);

            storage.deletes(fileVos);
        }

        if (!ArrayUtils.isEmpty(attachFiles)) {
            // TODO exception 발생 시 attachFiles을 지워야 한다.
            List<Long> attachFileIds = null;

            for (MultipartFile attachFile: attachFiles) {
                if (attachFile.getSize() == 0)	continue;

                File file = pcUtils.save(attachFile);
                long fileId = storage.save(file, attachFile.getOriginalFilename(), attachFile.getSize()/*, FILE.NN*/);
                if (attachFileIds == null) {
                    attachFileIds = new ArrayList<>();
                }
                attachFileIds.add(fileId);
            }

            if (attachFileIds != null) {
                param.clear();
                MapUtils.put(param, "qna_id", qnaVo.getQna_id());
                MapUtils.put(param, "type_div", "A");
                MapUtils.put(param, "fileIds", attachFileIds);
                ptaxDao.insertPTaxQnaFileList(param);
            }
        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp registerNotice(String sendType, String target, String targetPharmName, Long targetMemberId, String openYn, String reserveDate, String reserveHour, String reserveMinute, String osType, String title, String body, MultipartFile[] attachFiles, long regId) throws FailSaveFileException {
        PtaxNoticeVo noticeVo = new PtaxNoticeVo();
        noticeVo.setTitle(title);
        noticeVo.setBody(body);
        noticeVo.setTarget_div(target);
        if ("M".equals(target)) {
            noticeVo.setTarget_pharm_name(targetPharmName);
            noticeVo.setTarget_member_id(targetMemberId);
        }
        noticeVo.setIs_open(openYn);
        noticeVo.setReg_id(regId);
        ptaxDao.insertPtaxNotice(noticeVo);

        if (!ArrayUtils.isEmpty(attachFiles)) {
            List<Long> attachFileIds = null;
            for (MultipartFile attachFile: attachFiles) {
                if (attachFile.getSize() == 0)  continue;

                File file = pcUtils.save(attachFile);
                long fileId = storage.save(file, attachFile.getOriginalFilename(), attachFile.getSize());
                if (attachFileIds == null) {
                    attachFileIds = new ArrayList<>();
                }
                attachFileIds.add(fileId);
            }

            if (attachFileIds != null) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "notice_id", noticeVo.getNotice_id());
                MapUtils.put(param, "fileIds", attachFileIds);;
                ptaxDao.insertPtaxNoticeFileList(param);
            }
        }

        if (sendType != null) {   // 푸시
            PushVo pushVo = new PushVo();
            pushVo.setTitle(title);

            String textBody = StringUtils.removeHtml(body);
            if (textBody.length() > 150) {
                textBody = textBody.substring(0, 150);
            }
            pushVo.setBody(textBody);

            pushVo.setOs_div(osType);
            pushVo.setCategory_div(PUSH_CATEGORY.PT.name());
            pushVo.setMove_div(MOVE.PT.name());
            pushVo.setMove_id(noticeVo.getNotice_id());
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
        }

        return new PcResp();
    }

    @Override
    public PtaxnoticeListResp noticeList(String regDtStargDate, String regDtEndDate, String target, String targetPharmName, String sendType, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        if (!StringUtils.isEmpty(regDtStargDate)) {
            try {
                // Controller에서 검증
                MapUtils.put(param, "regDtStartDate", pcUtils.dateFormat.parse(regDtStargDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(regDtEndDate)) {
            try {
                // Controller에서 검증
                MapUtils.put(param, "regDtEndDate", pcUtils.dateFormat.parse(regDtEndDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        MapUtils.put(param, "target_div", target);
        if ("M".equals(target)) {
            MapUtils.put(param, "target_pharm_name", targetPharmName);
        }
        if (!StringUtils.isEmpty(sendType)) {
            switch (sendType) {
                case "R":
                    MapUtils.put(param, "reservDtIsNotNull", true);
                    break;
                case "I":
                    MapUtils.put(param, "reservDtIsNull", true);
                    MapUtils.put(param, "pushIsNotNull", true);
                    break;
            }
        }
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.put(param, "withSRCnt", true);
        List<PtaxNoticeVo> noticeVos = ptaxDao.listPtaxNotice(param);

        PtaxnoticeListResp resp = new PtaxnoticeListResp();
        if (ListUtils.isEmpty(noticeVos)) {
            resp.setSearchCnt(0);
        } else {
            for (PtaxNoticeVo noticeVo: noticeVos) {
                if (resp.getNotices() == null) {
                    resp.setNotices(new ArrayList<>());
                }
                resp.getNotices().add(ptaxConverter.convert(noticeVo));
            }
        }
        return resp;
    }

    @Override
    public PtaxnoticeResp notice(Long noticeId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "notice_id", noticeId);
        PtaxNoticeVo noticeVo = ptaxDao.selectPtaxNotice(param);
        if (noticeVo == null)   throw new NotFoundException();

        PtaxnoticeResp resp = new PtaxnoticeResp();
        resp.setNotice(ptaxConverter.convert(noticeVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updateNotice(Long noticeId, String sendType, String target, String targetPharmName, Long targetMemberId, String openYn, String reserveDate, String reserveHour, String reserveMinute, String osType, String title, String body, Long[] delAttachFileIds, MultipartFile[] attachFiles, long modId) throws FailSaveFileException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "notice_id", noticeId);
        PtaxNoticeVo orgNoticeVo = ptaxDao.selectPtaxNotice(param);

        PtaxNoticeVo noticeVo = new PtaxNoticeVo();
        noticeVo.setNotice_id(noticeId);
        noticeVo.setTitle(title);
        noticeVo.setBody(body);
        noticeVo.setTarget_div(target);
        if ("M".equals(target)) {
            noticeVo.setTarget_pharm_name(targetPharmName);
            noticeVo.setTarget_member_id(targetMemberId);
        } else {
            noticeVo.setTargetPharmNameNull(true);
            noticeVo.setTargetMemberIdNull(true);
        }
        noticeVo.setIs_open(openYn);
        noticeVo.setMod_id(modId);
        ptaxDao.updatePtaxNotice(noticeVo);

        List<FileVo> delFileVos = null;
        if (!ArrayUtils.isEmpty(delAttachFileIds)) {
            param.clear();
            MapUtils.put(param, "fileIds", delAttachFileIds);
            delFileVos = commonDao.listFile(param);
            ptaxDao.deletePtaxNoticeFile(param);
        }
        if (!ArrayUtils.isEmpty(attachFiles)) {
            List<Long> attachFileIds = null;
            for (MultipartFile attachFile: attachFiles) {
                if (attachFile.getSize() == 0)  continue;

                File file = pcUtils.save(attachFile);
                // TODO 오류 발생 시 파일시스템의 파일 삭제
                long fileId = storage.save(file, attachFile.getOriginalFilename(), attachFile.getSize());
                if (attachFileIds == null) {
                    attachFileIds = new ArrayList<>();
                }
                attachFileIds.add(fileId);
            }
            if (attachFileIds != null) {
                param.clear();
                MapUtils.put(param, "notice_id", noticeVo.getNotice_id());
                MapUtils.put(param, "fileIds", attachFileIds);;
                ptaxDao.insertPtaxNoticeFileList(param);
            }
        }

        if (sendType != null) {   // 푸시
            PushVo pushVo = new PushVo();
            pushVo.setTitle(title);

            String textBody = StringUtils.removeHtml(body);
            if (textBody.length() > 150) {
                textBody = textBody.substring(0, 150);
            }
            pushVo.setBody(textBody);

            pushVo.setOs_div(osType);
            pushVo.setCategory_div(PUSH_CATEGORY.PT.name());
            pushVo.setMove_div(MOVE.PT.name());
            pushVo.setMove_id(noticeVo.getNotice_id());
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
            if (orgNoticeVo.getPush() != null) {
                pushVo.setPush_id(orgNoticeVo.getPush().getPush_id());
                pushVo.setMod_id(modId);
                pushDao.updatePush(pushVo);
            } else {
                pushVo.setReg_id(modId);
                pushDao.insertPush(pushVo);
            }
        } else {
            if (orgNoticeVo.getPush() != null) {
                pushDao.deletePush(orgNoticeVo.getPush());  // 파일이 없으니 그냥 지운다.
            }
        }

        storage.deletes(delFileVos);

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deleteNotice(Long noticeId) throws NotDeletablePtaxnoticeException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "notice_id", noticeId);
        PtaxNoticeVo noticeVo = ptaxDao.selectPtaxNotice(param);

        ptaxDao.deletePtaxNoticeFile(param);
        if (noticeVo.getPush() != null) {
            try {
                pushDao.deletePush(noticeVo.getPush());
            } catch (DataIntegrityViolationException e) {
                throw new NotDeletablePtaxnoticeException();
            }
        }
        ptaxDao.deletePtaxNotice(noticeVo);
        if (!ListUtils.isEmpty(noticeVo.getAttachFiles())) {
            storage.deletes(noticeVo.getAttachFiles());
        }

        return new PcResp();
    }
}
