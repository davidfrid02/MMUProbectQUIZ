package com.hit.algorithm;

public class IAlgoCacheTest {

	public static void main(String[] args) {
		IAlgoCache<Integer, Integer> lru = new LRUAlgoCacheImpl<>(3);

		for (int i = 0; i < 3; i++)
			lru.putElement(i, i);
		System.out.println(lru.toString());
		lru.getElement(0);
		System.out.println(lru.toString());
		Integer lruValue = lru.putElement(2, 2);
		System.out.println("removed " + lruValue);
		System.out.println(lru.toString());

		IAlgoCache<Integer, Integer> mru = new MRUAlgoCacheImpl<>(3);

		for (int i = 0; i < 3; i++)
			mru.putElement(i, i);
		System.out.println("Start :" + mru.toString());

		mru.getElement(0);
		System.out.println("Get 0 : " + mru.toString());

		Integer mruValue = mru.putElement(3, 3);
		System.out.print("Inserted 3 And ");
		System.out.println("Removed " + mruValue + " " + mru.toString());

		mru.getElement(2);
		System.out.println("Get 2 : " + mru.toString());

		mruValue = mru.putElement(4, 4);
		System.out.print("Inserted 4 And ");
		System.out.println("Removed " + mruValue + " " + mru.toString());

		mruValue = mru.putElement(5, 5);
		System.out.println("Removed " + mruValue + " " + mru.toString());
		IAlgoCache<Integer, Integer> rr = new RandomReplacementAlgoCacheImpl<>(3);
		for (int i = 0; i < 3; i++)
			rr.putElement(i, i);
		System.out.println(rr.toString());

		Integer rrValue = rr.putElement(3, 3);
		System.out.println("removed " + rrValue);
		System.out.println(rr.toString());

		rrValue = rr.putElement(7, 7);
		System.out.println("removed " + rrValue);
		System.out.println(rr.toString());

		rrValue = rr.putElement(5, 5);
		System.out.println("removed " + rrValue);
		System.out.println(rr.toString());

		rrValue = rr.putElement(6, 6);
		System.out.println("removed " + rrValue);
		System.out.println(rr.toString());

		rrValue = rr.putElement(8, 8);
		System.out.println("removed " + rrValue);
		System.out.println(rr.toString());
	}

}
