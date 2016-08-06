package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

public class MRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V> {

	private Map<K, V> cache;
	private int capacity;
	
	public MRUAlgoCacheImpl(int capacity){
		this.capacity = capacity;
		// setting the cache with a access-order instead of insert-order
		// in this way - every time using a put or a get the element will move forward to the top of the list
		// meaning it will be the most recently used element
		// used the default load factor
		cache = new LinkedHashMap<K,V>(capacity, 0.75F, true);
	}
	@Override
	public V getElement(K key) {
		return cache.get(key); // returns null if doesn't exist
	}

	@Override
	public V putElement(K key, V value) {
		V curValue = null;
		if(!cache.containsKey(key)){
			// if the cache contains this key then just putting it back (perhaps with a new value)
			// will make it the most recently used key (built in container ability)
			if(capacity == cache.size()){
				Iterator<K> iterator = this.cache.keySet().iterator();
				K mruKey = iterator.next();
				// gets the most recently used key
				while (iterator.hasNext()){
					mruKey = iterator.next();
				}
				curValue = cache.get(mruKey);
				cache.remove(mruKey);
			}
		}
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
