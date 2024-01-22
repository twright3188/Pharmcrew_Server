package kr.ant.kpa.pharmcrew.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.ant.kpa.pharmcrew.PcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bumdori.spring.BLogger;
import com.bumdori.util.MapUtils;

import kr.ant.kpa.pharmcrew.converter.AdminConverter;
import kr.ant.kpa.pharmcrew.db.dao.AdminDao;
import kr.ant.kpa.pharmcrew.db.vo.admin.AdminVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.session.LoginResp;
import kr.ant.kpa.pharmcrew.service.SessionService;
import kr.ant.kpa.pharmcrew.type.admin.ADMIN_STATE;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidAccountStateException;

@Service
public class SessionServiceImpl implements SessionService {
    private final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminConverter adminConverter;

    @Autowired
    private PcUtils pcUtils;

    @Override
    public LoginResp login(String loginId, String password, HttpServletRequest request) throws InvalidAccountException, InvalidAccountStateException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "account", loginId);
        MapUtils.put(param, "password", pcUtils.encryptPassword(password));
        AdminVo adminVo = adminDao.selectAdmin(param);
        if (adminVo == null) {
            throw new InvalidAccountException();
        }
        switch (ADMIN_STATE.valueOf(adminVo.getState_div())) {
            case N:
                break;
            default:
                throw new InvalidAccountStateException();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("admin_id", adminVo.getAdmin_id());
        session.setAttribute("organize_id", adminVo.getOrganize_id());

        LoginResp resp = new LoginResp();
        resp.setAdmin(adminConverter.convert(adminVo));
        return resp;
    }

    @Override
    public PcResp logout(HttpSession session) {
        Long adminId = (Long) session.getAttribute("admin_id");
        BLogger.debug(logger, "adminId: {}", adminId);

        session.invalidate();
        return new PcResp();
    }
}
