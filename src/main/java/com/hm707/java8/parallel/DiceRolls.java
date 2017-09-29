package com.hm707.java8.parallel;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceRolls {

	private int twoDiceThrows() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int firstThrow = random.nextInt(1, 7);
		int secondThrow = random.nextInt(1, 7);
		return firstThrow + secondThrow;
	}

	public Map<Integer, Double> parallelDiceRolls(){
		int n = 100000000;
		double fraction = 1.0/n;

		return IntStream.range(0, n).parallel()
					.mapToObj(v -> twoDiceThrows()).collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(x -> fraction)));

	}

	public static void main(String[] args) {
		DiceRolls diceRolls = new DiceRolls();
		Map<Integer, Double> resultMap = diceRolls.parallelDiceRolls();

		resultMap.forEach((key, value) -> {
			System.out.println(key + " : " + value);

		});
	}
}
