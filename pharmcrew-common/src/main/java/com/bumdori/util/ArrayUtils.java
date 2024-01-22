package com.bumdori.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public static <K> boolean isEmpty(K[] values) {
        if (values == null || values.length == 0) {
            return true;
        }

        return false;
    }

    public static <K> boolean isEqual(K[] values1, K[] values2) {
        if (values1.length != values2.length) {
            return false;
        }
        for (int i = 0; i < values1.length; i++) {
            if (values1[i].equals(values2[i]) == false) {
                return false;
            }
        }

        return true;
    }

    //	public static <K> K[] splice(K[] values, K value) {
//		List<K> list = new ArrayList<K>(Arrays.asList(values));
//		list.remove(value);
////		System.out.println("list: " + list);
//		K[] tmps = new K[list.size()];
//		K[] tmps = values.clone();
//		tmps = list.toArray(tmps);
//		return tmps;
//	}
    public static Long[] splice(Long[] values, Long value) {
        List<Long> list = new ArrayList<Long>(Arrays.asList(values));
        list.remove(value);
//		System.out.println("list: " + list);
        Long[] tmps = new Long[list.size()];
        tmps = list.toArray(tmps);
        return tmps;
    }

    public static <K> boolean isExist(K[] values, K value) {
        return indexOf(values, value) != -1;
    }

    public static <K> int indexOf(K[] values, K value) {
        if (isEmpty(values)) {
            return -1;
        }
        int index = Arrays.asList(values).indexOf(value);
//		System.out.println("index: " + index);
        return index;
//
//		int index = -1;
//		for (int i = 0; i < values.length; i++) {
//			Object tmp = values[i];
//			if (tmp instanceof String) {
//				if (tmp.equals(value)) {
//					index = i;
//				}
//			} else {
//				if (tmp == value) {
//					index = i;
//				}
//			}
//
//			if (index != -1) {
//				break;
//			}
//		}
//
//		return index;
    }
}
