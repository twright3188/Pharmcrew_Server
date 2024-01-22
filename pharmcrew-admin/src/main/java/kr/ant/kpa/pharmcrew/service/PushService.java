package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.push.PushListResp;
import kr.ant.kpa.pharmcrew.resp.push.PushResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;
import org.springframework.web.multipart.MultipartFile;

public interface PushService {
    PcResp registPush(String title, String body, MultipartFile imgFile,
                      String reserveDate, String reserveHour, String reserveMinute,
                      Long organizeId, String osType,
                      String categoryType, String moveType, Long moveId, String moveUrl,
                      Long regId) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException;
    PushListResp pushList(String reservDtStartDate, String reservDtEndDate,
                          String keyword,
                          Long organizeId,
                          String categoryType, String sendType,
                          Integer page, Integer cntPerPage);
    PushResp push(Long pushId);
    PcResp updatePush(Long pushId,
                      String title, String body, Long delImgFileId, MultipartFile imgFile,
                      String reserveDate, String reserveHour, String reserveMinute,
                      Long organizeId, String osType,
                      String categoryType, String moveType, Long moveId, String moveUrl,
                      Long modId) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException;
    PcResp deletePush(Long pushId);
    PcResp resendPush(Long pushId, String type);
}
