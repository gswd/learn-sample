package com.hm707.java8.delay.execute;

public interface MyList<T> {
	T head();
	MyList<T> tail();

	default boolean isEmpty() {
		return true;
	}
}
