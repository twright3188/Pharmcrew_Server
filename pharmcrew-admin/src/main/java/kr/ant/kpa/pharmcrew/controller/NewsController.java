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
import kr.ant.kpa.pharmcrew.resp.news.*;
import kr.ant.kpa.pharmcrew.service.NewsService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("소식")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/notices", method = RequestMethod.POST, name = "01. 공지 등록")
    @ResponseBody
    @Session
    @Description("공지를 등록한다." +
            "(v0.2_2020.05.11 - 22)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-17", description = "완료"),
    })
    public PcResp registerNotice(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("title") @Description("제목") @LengthValidation(max = 100) String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "attachFiles", required = false) @Description("첨부 파일 리스트") MultipartFile[] attachFiles,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            HttpSession session
            ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return newsService.registerNotice(organizeId, title, body, attachFiles, isOpen, regId);
    }

    @RequestMapping(value = "/notices", method = RequestMethod.GET, name = "02. 공지 리스트")
    @ResponseBody
    @Session
    @Description("공지 리스트를 조회한다." +
            "(v0.2_2020.05.11 - 20)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-16", description = "작업 중"),
            @History(date = "2020-05-18", description = "완료"),
    })
    public NoticeListResp noticeList(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목, 내용)") String keyword,
            @RequestParam(value = "isOpen", required = false) @Description("노출 여부<br>" +
                    "ALL: 전체, Y: 노출, N: 비노출") @EnumValidation({"ALL", "Y", "N"}) String isOpen,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        if ("ALL".equals(isOpen))   isOpen = null;

        return newsService.noticeList(organizeId, keyword, isOpen, page, cntPerPage);
    }

    @RequestMapping(value = "/notices/{noticeId}", method = RequestMethod.GET, name = "03. 공지")
    @ResponseBody
    @Session
    @Description("공지를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-20", description = "완료"),
    })
    public NoticeResp notice(
            @PathVariable("noticeId") @Description("공지 ID") Long noticeId
    ) {
        return newsService.notice(noticeId);
    }

    @RequestMapping(value = "/notices/{noticeId}", method = RequestMethod.POST, name = "04. 공지 수정")
    @ResponseBody
    @Session
    @Description("공지를 수정한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-21", description = "완료"),
    })
    public PcResp updateNotice(
            @PathVariable("noticeId") @Description("공지 ID") Long noticeId,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "delAttachFileIds", required = false) @Description("삭제된 첨부 파일 ID 리스트") Long[] delAttachFileIds,
            @RequestParam(value = "attachFiles", required = false) @Description("첨부 파일 리스트") MultipartFile[] attachFiles,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") String isOpen,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return newsService.updateNotice(noticeId, organizeId, title, body, delAttachFileIds, attachFiles, isOpen, modId);
    }

    @RequestMapping(value = "/notices/{noticeId}", method = RequestMethod.DELETE, name = "05. 공지 삭제")
    @ResponseBody
    @Session
    @Description("공지를 삭제한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-21", description = "완료"),
    })
    public PcResp deleteNotice(
            @PathVariable("noticeId") Long noticeId
    ) {
        return newsService.deleteNotice(noticeId);
    }

    @RequestMapping(value = "/popups", method = RequestMethod.POST, name = "06. 팝업 등록")
    @ResponseBody
    @Session
    @Description("팝업을 등록한다." +
            "(v0.2_2020.05.11 - 25)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-18", description = "작업 중"),
            @History(date = "2020-05-19", description = "완료"),
    })
    public PcResp registerPopup(
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("popupFile") @Description("팝업 파일") MultipartFile popupFile,
            @RequestParam(value = "idx", required = false) @Description("노출 순서") Integer idx,
            @RequestParam("moveType") @Description("이동 타입<br>MOVE.java") String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") String isOpen,
            @RequestParam("openStartDate") @Description("노출 시작 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openStartDate,
            @RequestParam("openStartHour") @Description("노출 시작 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openStartHour,
            @RequestParam("openEndDate") @Description("노출 종료 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openEndDate,
            @RequestParam("openEndHour") @Description("노출 종료 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openEndHour,
            HttpSession session
    ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return newsService.registerPopup(title, popupFile, idx,
                moveType, moveId, moveUrl,
                isOpen,
                openStartDate, openStartHour, openEndDate, openEndHour,
                regId);
    }

    @RequestMapping(value = "/popups", method = RequestMethod.GET, name = "07. 팝업 리스트")
    @ResponseBody
    @Session
    @Description("팝업 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-20", description = "완료"),
    })
    public PopupListResp popupList(
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return newsService.popupList(keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/popups/{popupId}", method = RequestMethod.GET, name = "08. 팝업")
    @ResponseBody
    @Session
    @Description("팝업을 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-21", description = "완료"),
    })
    public PopupResp popup(
            @PathVariable("popupId") Long popupId
    ) {
        return newsService.popup(popupId);
    }

    @RequestMapping(value = "/popups/{popupId}", method = RequestMethod.POST, name = "09. 팝업 수정")
    @ResponseBody
    @Session
    @Description("팝업을 수정한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public PcResp updatePopup(
            @PathVariable("popupId") @Description("팝업 ID") Long popupId,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam(value = "popupFile", required = false) @Description("팝업 파일") MultipartFile popupFile,
            @RequestParam(value = "idx", required = false) @Description("노출 순서") Integer idx,
            @RequestParam("moveType") @Description("이동 타입<br>MOVE.java") String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") String isOpen,
            @RequestParam("openStartDate") @Description("노출 시작 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openStartDate,
            @RequestParam("openStartHour") @Description("노출 시작 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openStartHour,
            @RequestParam("openEndDate") @Description("노출 종료 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openEndDate,
            @RequestParam("openEndHour") @Description("노출 종료 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openEndHour,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return newsService.updatePopup(popupId,
                title, popupFile, idx,
                moveType, moveId, moveUrl,
                isOpen,
                openStartDate, openStartHour, openEndDate, openEndHour,
                modId);
    }

    @RequestMapping(value = "/popups/{popupId}", method = RequestMethod.DELETE, name = "10. 팝업 삭제")
    @ResponseBody
    @Session
    @Description("팝업을 삭제한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public PcResp deletePopup(
            @PathVariable("popupId") @Description("팝업 ID") Long popupId
    ) {
        return newsService.deletePopup(popupId);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.POST, name = "11. 배너 등록")
    @ResponseBody
    @Session
    @Description("배너를 등록한다." +
            "(v0.2_2020.05.11 - 25)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-19", description = "완료"),
    })
    public PcResp registerBanner(
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("bannerFile") @Description("팝업 파일") MultipartFile bannerFile,
            @RequestParam(value = "idx", required = false) @Description("노출 순서") Integer idx,
            @RequestParam("moveType") @Description("이동 타입<br>MOVE.java") String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") String isOpen,
            @RequestParam("openStartDate") @Description("노출 시작 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openStartDate,
            @RequestParam("openStartHour") @Description("노출 시작 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openStartHour,
            @RequestParam("openEndDate") @Description("노출 종료 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openEndDate,
            @RequestParam("openEndHour") @Description("노출 종료 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openEndHour,
            HttpSession session
    ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return newsService.registerBanner(title, bannerFile, idx,
                moveType, moveId, moveUrl,
                isOpen,
                openStartDate, openStartHour, openEndDate, openEndHour,
                regId);
    }

    @RequestMapping(value = "/banners", method = RequestMethod.GET, name = "12. 배너 리스트")
    @ResponseBody
    @Session
    @Description("배너 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-20", description = "완료"),
    })
    public BannerListResp bannerList(
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return newsService.bannerList(keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/banners/{bannerId}", method = RequestMethod.GET, name = "13. 배너")
    @ResponseBody
    @Session
    @Description("배너를 조회한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public BannerResp banner(
            @PathVariable("bannerId") Long bannerId
    ) {
        return newsService.banner(bannerId);
    }

    @RequestMapping(value = "/banners/{bannerId}", method = RequestMethod.POST, name = "14. 배너 수정")
    @ResponseBody
    @Session
    @Description("배너를 수정한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public PcResp updateBanner(
            @PathVariable("bannerId") @Description("배너 ID") Long bannerId,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam(value = "bannerFile", required = false) @Description("배너 파일") MultipartFile bannerFile,
            @RequestParam(value = "idx", required = false) @Description("노출 순서") Integer idx,
            @RequestParam("moveType") @Description("이동 타입<br>MOVE.java") String moveType,
            @RequestParam(value = "moveId", required = false) @Description("이동 ID") Long moveId,
            @RequestParam(value = "moveUrl", required = false) @Description("이동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") String isOpen,
            @RequestParam("openStartDate") @Description("노출 시작 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openStartDate,
            @RequestParam("openStartHour") @Description("노출 시작 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openStartHour,
            @RequestParam("openEndDate") @Description("노출 종료 일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String openEndDate,
            @RequestParam("openEndHour") @Description("노출 종료 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") @RegexValidation(regex = "([1-9]|[01][0-9]|2[0-3])") String openEndHour,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return newsService.updateBanner(bannerId,
                title, bannerFile, idx,
                moveType, moveId, moveUrl,
                isOpen,
                openStartDate, openStartHour, openEndDate, openEndHour,
                modId);
    }

    @RequestMapping(value = "/banners/{bannerId}", method = RequestMethod.DELETE, name = "15. 배너 삭제")
    @ResponseBody
    @Session
    @Description("배너를 삭제한다.")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-23", description = "완료"),
    })
    public PcResp deleteBanner(
            @PathVariable("bannerId") @Description("배너 ID") Long bannerId
    ) {
        return newsService.deleteBanner(bannerId);
    }

    @RequestMapping(value = "/qnas", method = RequestMethod.GET, name = "16. QNA 리스트")
    @ResponseBody
    @Session
    @Description("QNA 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-26", description = "설계"),
            @History(date = "2020-05-26", description = "작업 중"),
    })
    public QnaListResp qnaList(
            @RequestParam("type") @Description("ALL: 전체, N: 공지, E: 교육, OTHER: 공지, 교육을 제외한 나머지") @EnumValidation({"ALL", "E", "N", "OTHER"}) String type,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "isAnswerd", required = false) @Description("답변 상태<br>" +
                    "ALL: 모두, Y: 답변 완료, N: 문의 중") @EnumValidation({"ALL", "Y", "N"}) String isAnswerd,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return newsService.qnaList(type, organizeId, isAnswerd, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/qnas/{qnaId}", method = RequestMethod.GET, name = "17. QNA")
    @ResponseBody
    @Session
    @Description("QNA 조회한다.")
    @Histories({
            @History(date = "2020-05-26", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public QnaResp qna(
            @PathVariable("qnaId") @Description("QNA ID") Long qnaId
    ) {
        return newsService.qna(qnaId);
    }

    @RequestMapping(value = "/qnas/{qnaId}", method = RequestMethod.PUT, name = "18. QNA 답변 등록")
    @ResponseBody
    @Session
    @Description("QNA 답변을 등록한다.")
    @Histories({
            @History(date = "2020-05-26", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public PcResp answerQna(
            @PathVariable("qnaId") @Description("QNA ID") Long qnaId,
            @RequestParam("answerTitle") @Description("답변 제목") @LengthValidation(max = 50) String answerTitle,
            @RequestParam("answerBody") @Description("답변 내용") @LengthValidation(max = 200) String answerBody,
            HttpSession session
    ) {
        long answerId = (long) session.getAttribute("admin_id");

        return newsService.answerQna(qnaId, answerTitle, answerBody, answerId);
    }
}
