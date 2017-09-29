package com.hm707.executor.framework.sample.shop_v01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client {
	public static void main(String[] args) {
		Shop shop = new Shop("BestShop");

		long start = System.nanoTime();

		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		//Future<Double> futurePrice = shop.getPriceAutoAsync("my favorite product");

		long invocationTime = ((System.nanoTime() - start) / 1_000_000);

		System.out.println("Invocation returned after " + invocationTime + " msecs");

		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (InterruptedException | ExecutionException e) {

			throw new RuntimeException(e);
		}

		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");

	}
}
