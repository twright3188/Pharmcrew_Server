package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.push.PushVo;
import kr.ant.kpa.pharmcrew.resp.push.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PushConverter {
//    @Autowired
//    private OrganizeFacade organizeFacade;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private PcUtils pcUtils;

    public Push convert(PushVo s) {
        Push o = new Push();
        o.setPushId(s.getPush_id());
        o.setOsType(s.getOs_div());
        o.setTitle(s.getTitle());
        o.setBody(s.getBody());
        if (s.getImgFile() != null) {
            o.setImgFile(commonConverter.convert(s.getImgFile()));
        }
        if (s.getReserv_dt() != null) {
            String[] reservDt = pcUtils.dateHMFormat.format(s.getReserv_dt()).split("-");
            o.setReserveDate(reservDt[0]);
            o.setReserveHour(reservDt[1]);
            o.setReserveMinute(reservDt[2]);
        }
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
        o.setCategoryType(s.getCategory_div());
        o.setMoveType(s.getMove_div());
        o.setMoveId(s.getMove_id());
        o.setMoveUrl(s.getMove_url());
        o.setState(s.getState_div());
        if (s.getSended_dt() != null) {
            o.setSendDt(s.getSended_dt().getTime());
        }
        o.setSendCnt(s.getSendCnt());
        o.setRecvCnt(s.getRecvCnt());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }
}
