package com.hit.algorithm;

public class AlgoCacheFactory {
	
	private AlgoCacheFactory(){}
	
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
