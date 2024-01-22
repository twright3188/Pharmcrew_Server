package com.bumdori.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isEmpty(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
	

	/**
	 * null포함한 Empty String인지?
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		if (isEmpty(value) || "null".equals(value)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNumber(String value) {
		if (Pattern.matches("^[0-9]+$", value)) {
			return true;
		}
		return false;
	}
	
	public static boolean isPhoneNumber(String value) {
		if (Pattern.matches("^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$", value)) {
			return true;
		}
		return false;
	}

	/**
	 * UUID 코드 생성 - QR 코드 생성도 같이 사용할 수 있음 
	 * @return
	 */
	public static String makeRandomUUID() {
		String code = UUID.randomUUID().toString();
		String encoded = Base64.getEncoder().encodeToString(code.getBytes());
		return encoded;
	}
	
	/**
	 * 랜덤 String 생성
	 * @param length
	 * @param withSmallAlpha
	 * @return
	 */
	public static String makeRandom(int length, boolean withSmallAlpha) {
		Random random = new Random();
		
		int numRandom = random.nextInt(length);
		int charRandom = random.nextInt(length);
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			if (i==numRandom) {
				sb.append(random.nextInt(10));
				continue;
			}
			if (i==charRandom) {
				if (withSmallAlpha && random.nextBoolean()) {
					sb.append((char) (random.nextInt(26) + 97));
				} else {
					sb.append((char) (random.nextInt(26) + 65));
				}
				continue;
			}
			if (random.nextBoolean()) {
				if (withSmallAlpha && random.nextBoolean()) {
					sb.append((char) (random.nextInt(26) + 97));
				} else {
					sb.append((char) (random.nextInt(26) + 65));
				}
			} else {
				sb.append(random.nextInt(10));
			}
		}
		
		return sb.toString();
	}

	public static String removeHtml(String s) {
		s = s.replaceAll("<br\\/>", "\n");
		s = s.replaceAll("<br>", "\n");
//		System.out.println("s: " + s);
		s = s.replaceAll("<(\\/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(\\/)?>", "");
//		System.out.println("s: " + s);
		return s;
	}

	public static String valueOf(InputStream is) throws IOException {
		byte[] readBytes = new byte[1024];
		byte[] appendingBytes = null;
		
		for (int n; (n = is.read(readBytes)) != -1;) {
			appendingBytes = appendBytes(appendingBytes, readBytes, n);
		}
		
		String str = new String(appendingBytes);
//		System.out.println("str : " + str);
		return str;
	}

	private static byte[] appendBytes(byte[] src1, byte[] src2, int src2Length) {
		int src1Length = src1 == null ? 0 : src1.length;
		
		byte[] tmp = new byte[src1Length + src2Length];
		if (src1 != null) {
			System.arraycopy(src1, 0, tmp, 0, src1Length);
		}
		System.arraycopy(src2, 0, tmp, src1Length, src2Length);
		
		return tmp;
	}
	
	/**
	 * SMS 인증 번호 생성함수
	 * @param length
	 * @return
	 */
	public static String generateAuthKey(int length){
		String alphabet = new String("0123456789"); //9
		int n = alphabet.length(); //10

		String result = new String();
		Random r = new Random(); //11

		for (int i=0; i<length; i++) //12
			result = result + alphabet.charAt(r.nextInt(n)); //13

		return result;
	}

//	  private static final char[] numTables =  { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
//	  private static final char[] allNumTables =  { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
//	  private static final char[] charTables =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
//              'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z' };
//	  private static final char[] allCharTables =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
//              'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
//
////	영문자 사용 타입 => 전체(A), I,L,O,U,V 제외(E), 사용안함(N)
////	 전체(A), 0제외(Z), 사용안함(N)")
//	public static String generateSerialKey(int length, String charOpt, String numberOpt) {
//		char[] keys = null;
//		if ("A".equals(charOpt)) {		// 문자 전체 사용
//			if ("A".equals(numberOpt)) {	// 숫자 전체 사용
//				keys = new char[allNumTables.length+allCharTables.length];
//				System.arraycopy(allNumTables, 0, keys, 0, allNumTables.length);
//				System.arraycopy(allCharTables, 0, keys, allNumTables.length, allCharTables.length);
//			} else if ("Z".equals(numberOpt)) {	// 숫자 0 제외
//				keys = new char[numTables.length+allCharTables.length];
//				System.arraycopy(numTables, 0, keys, 0, numTables.length);
//				System.arraycopy(allCharTables, 0, keys, numTables.length, allCharTables.length);
//			} else {			//숫자 사용 안함
//				keys = new char[allCharTables.length];
//				System.arraycopy(allCharTables, 0, keys, 0, allCharTables.length);
//			}
//		} else if ("E".equals(charOpt)) {	// 문자 일부 제외
//			if ("A".equals(numberOpt)) {	// 숫자 전체 사용
//				keys = new char[allNumTables.length+charTables.length];
//				System.arraycopy(allNumTables, 0, keys, 0, allNumTables.length);
//				System.arraycopy(charTables, 0, keys, allNumTables.length, charTables.length);
//			} else if ("Z".equals(numberOpt)) {	// 숫자 0 제외
//				keys = new char[numTables.length+charTables.length];
//				System.arraycopy(numTables, 0, keys, 0, numTables.length);
//				System.arraycopy(charTables, 0, keys, numTables.length, charTables.length);
//			} else {			// 숫자 사용 안함
//				keys = new char[charTables.length];
//				System.arraycopy(charTables, 0, keys, 0, charTables.length);
//			}
//		} else {		// 문자 사용안함
//			if ("A".equals(numberOpt)) {	// 숫자 전체 사용
//				keys = new char[allNumTables.length];
//				System.arraycopy(allNumTables, 0, keys, 0, allNumTables.length);
//			} else if ("Z".equals(numberOpt)) {	// 숫자 0 제외
//				keys = new char[numTables.length];
//				System.arraycopy(numTables, 0, keys, 0, numTables.length);
//			} else { 			// 숫자 사용 안함
//				// Exception 발생 아무것도 없음
//			}
//		}
//
//		StringBuffer sb = new StringBuffer();
//
//        Random rn = new Random();
//
//        for( int i = 0 ; i < length ; i++ ){
//        	// 8자리 이상에서 4자리 생성시 마다 -표시
//        	if (length>=8 && i!=0 && i%4==0) {
//        		sb.append( "-" );
//			}
//            sb.append( keys[ rn.nextInt( keys.length ) ] );
//
//        }
//        return sb.toString();
//	}
}
