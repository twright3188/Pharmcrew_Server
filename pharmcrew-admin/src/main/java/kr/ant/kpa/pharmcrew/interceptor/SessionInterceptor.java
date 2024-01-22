package kr.ant.kpa.pharmcrew.interceptor;

import com.bumdori.spring.interceptor.AbsSessionInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends AbsSessionInterceptor {
    @Override
    protected boolean checkSession(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        HttpSession session = httpRequest.getSession();
        if (session != null && session.getAttribute("admin_id") != null) {
            return true;
        }
        return false;
    }

    @Override
    protected String loginPage() {
        return null;
    }
}
