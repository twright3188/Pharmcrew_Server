package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.resp.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MemberConverter {
    @Autowired
    private OrganizeFacade organizeFacade;

    @Autowired
    private CommonConverter commonConverter;

    public Member convert(MemberVo s) {
        Member o = new Member();
        o.setMemberId(s.getMember_id());
        o.setOrganize1(commonConverter.convertOrganize2(s.getOrganize_1_id()));
        o.setOrganize2(commonConverter.convertOrganize2(s.getOrganize_2_id()));
        o.setLicenseNo(s.getLicense_no());
        o.setName(s.getName());
        o.setBirthday(s.getBirthday());
//        o.setPhone(s.getAuthphone() == null ? s.getHandphone() : s.getAuthphone());
        o.setPhone(s.getHandphone());
        o.setEmail(s.getEmail());
        o.setSex(s.getSex_div());
        o.setPushYn(s.getPush_yn());
        o.setAdSmsYn(s.getAd_sms_yn());
        o.setAdPushYn(s.getAd_push_yn());
        if (s.getLast_login_dt() != null) { // 이게 없을 순 없지만...
            o.setLastLoginDt(s.getLast_login_dt().getTime());
        }
        if (s.getReg_dt() != null) {
            o.setRegDt(s.getReg_dt().getTime());
        }
        return o;
    }
}
