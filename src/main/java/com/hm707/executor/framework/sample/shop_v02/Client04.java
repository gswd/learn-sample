package com.hm707.executor.framework.sample.shop_v02;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Client04 {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll"), new Shop("testShop"));
	static ExecutorService executor = Executors.newCachedThreadPool();

	/**
	 * thenAccept 在每个CompletableFuture 上注册一个操作，该操作会在 CompletableFuture 完成执行后使用它的返回值。
	 * thenAccept 接收CompletableFuture执行完毕后的返回值作为参数。
	 *
	 *  thenAcceptAsync 异步版本的方法会对处理结果的消费者进行调度，
	 *  从线程池中选择一个新的线程继续执行，不再由同一个线程完成 CompletableFuture 的所有任务。
	 *
	 *  希望避免在等待线程上浪费时间，尽快响应 CompletableFuture 的 completion 事件，所以这里没有采用异步版本。
	 *
	 *  因为thenAccept 定义类如何处理CompletableFuture返回的结果，所以返回的是CompletableFuture<Void>类型
	 */
	public static Stream<CompletableFuture<String>> findPricesStream(String product) {
		System.out.println(">>>>>>>>>> " + LocalDateTime.now());

		Stream<CompletableFuture<String>> completableFutureStream = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(
				quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

		return completableFutureStream;

	}

	public static void main(String[] args) {
		long start = System.nanoTime();

		CompletableFuture[] futures = findPricesStream("myPhone27S")
			.map(future -> future.thenAccept(s -> System.out.println("<END> --> " + s)))
			//把所有CompletableFuture<Void>对象放到一个数组中
			.toArray(size -> new CompletableFuture[size]);

		//allOf 工厂方法接收一个CompletableFuture数组，数组中的所有CompletableFuture执行完毕后，
		//返回一个CompletableFuture<Void>对象。
		//如果需要等待最初Stream中的所有CompletableFuture对象执行完毕，可以对allOf方法返回的CompletableFuture执行join。

		//anyOf工厂方法，任意一个任务执行完毕就不再等待，改方法接收一个CompletableFuture对象数组，
		//返回由第一个执行完毕的CompletableFuture对象的返回值构成的CompletableFuture<Object>
		CompletableFuture.allOf(futures).join();

		executor.shutdown();
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}
}
