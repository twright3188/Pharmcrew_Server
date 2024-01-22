package com.bumdori.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class MapUtils {

    /**
     * 맵에 key-value를 넣는다.<br>
     * value가 null이거나 empty면 기존의 value를 제거한다.
     * @param param
     * @param key
     * @param value
     */
    public static void put(Map<String, Object> param, String key, Object value) {
        if (value == null ||
                (value instanceof String && StringUtils.isEmpty((String) value)) ||
                (value instanceof Long && ((Long) value) == -1) ||
                (value.getClass().isArray() && ArrayUtils.isEmpty((Object[]) value))
        ) {
            param.remove(key);
            return;
        }

        if (value.getClass().isArray()) {
//			value = Arrays.asList(value);
            Object[] tmp = (Object[]) value;
            value = Arrays.asList(tmp);
        }

        param.put(key, value);
    }

    public static int getInt(Map<String, Object> param, String key, int defaultValue) {
        Object value = param.get(key);
        if (value != null) {
            return Integer.parseInt(value.toString());
        }
        return defaultValue;
    }

    public static Long getLong(Map map, Object key, Long defaultValue) {
        Object value = map.get(key);
        if (value != null) {
            if (value instanceof Long) {
                return (Long) value;
            } else if (value instanceof String) {
                return Long.parseLong((String) value);
            } else if (value instanceof Integer) {
                return Long.valueOf(((Integer) value).toString());
            }
            // else if
            else {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static String getString(Map map, Object key, String defaultValue) {
        Object value = map.get(key);
        if (value != null) {
            if (value instanceof String) {
                return (String) value;
            }
//			else if (value instanceof Integer) {
//				return String.value)
//			}
            else {
                return String.valueOf(value);
            }
        }
        return defaultValue;
    }

    public static void pagingMySql(Map<String, Object> param, Integer page, Integer cntPerPage) {
        if (page != null && cntPerPage != null) {
            param.put("start", (page - 1) * cntPerPage);
            param.put("limit", cntPerPage);
        }
//		print(param);
    }

    public static void pagingMaridDb(Map<String, Object> param, int page, Integer cntPerPage) {
        pagingMySql(param, page, cntPerPage);
    }

    public static void print(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        for (Object key: keys) {
            System.out.println("key: " + key + ", value: " + map.get(key));
        }
    }
}
