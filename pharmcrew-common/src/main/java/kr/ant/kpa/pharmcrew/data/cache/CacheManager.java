package kr.ant.kpa.pharmcrew.data.cache;

import java.io.Serializable;

public interface CacheManager {
    <K extends Serializable, V extends Serializable> void put(CACHE cache, K k, V v);
    <K extends Serializable, V extends Serializable> void put(CACHE cache, K k, V v, Integer expireMillisec);
    <K extends Serializable, V extends Serializable> V get(CACHE cache, K k);
    <K extends Serializable> void delete(CACHE cache, K k);
}
