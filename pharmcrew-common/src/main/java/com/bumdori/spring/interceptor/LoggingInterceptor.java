package com.bumdori.spring.interceptor;

import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.bumdori.spring.BLogger;

public class LoggingInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = UUID.randomUUID().toString();
        request.setAttribute("REQUEST_ID", requestId);
        BLogger.info(logger, "[{}] ==== start =====", requestId);
        BLogger.info(logger, "[{}] uri: {}", requestId, request.getRequestURI());

        if (handler instanceof HandlerMethod) { // DefaultServlet이 처리하는 경우에는 DefaultServletHttpRequestHandler이다.
            HandlerMethod hm = (HandlerMethod) handler;
            BLogger.info(logger, "[{}] method: {}, function: {}",
                    requestId, request.getMethod(), hm.getMethod().getName());

            Enumeration<String> names;

            names = request.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                if (name.startsWith("org.springframework") || name.endsWith(".FILTERED") || name.equals("REQUEST_ID"))
                    continue;
                Object value = request.getAttribute(name);
                BLogger.debug(logger, "[{}] attr - name: {}, value: {}", requestId, name, value);
            }
			names = request.getHeaderNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = request.getHeader(name);
				BLogger.debug(logger, "[{}] header - name: {}, value: {}", requestId, name, value);
			}
            names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                String[] values = request.getParameterValues(name);
                BLogger.debug(logger, "[{}] param - name: {}, value: {}", requestId, name, values);
            }
            if (request instanceof MultipartHttpServletRequest) {
                MultiValueMap<String, MultipartFile> map = ((MultipartHttpServletRequest) request).getMultiFileMap();
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    List<MultipartFile> values = map.get(key);
                    BLogger.debug(logger, "[{}] file - name: {}, value: {}", requestId, key, values);
                }
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestId = (String) request.getAttribute("REQUEST_ID");

        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        String message = new String(cachingResponse.getContentAsByteArray());
        BLogger.info(logger, "[{}] response body: {}", requestId, message);
        BLogger.info(logger, "[{}] ==== end =====", requestId);
    }
}
