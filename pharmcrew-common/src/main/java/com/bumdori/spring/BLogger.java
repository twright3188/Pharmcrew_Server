package com.bumdori.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

public class BLogger {

	public static void error(Logger logger, String format, Object ...params) {
		printError(logger, format, params);
	}
	
	public static void warn(Logger logger, String format, Object ...params) {
		printWarn(logger, format, params);
	}
	
	public static void info(Logger logger, String format, Object ...params) {
		printInfo(logger, format, params);
	}
	
	public static void debug(Logger logger, String format, Object ...params) {
		printDebug(logger, format, params);
	}
	
	public static void trace(Logger logger, String format, Object ...params) {
		printTrace(logger, format, params);
	}
	
	private static void printError(Logger logger, String format, Object[] param) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		format = "\u001b[7;31m[{}:{}] " + format + "\u001b[m";
		List<Object> tmpList = param == null ? new ArrayList<Object>() : Arrays.asList(param);
		tmpList = new ArrayList<Object>(tmpList);
		tmpList.add(0, element.getLineNumber());
		tmpList.add(0, element.getMethodName());
		pringLog(LEVEL.ERROR, logger, format, tmpList.toArray());
	}
	
	private static void printWarn(Logger logger, String format, Object[] param) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		format = "\u001b[1;33m[{}:{}] " + format + "\u001b[m";
		List<Object> tmpList = param == null ? new ArrayList<Object>() : Arrays.asList(param);
		tmpList = new ArrayList<Object>(tmpList);
		tmpList.add(0, element.getLineNumber());
		tmpList.add(0, element.getMethodName());
		pringLog(LEVEL.WARN, logger, format, tmpList.toArray());
	}
	
	private static void printInfo(Logger logger, String format, Object[] param) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		format = "\u001b[0;34m[{}:{}] " + format + "\u001b[m";
		List<Object> tmpList = param == null ? new ArrayList<Object>() : Arrays.asList(param);
		tmpList = new ArrayList<Object>(tmpList);
		tmpList.add(0, element.getLineNumber());
		tmpList.add(0, element.getMethodName());
		pringLog(LEVEL.INFO, logger, format, tmpList.toArray());
	}
	
	private static void printDebug(Logger logger, String format, Object[] param) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		format = "\u001b[0;36m[{}:{}] " + format + "\u001b[m";
		List<Object> tmpList = param == null ? new ArrayList<Object>() : Arrays.asList(param);
		tmpList = new ArrayList<Object>(tmpList);
		tmpList.add(0, element.getLineNumber());
		tmpList.add(0, element.getMethodName());
		pringLog(LEVEL.DEBUG, logger, format, tmpList.toArray());
	}
	
	private static void printTrace(Logger logger, String format, Object[] param) {
		StackTraceElement element = new Throwable().getStackTrace()[2];
		format = "\u001b[0;92m[{}:{}] " + format + "\u001b[m";
		List<Object> tmpList = param == null ? new ArrayList<Object>() : Arrays.asList(param);
		tmpList = new ArrayList<Object>(tmpList);
		tmpList.add(0, element.getLineNumber());
		tmpList.add(0, element.getMethodName());
		pringLog(LEVEL.TRACE, logger, format, tmpList.toArray());
	}
	
	private enum LEVEL {
		ERROR,
		WARN,
		INFO,
		DEBUG,
		TRACE,
		;
	}
	
	private static void pringLog(LEVEL level, Logger logger, String format, Object[] param) {
		switch (level) {
		case ERROR:
			logger.error(format, param);
			break;
		case WARN:
			logger.warn(format, param);
			break;
		case INFO:
			logger.info(format, param);
			break;
		case DEBUG:
			logger.debug(format, param);
			break;
		case TRACE:
			logger.trace(format, param);
			break;
		}
	}
}
