package kr.ant.kpa.pharmcrew.aspect;

import com.bumdori.spring.BLogger;
import com.bumdori.spring.annotation.validation.*;
import com.bumdori.spring.annotation.validation.regex.EmailValidation;
import com.bumdori.spring.annotation.validation.regex.HandphoneValidation;
import com.bumdori.spring.annotation.validation.regex.RegexValidation;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.validation.exception.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

// TODO Abs
// [REFERENCE] aspect https://addio3305.tistory.com/86
@Aspect
@Component
public class ValidationAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

	@Around("execution(* kr.ant.kpa.pharmcrew.controller.*Controller.*(..))")
	public Object validateParameter(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodInvocationProceedingJoinPoint methodJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
		
		MethodSignature methodSignature = (MethodSignature) methodJoinPoint.getSignature();
		Parameter[] parameters = methodSignature.getMethod().getParameters();
		
		CodeSignature codeSignature = (CodeSignature) methodJoinPoint.getSignature();
		String[] parameterNames = codeSignature.getParameterNames();
		
		Object[] args = methodJoinPoint.getArgs();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		String reqId = (String) request.getAttribute("REQUEST_ID");
		
		for (int i = 0; i < args.length; i++) {
			String parameterName = parameterNames[i];
			Parameter parameter = parameters[i];
			Object arg = args[i];
			
			RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
			if (requestParam != null) {
				if (requestParam.required()) {
					if (arg == null) {
						throw new BadRequestException("Required " + parameter.getType().getSimpleName() + " parameter '" + parameterNames[i] + "' is not present");
					} else if (arg instanceof String && StringUtils.isEmpty((String) arg)) {
						throw new BadRequestException("Required String parameter '" + parameterNames[i] + "' is not present");
					}
				} else {
					if (arg instanceof String && StringUtils.isEmpty((String) arg)) {
						args[i] = null;
						BLogger.debug(logger, "[{}] {}: empty string -> null", reqId, parameterNames[i]);
						continue;
					}
				}
			}
			
			Annotation[] annotations = parameter.getAnnotations();
			for (Annotation annotation: annotations) {
				if (annotation instanceof LengthValidation) {
					LengthValidation lengthValidation = (LengthValidation) annotation;
					lengthValidate(parameterName, lengthValidation.min(), lengthValidation.max(), arg);
				} else if (annotation instanceof RegexValidation) {
					regexValidate(parameterName, ((RegexValidation) annotation).regex(), arg);
				} else if (annotation instanceof HandphoneValidation) {
					regexValidate(parameterName, ((HandphoneValidation) annotation).regex(), arg);
				} else if (annotation instanceof EmailValidation) {
					regexValidate(parameterName, ((EmailValidation) annotation).regex(), arg);
				} else if (annotation instanceof EnumValidation) {
					enumValidate(parameterName, ((EnumValidation) annotation).value(), arg);
				} else if (annotation instanceof RangeValidation) {
					RangeValidation rangeValidation = (RangeValidation) annotation;
					rangeValidate(parameterName, rangeValidation.min(), rangeValidation.max(), arg);
				} else if (annotation instanceof DatetimeValidation) {
					datetimeValidate(parameterName, ((DatetimeValidation) annotation).format(), arg);
				} else if (annotation instanceof CountValidation) {
					CountValidation countValidation = (CountValidation) annotation;
					countValidate(parameterName, countValidation.min(), countValidation.max(), arg);
				}
			}
			
		}
		
		return joinPoint.proceed(args);
	}

	private void lengthValidate(String parameterName, int min, int max, Object arg) {
		if (arg instanceof String == false) {
			return;
		}
		String value = (String) arg;
		if (StringUtils.isEmpty(value)) {
			return;
		}
		
		Integer min_ = min == -1 ? null : min;
		Integer max_ = max == -1 ? null : max;
		
		int length = value.length();
		if ((min_ != null && length < min_) || (max_ != null && length > max_)) {
			throw new InvalidLengthException(parameterName, value, min_, max_);
		}
	}
	
	private void regexValidate(String parameterName, String regex, Object arg) {
		if (StringUtils.isEmpty(regex)) {
			return;
		}
		if (arg instanceof String == false) {
			return;
		}
		String value = (String) arg;
		if (StringUtils.isEmpty(value)) {
			return;
		}
		
		if (!Pattern.matches(regex, value)) {
			throw new InvalidRegexException(parameterName, value, regex);
		}
	}
	
	private void enumValidate(String parameterName, String[] enums, Object arg) {
		if (arg instanceof String == false) {
			return;
		}
		String value = ((String) arg).trim();
		if (StringUtils.isEmpty(value)) {
			return;
		}
		
		boolean find = false;
		for (String enum_: enums) {
			if (enum_.equals(value)) {
				find = true;
				break;
			}
		}
		if (!find) {
			throw new InvalidEnumException(parameterName, value, enums);
		}
	}
	
	private void rangeValidate(String parameterName, int min, int max, Object arg) {
		if (Integer.class.isInstance(arg) == false) {
			return;
		}
		
		Integer min_ = min == -1 ? null : min;
		Integer max_ = max == -1 ? null : max;
		
		int value = (int) arg;
		if ((min_ != null && value < min_) || (max_ != null && value > max_)) {
			throw new InvalidRangeException(parameterName, value, min_, max_);
		}
	}
	
	private void datetimeValidate(String parameterName, String format, Object arg) {
		if (StringUtils.isEmpty(format)) {
			return;
		}
		if (arg instanceof String == false) {
			return;
		}
		String value = (String) arg;
		if (StringUtils.isEmpty(value)) {
			return;
		}
		
		try {
			new SimpleDateFormat(format).parse(value);
		} catch (ParseException e) {
			throw new InvalidDatetimeException(parameterName, value, format);
		}
	}

	private void countValidate(String parameterName, int min, int max, Object arg) {
		if (arg instanceof Object[] == false) {
			return;
		}
		Object[] value = (Object[]) arg;
		
		Integer min_ = min == -1 ? null : min;
		Integer max_ = max == -1 ? null : max;
		
		int length = value.length;
		if ((min_ != null && length < min_) || (max_ != null && length > max_)) {
			throw new InvalidCountException(parameterName, value, min_, max_);
		}
	}
	
}
