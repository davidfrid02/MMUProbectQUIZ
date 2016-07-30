package com.hit.algorithm;

import java.util.Map;

public class LRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V>{

	private Map<K, V> ram;
	private int capacity;
	@Override
	public V getElement(K key) {
			return ram.get(key);
	}

	@Override
	public V putElement(K key, V value) {
		return null;
	}

	@Override
	public void removeElement(K key) {
		 ram.remove(key);
		
	}

}
