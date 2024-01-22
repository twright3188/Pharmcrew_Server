package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.EnumValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerListResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerResp;
import kr.ant.kpa.pharmcrew.service.PartnerService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePartnersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("파트너")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @RequestMapping(value = "/partners", method = RequestMethod.POST, name = "1. 파트너 등록")
    @ResponseBody
    @Session
    @Description("파트너를 등록한다.")
    @Histories({
            @History(date = "2020-05-25", description = "설계"),
            @History(date = "2020-05-25", description = "완료"),
    })
    public PcResp registPartner(
            @RequestParam("name") @Description("이름") String name,
            @RequestParam("detail") @Description("설명") String detail,
            @RequestParam("iconFile") @Description("아이콘 파일") MultipartFile iconFile,
            @RequestParam("moveUrl") @Description("연동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            HttpSession session
            ) throws FailSaveFileException {
        long regId = (long) session.getAttribute("admin_id");

        return partnerService.registPartner(name, detail, iconFile, moveUrl, isOpen, regId);
    }

    @RequestMapping(value = "/partners", method = RequestMethod.GET, name = "2. 파트너 리스트")
    @ResponseBody
    @Session
    @Description("파트너 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-25", description = "설계"),
            @History(date = "2020-05-25", description = "완료"),
    })
    public PartnerListResp partnerList(
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return partnerService.partnerList(page, cntPerPage);
    }

    @RequestMapping(value = "/partners/{partnerId}", method = RequestMethod.GET, name = "3. 파트너")
    @ResponseBody
    @Session
    @Description("파트너를 조회한다.")
    @Histories({
            @History(date = "2020-05-25", description = "설계"),
            @History(date = "2020-05-25", description = "완료"),
    })
    public PartnerResp partner(
            @PathVariable("partnerId") @Description("파트너 ID") Long partnerId
    ) {
        return partnerService.partner(partnerId);
    }

    @RequestMapping(value = "/partners/{partnerId}", method = RequestMethod.POST, name = "4. 파트너 수정")
    @ResponseBody
    @Session
    @Description("파트너를 수정한다.")
    @Histories({
            @History(date = "2020-05-25", description = "설계"),
            @History(date = "2020-05-25", description = "완료"),
    })
    public PcResp updatePartner(
            @PathVariable("partnerId") @Description("파트너 ID") Long partnerId,
            @RequestParam("name") @Description("이름") String name,
            @RequestParam("detail") @Description("설명") String detail,
            @RequestParam(value = "iconFile", required = false) @Description("아이콘 파일") MultipartFile iconFile,
            @RequestParam("moveUrl") @Description("연동 URL") String moveUrl,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y(es): 노출, N(o): 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            HttpSession session
    ) throws FailSaveFileException {
        long modId = (long) session.getAttribute("admin_id");

        return partnerService.updatePartner(partnerId, name, detail, iconFile, moveUrl, isOpen, modId);
    }

    @RequestMapping(value = "/partners/{partnerId}", method = RequestMethod.DELETE, name = "5. 파트너 삭제")
    @ResponseBody
    @Session
    @Description("파트너를 삭제한다.")
    @Histories({
            @History(date = "2020-05-25", description = "설계"),
            @History(date = "2020-05-25", description = "완료"),
    })
    public PcResp deletePartner(
            @PathVariable("partnerId") @Description("파트너 ID") Long partnerId
    ) throws NotDeletablePartnersException {
        return partnerService.deletePartner(partnerId);
    }

//    @RequestMapping(value = "/ptax/qnas", method = RequestMethod.GET, name = "6. 팜텍스 QNA 리스트")
//    @ResponseBody
//    @Session
//    @Description("팜텍스 QNA 리스트를 조회한다.")
//    @Histories({
//            @History(date = "2020-06-17", description = "설계")
//    })
//    public PTaxQnaListResp pTaxQnaList() {
//        return null;
//    }
}
