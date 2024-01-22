package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.DatetimeValidation;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyAnswerListResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyListResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveyResp;
import kr.ant.kpa.pharmcrew.service.SurveyService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletableSurveyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller("설문")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/surveys", method = RequestMethod.POST, name = "01. 설문 등록")
    @ResponseBody
    @Session
    @Description("설문을 등록한다.")
    @Histories({
            @History(date = "2020-05-26", description = "설계"),
            @History(date = "2020-05-27", description = "완료"),
    })
   public PcResp registSurvey(
           @RequestParam("startDate") @Description("설문 시작 일(SimpleDateFormat: yyyy.MM.dd") String startDate,
           @RequestParam(value = "endDate", required = false) @Description("설문 종료 일(SimpleDateFormat: yyyy.MM.dd") String endDate,
           @RequestParam("isOpen") @Description("노출 여부" +
                   "Y: 노출, N: 비노출") @EnumValidation({"Y", "N"}) String isOpen,
           @RequestParam("openResult") @Description("결과 노출 여부" +
                   "Y: 노출, N: 비노출") @EnumValidation({"Y", "N"}) String openResult,
           @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
           @RequestParam("title") @Description("제목") @LengthValidation(max = 100) String title,
           @RequestParam("detail") @Description("상세") @LengthValidation(max = 45) String detail,
           @RequestParam("questionsJson") @Description("SurveyQuestions JSON 문자열") String questionsJson,
           HttpSession session
    ) {
        long regId = (long) session.getAttribute("admin_id");

        return surveyService.registSurvey(startDate, endDate, isOpen, openResult, organizeId, title, detail, questionsJson, regId);
    }

    @RequestMapping(value = "/surveys", method = RequestMethod.GET, name = "02. 설문 리스트")
    @ResponseBody
    @Session
    @Description("설문 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-26", description = "설계"),
            @History(date = "2020-05-26", description = "완료"),
    })
    public SurveyListResp surveyList(
            @RequestParam(value = "startDate", required = false) @Description("설문 기간 검색 시작일") @DatetimeValidation(format = "yyyy.MM.dd") String startDate,
            @RequestParam(value = "endDate", required = false) @Description("설문 기간 검색 종료일") @DatetimeValidation(format = "yyyy.MM.dd") String endDate,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "isOpen", required = false) @Description("노출 여부<br>" +
                    "ALL: 전체, Y: 노출, N: 비노출") @EnumValidation({"ALL", "Y", "N"}) String isOpen,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return surveyService.surveyList(startDate, endDate, organizeId, keyword, isOpen, page, cntPerPage);
    }

    @RequestMapping(value = "/surveys/{surveyId}", method = RequestMethod.GET, name = "03. 설문")
    @ResponseBody
    @Session
    @Description("설문을 조회한다.")
    @Histories({
            @History(date = "2020-05-27", description = "설계"),
            @History(date = "2020-05-27", description = "완료"),
    })
    public SurveyResp survey(
            @PathVariable("surveyId") @Description("설문 ID") Long surveyId
    ) {
        return surveyService.survey(surveyId);
    }

    @RequestMapping(value = "/surveys/{surveyId}", method = RequestMethod.PUT, name = "04. 설문 수정")
    @ResponseBody
    @Session
    @Description("설문을 수정한다.")
    @Histories({
            @History(date = "2020-05-27", description = "설계"),
            @History(date = "2020-05-27", description = "완료"),
    })
    public PcResp updateSurvey(
            @PathVariable("surveyId") @Description("설문 ID") Long surveyId,
            @RequestParam("startDate") @Description("설문 시작 일(SimpleDateFormat: yyyy.MM.dd") String startDate,
            @RequestParam(value = "endDate", required = false) @Description("설문 종료 일(SimpleDateFormat: yyyy.MM.dd") String endDate,
            @RequestParam("isOpen") @Description("노출 여부" +
                    "Y: 노출, N: 비노출") @EnumValidation({"Y", "N"}) String isOpen,
            @RequestParam("openResult") @Description("결과 노출 여부" +
                    "Y: 노출, N: 비노출") @EnumValidation({"Y", "N"}) String openResult,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("questionsJson") @Description("SurveyQuestions JSON 문자열") String questionsJson,
            HttpSession session
    ) {
        long modId = (long) session.getAttribute("admin_id");

        return surveyService.updateSurvey(surveyId, startDate, endDate, isOpen, openResult, organizeId, title, questionsJson, modId);
    }

    @RequestMapping(value = "/surveys/{surveyId}", method = RequestMethod.DELETE, name = "05. 설문 삭제")
    @ResponseBody
    @Session
    @Description("설문을 삭제한다.")
    @Histories({
            @History(date = "2020-05-27", description = "설계"),
            @History(date = "2020-05-27", description = "완료"),
    })
    public PcResp deleteSurvey(
            @PathVariable("surveyId") @Description("설문 ID") Long surveyId
    ) throws NotDeletableSurveyException {
        return surveyService.deleteSurvey(surveyId);
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionIdx}/answers", method = RequestMethod.GET, name = "06. 설문 응답 리스트")
    @ResponseBody
    @Session
    @Description("설문 문항(주관식)의 응답 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-28", description = "설계"),
            @History(date = "2020-05-28", description = "완료"),
    })
    public SurveyAnswerListResp answerList(
            @PathVariable("surveyId") @Description("설문 ID") Long surveyId,
            @PathVariable("questionIdx") @Description("질문 인덱스") Long questionIdx,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return surveyService.answerList(surveyId, questionIdx, page, cntPerPage);
    }
}
