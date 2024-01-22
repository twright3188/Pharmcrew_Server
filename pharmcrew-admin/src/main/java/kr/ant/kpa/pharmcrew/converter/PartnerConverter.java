package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;
import kr.ant.kpa.pharmcrew.resp.partner.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerConverter {
    @Autowired
    private CommonConverter commonConverter;

    public Partner convert(PartnersVo s) {
        Partner o = new Partner();
        o.setPartnerId(s.getPartner_id());
        o.setName(s.getName());
        o.setDetail(s.getDetail());
        o.setIconFile(commonConverter.convert(s.getIconFile()));
        o.setMoveUrl(s.getMove_url());
        o.setIsOpen(s.getIs_open());
        o.setMemberCount(s.getMemberCount());
        o.setRegName(s.getRegAdmin().getName());
        o.setRegDt(s.getReg_dt().getTime());
        return o;
    }
}
