package com.hm707.executor.framework.sample.shop_v02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Client02 {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll"), new Shop("testShop"));

	/**
	 *  thenCompose 方法也提供了一个以 Async 后缀结尾的版本 thenComposeAsync 。
	 *  通常而言，名称中不带 Async	 的方法与它的前一个任务在同一个线程中运行；
	 *  而名称以 Async 结尾的方法会将后续的任务提交到一个线程池，所以每个任务是由不同的线程处理的。
	 *  就这个例子而言，第二个 CompletableFuture 对象的结果取决于第一个 CompletableFuture ，
	 *  所以无论你使用哪个版本的方法来处理 CompletableFuture 对象，对于最终的结果，或者大致的时间而言都没有多少差别。
	 *
	 *  选择 thenCompose 方法的原因是因为它更高效一些，因为少了很多线程切换的开销。
	 *
	 */
	public static List<String> findPrices(String product) {

		//future.thenCompose 方法将后面的任务编入到上一个任务完成后执行，并且是异步的方式来执行
		List<CompletableFuture<String>> priceFuture = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
			.collect(Collectors.toList());

		return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
