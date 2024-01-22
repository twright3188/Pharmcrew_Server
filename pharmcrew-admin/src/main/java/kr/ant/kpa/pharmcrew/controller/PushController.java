package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.DatetimeValidation;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import com.bumdori.spring.annotation.validation.regex.RegexValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.push.PushListResp;
import kr.ant.kpa.pharmcrew.resp.push.PushResp;
import kr.ant.kpa.pharmcrew.service.PushService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("푸시")
public class PushController {
    @Autowired
    private PushService pushService;

    @RequestMapping(value = "/pushes", method = RequestMethod.POST, name = "01. 푸시 등록")
    @ResponseBody
    @Session
    @Description("푸시를 등록한다.")
    @Histories({
            @History(date = "2020-05-23", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public PcResp registPush(
            @RequestParam("title") @Description("제목") @LengthValidation(max = 30) String title,
            @RequestParam("body") @Description("내용") @LengthValidation(max = 150) String body,
            @RequestParam(value = "imgFile", required = false) @Description("이미지 파일") MultipartFile imgFile,
            @RequestParam(value = "reserveDate", required = false) @Description("전송 예약일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String reserveDate,
            @RequestParam(value = "reserveHour", required = false) @Description("전송 예약 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String reserveHour,
            @RequestParam(value = "reserveMinute", required = false) @Description("전송 예약 분<br>" +
                    "Regex: ([0-5][0-9])") @RegexValidation(regex = "([0-5][0-9])") String reserveMinute,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "osType", required = false) @Description("OS - ALL(모두), I(iOS), A(Android)") @EnumValidation({"ALL", "I", "A"}) String osType,
            @RequestParam("categoryType") @Description("이동 카테고리 타입<br>" +
                    "PUSH_CATEGORY.java") @EnumValidation({"AD", "NT"}) String categoryType,
            @RequestParam("moveType") @Description("이동 타입<br>" +
                    "MOVE.java") @EnumValidation({"NONE", "WU", "NO", "ED", "SU"}) String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") @LengthValidation(max = 200) String moveUrl,
            HttpSession session
    ) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException {
        long regId = (long) session.getAttribute("admin_id");

        if ("ALL".equals(osType)) {
            osType = null;
        }
        if ("NONE".equals(moveType)) {
            moveType = null;
        }

        return pushService.registPush(title, body, imgFile,
                reserveDate, reserveHour, reserveMinute,
                organizeId, osType,
                categoryType, moveType, moveId, moveUrl,
                regId);
    }

    @RequestMapping(value = "/pushes", method = RequestMethod.GET, name = "02. 푸시 리스트")
    @ResponseBody
    @Session
    @Description("푸시 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-23", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
            @History(date = "2020-05-23", description = "기간은 전송->등록으로 동작"),
    })
    public PushListResp pushList(
            @RequestParam(value = "reservDtStartDate", required = false) @Description("전송 일시 검색 시작일(SimpleDateFormat: yyyy.MM.dd") String reservDtStartDate,
            @RequestParam(value = "reservDtEndDate", required = false) @Description("전송 일시 검색 종료일(SimpleDateFormat: yyyy.MM.dd") String reservDtEndDate,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("categoryType") @Description("이동 카테고리 타입<br>" +
                    "PUSH_CATEGORY.java") @EnumValidation({"AD", "NT"}) String categoryType,
            @RequestParam(value = "sendType", required = false) @Description("전송 타입<br>" +
                    "ALL(전체), R(eserve): 예악, I(mmediate): 즉시") @EnumValidation({"ALL", "R", "I"}) String sendType,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        if ("ALL".equals(sendType)) {
            sendType = null;
        }

        return pushService.pushList(reservDtStartDate, reservDtEndDate,
                keyword,
                organizeId,
                categoryType, sendType,
                page, cntPerPage);
    }

    @RequestMapping(value = "/pushes/{pushId}", method = RequestMethod.GET, name = "03. 푸시")
    @ResponseBody
    @Session
    @Description("푸시를 조회한다.")
    @Histories({
            @History(date = "2020-05-23", description = "설계"),
            @History(date = "2020-05-24", description = "완료"),
    })
    public PushResp push(
            @PathVariable("pushId") Long pushId
    ) {
        return pushService.push(pushId);
    }

    @RequestMapping(value = "/pushes/{pushId}", method = RequestMethod.POST, name = "04. 푸시 수정")
    @ResponseBody
    @Session
    @Description("푸시를 수정한다.")
    @Histories({
            @History(date = "2020-05-23", description = "설계"),
            @History(date = "2020-05-24", description = "완료"),
    })
    public PcResp updatePush(
            @PathVariable("pushId") @Description("푸시 ID") @LengthValidation(max = 30) Long pushId,
            @RequestParam("title") @Description("제목") @LengthValidation(max = 150) String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "delImgFileId", required = false) @Description("삭제된 이미지 파일 ID") Long delImgFileId,
            @RequestParam(value = "imgFile", required = false) @Description("이미지 파일") MultipartFile imgFile,
            @RequestParam(value = "reserveDate", required = false) @Description("전송 예약일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String reserveDate,
            @RequestParam(value = "reserveHour", required = false) @Description("전송 예약 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String reserveHour,
            @RequestParam(value = "reserveMinute", required = false) @Description("전송 예약 분<br>" +
                    "Regex: ([0-5][0-9])") @RegexValidation(regex = "([0-5][0-9])") String reserveMinute,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "osType", required = false) @Description("OS - ALL(모두), I(iOS), A(Android)") @EnumValidation({"ALL", "I", "A"}) String osType,
            @RequestParam("categoryType") @Description("이동 카테고리 타입<br>" +
                    "PUSH_CATEGORY.java") @EnumValidation({"AD", "NT"}) String categoryType,
            @RequestParam("moveType") @Description("이동 타입<br>" +
                    "MOVE.java") String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") @LengthValidation(max = 200) String moveUrl,
            HttpSession session
    ) throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException {
        long modId = (long) session.getAttribute("admin_id");

        if ("ALL".equals(osType)) {
            osType = null;
        }
        if ("NONE".equals(moveType)) {
            moveType = null;
        }

        return pushService.updatePush(pushId,
                title, body, delImgFileId, imgFile,
                reserveDate, reserveHour, reserveMinute,
                organizeId,
                osType,
                categoryType, moveType, moveId, moveUrl,
                modId);
    }

    @RequestMapping(value = "/pushes/{pushId}", method = RequestMethod.DELETE, name = "05. 푸시 삭제")
    @ResponseBody
    @Session
    @Description("푸시를 삭제한다.")
    @Histories({
            @History(date = "2020-05-23", description = "설계"),
            @History(date = "2020-05-24", description = "완료"),
    })
    public PcResp deletePush(
            @PathVariable("pushId") @Description("푸시 ID") Long pushId
    ) {
        return pushService.deletePush(pushId);
    }

    @RequestMapping(value = "/pushes/{pushId}/resend", method = RequestMethod.POST, name = "06. 푸시 재전송")
    @ResponseBody
    @Session
    @Description("푸시를 재전송한다.")
    @Histories({
            @History(date = "2020-06-15", description = "설계"),
            @History(date = "2020-06-15", description = "완료"),
    })
    public PcResp resendPush(
            @PathVariable("pushId") @Description("푸시 ID") Long pushId,
            @RequestParam("type") @Description("타입<br>" +
                    "P: 푸시로 전송, S: SMS로 전송") @EnumValidation({"P", "S"}) String type
    ) {
        return pushService.resendPush(pushId, type);
    }
}
