package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.resp.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminConverter {
//    @Autowired
//    private OrganizeFacade organizeFacade;

    @Autowired
    private CommonConverter commonConverter;

    public Admin convert(AdminVo s) {
        Admin o = new Admin();
        o.setAdminId(s.getAdmin_id());
        o.setLoginId(s.getAccount());
        o.setName(s.getName());
        o.setEmail(s.getEmail());
        o.setTel(s.getTel());
        o.setPhone(s.getPhone());
        o.setAuthority(s.getAuthority());
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
        o.setRegDt(s.getReg_dt().getTime());
        o.setState(s.getState_div());
        return o;
    }
}
