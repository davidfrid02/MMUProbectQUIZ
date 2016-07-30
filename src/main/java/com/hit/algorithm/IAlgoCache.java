// Interface for defining the API for accessing a cache algorithm
// A cache algorithm will hold a container that is similar to the computer's RAM
// when the ram is "full" and a paging is needed this algorithm will recommend which page to remove from ram
// and replace with a page from hard disk.

package com.hit.algorithm;

public interface IAlgoCache<K,V> {
	
	V getElement(K key);
	V putElement(K key, V value);
	void removeElement(K key);
}
