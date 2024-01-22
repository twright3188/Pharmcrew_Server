package kr.ant.kpa.pharmcrew.data.cache.impl.redis;

import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class CacheManagerImpl implements CacheManager {
    @Resource(name = "redisTemplate")
    private ValueOperations<Serializable, Serializable> valueOps;

    @Override
    public <K extends Serializable, V extends Serializable> void put(CACHE cache, K k, V v) {
        this.put(cache, k, v, null);
    }

    @Override
    public <K extends Serializable, V extends Serializable> void put(CACHE cache, K k, V v, Integer expireMillisec) {
        String key = getKey(cache, k);

        if (expireMillisec != null) {
            valueOps.set(key, v, expireMillisec, TimeUnit.MILLISECONDS);
        } else {
            valueOps.set(key, v);
        }
    }

    @Override
    public <K extends Serializable, V extends Serializable> V get(CACHE cache, K k) {
        return (V) valueOps.get(getKey(cache, k));
    }

    @Override
    public <K extends Serializable> void delete(CACHE cache, K k) {
        valueOps.set(getKey(cache, k), null);
    }

    private <K extends Serializable> String getKey(CACHE cache, K k) {
        return cache.name() + k;
    }
}
