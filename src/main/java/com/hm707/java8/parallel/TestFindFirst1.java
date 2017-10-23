package com.hm707.java8.parallel;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by LH on 2017/10/23.
 */
public class TestFindFirst1 {
	public static void main(String[] args) {
		IntStream stream = IntStream.iterate(1, x -> x + 1);

		OptionalInt optionalInt = stream.parallel().peek(
			x -> System.out.println(
				"before --> " + Thread.currentThread() + "  [" + Thread.activeCount() + "] " + x)).findFirst();
		System.out.println("-------");
		optionalInt.ifPresent(System.out::println);

	}
}
