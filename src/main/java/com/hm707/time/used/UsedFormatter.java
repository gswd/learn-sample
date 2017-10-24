package com.hm707.time.used;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class UsedFormatter {

	/**
	 * 与java.util.DateFormat相比，所有的DateTimeFormatter实例都是线程安全的，
	 * 所以，能够以单例模式创建格式器实例。
	 * @param args
	 */
	public static void main(String[] args) {

		LocalDate date = LocalDate.of(2017, 10, 24);

		String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

		System.out.println(s1);
		System.out.println(s2);

		System.out.println("--------------------");

		LocalDate date1 = LocalDate.parse("20171024", DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate date2 = LocalDate.parse("2017-10-24", DateTimeFormatter.ISO_LOCAL_DATE);

		System.out.println(date1);
		System.out.println(date2);

		System.out.println("----------------");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate date3 = LocalDate.of(2014, 3, 18);
		String formattedDate = date3.format(formatter);
		System.out.println(formattedDate);

		LocalDate date4 = LocalDate.parse(formattedDate, formatter);
		System.out.println(date4);

		//创建一个本地化的DateTimeFormatter
		DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
		LocalDate date5 = LocalDate.of(2014, 3, 18);
		String formattedDate5 = date5.format(italianFormatter); // 18. marzo 2014
		System.out.println(formattedDate5);

		LocalDate date6 = LocalDate.parse(formattedDate5, italianFormatter);
		System.out.println(date6);


		//使用编程的方式生成格式化器
		DateTimeFormatter italianFormatter1 = new DateTimeFormatterBuilder()
			.appendText(ChronoField.DAY_OF_MONTH)
			.appendLiteral(". ")
			.appendText(ChronoField.MONTH_OF_YEAR)
			.appendLiteral(" ")
			.appendText(ChronoField.YEAR)
			.parseCaseInsensitive()
			.toFormatter(Locale.ITALIAN);

		String formattedDate6 = date5.format(italianFormatter1); // 18. marzo 2014
		System.out.println(formattedDate6);
	}
}
