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
		System.out.println(LocalDateTime.now() + "\tstart discount --- [" + quote + "]");
		String res = quote.getShopName() + " discount price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
		System.out.println(LocalDateTime.now() + "\t end discount --- [" + quote + "]");
		return res;
	}

	private static String apply(double price, Code discountCode) {

		Shop.delay();

		return String.format("%.2f", price * (100 - discountCode.percentage) / 100);
	}

}