package com.hm707.executor.framework.sample.shop_v02;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Shop {

	private String name;

	public Shop(String name) {
		this.name = name;
	}

	public String getPrice(String product) {
		System.out.println(LocalDateTime.now() + "\tstart get price --- " + "[" + name + "]");
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];

		return String.format("%s:%.2f:%s", name, price, code);
	}

	private double calculatePrice(String product) {
		delay();
		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public static void delay() {
		int delay = 500 + new Random().nextInt(2000);
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return name;
	}

}
