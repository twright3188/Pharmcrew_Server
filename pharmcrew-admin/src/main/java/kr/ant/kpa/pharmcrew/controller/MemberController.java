package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.DatetimeValidation;
import kr.ant.kpa.pharmcrew.resp.member.MemberEduTimeResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberListResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberResp;
import kr.ant.kpa.pharmcrew.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("회원")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members", method = RequestMethod.GET, name = "01. 회원 리스트")
    @ResponseBody
    @Session
    @Description("회원 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-28", description = "완료"),
    })
    public MemberListResp memberList(
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(이름, 면허번호)") String keyword,
            @RequestParam(value = "pharmName", required = false) @Description("약국명") String pharmName,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return memberService.memberList(organizeId, keyword, pharmName, page, cntPerPage);
    }

    @RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET, name = "02. 회원")
    @ResponseBody
    @Session
    @Description("회원을 조회한다.")
    @Histories({
            @History(date = "2020-05-28", description = "완료"),
    })
    public MemberResp member(
            @PathVariable("memberId") @Description("회원 ID") Long memberId
    ) {
        return memberService.member(memberId);
    }

    @RequestMapping(value = "/members/{memberId}/edutime", method = RequestMethod.GET, name = "03. 회원 교육 이수 시간")
    @ResponseBody
    @Session
    @Description("회원 교육 이수 시간을 조회한다.")
    @Histories({
            @History(date = "2020-06-05", description = "완료"),
    })
    public MemberEduTimeResp memberEduTime(
            @PathVariable("memberId") @Description("회원 ID") Long memberId,
            @RequestParam("year") @Description("년도<br>" +
                    "SimpleDateFormat: yyyy") @DatetimeValidation(format = "yyyy") String year
    ) {
        return memberService.memberEduTime(memberId, year);
    }
}
