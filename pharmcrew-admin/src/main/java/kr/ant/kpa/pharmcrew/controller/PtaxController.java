package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.DatetimeValidation;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.regex.RegexValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxnoticeListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxnoticeResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxqnaListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxqnaResp;
import kr.ant.kpa.pharmcrew.service.PtaxService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePtaxnoticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("팜텍스")
public class PtaxController {
    @Autowired
    private PtaxService ptaxService;

    @RequestMapping(value = "/ptaxqnas", method = RequestMethod.GET, name = "01. QNA 리스트")
    @ResponseBody
    @Session
    @Description("QNA 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-21", description = "완료"),
    })
    public PtaxqnaListResp qnaList(
            @RequestParam(value = "regDtStartDate", required = false) @Description("등록일 검색 시작일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String regDtStartDate,
            @RequestParam(value = "regDtEndDate", required = false) @Description("등록일 검색 종료일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String regDtEndDate,
            @RequestParam(value = "target", defaultValue = "ALL") @Description("대상 - ALL(모두), G(Guest, 비회원), M(Member, 회원)") @EnumValidation({"ALL", "G", "M"}) String target,
            @RequestParam(value = "pharmName", required = false) @Description("약국명, target이 M일 경우에만 유효") String pharmName,
            @RequestParam(value = "answerYn", required = false) @Description("답변 여부 - Y(답변 완료), N(답변 대기)") String answerYn,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목, 내용)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
//        if ("ALL".equals(target)) {
//            target = null;
//        }
        if ("ALL".equals(answerYn)) {
            answerYn = null;
        }
        return ptaxService.qnaList(regDtStartDate, regDtEndDate, target, pharmName, answerYn, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/ptaxqnas/{qnaId}", method = RequestMethod.GET, name = "02. QNA")
    @ResponseBody
    @Session
    @Description("QNA를 조회한다.")
    @Histories({
            @History(date = "2020-06-21", description = "완료"),
    })
    public PtaxqnaResp qna(
            @PathVariable("qnaId") @Description("QNA ID") Long qnaId
    ) {
        return ptaxService.qna(qnaId);
    }

    @RequestMapping(value = "/ptaxqnas/{qnaId}", method = RequestMethod.POST, name = "03. QNA 답변 등록")
    @ResponseBody
    @Session
    @Description("QNA 답변을 등록한다.")
    @Histories({
            @History(date = "2020-06-21", description = "완료"),
    })
    public PcResp answerQna(
            @PathVariable("qnaId") @Description("QNA ID") Long qnaId,
            @RequestParam("answerTitle") @Description("답변 제목") String answerTitle,
            @RequestParam("answerBody") @Description("답변 내용") String answerBody,
            @RequestParam(value = "delAttachFileIds", required = false) @Description("삭제된 첨부 파일 ID 리스트") Long[] delAttachFileIds,
            @RequestParam(value = "attachFiles", required = false) @Description("첨부 파일 리스트") MultipartFile[] attachFiles,
            HttpSession session
    ) throws FailSaveFileException {
        long answerId = (long) session.getAttribute("admin_id");

        return ptaxService.answerQna(qnaId, answerTitle, answerBody, delAttachFileIds, attachFiles, answerId);
    }

    @RequestMapping(value = "/ptaxnotices", method = RequestMethod.POST, name = "04. 공지 등록")
    @ResponseBody
    @Session
    @Description("공지를 등록한다.")
    @Histories({
            @History(date = "2020-06-21", description = "설계"),
            @History(date = "2020-06-22", description = "완료"),
    })
    public PcResp registerNotice(
            @RequestParam("sendType") @Description("푸시 발송" +
                    "NONE: 발송하지 않음, R(eserve): 예약, I(mmediate): 즉시") @EnumValidation({"NONE", "R", "I"}) String sendType,
            @RequestParam(value = "target", required = false) @Description("수신 대상<br>" +
                    "ALL: 모두, M: 팜텍스 회원, G: 팜텍스 비회원") @EnumValidation({"ALL", "M", "G"}) String target,
            @RequestParam(value = "targetPharmName", required = false) @Description("target이 M일 경우 옵션") String targetPharmName,
            @RequestParam(value = "targetMemberId", required = false) @Description("target이 M일 경우 옵션") Long targetMemberId,
            @RequestParam("openYn") @Description("노출 여부<br>" +
                    "Y: 노출, N: 비노출") String openYn,
            @RequestParam(value = "reserveDate", required = false) @Description("전송 예약일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String reserveDate,
            @RequestParam(value = "reserveHour", required = false) @Description("전송 예약 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String reserveHour,
            @RequestParam(value = "reserveMinute", required = false) @Description("전송 예약 분<br>" +
                    "Regex: ([0-5][0-9])") @RegexValidation(regex = "([0-5][0-9])") String reserveMinute,
            @RequestParam(value = "osType", required = false) @Description("OS - ALL(모두), I(iOS), A(Android)") @EnumValidation({"ALL", "I", "A"}) String osType,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "attachFiles", required = false) @Description("첨부 파일 리스트") MultipartFile[] attachFiles,
            HttpSession session
    ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        if ("ALL".equals(target)) {
            target = null;
        }
        if ("ALL".equals(osType)) {
            osType = null;
        }
        if ("NONE".equals(sendType)) {
            sendType = null;
        }

        return ptaxService.registerNotice(sendType, target, targetPharmName, targetMemberId, openYn, reserveDate, reserveHour, reserveMinute, osType, title, body, attachFiles, regId);
    }

    @RequestMapping(value = "/ptaxnotices", method = RequestMethod.GET, name = "05. 공지 리스트")
    @ResponseBody
    @Session
    @Description("공지 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-21", description = "설계"),
            @History(date = "2020-06-23", description = "완료"),
    })
    public PtaxnoticeListResp noticeList(
            @RequestParam(value = "regDtStartDate", required = false) @Description("등록 일시 검색 시작일(SimpleDateFormat: yyyy.MM.dd") String regDtStartDate,
            @RequestParam(value = "regDtEndDate", required = false) @Description("등록 일시 검색 종료일(SimpleDateFormat: yyyy.MM.dd") String regDtEndDate,
            @RequestParam(value = "target", required = false) @Description("수신 대상<br>" +
                    "ALL: 전체, G(uest): 비회원, M(ember): 회원") @EnumValidation({"ALL", "G", "M"}) String target,
            @RequestParam(value = "targetPharmName", required = false) @Description("target이 M일 경우 유효") String targetPharmName,
            @RequestParam(value = "sendType", required = false) @Description("전송 타입<br>" +
                    "ALL: 전체, R(eserve): 예악, I(mmediate): 즉시") @EnumValidation({"ALL", "R", "I"}) String sendType,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        if ("ALL".equals(target)) {
            target = null;
        }
        if ("ALL".equals(sendType)) {
            sendType = null;
        }

        return ptaxService.noticeList(regDtStartDate, regDtEndDate, target, targetPharmName, sendType, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/ptaxnotices/{noticeId}", method = RequestMethod.GET, name = "06. 공지")
    @ResponseBody
    @Session
    @Description("공지를 조회한다.")
    @Histories({
            @History(date = "2020-06-21", description = "설계"),
            @History(date = "2020-06-23", description = "완료"),
    })
    public PtaxnoticeResp notice(
            @PathVariable("noticeId") @Description("공지 ID") Long noticeId
    ) {
        return ptaxService.notice(noticeId);
    }

    @RequestMapping(value = "/ptaxnotices/{noticeId}", method = RequestMethod.POST, name = "07. 공지 수정")
    @ResponseBody
    @Session
    @Description("공지를 수정한다.")
    @Histories({
            @History(date = "2020-06-21", description = "설계"),
    })
    public PcResp updateNotice(
            @PathVariable("noticeId") @Description("공지 ID") Long noticeId,
            @RequestParam("sendType") @Description("푸시 발송" +
                    "NONE: 발송하지 않음, R(eserve): 예약, I(mmediate): 즉시") @EnumValidation({"NONE", "R", "I"}) String sendType,
            @RequestParam(value = "target", required = false) @Description("수신 대상<br>" +
                    "ALL: 모두, M: 팜텍스 회원, G: 팜텍스 비회원") @EnumValidation({"ALL", "M", "G"}) String target,
            @RequestParam(value = "targetPharmName", required = false) @Description("target이 M일 경우 옵션") String targetPharmName,
            @RequestParam(value = "targetMemberId", required = false) @Description("target이 M일 경우 옵션") Long targetMemberId,
            @RequestParam("openYn") @Description("노출 여부<br>" +
                    "Y: 노출, N: 비노출") String openYn,
            @RequestParam(value = "reserveDate", required = false) @Description("전송 예약일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String reserveDate,
            @RequestParam(value = "reserveHour", required = false) @Description("전송 예약 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String reserveHour,
            @RequestParam(value = "reserveMinute", required = false) @Description("전송 예약 분<br>" +
                    "Regex: ([0-5][0-9])") @RegexValidation(regex = "([0-5][0-9])") String reserveMinute,
            @RequestParam(value = "osType", required = false) @Description("OS - ALL(모두), I(iOS), A(Android)") @EnumValidation({"ALL", "I", "A"}) String osType,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "delAttachFileIds", required = false) @Description("삭제된 첨부 파일 ID 리스트") Long[] delAttachFileIds,
            @RequestParam(value = "attachFiles", required = false) @Description("첨부 파일 리스트") MultipartFile[] attachFiles,
            HttpSession session) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        if ("ALL".equals(target)) {
            target = null;
        }
        if ("ALL".equals(osType)) {
            osType = null;
        }

        return ptaxService.updateNotice(noticeId, sendType, target, targetPharmName, targetMemberId, openYn, reserveDate, reserveHour, reserveMinute, osType, title, body, delAttachFileIds, attachFiles, modId);
    }

    @RequestMapping(value = "/ptaxnotices/{noticeId}", method = RequestMethod.DELETE, name = "08. 공지 삭제")
    @ResponseBody
    @Session
    @Description("공지를 삭제한다.")
    @Histories({
            @History(date = "2020-06-21", description = "설계"),
            @History(date = "2020-06-23", description = "완료"),
    })
    public PcResp deleteNotice(
            @PathVariable("noticeId") @Description("공지 ID") Long noticeId
    ) throws NotDeletablePtaxnoticeException {
        return ptaxService.deleteNotice(noticeId);
    }
}
