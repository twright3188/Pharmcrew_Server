package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ArrayUtils;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.NewsConverter;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.NewsDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;
import kr.ant.kpa.pharmcrew.notify.NotifyHelper;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.news.*;
import kr.ant.kpa.pharmcrew.service.NewsService;
import kr.ant.kpa.pharmcrew.type.news.MOVE;
import kr.ant.kpa.pharmcrew.type.news.QNA_TARGET;
import kr.ant.kpa.pharmcrew.type.notify.PUSH_CATEGORY;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private NewsConverter newsConverter;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;

    @Autowired
    private NotifyHelper notifyHelper;

    @Transactional
    @Override
    public PcResp registerNotice(Long organizeId, String title, String body, MultipartFile[] attachFiles, String isOpen, long regId) throws FailSaveFileException {
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setOrganize_id(organizeId);
        noticeVo.setTitle(title);
        noticeVo.setBody(body);
        noticeVo.setIs_open(isOpen);
        noticeVo.setReg_id(regId);
        newsDao.insertNotice(noticeVo);

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
	            Map<String, Object> param = new HashMap<>();
	            MapUtils.put(param, "notice_id", noticeVo.getNotice_id());
	            MapUtils.put(param, "fileIds", attachFileIds);
	            newsDao.insertNoticeFileList(param);
            }
        }

        return new PcResp();
    }

    @Override
    public NoticeListResp noticeList(Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        MapUtils.put(param, "is_open", isOpen);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
//        MapUtils.put(param, "withOrganizePath", true);
        List<NoticeVo> noticeVos = newsDao.listNotice(param);

        NoticeListResp resp = new NoticeListResp();
        if (ListUtils.isEmpty(noticeVos)) {
            resp.setSearchCnt(0);
        } else {
            for (NoticeVo noticeVo : noticeVos) {
                if (resp.getNotices() == null) {
                    resp.setNotices(new ArrayList<>());
                }
                resp.getNotices().add(newsConverter.convert(noticeVo));
            }
            resp.setSearchCnt(newsDao.countNotice(param));
        }
        return resp;
    }

    @Override
    public NoticeResp notice(Long noticeId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "notice_id", noticeId);
        NoticeVo noticeVo = newsDao.selectNotice(param);
        if (noticeVo == null)   throw new NotFoundException();

        NoticeResp resp = new NoticeResp();
        resp.setNotice(newsConverter.convert(noticeVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updateNotice(Long noticeId, Long organizeId, String title, String body, Long[] delAttachFileIds, MultipartFile[] attachFiles, String isOpen, long modId) throws FailSaveFileException {
        NoticeVo noticeVo = new NoticeVo();
        noticeVo.setNotice_id(noticeId);
        noticeVo.setOrganize_id(organizeId);
        noticeVo.setTitle(title);
        noticeVo.setBody(body);
        noticeVo.setIs_open(isOpen);
        noticeVo.setMod_id(modId);
        newsDao.updateNotice(noticeVo);

        List<FileVo> delFileVos = null;
        if (!ArrayUtils.isEmpty(delAttachFileIds)) {
            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "fileIds", delAttachFileIds);
            delFileVos = commonDao.listFile(param);
            newsDao.deleteNoticeFile(param);
        }
        // 새로운 첨부 파일 추가
        if (!ArrayUtils.isEmpty(attachFiles)) {
            // TODO exception 발생 시 attachFiles을 지워야 한다.
            List<Long> attachFileIds = null;
            // t_file 등록
            for (MultipartFile attachFile: attachFiles) {
                if (attachFile.getSize() == 0)	continue;

                File file = pcUtils.save(attachFile);
                long fileId = storage.save(file, attachFile.getOriginalFilename(), attachFile.getSize()/*, FILE.NN*/);
                if (attachFileIds == null) {
                    attachFileIds = new ArrayList<>();
                }
                attachFileIds.add(fileId);
            }
            // t_notice_file 등록
            if (attachFileIds != null) {
                Map<String, Object> param = new HashMap<>();
                MapUtils.put(param, "notice_id", noticeVo.getNotice_id());
                MapUtils.put(param, "fileIds", attachFileIds);
                newsDao.insertNoticeFileList(param);
            }
        }

        // 삭제된 파일 삭제
        storage.deletes(delFileVos);

        cacheManager.delete(CACHE.NOTICE, noticeId);

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deleteNotice(Long noticeId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "notice_id", noticeId);
        NoticeVo noticeVo = newsDao.selectNotice(param);

        // t_notice_file 삭제
        newsDao.deleteNoticeFile(param);
        // t_notice 삭제
        newsDao.deleteNotice(noticeVo);
        // t_file 삭제
        if (!ListUtils.isEmpty(noticeVo.getAttachFiles())) {
            storage.deletes(noticeVo.getAttachFiles());
        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp registerPopup(String title, MultipartFile popupFile, Integer idx, String moveType, Long moveId, String moveUrl, String isOpen, String openStartDate, String openStartHour, String openEndDate, String openEndHour, long regId) throws FailSaveFileException {
        switch (MOVE.valueOf(moveType)) {
            case WU:
                if (StringUtils.isEmpty(moveUrl))   throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                break;
            default:
                if (moveId == null) throw new BadRequestException(String.format("Required Long parameter 'moveUrl' is not present - 'moveType' is '{}'", moveType));
                break;
        }

        File file = pcUtils.save(popupFile);
        long fileId = storage.save(file, popupFile.getOriginalFilename(), popupFile.getSize());

        PopupVo popupVo = new PopupVo();
        popupVo.setTitle(title);
        popupVo.setImg_file_id(fileId);
        popupVo.setMove_div(moveType);
        popupVo.setMove_id(moveId);
        popupVo.setMove_url(moveUrl);
        popupVo.setIs_open(isOpen);
        try {
            // Controller에서 검증하니...
            popupVo.setOpen_start_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openStartDate, openStartHour)));
            popupVo.setOpen_end_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openEndDate, openEndHour)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (idx == null) {
            idx = newsDao.selectMaxOpenIdxFromActivePopup() + 1;
        }
        popupVo.setOpen_idx(idx);
        popupVo.setReg_id(regId);
        newsDao.insertPopup(popupVo);

        // TODO exception 발생 시 popupFile을 지워야 한다.

        return new PcResp();
    }

    @Override
    public PopupListResp popupList(String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<PopupVo> popupVos = newsDao.listPopup(param);

        PopupListResp resp = new PopupListResp();
        if (ListUtils.isEmpty(popupVos)) {
            resp.setSearchCnt(0);
        } else {
            for (PopupVo popupVo: popupVos) {
                if (resp.getPopups() == null) {
                    resp.setPopups(new ArrayList<>());
                }
                resp.getPopups().add(newsConverter.convert(popupVo));
            }
            resp.setSearchCnt(newsDao.countPopup(param));
        }
        return resp;
    }

    @Override
    public PopupResp popup(Long popupId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "popup_id", popupId);
        PopupVo popupVo = newsDao.selectPopup(param);

        PopupResp resp = new PopupResp();
        resp.setPopup(newsConverter.convert(popupVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updatePopup(Long popupId, String title, MultipartFile popupFile, Integer idx, String moveType, Long moveId, String moveUrl, String isOpen, String openStartDate, String openStartHour, String openEndDate, String openEndHour, long modId) throws FailSaveFileException {
        switch (MOVE.valueOf(moveType)) {
            case WU:
                if (StringUtils.isEmpty(moveUrl))   throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                break;
            default:
                if (moveId == null) throw new BadRequestException(String.format("Required Long parameter 'moveUrl' is not present - 'moveType' is '{}'", moveType));
                break;
        }

        File file = null;
        PopupVo orgPopupVo = null;
        if (popupFile != null) {
            file = pcUtils.save(popupFile);

            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "popup_id", popupId);
            orgPopupVo = newsDao.selectPopup(param);
        }

        PopupVo popupVo = new PopupVo();
        popupVo.setPopup_id(popupId);
        popupVo.setTitle(title);
//        popupVo.setImg_file_id(fileId);
        popupVo.setMove_div(moveType);
        switch (MOVE.valueOf(moveType)) {
            case WU:
                popupVo.setMoveIdNull(true);
                popupVo.setMove_url(moveUrl);
                break;
            default:
                popupVo.setMove_id(moveId);
                popupVo.setMoveUrlNull(true);
                break;
        }
        popupVo.setIs_open(isOpen);
        try {
            // Controller에서 검증
            popupVo.setOpen_start_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openStartDate, openStartHour)));
            popupVo.setOpen_end_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openEndDate, openEndHour)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (idx == null) {
            idx = newsDao.selectMaxOpenIdxFromActivePopup() + 1;
        }
        popupVo.setOpen_idx(idx);
        popupVo.setMod_id(modId);
        newsDao.updatePopup(popupVo);

        if (file != null) {
            storage.replace(orgPopupVo.getImgFile(), file, popupFile.getOriginalFilename(), popupFile.getSize());
        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deletePopup(Long popupId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "popup_id", popupId);
        PopupVo popupVo = newsDao.selectPopup(param);

        newsDao.deletePopup(popupVo);

        storage.delete(popupVo.getImgFile());

        return new PcResp();
    }

    @Override
    public PcResp registerBanner(String title, MultipartFile bannerFile, Integer idx, String moveType, Long moveId, String moveUrl, String isOpen, String openStartDate, String openStartHour, String openEndDate, String openEndHour, long regId) throws FailSaveFileException {
        switch (MOVE.valueOf(moveType)) {
            case WU:
                if (StringUtils.isEmpty(moveUrl))   throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                break;
            default:
                if (moveId == null) throw new BadRequestException(String.format("Required Long parameter 'moveUrl' is not present - 'moveType' is '{}'", moveType));
                break;
        }

        File file = pcUtils.save(bannerFile);
        long fileId = storage.save(file, bannerFile.getOriginalFilename(), bannerFile.getSize());

        BannerVo bannerVo = new BannerVo();
        bannerVo.setTitle(title);
        bannerVo.setImg_file_id(fileId);
        bannerVo.setMove_div(moveType);
        bannerVo.setMove_id(moveId);
        bannerVo.setMove_url(moveUrl);
        bannerVo.setIs_open(isOpen);
        try {
            // Controller에서 검증하니...
            bannerVo.setOpen_start_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openStartDate, openStartHour)));
            bannerVo.setOpen_end_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openEndDate, openEndHour)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (idx == null) {
            idx = newsDao.selectMaxOpenIdxFromActiveBanner() + 1;
        }
        bannerVo.setOpen_idx(idx);
        bannerVo.setReg_id(regId);
        newsDao.insertBanner(bannerVo);

        // TODO exception 발생 시 bannerFile을 지워야 한다.

        return new PcResp();
    }

    @Override
    public BannerListResp bannerList(String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<BannerVo> bannerVos = newsDao.listBanner(param);

        BannerListResp resp = new BannerListResp();
        if (ListUtils.isEmpty(bannerVos)) {
            resp.setSearchCnt(0);
        } else {
            for (BannerVo bannerVo: bannerVos) {
                if (resp.getBanners() == null) {
                    resp.setBanners(new ArrayList<>());
                }
                resp.getBanners().add(newsConverter.convert(bannerVo));
            }
            resp.setSearchCnt(newsDao.countBanner(param));
        }
        return resp;
    }

    @Override
    public BannerResp banner(Long bannerId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "banner_id", bannerId);
        BannerVo bannerVo = newsDao.selectBanner(param);

        BannerResp resp = new BannerResp();
        resp.setBanner(newsConverter.convert(bannerVo));
        return resp;
    }

    @Override
    public PcResp updateBanner(Long bannerId, String title, MultipartFile bannerFile, Integer idx, String moveType, Long moveId, String moveUrl, String isOpen, String openStartDate, String openStartHour, String openEndDate, String openEndHour, long modId) throws FailSaveFileException {
        switch (MOVE.valueOf(moveType)) {
            case WU:
                if (StringUtils.isEmpty(moveUrl))   throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
                break;
            default:
                if (moveId == null) throw new BadRequestException(String.format("Required Long parameter 'moveUrl' is not present - 'moveType' is '{}'", moveType));
                break;
        }

        File file = null;
        BannerVo orgBannerVo = null;
        if (bannerFile != null) {
            file = pcUtils.save(bannerFile);

            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "banner_id", bannerId);
            orgBannerVo = newsDao.selectBanner(param);
        }

        BannerVo bannerVo = new BannerVo();
        bannerVo.setBanner_id(bannerId);
        bannerVo.setTitle(title);
