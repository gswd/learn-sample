package com.hm707.executor.framework.sample.shop_v02;

/**
 * Created by LH on 2017/10/18.
 */
public class Quote {
	private final String shopName;
	private final double price;
	private final Discount.Code discountCode;

	public Quote(String shopName, double price, Discount.Code discountCode) {
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}

	public static Quote parse(String s) {
		String[] split = s.split(":");
		String shopName = split[0];
		double price = Double.parseDouble(split[1]);
		Discount.Code discountCode = Discount.Code.valueOf(split[2]);
		return new Quote(shopName, price, discountCode);
	}

	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Quote{");
		sb.append("shopName='").append(shopName).append('\'');
		sb.append(", price=").append(price);
		sb.append(", discountCode=").append(discountCode);
		sb.append('}');
		return sb.toString();
	}
}
