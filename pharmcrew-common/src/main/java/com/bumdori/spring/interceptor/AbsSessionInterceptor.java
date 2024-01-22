package com.bumdori.spring.interceptor;

import com.bumdori.spring.annotation.Session;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbsSessionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Session sessionAnnotation = handlerMethod.getMethodAnnotation(Session.class);
            if (sessionAnnotation == null || sessionAnnotation.required()) {
                boolean checkSessionResult = checkSession(request, response);
                if (!checkSessionResult) {
                    if (isApi(handlerMethod)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    } else {
                        response.sendRedirect(loginPage());
                    }
                }

                return checkSessionResult;
            }
        }

        return true;
    }

    abstract protected boolean checkSession(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception;
    abstract protected String loginPage();

    private boolean isApi(HandlerMethod method) {
        MethodParameter returnType = method.getReturnType();
        if (returnType.getMethodAnnotation(ResponseBody.class) != null) {
            return true;
        }

        return false;
    }
}
