package kr.ant.kpa.pharmcrew.data;

import com.bumdori.data.AbsDataChain;
import kr.ant.kpa.pharmcrew.data.cache.CACHE;
import kr.ant.kpa.pharmcrew.data.cache.CacheManager;

import java.io.Serializable;

/**
 * DataFacade안에서 사용될 CacheChain
 * @author donghyouk
 *
 * @param <K>	key
 * @param <V>	value
 */
public abstract class AbsRedisChain<K extends Serializable, V extends Serializable> extends AbsDataChain<K, V> {
	
	private CacheManager cacheManager;
	
	/**
	 * 캐시 종류
	 */
	private CACHE cache;

	public AbsRedisChain(CACHE cache, CacheManager cacheManager) {
		super();
		this.cacheManager = cacheManager;
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected V getData(K k) {
		return (V) cacheManager.get(cache, k);
	}

	@Override
	protected V onSuccess(K k, V v) {
		cacheManager.put(cache, k, v);
		return v;
	}

}
