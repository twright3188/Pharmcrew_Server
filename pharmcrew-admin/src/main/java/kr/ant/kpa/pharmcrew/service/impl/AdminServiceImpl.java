package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.converter.AdminConverter;
import kr.ant.kpa.pharmcrew.db.dao.AdminDao;
import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminListResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminResp;
import kr.ant.kpa.pharmcrew.service.AdminService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotUseableLoginidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminConverter adminConverter;

    @Autowired
    private PcUtils pcUtils;

    @Override
    public PcResp registAdmin(String loginId, String password, String name, String email, String authority, String tel, String phone, Long organizeId, String state, long regId) {
        AdminVo adminVo = new AdminVo();
        adminVo.setOrganize_id(organizeId);
        adminVo.setAuthority(authority);
        adminVo.setAccount(loginId);
        adminVo.setPassword(pcUtils.encryptPassword(password));
        adminVo.setName(name);
        adminVo.setEmail(email);
        adminVo.setTel(tel);
        adminVo.setPhone(phone);
        adminVo.setReg_id(regId);
        adminDao.insertAdmin(adminVo);

        return new PcResp();
    }

    @Override
    public PcResp checkLoginId(String loginId) throws NotUseableLoginidException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "account", loginId);
        AdminVo adminVo = adminDao.selectAdmin(param);
        if (adminVo != null) {
            throw new NotUseableLoginidException();
        }

        return new PcResp();
    }

    @Override
    public AdminListResp adminList(String keyword, Integer page, Integer cntPerPage, Long organizeId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "keyword", keyword);
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        List<AdminVo> adminVos = adminDao.listAdmin(param);

        AdminListResp resp = new AdminListResp();
        if (ListUtils.isEmpty(adminVos)) {
            resp.setSearchCnt(0);
        } else {
            for (AdminVo adminVo: adminVos) {
                if (resp.getAdmins() == null) {
                    resp.setAdmins(new ArrayList<>());
                }
                resp.getAdmins().add(adminConverter.convert(adminVo));
            }
            resp.setSearchCnt(adminDao.countAdmin(param));
        }

        return resp;
    }

    @Override
    public AdminResp admin(Long adminId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "admin_id", adminId);
        AdminVo adminVo = adminDao.selectAdmin(param);

        AdminResp resp = new AdminResp();
        resp.setAdmin(adminConverter.convert(adminVo));
        return resp;
    }

    @Override
    public PcResp updateAdmin(Long adminId, String password, String name, String email, String authority, String tel, String phone, Long organizeId, String state, long modId) {
        AdminVo adminVo = new AdminVo();
        adminVo.setAdmin_id(adminId);
        if (password != null) {
            adminVo.setPassword(pcUtils.encryptPassword(password));
        }
        adminVo.setName(name);
        adminVo.setEmail(email);
        adminVo.setAuthority(authority);
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
        adminVo.setOrganize_id(organizeId);
        adminVo.setState_div(state);
        adminVo.setMod_id(modId);
        adminDao.updateAdmin(adminVo);

        return new PcResp();
    }

    @Override
    public AdminResp deleteAdmin(Long adminId) {
        return null;
    }
}
