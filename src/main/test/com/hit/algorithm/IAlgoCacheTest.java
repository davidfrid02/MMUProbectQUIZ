package com.hit.algorithm;


public class IAlgoCacheTest {

	public static void main(String[] args) {
//		IAlgoCache<Integer,Integer> lru = new LRUAlgoCacheImpl<>(3);
//		
//		for(int i =0;i<3;i++)
//			lru.putElement(i, i);
//		System.out.println(lru.toString());
//		lru.getElement(0);
//		System.out.println(lru.toString());
//		Integer lruvalue = lru.putElement(3, 3);
//		System.out.println("removed " + lruvalue);
//		System.out.println(lru.toString());
//		
		IAlgoCache<Integer,Integer> rr = new RandomReplacementAlgoCacheImpl<>(3);
		for(int i=0;i<3;i++)
			rr.putElement(i, i);
		System.out.println(rr.toString());
		
		Integer rrvalue = rr.putElement(3, 3);
		System.out.println("removed " + rrvalue);
		System.out.println(rr.toString());
		
		rrvalue = rr.putElement(4, 4);
		System.out.println("removed " + rrvalue);
		System.out.println(rr.toString());
		
		rrvalue = rr.putElement(5, 5);
		System.out.println("removed " + rrvalue);
		System.out.println(rr.toString());
		
		rrvalue = rr.putElement(6, 6);
		System.out.println("removed " + rrvalue);
		System.out.println(rr.toString());
		
		rrvalue = rr.putElement(7, 7);
		System.out.println("removed " + rrvalue);
		System.out.println(rr.toString());
	}

}
