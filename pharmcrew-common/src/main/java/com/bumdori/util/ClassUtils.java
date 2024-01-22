package com.bumdori.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtils {

	public static String capitalize(String name) {
		if (name.length() > 1 &&
				Character.isUpperCase(name.charAt(1)) &&
				Character.isUpperCase(name.charAt(0))) {
		    return name;
		}

		char chars[] = name.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}
	
	public static String decapitalize(String name) {
		if (name.length() > 1 &&
				Character.isUpperCase(name.charAt(1)) &&
				Character.isUpperCase(name.charAt(0))) {
		    return name;
		}

		char chars[] = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return new String(chars);
	}
	
	public static void getSuperFields(Class<?> destClass, List<Field> fieldList) {
		Field[] fields = destClass.getDeclaredFields();
		if (fields.length > 0)
		{	fieldList.addAll(Arrays.asList(fields));
		
			if (destClass.getSuperclass() != Object.class && destClass.getSuperclass() != Enum.class) {
				getSuperFields(destClass.getSuperclass(), fieldList);
			}
		}
	}
	
	public static Method getSuperMethod(Class<?> destClass, String name, Class<?> ... paramClass) {
		Method method = null;
		try {
			method = destClass.getDeclaredMethod(name, paramClass);
		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
		} finally {
			if (method == null) {
				try {
					method = destClass.getMethod(name, paramClass);
				} catch (NoSuchMethodException e) {
//					e.printStackTrace();
				} finally {
					if (method == null && destClass.getSuperclass() != null) {
						return getSuperMethod(destClass.getSuperclass(), name);
					}
				}
			}
		}
		
		return method;
	}

	public static List<Method> getMethods(Class<?> destClass, String name) {
		Method[] methods = destClass.getMethods();
		if (ArrayUtils.isEmpty(methods)) {
			return null;
		}
		
		List<Method> methodList = new ArrayList<Method>();
		for (Method method : methods) {
			if (method.getName().equals(name)) {
				methodList.add(method);
			}
		}
		
		return methodList;
	}
	
	public static void getSuperMethods(Class<?> destClass, List<Method> methodList) {
		Method[] methods = destClass.getDeclaredMethods();
		methodList.addAll(Arrays.asList(methods));
		
		if (destClass.getSuperclass() != Object.class) {
			getSuperMethods(destClass.getSuperclass(), methodList);
		}
	}
}
