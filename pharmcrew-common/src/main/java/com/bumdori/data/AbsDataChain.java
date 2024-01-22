package com.bumdori.data;

import java.io.Serializable;

public abstract class AbsDataChain<K extends Serializable, V extends Serializable> {

	private AbsDataChain<K, V> nextChain = null;
	
	public V get(K k) {
		V v = getData(k);
		if (v != null) {
			return v;
		}
		if (nextChain == null) {
			return onFail(k);
		} else {
			v = nextChain.get(k);
			if (v == null) {
				return onFail(k);
			} else {
				return onSuccess(k, v);
			}
		}
	}
	
	protected abstract V getData(K k);
	
	protected V onFail(K k) {
		return null;
	}
	
	protected V onSuccess(K k, V v) {
		return v;
	}
	
	public final AbsDataChain<K, V> setNextChain(AbsDataChain<K, V> next) {
		this.nextChain = next;
		return next;
	}
}
