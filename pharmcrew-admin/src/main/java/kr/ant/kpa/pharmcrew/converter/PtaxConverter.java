package kr.ant.kpa.pharmcrew.converter;

import com.bumdori.util.ListUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.resp.ptax.Ptaxnotice;
import kr.ant.kpa.pharmcrew.resp.ptax.Ptaxqna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PtaxConverter {
    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private PcUtils pcUtils;

    public Ptaxqna convert(PtaxQnaVo s) {
        Ptaxqna o = new Ptaxqna();
        o.setQnaId(s.getQna_id());
        o.setMemberName(s.getMember().getName());
        o.setPharmName(s.getPharm_name());
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        o.setIsAnswered(s.getIs_answerd());
        o.setAnsTitle(s.getAns_title());
        o.setAnsBody(s.getAns_body());
        o.setQuestionDt(s.getQuestion_dt().getTime());
        if (s.getAdmin() != null) {
            o.setAdminName(s.getAdmin().getName());
        }
        if (s.getAnswer_dt() != null) {
            o.setAnswerDt(s.getAnswer_dt().getTime());
        }
        if (!ListUtils.isEmpty(s.getqAttachFiles())) {
            for (FileVo fileVo: s.getqAttachFiles()) {
                if (o.getQAttachFiles() == null) {
                    o.setQAttachFiles(new ArrayList<>());
                }
                o.getQAttachFiles().add(commonConverter.convert(fileVo));
            }
        }
        if (!ListUtils.isEmpty(s.getaAttachFiles())) {
            for (FileVo fileVo: s.getaAttachFiles()) {
                if (o.getAAttachFiles() == null) {
                    o.setAAttachFiles(new ArrayList<>());
                }
                o.getAAttachFiles().add(commonConverter.convert(fileVo));
            }
        }
        return o;
    }

    public Ptaxnotice convert(PtaxNoticeVo s) {
        Ptaxnotice o = new Ptaxnotice();
        o.setNoticeId(s.getNotice_id());
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        o.setOpenYn(s.getIs_open());
        if (!ListUtils.isEmpty(s.getAttachFiles())) {
            for (FileVo fileVo: s.getAttachFiles()) {
                if (o.getAttachFiles() == null) {
                    o.setAttachFiles(new ArrayList<>());
                }
                o.getAttachFiles().add(commonConverter.convert(fileVo));
            }
        }
//        o.setSendType();
        o.setTarget(s.getTarget_div() == null ? "ALL" : s.getTarget_div());
        o.setTargetPharmName(s.getTarget_pharm_name());
//        o.setTargetMemberId(s.getTarget_member_id());   // TODO 왜 없지?
        if (s.getMember() != null) {
            o.setTargetMemberId(s.getMember().getMember_id());
            o.setTargetMemberName(s.getMember().getName());
        }

        if (s.getPush() != null) {
            o.setOs(s.getPush().getOs_div() == null ? "ALL" : s.getPush().getOs_div());
            if (s.getPush().getReserv_dt() != null) {
                String[] reservDt = pcUtils.dateHMFormat.format(s.getPush().getReserv_dt()).split("-");
                o.setReserveDate(reservDt[0]);
                o.setReserveHour(reservDt[1]);
                o.setReserveMinute(reservDt[2]);
                o.setSendType("R");
            } else {
                o.setSendType("I");
            }
            o.setState(s.getPush().getState_div());
            if (s.getPush().getSended_dt() != null) {
                o.setSendDt(s.getPush().getSended_dt().getTime());
            }
            o.setSendCnt(s.getPush().getSendCnt());
            o.setRecvCnt(s.getPush().getRecvCnt());
            o.setRegDt(s.getReg_dt().getTime());
        } else {
            o.setSendType("NONE");
            o.setOs("ALL");
        }

        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }
}
