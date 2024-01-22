package com.bumdori.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 날짜, 시간 관련 유틸
 * @author beksung 
 *
 */
public class DateUtils {

	/**
	 * 현재와의 차이를 millisecond로 반환한다.<br>
	 * @param date
	 * @return
	 */
	public static long diffNow(Date date) {
		Calendar target = Calendar.getInstance();
		target.setTime(date);

		return diffNow(target);
	}
	
	public static long diffNow(Calendar calendar) {
		Calendar now = Calendar.getInstance();
		System.out.println("now: " + DateUtils.getStringFromDate(now.getTime(), "yyyyMMdd HHmm") + ", target: " + DateUtils.getStringFromDate(calendar.getTime(), "yyyyMMdd HHmm"));
		System.out.println("now: " + now.getTimeInMillis() + ", target: " + calendar.getTimeInMillis() + " diff:" + (now.getTimeInMillis()-calendar.getTimeInMillis()));
			
		return now.getTimeInMillis() - calendar.getTimeInMillis();
	}
	
	/**
	 * 입력 날짜와 오늘과의 차이 
	 * @param calendar
	 * @return
	 */
	public static int diffNowDay(Calendar calendar) {
		return diffNowDay(calendar.getTimeInMillis());
	}
	
	public static int diffNowDay(Long target) {
		Calendar now = Calendar.getInstance();
		return diffDay(now.getTimeInMillis(), target);
	}
	
	/**
	 * 두 날짜 사이의 일자 차이
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int diffDay(Long begin, Long end) {
		long diff = end - begin;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (int) diffDays;
	}
	
	public static int diffDay(Date begin, Date end) {
		return diffDay(begin.getTime(), end.getTime());
	}
	
	
//	/**
//	 * millisecond를 읽기 쉬운 형태로 변환한다.<br>
//	 * xx분 xx초
//	 * @param millisecond
//	 * @return
//	 */
//	public static String getReadableTime(long millisecond) {
//		StringBuffer sb = new StringBuffer();
//		
//		int minute = (int) (millisecond / (1000 * 60));
//		int second = (int) (millisecond % (1000 * 60)) / 1000;
////		System.out.println("millisecond: " + millisecond + ", minute: " + minute + ", second: " + second);
//		if (minute > 0) {
//			sb.append(minute);
//			sb.append("분");
//		}
//		if (second > 0) {
//			if (sb.length() > 0) {
//				sb.append(" ");
//			}
//			sb.append(second);
//			sb.append("초");
//		}
//		
//		return sb.toString();
//	}
//	
//	/**
//	 * 두 날짜 사이의 개월 차이 
//	 * @param begin
//	 * @param end
//	 * @return
//	 */
//	public static int diffMonth(Long begin, Long end) {
//		Calendar bCal = Calendar.getInstance();
//		Calendar eCal = Calendar.getInstance();
//		
//		bCal.setTimeInMillis(begin);
//		eCal.setTimeInMillis(end);
//		
//		int year = eCal.get(Calendar.YEAR) - bCal.get(Calendar.YEAR);
//		int month = eCal.get(Calendar.MONTH) - bCal.get(Calendar.MONTH);
//		int diffMonth = year*12 + month;
//		
//		return diffMonth;
//	}
//	
	/**
	 * Long에서 타임 포맷으로 가져오기
	 * @param millis
	 * @param format
	 * @return
	 */
	public static String getStringFromLong(Long millis, String format) {
		return getStringFromDate(new Date(millis), format);
	}
	
	/**
	 * 캘린더를 원하는 형식으로 반환하는 함수
	 * @param cal		Calendar
	 * @param format	c
	 * @return
	 */
	public static String getStringFromCalendar(Calendar cal, String format) {
		return getStringFromDate(new Date(cal.getTimeInMillis()), format);
	}
	
	/**
	 * 날짜(Date) 를 원하는 형식으로 반환하는 함수
	 * @param date		Date
	 * @param format	"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getStringFromDate(Date date, String format) {
		String response = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		response = sdf.format(date);	
		return response;
	}
	
	/**
	 * from 형식의 input 을 to 형식으로 변경하는 함수  
	 * @param input		입력하는 데이터
	 * @param from		입력하는 데이터의 형식
	 * @param to		원하는 출력 데이터 형식
	 * @return
	 */
    public static String toFormatStringFromString(String input, String from, String to) {
        SimpleDateFormat format = new SimpleDateFormat(from);
        Date date = null;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getStringFromDate(date, to);
    }
    
	/**
	 * 포맷의 String을 Calendar 형태로 반환하는 함수
	 * @param time		'20170911'
	 * @param format	'yyyyMMdd'
	 * @return	Calendar
	 */
	public static Calendar getCalendarFromString(String time, String format) {
		
		Date date = getDateFromString(time, format);
		if (date == null) {
			return null;
		}
		Calendar cal=  Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}
	
	/**
	 * 포맷의 String을 Date로 반환하는 함수
	 * @param time		'20170911'
	 * @param format	'yyyyMMdd'
	 * @return	Date
	 */
	public static Date getDateFromString(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return null; 
		}
		
