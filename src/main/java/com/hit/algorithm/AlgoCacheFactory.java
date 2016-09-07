package com.hit.algorithm;

public class AlgoCacheFactory {
	
	private AlgoCacheFactory(){}
	
	// used to return the requested implementation of IAlgoCache
	public static IAlgoCache<Long,Long> getAlgo(String algo, int capacity) {
		switch (algo.toUpperCase()) {
		case "LRU":
			return new LRUAlgoCacheImpl<>(capacity);
		case "MRU":
			return new MRUAlgoCacheImpl<>(capacity);
		case "RR":
			return new RandomReplacementAlgoCacheImpl<>(capacity);
		}
		
		return null;
	}
}
