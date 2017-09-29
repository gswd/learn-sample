package com.hm707.java8.parallel;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParallelArray {

	public static double[] imperativeInitilize(int size) {
		double[] values = new double[size];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		return values;
	}

	public static double[] parallelInitialize(int size) {
		double[] values = new double[size];
		Arrays.parallelSetAll(values, i -> i);
		return values;
	}

	public static double[] simpleMovingAverage(double[] values, int n) {
		double[] sums = Arrays.copyOf(values, values.length);
		Arrays.parallelPrefix(sums, Double::sum);

		Arrays.stream(sums).forEach(System.out::println);

		System.out.println("------------");

		int start = n - 1;
		return IntStream.range(start, sums.length).mapToDouble(i -> {
			double prefix = i == start ? 0 : sums[i - n];
			return (sums[i] - prefix) / n;
		}).toArray();
	}

	public static void main(String[] args) {
		double[] values = parallelInitialize(6);
		double[] result = simpleMovingAverage(values, 3);

		Arrays.stream(result).forEach(System.out::println);

		System.out.println("------------");
	}
}
