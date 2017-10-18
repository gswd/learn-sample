package com.hm707.executor.framework.sample.shop_v02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll"), new Shop("testShop"));

	public static List<String> findPrices(String product) {
		return shops.stream()
			.map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount)
			.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
