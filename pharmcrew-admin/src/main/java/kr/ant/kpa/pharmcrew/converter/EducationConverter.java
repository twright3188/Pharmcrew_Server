package kr.ant.kpa.pharmcrew.converter;

import com.bumdori.util.ListUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.resp.education.EduAttend;
import kr.ant.kpa.pharmcrew.resp.education.Education;
import kr.ant.kpa.pharmcrew.resp.education.EducationCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationConverter {
    @Autowired
    private PcUtils pcUtils;

    @Autowired
    private CommonConverter commonConverter;

    public Education convert(EducationVo s) {
        Education o = new Education();
        o.setEducationId(s.getEducation_id());
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setIsOpen(s.getIs_open());
        o.setTitle(s.getTitle());
        o.setStartDate(pcUtils.dateFormat.format(s.getStart_date()));
        o.setEndDate(pcUtils.dateFormat.format(s.getEnd_date()));
        o.setStartHour(s.getStart_time().substring(0, 2));
        o.setStartMinute(s.getStart_time().substring(2));
        o.setEndHour(s.getEnd_time().substring(0, 2));
        o.setEndMinute(s.getEnd_time().substring(2));
        String takeMin = String.format("%02d%02d", s.getTake_min() / 60, s.getTake_min() % 60);
        o.setTakeHour(takeMin.substring(0, 2));
        o.setTakeMinute(takeMin.substring(2));
        o.setAuthType(s.getAuth_div());
        o.setAddress(s.getAddress());
        o.setAddressDetail(s.getAddress_detail());
        o.setLongitude(s.getLongitude());
        o.setLatitude(s.getLatitude());
        o.setWayGuide(s.getWay_guide());
        o.setWayDetail(s.getWay_detail());
        o.setMapFile(commonConverter.convert(s.getMapFile()));
        o.setTimetableFile(commonConverter.convert(s.getTimetableFile()));
        o.setMaster(s.getMaster());
        o.setMasterPhone(s.getMaster_phone());
        if (!ListUtils.isEmpty(s.getQrs())) {
            o.setQr1(s.getQrs().get(0).getCode());
            if (s.getQrs().size() > 1) {
                o.setQr2(s.getQrs().get(1).getCode());
            }
        }
        o.setViewCnt(s.getView_cnt());
        o.setApprovalCnt(s.getApprovalCnt());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }

    public EducationCourse convert(EducationCourseVo s) {
        EducationCourse o = new EducationCourse();
        o.setCourseId(s.getCourse_id());
        o.setDay(s.getDays());
        o.setIdx(s.getIdx());
        o.setTitle(s.getTitle());
        o.setRoom(s.getRoom());
        o.setTeacher(s.getTeacher());
        o.setType(s.getType());
        o.setStartHour(s.getStart_time().substring(0, 2));
        o.setStartMinute(s.getStart_time().substring(2));
        o.setEndHour(s.getEnd_time().substring(0, 2));
        o.setEndMinute(s.getEnd_time().substring(2));
        if (s.getTake_min() != null) {
            o.setTakeHour(String.format("%2d", s.getTake_min() / 60));
            o.setTakeMinute(String.format("%2d", s.getTake_min() % 60));
        }
        if (!ListUtils.isEmpty(s.getQrs())) {
            o.setQr1(s.getQrs().get(0).getCode());
            if (s.getQrs().size() > 1) {
                o.setQr2(s.getQrs().get(1).getCode());
            }
        }
        o.setAttendCnt(s.getAttendCnt());
        return o;
    }

    public EduAttend convert(MemberEducationVo s) {
        EduAttend o = new EduAttend();
        o.setAttendId(s.getAttend_id());
        o.setMemberName(s.getMemberVo().getName());
        o.setStartDt(s.getStart_dt().getTime());
        if (s.getEnd_dt() != null) {
            o.setEndDt(s.getEnd_dt().getTime());
        }
        o.setApproval(s.getApproval());
        o.setEvalStar(s.getEval_star());
        o.setEvalBody(s.getEval_body());
        return o;
    }
}
