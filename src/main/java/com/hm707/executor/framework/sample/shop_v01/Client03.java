package com.hm707.executor.framework.sample.shop_v01;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Client03 {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll"));

	public static List<String> findPrices(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream().map(
			shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product)))
			.collect(Collectors.toList());

		return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
