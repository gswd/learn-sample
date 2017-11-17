package com.hm707.java8.delay.execute;

/**
 * Created by LH on 2017/10/25.
 */
public class TestDelayList {
	public static void main(String[] args) {
		//MyList<Integer> myList = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

		LazyList<Integer> numbers = from(2);

		//int two = numbers.head();
		int three = numbers.tail().head();
		int four = numbers.tail().tail().head();
		System.out.println( three + " " + four);
	}

	public static LazyList<Integer> from(int n) {
		return new LazyList<>(n, () -> from(n + 1));
	}
}
