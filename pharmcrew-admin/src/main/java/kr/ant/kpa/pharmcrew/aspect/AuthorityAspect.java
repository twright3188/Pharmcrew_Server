package kr.ant.kpa.pharmcrew.aspect;

import com.bumdori.spring.BLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AuthorityAspect {

    private Logger logger = LoggerFactory.getLogger(AuthorityAspect.class);

    @Around("execution(* kr.ant.kpa.pharmcrew.controller.*Controller.*(..))")
    public Object checkOrganizeId(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodInvocationProceedingJoinPoint methodJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;

        CodeSignature codeSignature = (CodeSignature) methodJoinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();

        Object[] args = methodJoinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            String parameterName = parameterNames[i];
            if ("organizeId".equals(parameterName)) {
                if (args[i] == null) {  // API 요청시 조직 값이 없으면 자신의 조직 값을 넣는다.
                    // [REFRENCE] AOP에서 HttpServletRequest 사용 https://whitelife.tistory.com/214
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

                    String reqId = (String) request.getAttribute("REQUEST_ID");

                    HttpSession session = request.getSession();
                    Long organizeId = (Long) session.getAttribute("organize_id");
                    if (organizeId != null) {
                        args[i] = organizeId;
                        BLogger.debug(logger, "[{}] organizeId: null -> {}", reqId, organizeId);
                    }
                    break;
                }
            }
        }

        return joinPoint.proceed(args);
    }
}
