package com.hm707.executor.framework.sample.shop_v02;

import java.time.LocalDateTime;

/**
 * Created by LH on 2017/10/19.
 */
public class ExchangeService {
	public static double getRate(Money m1, Money m2) {
		System.out.println(LocalDateTime.now() + "start exchange rate --- ");
		Shop.delay();
		return 0.5;
	}
}
