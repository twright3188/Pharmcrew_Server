package kr.ant.kpa.pharmcrew.advice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ant.kpa.pharmcrew.sms.exception.SmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bumdori.spring.BLogger;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidCountException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidDatetimeException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidEnumException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidLengthException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidRangeException;
import kr.ant.kpa.pharmcrew.validation.exception.InvalidRegexException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;

// TODO Abs
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	public void handleErrorNotFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		response.sendError(HttpURLConnection.HTTP_NOT_FOUND);
		
		BLogger.warn(logger, "[{}] HTTP_NOT_FOUND", requestId);
	}
	
	@ExceptionHandler({BadRequestException.class, MissingServletRequestParameterException.class})
	public void handleErrorBadRequest(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		response.sendError(HttpURLConnection.HTTP_BAD_REQUEST, e.getLocalizedMessage());
		
		BLogger.warn(logger, "[{}] HTTP_BAD_REQUEST: {}", requestId, e.toString());
	}
	
	// TODO Bad Request로 처리하는게 맞는 거 같기도...
	@ExceptionHandler(InvalidLengthException.class)
	@ResponseBody
	public PcResp handlerErrorInvalidLength(HttpServletRequest request, InvalidLengthException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 '%s' 길이는 최소(%d) 최대(%d) 입니다.%s(입력값:%s)", e.getParameterName(), e.getMin(), e.getMax(), System.getProperty("line.separator"), e.getInputValue());
		
		BLogger.warn(logger, "[{}] LengthValidation - {}", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(10);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(InvalidRegexException.class)
	@ResponseBody
	public PcResp handlerErrorInvalidRegex(HttpServletRequest request, InvalidRegexException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 %s (%s) (입력값: %s)", e.getParameterName(), e.getRegex(), e.getInputValue());
		
		BLogger.warn(logger, "[{}] RegexValidation - {}", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(11);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(InvalidEnumException.class)
	@ResponseBody
	public PcResp handlerErrorInvalidEnum(HttpServletRequest request, InvalidEnumException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 %s (%s) (입력값: %s)", e.getParameterName(), Arrays.toString(e.getEnums()), e.getInputValue());
		
		BLogger.warn(logger, "[{}] EnumValidation - {}", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(12);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(InvalidRangeException.class)
	@ResponseBody
	public PcResp handlerErrorInvalidRange(HttpServletRequest request, InvalidRangeException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 %s (%d~%d) (입력값: %s)", e.getParameterName(), e.getMin(), e.getMax(), e.getInputValue());
		
		BLogger.warn(logger, "[{}] RangeValidation - {}", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(13);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(InvalidDatetimeException.class)
	@ResponseBody
	public PcResp handleErrorInvalidDatetime(HttpServletRequest request, InvalidDatetimeException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 %s (%s) (입력값: %s)", e.getParameterName(), e.getFormat(), e.getInputValue());
		
		BLogger.warn(logger, "[{}] DatetimeValidation - {}", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(14);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(InvalidCountException.class)
	@ResponseBody
	public PcResp handlerErrorInvalidCount(HttpServletRequest request, InvalidCountException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		String error = String.format("파라미터 %s (%d~%d) (입력값: %s)", e.getParameterName(), e.getMin(), e.getMax(), Arrays.toString(e.getInputValue()));
		
		BLogger.warn(logger, "[{}] CountValidation", requestId, error);
		
		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(15);
		resp.setMessage(error);
		return resp;
	}
	
	@ExceptionHandler(AbsValidationException.class)
	@ResponseBody
	public PcResp handleError(HttpServletRequest request, AbsValidationException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");
		
		PcResp resp = new PcResp(e.getValidation());
		resp.setReqId(requestId);
		if (StringUtils.isEmpty(e.getMessage()) == false) {
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	@ExceptionHandler(SmsException.class)
	@ResponseBody
	public PcResp handleError(HttpServletRequest request, SmsException e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");

		String error = e.getMessage();
		BLogger.warn(logger, "[{}] Smssender - {}", requestId, error);

		PcResp resp = new PcResp();
		resp.setReqId(requestId);
		resp.setCode(50);
		resp.setMessage(e.getMessage());
		return resp;
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody PcResp handleError(HttpServletRequest request, Exception e) {
		String requestId = (String) request.getAttribute("REQUEST_ID");

		PcResp resp = new PcResp(VALIDATION.UNTREATED_EXCEPTION);
		resp.setMessage(String.format("%s(%s)", resp.getMessage(), e.toString()));
		resp.setReqId(requestId);
		BLogger.error(logger, "[{}] Untreated: {}", requestId, resp.getMessage());
		e.printStackTrace();
		
		return resp;
	}
}
