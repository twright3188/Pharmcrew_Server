package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.FileUtils;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import com.google.gson.Gson;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.EducationConverter;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.EducationDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.vo.common.QrVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.education.*;
import kr.ant.kpa.pharmcrew.service.EducationService;
import kr.ant.kpa.pharmcrew.type.education.EDUCATION_AUTH;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidJsonFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.util.*;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationDao educationDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private EducationConverter educationConverter;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;
    @Autowired
    private Gson gson;

    @Transactional
    @Override
    public PcResp registEducation(String authType, Long organizeId, String isOpen, String title, String startDate, String endDate, String startHour, String startMinute, String endHour, String endMinute, String takeHour, String takeMinute, String qrType, String address, String addressDetail, String longitude, String latitude, String wayGuide, String wayDetail, MultipartFile mapFile, MultipartFile timetableFile, String master, String masterPhone, String coursessJson, long regId) throws FailSaveFileException, InvalidJsonFormatException {
        Long mapFileId = null;
        File mapFile_ = null;
        Long timetableFileId = null;
        File timetableFile_ = null;
        try {
            if (mapFile != null) {
                mapFile_ = pcUtils.save(mapFile);
                mapFileId = storage.save(mapFile_, mapFile.getOriginalFilename(), mapFile.getSize());
            }
            if (timetableFile != null) {
                timetableFile_ = pcUtils.save(timetableFile);
                timetableFileId = storage.save(timetableFile_, timetableFile.getOriginalFilename(), timetableFile.getSize());
            }
        } catch (FailSaveFileException e) {
            if (mapFile != null)    FileUtils.deletes(mapFile_);
            throw e;
        }

        EducationCoursess coursess = gson.fromJson(coursessJson, EducationCoursess.class);

        EDUCATION_AUTH authType_ = EDUCATION_AUTH.valueOf(authType);
        switch (authType_) {
            case A:
                if (StringUtils.isEmpty(takeHour))  throw new BadRequestException("Required String parameter 'takeHour' is not present - 'authType' is 'A'");
                if (StringUtils.isEmpty(takeMinute))  throw new BadRequestException("Required String parameter 'takeMinute' is not present - 'authType' is 'A'");
                if (StringUtils.isEmpty(qrType))  throw new BadRequestException("Required String parameter 'qrType' is not present - 'authType' is 'A'");
                break;
        }

        EducationVo educationVo = new EducationVo();
        educationVo.setOrganize_id(organizeId);
        educationVo.setIs_open(isOpen);
        educationVo.setTitle(title);
        try {
            // Controller에서 검증
            educationVo.setStart_date(pcUtils.dateFormat.parse(startDate));
            educationVo.setEnd_date(pcUtils.dateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        educationVo.setStart_time(String.format("%s%s", startHour, startMinute));
        educationVo.setEnd_time(String.format("%s%s", endHour, endMinute));
        if (authType_ == EDUCATION_AUTH.A) {
            educationVo.setTake_min(Integer.parseInt(takeHour) * 60 + Integer.parseInt(takeMinute));
        }
        educationVo.setAddress(address);
        educationVo.setAddress_detail(addressDetail);
        educationVo.setWay_guide(wayGuide);
        educationVo.setWay_detail(wayDetail);
        educationVo.setMap_file_id(mapFileId);
        educationVo.setMaster(master);
        educationVo.setMaster_phone(masterPhone);
        educationVo.setAuth_div(authType);
        educationVo.setTimetable_file_id(timetableFileId);
        educationVo.setLatitude(latitude);
        educationVo.setLongitude(longitude);
        educationVo.setReg_id(regId);
        educationDao.insertEducation(educationVo);
        if (authType_ == EDUCATION_AUTH.A) {
            QrVo qr1Vo = new QrVo();
            QrVo qr2Vo = null;
            switch (qrType) {
                case "O":
                    qr1Vo.setCode(UUID.randomUUID().toString());
                    qr1Vo.setType("O");
                    qr1Vo.setEducation_id(educationVo.getEducation_id());
                    break;
                case "M":
                    qr1Vo.setCode(UUID.randomUUID().toString());
                    qr1Vo.setType("S");
                    qr1Vo.setEducation_id(educationVo.getEducation_id());
                    qr2Vo = new QrVo();
                    qr2Vo.setCode(UUID.randomUUID().toString());
                    qr2Vo.setType("E");
                    qr2Vo.setEducation_id(educationVo.getEducation_id());
                    break;
            }
            commonDao.insertQr(qr1Vo);
            if (qr2Vo != null) {
                commonDao.insertQr(qr2Vo);
            }
        }

        for (int dayIdx = 0; dayIdx < coursess.getCoursess().size(); dayIdx++) {
            List<EducationCourse> courses = coursess.getCoursess().get(dayIdx);
            for (int courseIdx = 0; courseIdx < courses.size(); courseIdx++) {
                EducationCourse course = courses.get(courseIdx);

                EducationCourseVo courseVo = new EducationCourseVo();
                QrVo qr1Vo = null;
                QrVo qr2Vo = null;
                switch (authType_) {
                    case P:
                        if (EDUCATION_AUTH.P.name().equals(authType)) {
                            if (StringUtils.isEmpty(course.getTakeHour())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "takeHour"));
                                throw e;
                            }
                            if (StringUtils.isEmpty(course.getTakeMinute())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "takeMinute"));
                                throw e;
                            }
                            if (StringUtils.isEmpty(course.getQrType())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "qrType"));
                                throw e;
                            }
                        }

                        courseVo.setTake_min(Integer.parseInt(course.getTakeHour()) * 60 + Integer.parseInt(course.getTakeMinute()));
                        switch (course.getQrType()) {
                            case "O":
                                qr1Vo = new QrVo();
                                qr1Vo.setCode(UUID.randomUUID().toString());
                                qr1Vo.setType("O");
                                qr1Vo.setEducation_id(educationVo.getEducation_id());
                                break;
                            case "M":   // 각각(시작, 종료)
                                qr1Vo = new QrVo();
                                qr1Vo.setCode(UUID.randomUUID().toString());
                                qr1Vo.setType("S");
                                qr1Vo.setEducation_id(educationVo.getEducation_id());
                                qr2Vo = new QrVo();
                                qr2Vo.setCode(UUID.randomUUID().toString());
                                qr2Vo.setType("E");
                                qr2Vo.setEducation_id(educationVo.getEducation_id());
                                break;
                        }
                        break;
                }
                courseVo.setEducation_id(educationVo.getEducation_id());
                courseVo.setDays(dayIdx+1);
                courseVo.setIdx(courseIdx+1);
                courseVo.setTitle(course.getTitle());
                courseVo.setTeacher(course.getTeacher());
                courseVo.setType(course.getType());
                courseVo.setRoom(course.getRoom());
                courseVo.setStart_time(String.format("%s%s", course.getStartHour(), course.getStartMinute()));
                courseVo.setEnd_time(String.format("%s%s", course.getEndHour(), course.getEndMinute()));
                educationDao.insertEducationCourse(courseVo);

                if (qr1Vo != null) {
                    qr1Vo.setCourse_id(courseVo.getCourse_id());
                    commonDao.insertQr(qr1Vo);
                }
                if (qr2Vo != null) {
                    qr2Vo.setCourse_id(courseVo.getCourse_id());
                    commonDao.insertQr(qr1Vo);
                }
            }
        }

        return new PcResp();
    }

    @Override
    public EducationListResp educationList(String startDate, String endDate, Long organizeId, String keyword, String isOpen, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "startDate", startDate);
        MapUtils.put(param, "endDate", endDate);
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        if (!StringUtils.isEmpty(isOpen)) {
            switch (isOpen) {
                case "Y":
                case "N":
                    MapUtils.put(param, "is_open", isOpen);
                    break;
            }
        }
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.put(param, "withApprovalCnt", true);   // 관리자에 의해 인정된 참석자 수
        List<EducationVo> educationVos = educationDao.listEducation(param);

        EducationListResp resp = new EducationListResp();
        if (ListUtils.isEmpty(educationVos)) {
            resp.setSearchCnt(0);
        } else {
            for (EducationVo educationVo: educationVos) {
                if (resp.getEducations() == null) {
                    resp.setEducations(new ArrayList<>());
                }
                resp.getEducations().add(educationConverter.convert(educationVo));
            }
            resp.setSearchCnt(educationDao.countEducation(param));
        }
        return resp;
    }

    @Override
    public EducationResp education(Long educationId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "education_id", educationId);
        MapUtils.put(param, "withApprovalCnt", true);   // 관리자에 의해 인정된 참석자 수
        EducationVo educationVo = educationDao.selectEducation(param);

        param.clear();
        MapUtils.put(param, "education_id", educationId);
        MapUtils.put(param, "withAttendCnt", true);
        List<EducationCourseVo> courseVos = educationDao.listEducationCourse(param);

        EducationResp resp = new EducationResp();
        resp.setEducation(educationConverter.convert(educationVo));
        for (EducationCourseVo courseVo: courseVos) {
            if (resp.getCoursess() == null) {
                EducationCoursess coursess = new EducationCoursess();
                coursess.setCoursess(new ArrayList<>());
                resp.setCoursess(coursess);
            }

            if (resp.getCoursess().getCoursess().size() < courseVo.getDays()) {
                for (int i = 0; i < courseVo.getDays() - resp.getCoursess().getCoursess().size(); i++) {
                    resp.getCoursess().getCoursess().add(new ArrayList<>());
                }
            }

            resp.getCoursess().getCoursess().get(courseVo.getDays()-1).add(educationConverter.convert(courseVo));
        }
        return resp;
    }

    @Transactional
    @Override
    public PcResp updateEducation(Long educationId, Long organizeId, String isOpen, String title, String startDate, String endDate, String startHour, String startMinute, String endHour, String endMinute, String takeHour, String takeMinute, String address, String addressDetail, String longitude, String latitude, String wayGuide, String wayDetail, MultipartFile mapFile, MultipartFile timetableFile, String master, String masterPhone, String coursessJson, Long delMapFileId, Long delTimetableFileId, long modId) throws FailSaveFileException, InvalidJsonFormatException {
        Long mapFileId = null;
        File mapFile_ = null;
        Long timetableFileId = null;
        File timetableFile_ = null;
        try {
            if (mapFile != null) {
                mapFile_ = pcUtils.save(mapFile);
            }
            if (timetableFile != null) {
                timetableFile_ = pcUtils.save(timetableFile);
            }
        } catch (FailSaveFileException e) {
            if (mapFile != null)    FileUtils.deletes(mapFile_);
            throw e;
        }

        EducationCoursess coursess = gson.fromJson(coursessJson, EducationCoursess.class);

        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "education_id", educationId);
        EducationVo orgEducationVo = educationDao.selectEducation(param);

        EDUCATION_AUTH authType_ = EDUCATION_AUTH.valueOf(orgEducationVo.getAuth_div());
        switch (authType_) {
            case A:
                if (StringUtils.isEmpty(takeHour))  throw new BadRequestException("Required String parameter 'takeHour' is not present - 'authType' is 'A'");
                if (StringUtils.isEmpty(takeMinute))  throw new BadRequestException("Required String parameter 'takeMinute' is not present - 'authType' is 'A'");
                break;
        }

        if (mapFile_ != null && orgEducationVo.getMap_file_id() == null) {
            mapFileId = storage.save(mapFile_, mapFile.getOriginalFilename(), mapFile.getSize());
        }
        if (mapFile_ != null && orgEducationVo.getTimetable_file_id() == null) {
            timetableFileId = storage.save(timetableFile_, timetableFile.getOriginalFilename(), timetableFile.getSize());
        }

        EducationVo educationVo = new EducationVo();
        educationVo.setEducation_id(educationId);
        educationVo.setOrganize_id(organizeId);
        educationVo.setIs_open(isOpen);
        educationVo.setTitle(title);
        try {
            // Controller에서 검증
            educationVo.setStart_date(pcUtils.dateFormat.parse(startDate));
            educationVo.setEnd_date(pcUtils.dateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        educationVo.setStart_time(String.format("%s%s", startHour, startMinute));
        educationVo.setEnd_time(String.format("%s%s", endHour, endMinute));
        if (authType_ == EDUCATION_AUTH.A) {
            educationVo.setTake_min(Integer.parseInt(takeHour) * 60 + Integer.parseInt(takeMinute));
        }
        educationVo.setAddress(address);
        educationVo.setAddress_detail(addressDetail);
        educationVo.setWay_guide(wayGuide);
        educationVo.setWay_detail(wayDetail);
        if (delMapFileId != null && mapFile_ == null) {
            educationVo.setMapFileIdNull(true);
        } else {
            educationVo.setMap_file_id(mapFileId);
        }
        educationVo.setMaster(master);
        educationVo.setMaster_phone(masterPhone);
        if (delTimetableFileId != null && timetableFile_ == null) {
        	educationVo.setTimetableFileIdNull(true);
        } else {
        	educationVo.setTimetable_file_id(timetableFileId);
        }
        educationVo.setLatitude(latitude);
        educationVo.setLongitude(longitude);
        educationVo.setMod_id(modId);
        educationDao.updateEducation(educationVo);

        List<EducationCourseVo> courseVos = new ArrayList<>();
        for (int dayIdx = 0; dayIdx < coursess.getCoursess().size(); dayIdx++) {
            List<EducationCourse> courses = coursess.getCoursess().get(dayIdx);
            for (int courseIdx = 0; courseIdx < courses.size(); courseIdx++) {
                EducationCourse course = courses.get(courseIdx);

                EducationCourseVo courseVo = new EducationCourseVo();
                switch (authType_) {
                    case P:
                        if (EDUCATION_AUTH.P.name().equals(orgEducationVo.getAuth_div())) {
                            if (StringUtils.isEmpty(course.getTakeHour())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "takeHour"));
                                throw e;
                            }
                            if (StringUtils.isEmpty(course.getTakeMinute())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "takeMinute"));
                                throw e;
                            }
                            if (StringUtils.isEmpty(course.getQrType())) {
                                InvalidJsonFormatException e = new InvalidJsonFormatException();
                                e.setMessage(String.format("%d일차 %d번째 강의 데이터 오류 - %s: 없음", dayIdx + 1, courseIdx + 1, "qrType"));
                                throw e;
                            }
                        }

                        courseVo.setTake_min(Integer.parseInt(course.getTakeHour()) * 60 + Integer.parseInt(course.getTakeMinute()));
                        break;
                }
                courseVo.setCourse_id(course.getCourseId());
                courseVo.setEducation_id(educationId);
                courseVo.setDays(dayIdx+1);
                courseVo.setIdx(courseIdx+1);
                courseVo.setTitle(course.getTitle());
                courseVo.setTeacher(course.getTeacher());
                courseVo.setType(course.getType());
                courseVo.setRoom(course.getRoom());
                courseVo.setStart_time(String.format("%s%s", course.getStartHour(), course.getStartMinute()));
                courseVo.setEnd_time(String.format("%s%s", course.getEndHour(), course.getEndMinute()));
                courseVos.add(courseVo);
            }
        }

        educationDao.deleteEducationCourseMap(param);

        MapUtils.put(param, "courses", courseVos);
        educationDao.mergeEducationCourseList(param);

        if (mapFile_ != null && orgEducationVo.getMap_file_id() != null) {
            storage.replace(educationVo.getMapFile(), mapFile_, mapFile.getOriginalFilename(), mapFile.getSize());
        }
        if (timetableFile_ != null && orgEducationVo.getTimetable_file_id() != null) {
            storage.replace(educationVo.getTimetableFile(), timetableFile_, timetableFile.getOriginalFilename(), timetableFile.getSize());
        }

        cacheManager.delete(CACHE.EDUCATION, educationId);

        return new PcResp();
    }

    @Override
    public PcResp deleteEducation(Long educationId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "education_id", educationId);
        EducationVo orgEducationVo = educationDao.selectEducation(param);
        if (orgEducationVo != null) throw new NotFoundException();

        EducationVo educationVo = new EducationVo();
        educationVo.setEducation_id(educationId);
        educationDao.deleteEducation(educationVo);

        if (orgEducationVo.getMap_file_id() != null) {
            storage.delete(orgEducationVo.getMapFile());
        }
        if (orgEducationVo.getTimetable_file_id() != null) {
            storage.delete(orgEducationVo.getTimetableFile());
        }

        return new PcResp();
    }

    @Override
    public EduAttendListResp eduAttendList(Long educationId, Long courseId, String type, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "education_id", educationId);
        MapUtils.put(param, "course_id", courseId);
        switch (type) {
            case "E":
                MapUtils.put(param, "evalStarNotNull", true);
                break;
            case "A":
                MapUtils.put(param, "approval", "Y");
                break;
        }
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        List<MemberEducationVo> attendVos = memberDao.listMemberEducation(param);

        EduAttendListResp resp = new EduAttendListResp();

        if (ListUtils.isEmpty(attendVos)) {
            resp.setSearchCnt(0);
        } else {
            for (MemberEducationVo attendVo: attendVos) {
                if (resp.getAttends() == null) {
                    resp.setAttends(new ArrayList<>());
                }
                resp.getAttends().add(educationConverter.convert(attendVo));
            }
            resp.setSearchCnt(memberDao.countMember(param));
        }

        if (page == 1) {
            Map eval = memberDao.selectMemberEducationEval(param);
            resp.setEvalCnt(MapUtils.getInt(eval, "evalCnt", 0));
            resp.setEvalStarAvg(MapUtils.getInt(eval, "evalStarAvg", 0));
        }

        return resp;
    }

    @Override
    public PcResp updateEduAttendApproval(Long educationId, Long[] attendIds, String approval) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "attendIds", attendIds);
        MapUtils.put(param, "approval", approval);
        memberDao.updateMemberEducation(param);

        return new PcResp();
    }
}
