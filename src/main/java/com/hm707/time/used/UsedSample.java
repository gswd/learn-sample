package com.hm707.time.used;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class UsedSample {
	public static void main(String[] args) {
		//testLocalDate();
		testLocalTime();

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
}