		return date;
	}
	
//	/**
//     * local time -> GMT 표준시
//     * @param localDateTime
//     * @return
//     */
//    public static Long convertLocalTimeToUTC(Long localDateTime) {
//        long utc_time = localDateTime;
//
//        TimeZone z = TimeZone.getDefault();
//        int offset = z.getOffset(localDateTime);
//        utc_time = localDateTime - offset;
//        return utc_time;
//    }
//
//    /**
//     * GMT 표준시 -> local time
//     * @param utcTime
//     * @return
//     */
	public static Date convertUtcToLocal(Date utc) {
		if (utc == null) {
			return null;
		}
		return new Date(convertUtcToLocal(utc.getTime()));
	}
	
    public static Long convertUtcToLocal(Long utc) {
        long local = utc;

        TimeZone z = TimeZone.getDefault();
        int offset = z.getOffset(utc);
        local = utc + offset;
        return local;
    }
//
//    public static Long getLocalUTCTime() {
//        long localTime = Calendar.getInstance().getTimeInMillis();
//        return convertLocalTimeToUTC(localTime);
//    }
//
//    /**
//     * Locale의 String date를 GMT Calendar로 변환 
//     * @param dt
//     * @param locale
//     * @return
//     */
//    public static Calendar convertLocaleStringToGMT(String dt, String locale) {
//    	SimpleDateFormat sdfgmtOrg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale(locale));
//		TimeZone utcZone = TimeZone.getTimeZone(locale);
//		sdfgmtOrg.setTimeZone(utcZone);
//		Date time = null;
//		try {
//			time = sdfgmtOrg.parse(dt);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		if (time != null) {
//			System.out.println("locale: " + locale + ", time: " + sdfgmtOrg.format(time));
//			
//			sdfgmtOrg.setTimeZone(TimeZone.getTimeZone("UTC"));
//			System.out.println("locale:GMT, : time :" + sdfgmtOrg.format(time));
//			
//			return sdfgmtOrg.getCalendar();
//		} else {
//			return null;
//		}
//    }
//    
    public static Calendar convertLocaleLongToGMT(long longdt, String locale) {
    	String dt = getStringFromLong(longdt, "yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdfgmtOrg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale(locale));
		TimeZone utcZone = TimeZone.getTimeZone(locale);
		sdfgmtOrg.setTimeZone(utcZone);
		Date time = null;
		try {
			time = sdfgmtOrg.parse(dt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (time != null) {
			System.out.println("locale: " + locale + ", time: " + sdfgmtOrg.format(time));
			
			sdfgmtOrg.setTimeZone(TimeZone.getTimeZone("UTC"));
			System.out.println("locale:GMT, : time :" + sdfgmtOrg.format(time));
			
			return getCalendarFromString(sdfgmtOrg.format(time), "yyyy-MM-dd HH:mm:ss");
//			return sdfgmtOrg.getCalendar();
		} else {
			return null;
		}
    }
	
	public static Date toDate(String dateString, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateString);
	}
	
	public static String toString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 오늘 시간을 time으로 지정
	 * @param time	HHmm
	 * @return
	 */
	public static Date getTodayWithTime(String time) {
		return getDateWithTime(null, time);
	}
	
	/**
	 * 시간을 time으로 지정
	 * @param date
	 * @param time	HHmm
	 * @return
	 */
	public static Date getDateWithTime(Date date, String time) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.set(Calendar.HOUR, Integer.valueOf(time.substring(0, 2)));
		cal.set(Calendar.MINUTE, Integer.valueOf(time.substring(2, 4)));
		cal.set(Calendar.SECOND, Integer.valueOf(time.substring(4, 6)));
		return cal.getTime();
	}
	
	/**
	 * 현재에서 분이 지난 후
	 * @param minute
	 * @return
	 */
	public static Date getNowAfterPeriodMinute(int minute) {
		return getDatetimeAfterPeriod(null, null, minute, null);
	}
	
	/**
	 * 현재애서 시간이 지난 후
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getNowAfterPeriod(Integer hour, Integer minute, Integer second) {
		return getDatetimeAfterPeriod(null, hour, minute, second);
	}
	
	/**
	 * 분이 지난 후
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDatetimeAfterPeriodMinute(Date date, int minute) {
		return getDatetimeAfterPeriod(date, null, minute, null);
	}
	
	/**
	 * 시간이 지난 후
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDatetimeAfterPeriod(Date date, Integer hour, Integer minute, Integer second) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		if (hour != null)	cal.add(Calendar.HOUR, hour);
		if (minute != null)	cal.add(Calendar.MINUTE, minute);
		if (second != null)	cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}
    
    // 특정한 날짜에 원하는 일수를 더해서 String format 얻기
	public static String getStringFromDateWithAddDays(Date date, String format, int days)
	{
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        cal.add(Calendar.DATE, days);
        
        
		return sdf.format(cal.getTime());
	}
    
}
