package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxnoticeListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxnoticeResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxqnaListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxqnaResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePtaxnoticeException;
import org.springframework.web.multipart.MultipartFile;

public interface PtaxService {
    PtaxqnaListResp qnaList(String regDtStartDate, String regDtEndDate, String target, String pharmName, String answerYn, String keyword, Integer page, Integer cntPerPage);
    PtaxqnaResp qna(Long qnaId);
    PcResp answerQna(Long qnaId, String answerTitle, String answerBody, Long[] delAttachFileIds, MultipartFile[] attachFiles, long answerId) throws FailSaveFileException;

    PcResp registerNotice(String sendType, String target, String targetPharmName, Long targetMemberId, String openYn, String reserveDate, String reserveHour, String reserveMinute, String osType, String title, String body, MultipartFile[] attachFiles, long regId) throws FailSaveFileException;
    PtaxnoticeListResp noticeList(String regDtStartDate, String regDtEndDate, String target, String targetPharmName, String sendType, String keyword, Integer page, Integer cntPerPage);
    PtaxnoticeResp notice(Long noticeId);
    PcResp updateNotice(Long noticeId, String sendType, String target, String targetPharmName, Long targetMemberId, String openYn, String reserveDate, String reserveHour, String reserveMinute, String osType, String title, String body, Long[] delAttachFileIds, MultipartFile[] attachFiles, long modId) throws FailSaveFileException;
    PcResp deleteNotice(Long noticeId) throws NotDeletablePtaxnoticeException;
}
