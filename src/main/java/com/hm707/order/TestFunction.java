package com.hm707.order;

import java.util.function.Function;

public class TestFunction {

	public static void main(String[] args) {
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		int result = h.apply(1);
		System.out.println(result);


		Function<Integer, Integer> f1 = x -> x + 1;
		Function<Integer, Integer> g1 = x -> x * 2;
		Function<Integer, Integer> h1 = f1.compose(g1);
		int result1 = h1.apply(1);
		System.out.println(result1);
	}
}
