package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.news.*;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {
    PcResp registerNotice(Long organizeId, String title, String body,
                          MultipartFile[] attachFiles,
                          String isOpen,
                          long regId) throws FailSaveFileException;
    NoticeListResp noticeList(Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage);
    NoticeResp notice(Long noticeId);
    PcResp updateNotice(Long noticeId,
                        Long organizeId, String title, String body,
                        Long[] delAttachFileIds,
                        MultipartFile[] attachFiles,
                        String isOpen,
                        long modId) throws FailSaveFileException;
    PcResp deleteNotice(Long noticeId);

    PcResp registerPopup(String title, MultipartFile popupFile, Integer idx,
                         String moveType, Long moveId, String moveUrl,
                         String isOpen,
                         String openStartDate, String openStartHour, String openEndDate, String openEndHour,
                         long regId) throws FailSaveFileException;
    PopupListResp popupList(String keyword, Integer page, Integer cntPerPage);
    PopupResp popup(Long popupId);
    PcResp updatePopup(Long popupId,
                       String title, MultipartFile popupFile, Integer idx,
                       String moveType, Long moveId, String moveUrl,
                       String isOpen,
                       String openStartDate, String openStartHour, String openEndDate, String openEndHour,
                       long modId) throws FailSaveFileException;
    PcResp deletePopup(Long popupId);

    PcResp registerBanner(String title, MultipartFile bannerFile, Integer idx,
                          String moveType, Long moveId, String moveUrl,
                          String isOpen,
                          String openStartDate, String openStartHour, String openEndDate, String openEndHour,
                          long regId) throws FailSaveFileException;
    BannerListResp bannerList(String keyword, Integer page, Integer cntPerPage);
    BannerResp banner(Long bannerId);
    PcResp updateBanner(Long bannerId,
                       String title, MultipartFile bannerFile, Integer idx,
                       String moveType, Long moveId, String moveUrl,
                       String isOpen,
                       String openStartDate, String openStartHour, String openEndDate, String openEndHour,
                       long modId) throws FailSaveFileException;
    PcResp deleteBanner(Long bannerId);

    QnaListResp qnaList(String type, Long organizeId, String isAnswerd, String keyword, Integer page, Integer cntPerPage);
    QnaResp qna(Long qnaId);
    PcResp answerQna(Long qnaId, String answerTitle, String answerBody, long answerId);
}
