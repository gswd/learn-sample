package com.hm707.executor.framework.sample.shop_v02;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Client03 {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll"), new Shop("testShop"));

	/**
	 *  当任务之间存在依赖关系时，使用thenCompose方法将任务编排到上个任务结束后运行，
	 *  如果任务之间不存在依赖关系，可以同时异步操作，则可以使用thenCombine方法，
	 *
	 *  thenCombine方法接收两个参数，第一个参数指定了异步操作方式，
	 *  第二个参数指定如何将之前异步操作结果和当前异步操作结果合并。
	 *
	 */
	public static List<String> findPrices(String product) {
		Executor executor = Executors.newCachedThreadPool();
		//future.thenCompose 方法将后面的任务编入到上一个任务完成后执行，并且是异步的方式来执行
		List<CompletableFuture<String>> priceFuture = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor ))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(
				quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor )))
			.map(future -> future.thenCombine(
				CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD), executor ),
				(quote, rate) -> quote + " -- exchange rate is [" + rate + "]"))

			.map(future -> future.thenCombine(
				CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD), executor ),
				(quote, rate) -> quote + " ++ [second] exchange rate [" + rate + "]"))
			.collect(Collectors.toList());

		List<String> resultList = priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());

		ExecutorService executorService = (ExecutorService)executor;
		executorService.shutdown();

		return resultList;
	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
