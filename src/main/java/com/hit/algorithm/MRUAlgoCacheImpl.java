package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class MRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V> {

	private Map<K, V> cache;
	private int capacity;
	private K lastAccessedKey; // used to hold the last accesed key so won't have to iterate the entire list to find it
	
	public MRUAlgoCacheImpl(int capacity){
		this.capacity = capacity;
		// setting the cache with a access-order instead of insert-order
		// in this way - every time using a put or a get the element will move forward to the top of the list
		// meaning it will be the most recently used element
		// used the default load factor
		cache = new LinkedHashMap<K,V>(capacity, 0.75F, true);
		lastAccessedKey = null;
	}
	@Override
	public V getElement(K key) {
		V value = cache.get(key);
		if(value != null)
			lastAccessedKey = key;
		return cache.get(key); // returns null if doesn't exist
	}

	@Override
	public V putElement(K key, V value) {
		V curValue = null;
		if(!cache.containsKey(key)){
			// if the cache contains this key then just putting it back (perhaps with a new value)
			// will make it the most recently used key (built in container ability)
			if(capacity == cache.size()){	
				// gets the most recently used value by the key
				curValue = cache.get(lastAccessedKey);
				cache.remove(lastAccessedKey);
			}
		}
		lastAccessedKey = key;
		cache.put(key, value);
		return curValue;
	}

	@Override
	public void removeElement(K key) {
		if(cache.containsKey(key)){
			cache.remove(key);
		}
	}
	
	@Override
	public String toString(){
		return cache.toString();
	}

}
