package com.hm707.java8.stream.forker;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by LH on 2017/11/20.
 */
public class TestMain {
	public static void main(String[] args) {
		Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

		StreamForker<Integer> forker = new StreamForker<>(stream);
		Results results = forker.fork("sum", s -> s.mapToInt(t -> t).sum())
			.getResults();
			//.fork("avg", s -> s.mapToInt(t -> t).average().getAsDouble()).getResults();

		int sum = results.get("sum");
		double avg = results.get("avg");

		System.out.println(sum);
		System.out.println("--------------");
		System.out.println(avg);
	}
}
