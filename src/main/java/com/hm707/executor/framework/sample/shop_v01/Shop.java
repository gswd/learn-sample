package com.hm707.executor.framework.sample.shop_v01;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Shop {

	private String name;

	public Shop(String name) {
		this.name = name;
	}

	public double getPrice(String product) {
		return calculatePrice(product);
	}

	public Future<Double> getPriceAsync(String product) {

		CompletableFuture<Double> futurePrice = new CompletableFuture<>();

		new Thread(() -> {
			try {

				double price = calculatePrice(product);
				futurePrice.complete(price);
			} catch (Exception ex) {
				futurePrice.completeExceptionally(ex);
			}
		}).start();

		return futurePrice;
	}

	public Future<Double> getPriceAutoAsync(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}

	private double calculatePrice(String product) {
		delay();
		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public static void delay() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return name;
	}

}
