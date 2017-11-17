package com.hm707.java8.delay.execute;

import java.util.function.Supplier;

/**
 * Created by LH on 2017/10/25.
 */
public class LazyList<T> implements MyList<T> {

	final T head;
	final Supplier<MyList<T>> tail;

	public LazyList(T head, Supplier<MyList<T>> tail) {
		this.head = head;
		this.tail = tail;
	}

	@Override
	public T head() {
		return head;
	}

	@Override
	public MyList<T> tail() {
		return tail.get();
	}

	public boolean isEmpty() {
		return false;
	}
}
