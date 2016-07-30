package com.hit.algorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class RandomReplacementAlgoCacheImpl<K,V> implements IAlgoCache<K,V> {
	
	private Map<K, V> ram;

	RandomReplacementAlgoCacheImpl(int capacity) {
		ram = new HashMap<>(capacity);
	}
	@Override
	public V getElement(K key) {
		return ram.get(key);
	}

	@Override
	public V putElement(K key, V value) {
		if(ram.containsKey(key))
			return value;
		else {
			Random rand = new Random();
			List<K> keys = new ArrayList<K>(ram.keySet());
			K randomKey = keys.get(rand.nextInt(ram.size()));
			V element = ram.get(randomKey);
			removeElement(randomKey);
			ram.put(key,value);
			return element;
		}
		
	}

	@Override
	public void removeElement(K key) {
		 ram.remove(key);
	}

}
