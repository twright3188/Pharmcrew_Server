package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoResp;
import kr.ant.kpa.pharmcrew.service.AcaedmyService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("학술")
public class AcademyController {
    @Autowired
    private AcaedmyService acaedmyService;

    @RequestMapping(value = "/docs", method = RequestMethod.POST, name = "01. 문서 등록")
    @ResponseBody
    @Session
    @Description("문서를 등록한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계"),
            @History(date = "2020-06-17", description = "완료"),
    })
    public PcResp registerDoc(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam("docFile") @Description("문서 파일") MultipartFile docFile,
            HttpSession session
    ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return acaedmyService.registerDoc(organizeId, isOpen, title, body, docFile, regId);
    }

    @RequestMapping(value = "/docs", method = RequestMethod.GET, name = "02. 문서 리스트")
    @ResponseBody
    @Session
    @Description("문서 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계"),
    })
    public DocListResp docList(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return acaedmyService.docList(organizeId, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/docs/{docId}", method = RequestMethod.GET, name = "03. 문서")
    @ResponseBody
    @Session
    @Description("문서를 조회한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public DocResp doc(
            @PathVariable("docId") @Description("문서 ID") Long docId
    ) {
        return acaedmyService.doc(docId);
    }

    @RequestMapping(value = "/docs/{docId}", method = RequestMethod.POST, name = "04. 문서 수정")
    @ResponseBody
    @Session
    @Description("문서를 수정한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public PcResp updateDoc(
            @PathVariable("docId") @Description("문서 ID") Long docId,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("body") @Description("내용") String body,
            @RequestParam(value = "docFile", required = false) @Description("문서 파일") MultipartFile docFile,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return acaedmyService.updateDoc(docId, organizeId, isOpen, title, body, docFile, modId);
    }

    @RequestMapping(value = "/docs/{docId}", method = RequestMethod.DELETE, name = "05. 문서 삭제")
    @ResponseBody
    @Session
    @Description("문서를 삭제한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public PcResp deleteDoc(
            @PathVariable("docId") @Description("문서 ID") Long docId
    ) {
        return acaedmyService.deleteDoc(docId);
    }

    @RequestMapping(value = "/videos", method = RequestMethod.POST, name = "06. 동영상 등록")
    @ResponseBody
    @Session
    @Description("동영상을 등록한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계"),
            @History(date = "2020-06-17", description = "완료"),
    })
    public PcResp registerVideo(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("url") @Description("URL") String url,
//            @RequestParam("thumbFile") @Description("썸네일 파일") MultipartFile thumbFile,
            HttpSession session
    ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return acaedmyService.registerVideo(organizeId, isOpen, title, url, regId);
    }

    @RequestMapping(value = "/videos", method = RequestMethod.GET, name = "07. 동영상 리스트")
    @ResponseBody
    @Session
    @Description("동영상 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계"),
    })
    public VideoListResp videoList(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return acaedmyService.videoList(organizeId, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/videos/{videoId}", method = RequestMethod.GET, name = "08. 동영상")
    @ResponseBody
    @Session
    @Description("동영상을 조회한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public VideoResp video(
            @PathVariable("videoId") @Description("동영상 ID") Long videoId
    ) {
        return acaedmyService.video(videoId);
    }

    @RequestMapping(value = "/videos/{videoId}", method = RequestMethod.POST, name = "09. 동영상 수정")
    @ResponseBody
    @Session
    @Description("동영상을 수정한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public PcResp updateVideo(
            @PathVariable("videoId") @Description("문서 ID") Long videoId,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("url") @Description("URL") String url,
//            @RequestParam(value = "thumbFile", required = false) @Description("썸네일 파일") MultipartFile thumbFile,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return acaedmyService.updateVideo(videoId, organizeId, isOpen, title, url, modId);
    }

    @RequestMapping(value = "/videos/{videoId}", method = RequestMethod.DELETE, name = "10. 동영상 삭제")
    @ResponseBody
    @Session
    @Description("동영상을 삭제한다.")
    @Histories({
            @History(date = "2020-06-17", description = "설계")
    })
    public PcResp deleteVideo(
            @PathVariable("videoId") @Description("동영상 ID") Long videoId
    ) {
        return acaedmyService.deleteVideo(videoId);
    }
}
