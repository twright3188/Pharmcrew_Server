package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.dao.AdminDao;
import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.me.FindLoginIdResp;
import kr.ant.kpa.pharmcrew.resp.me.MeResp;
import kr.ant.kpa.pharmcrew.service.MeService;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeServiceImpl implements MeService {
    @Autowired
    private OrganizeFacade organizeFacade;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PcUtils pcUtils;

    @Override
    public FindLoginIdResp findLoginId(String name, String email) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "name", name);
        MapUtils.put(param, "email", email);
        AdminVo adminVo = adminDao.selectAdmin(param);
        if (adminVo == null)    throw new NotFoundException();

        FindLoginIdResp resp = new FindLoginIdResp();
        resp.setLoginId(adminVo.getAccount());
        return resp;
    }

    @Override
    public PcResp resetPassword(String name, String loginId, String email) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "name", name);
        MapUtils.put(param, "account", loginId);
        MapUtils.put(param, "email", email);
        AdminVo adminVo = adminDao.selectAdmin(param);
//        if (adminVo == null)    throw new NotFoundException();
        if (adminVo != null) {
            String password = StringUtils.makeRandom(10, true);

            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom("팜크루 <no-reply@ant-soft.com>");
                    helper.setTo(String.format("%s <%s>", name, email));
                    helper.setSubject("[팜크루] 비밀번호 재설정 안내");
                    helper.setText(String.format("임시 비밀번호는 %s입니다. 로그인 후 비밀번호를 꼭 변경하세요.", password), true);
                }
            };
            mailSender.send(preparator);

            adminVo.setPassword(pcUtils.encryptPassword(password));
            adminDao.updateAdmin(adminVo);
        }

        return new PcResp();
    }

    @Override
    public MeResp me(long myId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "admin_id", myId);
        AdminVo adminVo = adminDao.selectAdmin(param);

        MeResp resp = new MeResp();
        resp.setLoginId(adminVo.getAccount());
        resp.setName(adminVo.getName());
        if (adminVo.getOrganize_id() != null) {
            OrganizeVo organize = organizeFacade.get(adminVo.getOrganize_id());
            List<Long> ids = new ArrayList<>();
            ids.add(organize.getOrganize_d1_id());
            if (organize.getOrganize_d2_id() != null) {
                ids.add(organize.getOrganize_d2_id());
                if (organize.getOrganize_d3_id() != null) {
                    ids.add(organize.getOrganize_d3_id());
                }
            }
            resp.setOrganizeIds(ids);
        }
        resp.setEmail(adminVo.getEmail());
        resp.setTel(adminVo.getTel());
        resp.setPhone(adminVo.getPhone());
        return resp;
    }

    @Override
    public PcResp updateMe(long myId, String password, String name, String email, String tel, String phone) {
        AdminVo adminVo = new AdminVo();
        adminVo.setAdmin_id(myId);
        if (!StringUtils.isEmpty(password)) {
            adminVo.setPassword(pcUtils.encryptPassword(password));
        }
        adminVo.setName(name);
        adminVo.setEmail(email);
        if (StringUtils.isEmpty(tel)) {
            adminVo.setTelNull(true);
        } else {
            adminVo.setTel(tel);
        }
        if (StringUtils.isEmpty(phone)) {
            adminVo.setPhoneNull(true);
        } else {
            adminVo.setPhone(phone);
        }
        adminVo.setMod_id(myId);
        adminDao.updateAdmin(adminVo);

        return new PcResp();
    }
}
