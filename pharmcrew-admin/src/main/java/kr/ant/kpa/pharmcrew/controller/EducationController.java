package kr.ant.kpa.pharmcrew.controller;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;
import com.bumdori.spring.annotation.validation.DatetimeValidation;
import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.education.EduAttendListResp;
import kr.ant.kpa.pharmcrew.resp.education.EducationListResp;
import kr.ant.kpa.pharmcrew.resp.education.EducationResp;
import kr.ant.kpa.pharmcrew.service.EducationService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidJsonFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller("교육")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @RequestMapping(value = "/educations", method = RequestMethod.POST, name = "01. 교육 등록")
    @ResponseBody
    @Session
    @Description("교육을 등록한다.")
    @Histories({
            @History(date = "2020-05-31", description = "설계"),
            @History(date = "2020-06-01", description = "완료"),
    })
    public PcResp registEducation(
            @RequestParam("authType") @Description("인증 타입<br>" +
                    "A: 강의 전체, O: 강의 개별") @EnumValidation({"A", "P"}) String authType,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y: 노출, N: 비노출") String isOpen,
            @RequestParam("title") @Description("제목") String title,
            @RequestParam("startDate") @Description("교육 시작일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String startDate,
            @RequestParam("endDate") @Description("교육 종료일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String endDate,
            @RequestParam(value = "startHour", required = true) @Description("교육 시작 시<br>" +
//                    "authType이 A일 때 필수<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String startHour,
            @RequestParam(value = "startMinute", required = true) @Description("교육 시작 분<br>" +
//                    "authType이 A일 때 필수<br>" +
                    "Regex: ([0-5][0-9])") String startMinute,
            @RequestParam(value = "endHour", required = true) @Description("교육 종료 시<br>" +
//                    "authType이 A일 때 필수<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String endHour,
            @RequestParam(value = "endMinute", required = true) @Description("교육 종료 분<br>" +
//                    "authType이 A일 때 필수<br>" +
                    "Regex: ([0-5][0-9])") String endMinute,
            @RequestParam(value = "takeHour", required = false) @Description("교육 인정 시간(시)<br>" +
                    "authType이 A일 때 필수<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String takeHour,
            @RequestParam(value = "takeMinute", required = false) @Description("교육 인정 시간(분)<br>" +
                    "authType이 A일 때 필수<br>" +
                    "Regex: ([0-5][0-9])") String takeMinute,
            @RequestParam(value = "qrType", required = false) @Description("QR 생성 타입" +
                    "authType이 A일 때 필수<br>" +
                    "O: 하나, M: 각각(시작, 종료)") @EnumValidation({"O", "M"}) String qrType,
            @RequestParam("address") @Description("주소") @LengthValidation(max = 100) String address,
            @RequestParam("addressDetail") @Description("주소 상세") @LengthValidation(max = 50) String addressDetail,
            @RequestParam("longitude") @Description("경도") String longitude,
            @RequestParam("latitude") @Description("위도") String latitude,
            @RequestParam("wayGuide") @Description("오시는 길(요약)") @LengthValidation(max = 50) String wayGuide,
            @RequestParam(value = "wayDetail", required = false) @Description("오시는 길(상세)") @LengthValidation(max = 150) String wayDetail,
            @RequestParam(value = "mapFile", required = false) @Description("약도 파일") MultipartFile mapFile,
            @RequestParam(value = "timetableFile", required = false) @Description("시간표 파일") MultipartFile timetableFile,
            @RequestParam("master") @Description("담당자") @LengthValidation(max = 100) String master,
            @RequestParam(value = "masterPhone", required = false) @Description("담당자 휴대 전화번호") @LengthValidation(max = 15) String masterPhone,
            @RequestParam("coursessJson") @Description("EducationCoursess JSON 문자열") String coursessJson,
            HttpSession session
            ) throws FailSaveFileException, InvalidJsonFormatException {
        long regId = (long) session.getAttribute("admin_id");

        return educationService.registEducation(authType, organizeId, isOpen, title,
                startDate, endDate, startHour, startMinute, endHour, endMinute,
                takeHour, takeMinute,
                qrType,
                address, addressDetail, longitude, latitude,
                wayGuide, wayDetail,
                mapFile, timetableFile,
                master, masterPhone,
                coursessJson,
                regId);
    }

    @RequestMapping(value = "/educations", method = RequestMethod.GET, name = "02. 교육 리스트")
    @ResponseBody
    @Session
    @Description("교육 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-05-30", description = "설계"),
            @History(date = "2020-05-30", description = "완료"),
    })
    public EducationListResp educationList(
            @RequestParam(value = "startDate", required = false) @Description("교육 기간 검색 시작일") @DatetimeValidation(format = "yyyy.MM.dd") String startDate,
            @RequestParam(value = "endDate", required = false) @Description("교육 기간 검색 종료일") @DatetimeValidation(format = "yyyy.MM.dd") String endDate,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam(value = "keyword", required = false) @Description("검색어(제목)") String keyword,
            @RequestParam(value = "isOpen", required = false) @Description("노출 여부<br>" +
                    "ALL: 전체, Y: 노출, N: 비노출") @EnumValidation({"ALL", "Y", "N"}) String isOpen,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return educationService.educationList(startDate, endDate, organizeId, keyword, isOpen, page, cntPerPage);
    }

    @RequestMapping(value = "/educations/{educationId}", method = RequestMethod.GET/*, name = "03. 교육"*/)
    @ResponseBody
    @Session
//    @Description("교육을 조회한다.")
//    @Histories({
//            @History(date = "2020-06-01", description = "설계"),
//            @History(date = "2020-06-01", description = "완료"),
//    })
    public EducationResp education(
            @PathVariable("educationId") Long educationId
    ) {
        return educationService.education(educationId);
    }

    @RequestMapping(value = "/educations/{educationId}", method = RequestMethod.POST, name = "04. 교육 수정")
    @ResponseBody
    @Session
    @Description("교육을 수정한다.")
    @Histories({
            @History(date = "2020-06-01", description = "설계"),
            @History(date = "2020-06-03", description = "완료"),
    })
    public PcResp updateEducation(
            @PathVariable("educationId") Long educationId,
            @RequestParam(value = "organizeId", required = false) @Description("조직 ID") Long organizeId,
            @RequestParam("isOpen") @Description("노출 여부<br>" +
                    "Y: 노출, N: 비노출") String isOpen,
            @RequestParam("title") @Description("제목") @LengthValidation(max = 100) String title,
            @RequestParam("startDate") @Description("교육 시작일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String startDate,
            @RequestParam("endDate") @Description("교육 종료일<br>" +
                    "SimpleDateFormat: yyyy.MM.dd") @DatetimeValidation(format = "yyyy.MM.dd") String endDate,
            @RequestParam(value = "startHour", required = true) @Description("교육 시작 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String startHour,
            @RequestParam(value = "startMinute", required = true) @Description("교육 시작 분<br>" +
                    "Regex: ([0-5][0-9])") String startMinute,
            @RequestParam(value = "endHour", required = true) @Description("교육 종료 시<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String endHour,
            @RequestParam(value = "endMinute", required = true) @Description("교육 종료 분<br>" +
                    "Regex: ([0-5][0-9])") String endMinute,
            @RequestParam(value = "takeHour", required = false) @Description("교육 인정 시간(시)<br>" +
                    "authType이 A일 때 필수<br>" +
                    "Regex: ([1-9]|[01][0-9]|2[0-3])") String takeHour,
            @RequestParam(value = "takeMinute", required = false) @Description("교육 인정 시간(분)<br>" +
                    "authType이 A일 때 필수<br>" +
                    "Regex: ([0-5][0-9])") String takeMinute,
            @RequestParam("address") @Description("주소") @LengthValidation(max = 100) String address,
            @RequestParam("addressDetail") @Description("주소 상세") @LengthValidation(max = 50) String addressDetail,
            @RequestParam("longitude") @Description("경도") String longitude,
            @RequestParam("latitude") @Description("위도") String latitude,
            @RequestParam("wayGuide") @Description("오시는 길(요약)") @LengthValidation(max = 50) String wayGuide,
            @RequestParam(value = "wayDetail", required = false) @Description("오시는 길(상세)") @LengthValidation(max = 150) String wayDetail,
            @RequestParam(value = "mapFile", required = false) @Description("약도 파일") MultipartFile mapFile,
            @RequestParam(value = "timetableFile", required = false) @Description("시간표 파일") MultipartFile timetableFile,
            @RequestParam("master") @Description("담당자") @LengthValidation(max = 100) String master,
            @RequestParam(value = "masterPhone", required = false) @Description("담당자 휴대 전화번호") @LengthValidation(max = 15) String masterPhone,
            @RequestParam("coursessJson") @Description("EducationCoursess JSON 문자열") String coursessJson,
            @RequestParam(value = "delMapFileId", required = false) @Description("삭제된 약도 파일 ID") Long delMapFileId,
            @RequestParam(value = "delTimetableFileId", required = false) @Description("삭제된 약도 파일 ID") Long delTimetableFileId,
            HttpSession session
    ) throws FailSaveFileException, InvalidJsonFormatException {
        long modId = (long) session.getAttribute("admin_id");

        return educationService.updateEducation(educationId,
                organizeId, isOpen, title,
                startDate, endDate, startHour, startMinute, endHour, endMinute,
                takeHour, takeMinute,
                address, addressDetail, longitude, latitude,
                wayGuide, wayDetail,
                mapFile, timetableFile,
                master, masterPhone,
                coursessJson,
                delMapFileId, delTimetableFileId,
                modId);
    }
    
    @RequestMapping(value = "/educations/{educationId}", method = RequestMethod.DELETE, name = "05. 교육 삭제")
    @ResponseBody
    @Session
    @Description("교육을 삭제한다.")
    @Histories({
    		@History(date = "2020-06-03", description = "설계"),
            @History(date = "2020-06-04", description = "완료"),
    })
    public PcResp deleteEducation(
            @PathVariable("educationId") Long educationId
    ) {
    	return educationService.deleteEducation(educationId);
    }

    @RequestMapping(value = "/educations/{educationId}/attends", method = RequestMethod.GET, name = "06. 교육 참여 리스트")
    @ResponseBody
    @Session
    @Description("교육 참여 리스트를 조회한다.")
    @Histories({
            @History(date = "2020-06-04", description = "설계"),
            @History(date = "2020-06-04", description = "완료"),
    })
    public EduAttendListResp eduAttendList(
            @PathVariable("educationId") @Description("교육 ID") Long educationId,
            @RequestParam(value = "courseId", required = false) @Description("강의 ID") Long courseId,
            @RequestParam("type") @Description("타입<br>" +
                    "ALL: 전체, E: 후기, A: 승인") @EnumValidation({"ALL", "E", "A"}) String type,
            @RequestParam(value = "keyword", required = false) @Description("키워드(회원명)") String keyword,
            @RequestParam(value = "page", defaultValue = "1") @Description("페이지") Integer page,
            @RequestParam(value = "cntPerPage", defaultValue = "20") @Description("페이지 당 보여줄 항목 수") Integer cntPerPage
    ) {
        return educationService.eduAttendList(educationId, courseId, type, keyword, page, cntPerPage);
    }

    @RequestMapping(value = "/educations/{educationId}/attendapproval", method = RequestMethod.PUT, name = "07. 교육 참여 승인 여부 설정")
    @ResponseBody
    @Session
    @Description("교육 참여 승인 여부를 결정한다.")
    @Histories({
            @History(date = "2020-06-04", description = "설계")
    })
    public PcResp updateEduAttendApproval(
            @PathVariable("educationId") @Description("교육 ID") Long educationId,
            @RequestParam("attendIds") @Description("참여 ID 리스트") Long[] attendIds,
            @RequestParam("approval") @Description("승인 상태<br>" +
                    "Y: 승인, N: 미승인") String approval
    ) {
        return educationService.updateEduAttendApproval(educationId, attendIds, approval);
    }
}
