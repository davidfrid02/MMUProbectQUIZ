package com.hit.algorithm;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class RandomReplacementAlgoCacheImpl<K, V> implements IAlgoCache<K, V> {

	// The container that holds the data in the cache
	private Map<K, V> cache;

	private int capacity;

	public RandomReplacementAlgoCacheImpl(int capacity) {
		this.capacity = capacity;
		cache = new HashMap<>(capacity);
	}

	@Override
	public V getElement(K key) {
		return cache.get(key); // returns null if no such key
	}

	@Override
	public V putElement(K key, V value) {
		V curValue = null;
		// if the cache contains this key then just overriding it (might be with a new value)
		if (!cache.containsKey(key)) {
			// otherwise check if capacity is full and follow the algorithm in order
			// to replace another key
			if (capacity == cache.size()) {
				Random rand = new Random();
				List<K> keys = new ArrayList<K>(cache.keySet()); // retrieve all keys from the set
				K randomKey = keys.get(rand.nextInt(cache.size())); // get a random key to be replaced
				curValue = cache.get(randomKey);
				removeElement(randomKey);
			}
		}
		cache.put(key, value);
		return curValue;
	}

	@Override
	public void removeElement(K key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}
	
	@Override
	public String toString(){
		return cache.toString();
	}

}
