package com.hm707.time.used;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class UsedSample {
	public static void main(String[] args) {
		//testLocalDate();
		//testLocalTime();

		//testInstant();
		testDuration();
	}

	private static void testLocalDate() {
		LocalDate date = LocalDate.of(2014, 3, 18);

		int year = date.getYear();
		System.out.println(String.format("year is [%d]", year));

		Month month = date.getMonth();
		System.out.println(String.format("month is [%s]", month.getValue()));

		int day = date.getDayOfMonth();
		System.out.println(String.format("day of month [%d]", day));

		DayOfWeek dow = date.getDayOfWeek();
		System.out.println(String.format("day of week [%s]", dow.getValue()));

		int len = date.lengthOfMonth();
		System.out.println(String.format("length of month [%d]", len));

		boolean leap = date.isLeapYear();
		System.out.println(String.format("is leap year [%b]", leap));

		System.out.println("------------");

		// get方法接收一个TemporalField参数，可以获取不同的字段信息。
		// TemporalField是一个接口它定义了如何访问temporal对象某个字段的值。
		// ChronoField是TemporalField的子类

		int year1 = date.get(ChronoField.YEAR);
		System.out.println(String.format("year is [%d]", year1));

		int month1 = date.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(String.format("month of year [%s]", month1));

		int day1 = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println(String.format("day of month is [%d]", day1));

		// ---------------

		LocalDate date1 = LocalDate.parse("2017-10-19");
		System.out.println(date1);

	}

	private static void testLocalTime() {
		LocalTime localTime = LocalTime.of(13, 45, 20);

		int hour = localTime.getHour();
		System.out.println(String.format("hour : [%d]", hour));

		int minute = localTime.getMinute();
		System.out.println(String.format("minute : [%d]", minute));

		int second = localTime.getSecond();
		System.out.println(String.format("second : [%d]", second));

		System.out.println("--------------------");
		System.out.println("hour of day : " + localTime.get(ChronoField.HOUR_OF_DAY));

		System.out.println("---------------");
		//也可以传递一个DateTimeFormatter来指定解析的格式
		System.out.println("parse local time : " + LocalTime.parse("13:45:20"));

	}

	/**
	 * 日期和时间的组合
	 */
	private static void composeDateAndTime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();

		LocalDateTime dt1 = LocalDateTime.of(2017, Month.OCTOBER, 12, 13, 54, 55);
		LocalDateTime dt2 = LocalDateTime.of(date, time);


		LocalDateTime dt3 = date.atTime(time);
		LocalDateTime dt4 = date.atTime(13, 54, 55);

		LocalDateTime dt5 = time.atDate(date);

	}

	/**
	 * Instant的设计初衷是为了便于机器使用，内部封装了秒和纳秒的数字，所以它无法处理我们容易理解的时间单位。
	 *
	 * 但可以通过Duration或者Period来使用Instant
	 */
	private static void testInstant() {
		Instant instant = Instant.ofEpochSecond(10, 500_000_000);
		System.out.println(instant);

		/**
		 * java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: DayOfMonth
		 */
		// int day = Instant.now().get(ChronoField.DAY_OF_MONTH);

		System.out.println(Instant.now().isSupported(ChronoField.DAY_OF_MONTH));

	}


	private static void testDuration() {
		//----------使用工厂方法创建Duration

		Duration threeMinutes = Duration.ofMinutes(3);
		System.out.println("threeMinutes : " + threeMinutes.getSeconds()/60);

		Duration fourMinutes = Duration.of(4, ChronoUnit.MINUTES);
		System.out.println("fourMinutes : " + fourMinutes.getSeconds()/60);

		//------------
		System.out.println("-=-=-=-=-=-=-=-=-=-=-");
		LocalTime time1 = LocalTime.of(9, 0);
		LocalTime time2 = LocalTime.of(10, 0);

		Duration duration1 = Duration.between(time1, time2);
		System.out.println(duration1.getSeconds() + "秒");

		LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.now().minusDays(1), time1);
		LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.now(), time2);

		Duration duration2 = Duration.between(dateTime1, dateTime2);
		System.out.println(duration2.getSeconds()/60/60 + "小时");

		Instant instant1 = Instant.ofEpochSecond(1000);
		Instant instant2 = Instant.ofEpochSecond(2000);

		Duration duration3 = Duration.between(instant1, instant2);
		System.out.println(duration3.getSeconds() + "秒");

		/**
		 * java.time.DateTimeException
		 * LocalXxx和Instant是为不同目的设计的，他们不能混用
 		 */
		//Duration.between(instant1, time1);

		/**
		 * java.time.temporal.UnsupportedTemporalTypeException
		 * Duration内部封装了秒和毫秒数值，是使用秒和纳秒来衡量时间长短，所以不能表示LocalDate之间的时间长度
		 */
		//Duration.between(LocalDate.now(), LocalDate.now());

	}

	/**
	 * 需要以年、月或者日的方式对多个时间单位建模，可以使用 Period 类
	 */
	private static void testPeriod() {

		Period tenDays = Period.ofDays(10);
		Period threeWeeks = Period.ofWeeks(3);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

		Period tenDays1 = Period.between(LocalDate.of(2014, 3, 8),
			LocalDate.of(2014, 3, 18));
	}
}
