package com.bumdori.data;

import java.io.Serializable;

public abstract class AbsDataFacade<K extends Serializable, V extends Serializable> {
	
	protected AbsDataChain<K, V> dataChain;
	
	public void setDataChain(AbsDataChain<K, V> dataChain) {
		this.dataChain = dataChain;
	}

	public V get(K k) {
		return dataChain.get(k);
	}

}
