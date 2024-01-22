package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;
import kr.ant.kpa.pharmcrew.resp.academy.Doc;
import kr.ant.kpa.pharmcrew.resp.academy.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcademyConverter {
    @Autowired
    private CommonConverter commonConverter;

    public Doc convert(DocVo s) {
        Doc o = new Doc();
        o.setDocId(s.getDoc_id());
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        o.setDocFile(commonConverter.convert(s.getDocFile()));
        o.setOpenYn(s.getOpen_yn());
        o.setViewCnt(s.getView_cnt());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }

    public Video convert(VideoVo s) {
        Video o = new Video();
        o.setVideoId(s.getVideo_id());
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setTitle(s.getTitle());
        o.setUrl(s.getUrl());
//        o.setThumbFile(commonConverter.convert(s.getThumbFile()));
        o.setOpenYn(s.getOpen_yn());
        o.setViewCnt(s.getView_cnt());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }
}
