package kr.ant.kpa.pharmcrew.converter;

import com.bumdori.util.ListUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;
import kr.ant.kpa.pharmcrew.resp.news.Banner;
import kr.ant.kpa.pharmcrew.resp.news.Notice;
import kr.ant.kpa.pharmcrew.resp.news.Popup;
import kr.ant.kpa.pharmcrew.resp.news.Qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConverter {
//    @Autowired
//    private OrganizeFacade organizeFacade;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private PcUtils pcUtils;

    public Notice convert(NoticeVo s) {
        Notice o = new Notice();
        // TODO notice_id는 왜 notice-result-map에서 명시해야 할까?
        o.setNoticeId(s.getNotice_id());
//        if (!StringUtils.isEmpty(s.getOrganizePath())) {
//            String[] tmps = s.getOrganizePath().split(",");
//            List<Long> ids = new ArrayList<>();
//            for (String tmp: tmps) {
//                ids.add(Long.parseLong(tmp));
//            }
//            o.setOrganizeIds(ids);
//        }
//        if (s.getOrganize_id() != null) {
//            OrganizeVo organize = organizeFacade.get(s.getOrganize_id());
//            List<Long> ids = new ArrayList<>();
//            ids.add(organize.getOrganize_d1_id());
//            if (organize.getOrganize_d2_id() != null) {
//                ids.add(organize.getOrganize_d2_id());
//                if (organize.getOrganize_d3_id() != null) {
//                    ids.add(organize.getOrganize_d3_id());
//                }
//            }
//            o.setOrganizeIds(ids);
//            o.setOrganizeName(organize.getName());
//        } else {
//            o.setOrganizeName("전체");
//        }
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        o.setIsOpen(s.getIs_open());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        o.setViewCnt(s.getView_cnt());
        if (!ListUtils.isEmpty(s.getAttachFiles())) {
            for (FileVo fileVo: s.getAttachFiles()) {
                if (o.getAttachFiles() == null) {
                    o.setAttachFiles(new ArrayList<>());
                }
                o.getAttachFiles().add(commonConverter.convert(fileVo));
            }
        }
        return o;
    }

    public Popup convert(PopupVo s) {
        Popup o = new Popup();
        o.setPopupId(s.getPopup_id());
        o.setTitle(s.getTitle());
        o.setImgFile(commonConverter.convert(s.getImgFile()));
        o.setIdx(s.getOpen_idx());
        o.setMoveType(s.getMove_div());
        o.setMoveId(s.getMove_id());
        o.setMoveUrl(s.getMove_url());
        o.setIsOpen(s.getIs_open());
        String[] openStartDt = pcUtils.dateHFormat.format(s.getOpen_start_dt()).split("-");
        o.setOpenStartDate(openStartDt[0]);
        o.setOpenStartHour(openStartDt[1]);
        String[] openEndDt = pcUtils.dateHFormat.format(s.getOpen_end_dt()).split("-");
        o.setOpenEndDate(openEndDt[0]);
        o.setOpenEndHour(openEndDt[1]);
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }

    public Banner convert(BannerVo s) {
        Banner o = new Banner();
        o.setBannerId(s.getBanner_id());
        o.setTitle(s.getTitle());
        o.setImgFile(commonConverter.convert(s.getImgFile()));
        o.setIdx(s.getOpen_idx());
        o.setMoveType(s.getMove_div());
        o.setMoveId(s.getMove_id());
        o.setMoveUrl(s.getMove_url());
        o.setIsOpen(s.getIs_open());
        String[] openStartDt = pcUtils.dateHFormat.format(s.getOpen_start_dt()).split("-");
        o.setOpenStartDate(openStartDt[0]);
        o.setOpenStartHour(openStartDt[1]);
        String[] openEndDt = pcUtils.dateHFormat.format(s.getOpen_end_dt()).split("-");
        o.setOpenEndDate(openEndDt[0]);
        o.setOpenEndHour(openEndDt[1]);
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }

    public Qna convert(QnaVo s) {
        Qna o = new Qna();
        o.setQnaId(s.getQna_id());
//        if (s.getOrganize_id() != null) {
//            OrganizeVo organize = organizeFacade.get(s.getOrganize_id());
//            List<Long> ids = new ArrayList<>();
//            ids.add(organize.getOrganize_d1_id());
//            if (organize.getOrganize_d2_id() != null) {
//                ids.add(organize.getOrganize_d2_id());
//                if (organize.getOrganize_d3_id() != null) {
//                    ids.add(organize.getOrganize_d3_id());
//                }
//            }
//            o.setOrganizeIds(ids);
//            o.setOrganizeName(organize.getName());
//        } else {
//            o.setOrganizeName("전체");
//        }
        o.setOrganize(commonConverter.convertOrganize2(s.getOrganize_id()));
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        if (s.getAttachFile() != null) {
            o.setAttachFile(commonConverter.convert(s.getAttachFile()));
        }
        o.setMemberName(s.getMember().getName());
        o.setQuestionDt(s.getQuestion_dt().getTime());
        o.setIsAnswerd(s.getIs_answerd());
        o.setAnswerTitle(s.getAns_title());
        o.setAnswerBody(s.getAns_body());
        if (s.getAdmin() != null) {
            o.setAdminName(s.getAdmin().getName());
        }
        if (s.getAnswer_dt() != null) {
            o.setAnswerDt(s.getAnswer_dt().getTime());
        }
        return o;
    }
}
