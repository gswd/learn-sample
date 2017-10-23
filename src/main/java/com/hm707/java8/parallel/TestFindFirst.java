package com.hm707.java8.parallel;

import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * findFirst 在并行流下，会等待所有并行线程中找到第一个满足条件的，然后将所有线程的结果计算，找到流中出现的第一个满足条件的数据.
 *
 * 但通过日志上观察，又不会所有线程都执行。
 */
public class TestFindFirst {
	public static void main(String[] args) throws InterruptedException {

		Runnable runnable = () -> {

			String f = Thread.currentThread().getName();
			System.out.println("-------------> start " + f);
			IntStream stream = IntStream.iterate(1, x -> x + 1);

			OptionalInt optionalInt = stream.parallel().peek(
				x -> System.out.println(
					"before --> [" + f + "] " + Thread.currentThread().getName() + "  [" + Thread.activeCount() + "] "
						+ x)).filter(
				x -> {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return x % 3 == 0;
				}).peek(
				x -> System.out.println(
					"filter **> [" + f + "] " + Thread.currentThread().getName() + "  [" + Thread.activeCount() + "] "
						+ x)).findFirst();
			System.out.println("-------");
			optionalInt.ifPresent(System.out::println);

		};

		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		long startTime = System.nanoTime();
		t1.start();
		t2.start();

		t1.join();
		t2.join();
		long d = System.nanoTime() - startTime;
		System.out.println("execute " + d / 1_000_000 + "s");
	}
}