//        bannerVo.setImg_file_id(fileId);
        bannerVo.setMove_div(moveType);
        switch (MOVE.valueOf(moveType)) {
            case WU:
                bannerVo.setMoveIdNull(true);
                bannerVo.setMove_url(moveUrl);
                break;
            default:
                bannerVo.setMove_id(moveId);
                bannerVo.setMoveUrlNull(true);
                break;
        }
        bannerVo.setIs_open(isOpen);
        try {
            // Controller에서 검증하니...
            bannerVo.setOpen_start_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openStartDate, openStartHour)));
            bannerVo.setOpen_end_dt(pcUtils.dateHFormat.parse(String.format("%s-%s", openEndDate, openEndHour)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (idx == null) {
            idx = newsDao.selectMaxOpenIdxFromActiveBanner() + 1;
        }
        bannerVo.setOpen_idx(idx);
        bannerVo.setMod_id(modId);
        newsDao.updateBanner(bannerVo);

        if (file != null) {
            storage.replace(orgBannerVo.getImgFile(), file, bannerFile.getOriginalFilename(), bannerFile.getSize());
        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deleteBanner(Long bannerId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "banner_id", bannerId);
        BannerVo bannerVo = newsDao.selectBanner(param);

        newsDao.deleteBanner(bannerVo);

        storage.delete(bannerVo.getImgFile());

        return new PcResp();
    }

    @Override
    public QnaListResp qnaList(String type, Long organizeId, String isAnswerd, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        switch (type) {
            case "N":
            case "E":
                MapUtils.put(param, "target_div", type);
                break;
            case "OTHER":
                MapUtils.put(param, "notTargetDivs", new String[] {QNA_TARGET.N.name(), QNA_TARGET.E.name()});
                break;
        }
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        if (!StringUtils.isEmpty(isAnswerd)) {
            switch (isAnswerd) {
                case "Y":
                case "N":
                    MapUtils.put(param, "is_answerd", isAnswerd);
                    break;
            }
        }
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "question_dt desc");
        List<QnaVo> qnaVos = newsDao.listQna(param);

        QnaListResp resp = new QnaListResp();
        if (ListUtils.isEmpty(qnaVos)) {
            resp.setSearchCnt(0);
        } else {
            for (QnaVo qnaVo : qnaVos) {
                if (resp.getQnas() == null) {
                    resp.setQnas(new ArrayList<>());
                }
                resp.getQnas().add(newsConverter.convert(qnaVo));
            }
            resp.setSearchCnt(newsDao.countQna(param));
        }

        return resp;
    }

    @Override
    public QnaResp qna(Long qnaId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "qna_id", qnaId);
        QnaVo qnaVo = newsDao.selectQna(param);
        if (qnaVo == null)  throw new NotFoundException();

        QnaResp resp = new QnaResp();
        resp.setQna(newsConverter.convert(qnaVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp answerQna(Long qnaId, String answerTitle, String answerBody, long answerId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "qna_id", qnaId);
        QnaVo orgQnaVo = newsDao.selectQna(param);
        if (orgQnaVo == null)  throw new NotFoundException();

        QNA_TARGET target = QNA_TARGET.valueOf(orgQnaVo.getTarget_div());
        if ("Y".equals(orgQnaVo.getMember().getPush_yn())) {
            notifyHelper.send(null,
                    String.format("%s %s", target.getName(), "문의 답변"), "답변이 등록되었습니다.", null,
                    PUSH_CATEGORY.NT, target == QNA_TARGET.N ? MOVE.NQ.name() : MOVE.EQ.name(), null, qnaId,
                    null, null, Collections.singletonList(orgQnaVo.getMember().getMember_id()));
        }

        QnaVo qnaVo = new QnaVo();
        qnaVo.setQna_id(qnaId);
        qnaVo.setAns_title(answerTitle);
        qnaVo.setAns_body(answerBody);
        qnaVo.setIs_answerd("Y");
        qnaVo.setAdmin_id(answerId);
        qnaVo.setAnswer_dt(new Date());
        newsDao.updateQna(qnaVo);

        return new PcResp();
    }
}
