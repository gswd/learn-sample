package com.hm707.java8.delay.execute;

public class Empty<T> implements MyList<T> {

	@Override
	public T head() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MyList<T> tail() {
		throw  new UnsupportedOperationException();
	}
}
