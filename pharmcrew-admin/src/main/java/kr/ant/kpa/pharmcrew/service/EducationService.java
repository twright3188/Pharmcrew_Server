package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.education.EduAttendListResp;
import kr.ant.kpa.pharmcrew.resp.education.EducationListResp;
import kr.ant.kpa.pharmcrew.resp.education.EducationResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidJsonFormatException;
import org.springframework.web.multipart.MultipartFile;

public interface EducationService {
    EducationListResp educationList(String startDate, String endDate, Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage);
    PcResp registEducation(String authType, Long organizeId, String isOpen, String title,
                           String startDate, String endDate, String startHour, String startMinute, String endHour, String endMinute,
                           String takeHour, String takeMinute,
                           String qrType,
                           String address, String addressDetail, String longitude, String latitude,
                           String wayGuide, String wayDetail,
                           MultipartFile mapFile, MultipartFile timetableFile,
                           String master, String masterPhone,
                           String coursessJson,
                           long regId) throws FailSaveFileException, InvalidJsonFormatException;
    EducationResp education(Long educationId);
    PcResp updateEducation(Long educationId,
                           Long organizeId, String isOpen, String title,
                           String startDate, String endDate, String startHour, String startMinute, String endHour, String endMinute,
                           String takeHour, String takeMinute,
                           String address, String addressDetail, String longitude, String latitude,
                           String wayGuide, String wayDetail,
                           MultipartFile mapFile, MultipartFile timetableFile,
                           String master, String masterPhone,
                           String coursessJson,
                           Long delMapFileId, Long delTimetableFileId,
                           long modId) throws FailSaveFileException, InvalidJsonFormatException;
    PcResp deleteEducation(Long educationId);

    EduAttendListResp eduAttendList(Long educationId, Long courseId, String type, String keyword, Integer page, Integer cntPerPage);
    PcResp updateEduAttendApproval(Long educationId, Long[] attendIds, String approval);
}
