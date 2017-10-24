package com.hm707.time.used;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 *  ZoneId 类 是不可变类。
 *
 *  ZoneId是java.util.TimeZone的替代。
 *
 */
public class UsedZoneDateTime {

	public static void main(String[] args) {
		//printAllZoneRules();

		//testZoneId();
		//testZonedDateTime();

		otherCalendarSystem();
	}

	private static void printAllZoneRules() {
		Set<String> zoneIdSet = ZoneId.getAvailableZoneIds();

		zoneIdSet.forEach(System.out::println);
	}

	/**
	 * 每个特定的 ZoneId 对象都由一个地区ID标识.
	 * 地区ID格式为"{区域}/{城市}".
	 */
	private static void testZoneId() {

		ZoneId romeZone = ZoneId.of("Asia/Hong_Kong");
		ZoneId romeZone1 = ZoneId.of("Asia/Harbin");
		System.out.println(romeZone.getRules());
		System.out.println(romeZone1.getRules());
		System.out.println(ZoneId.of("Asia/Shanghai").getRules());

		//使用toZoneId 可以将一个老的时区对象转换为ZoneId
		ZoneId zoneId = TimeZone.getDefault().toZoneId();

		System.out.println(zoneId.getRules());

	}

	/**
	 * 将一个 ZoneId 对象与 LocalDate 、 LocalDateTime 或者是 Instant对象整合起来，
	 * 构造为一个 ZonedDateTime 实例，它代表了相对于指定时区的时间点。
	 *
	 *  ZoneOffset 类，它是 ZoneId 的一个子类，表示的是当前时间和伦敦格林尼治子午线时间的差异，
	 *  ZoneOffset 没有考虑冬令时和夏令时，所以一般不推荐使用。
	 */
	private static void testZonedDateTime() {
		ZoneId romeZone = ZoneId.of("Asia/Tokyo");

		LocalDate date = LocalDate.of(2017, Month.MARCH, 18);
		ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
		System.out.println(zdt1);

		LocalDateTime dateTime = LocalDateTime.of(2017, Month.MARCH, 18, 13, 45);
		ZonedDateTime zdt2 = dateTime.atZone(romeZone);
		System.out.println(zdt2);

		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);
		System.out.println(zdt3);

		System.out.println("------- LocalDateTime => Instant -------");

		//2017-10-24 15:20
		LocalDateTime dateTime1 = LocalDateTime.of(2017, Month.OCTOBER, 24, 15, 20);
		ZonedDateTime zdt4 = dateTime1.atZone(romeZone);
		System.out.println(zdt4);
		Instant instantFromDateTime = zdt4.toInstant();
		System.out.println(instantFromDateTime);

		//日本时间早于伦敦9小时
		System.out.println("--->" + dateTime1.toInstant(ZoneOffset.of("+09:00")));

		Instant instant1 = Instant.now();
		LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant1, romeZone);
		System.out.println(timeFromInstant);

	}

	/**
	 * ISO-8601日历系统是世界文明日历系统的事实标准。但是，Java 8中另外还提供了4种其他的日历系统
	 * 这些日历系统中的每一个都有一个对应的日志类，分别是 ThaiBuddhistDate 、 MinguoDate 、 JapaneseDate 以及 HijrahDate
	 */
	private static void otherCalendarSystem() {
		LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
		JapaneseDate japaneseDate = JapaneseDate.from(date);
		System.out.println(japaneseDate);

		Chronology japaneseChronology = Chronology.ofLocale(Locale.CHINA);
		ChronoLocalDate now = japaneseChronology.dateNow();
		System.out.println(now);

		LocalDate.now();
	}

}


