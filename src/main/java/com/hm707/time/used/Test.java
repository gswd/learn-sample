package com.hm707.time.used;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by LH on 2017/10/25.
 */
public class Test {
	public static void main(String[] args) {
		//Instant startInstant = Instant.ofEpochMilli(24 * 60 * 60 * 1000);
		Instant startInstant = Instant.ofEpochMilli(1508907720000L);
		ZonedDateTime koreaStartZonedDateTime = startInstant.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println(koreaStartZonedDateTime);

		Date startDate = Date.from(koreaStartZonedDateTime.toInstant());

		System.out.println(startDate);


	}
}
