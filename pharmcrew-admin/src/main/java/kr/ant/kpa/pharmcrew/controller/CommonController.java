package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.RangeValidation;
import kr.ant.kpa.pharmcrew.resp.common.OrganizeListResp;
import kr.ant.kpa.pharmcrew.resp.common.PharmListResp;
import kr.ant.kpa.pharmcrew.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("공통")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/organizes", method = RequestMethod.GET, name = "01. 조직 리스트")
    @ResponseBody
    @Session
    @Description("하위 조직 리스트를 조회한다." +
            "(v0.2_2020.05.11 - 10, 13)")
    @Histories({
            @History(date = "2020-05-14", description = "설계"),
            @History(date = "2020-05-15", description = "완료"),
            @History(date = "2020-05-19", description = "조직 DB 변경 적용"),
    })
    public OrganizeListResp organizeList(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "depth", required = false) @Description("조직 레벨") @RangeValidation(min = 1, max = 2) Integer depth
    ) {
        OrganizeListResp resp = commonService.organizeList(organizeId, depth);
        return resp;
    }

    @RequestMapping(value = "/pharms", method = RequestMethod.GET, name = "02. 약국 리스트")
    @ResponseBody
    @Session
    @Description("약국 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-24", description = "완료"),
    })
    public PharmListResp pharmList() {
        return commonService.pharmList();
    }

    @RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public void downloadFile(
            @PathVariable("fileId") long fileId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        commonService.downloadFile(fileId, request, response);
    }
}
