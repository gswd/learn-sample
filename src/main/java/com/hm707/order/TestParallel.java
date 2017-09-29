package com.hm707.order;

import java.util.stream.IntStream;

public class TestParallel {
	public static void main(String[] args) {

		System.out.println(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
		System.out.println(Runtime.getRuntime().availableProcessors());


		//long count = IntStream.iterate(1, i -> i + 1).limit(100000).parallel().filter(i -> i % 2 != 0).peek(i ->
		//	System.out.println("[filter]  " + Thread.currentThread() + " ->  " + i)
		//).peek(i ->
		//	System.out.println("[count]  " + Thread.currentThread() + " ->  " + i)
		//).count();
		//
		//System.out.println("=================> " + count);
	}
}
