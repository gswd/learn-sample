package com.hm707.executor.framework.sample.shop_v02;

import java.time.LocalDateTime;

public class Discount {
	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}
	}

	public static String applyDiscount(Quote quote) {
		System.out.println(LocalDateTime.now() + "start discount --- ");
		return quote.getShopName() + "price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}

	private static String apply(double price, Code discountCode) {

		Shop.delay();

		return String.format("%.2f", price * (100 - discountCode.percentage) /100);
	}

}