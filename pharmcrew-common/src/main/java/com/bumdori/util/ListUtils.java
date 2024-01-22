package com.bumdori.util;

import java.util.List;

public class ListUtils {

	public static boolean isEmpty(List<?> value) {
		return value == null || value.size() == 0;
	}
}
