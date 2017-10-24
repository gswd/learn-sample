package com.hm707.time.used;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class ChangeTemporal {
	public static void main(String[] args) {
		//testChange();
		//testTemporalAdjuster();
		testCustomTemporalAdjuster();
	}

	/**
	 * with 和 withXxx 可以修改某个时间，这些方法被定义在Temporal接口中.
	 *
	 * 也可以以声明的方式操纵 LocalDate 对象
	 */
	private static void testChange() {
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		System.out.println(date1);

		LocalDate date2 = date1.withYear(2017);
		System.out.println(date2);

		LocalDate date3 = date2.withDayOfMonth(11);
		System.out.println(date3);

		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
		System.out.println(date4);

		LocalDate date5 = date4.with(LocalDate.now());
		System.out.println(date5);

		LocalDate date6 = date5.plusWeeks(2);
		System.out.println(date6);

		LocalDate date7 = date6.minusDays(14);
		System.out.println(date7);

		LocalDate date8 = date7.plus(1, ChronoUnit.MONTHS);
		System.out.println(date8);

	}

	private static void testTemporalAdjuster() {
		LocalDate date1 = LocalDate.of(2017, 10, 24);
		LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(date2);

		//如果今天已经符合，则返回当天
		LocalDate date3 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
		System.out.println(date3);

		//返回今天之后的下一个满足要求的日期
		LocalDate date4 = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		System.out.println(date4);

	}

	/**
	 * 计算下一个工作日
	 *
	 * TemporalAdjuster是一个函数式接口，接收一个temporal做某些调整后返回一个新的
	 */
	private static void testCustomTemporalAdjuster() {
		LocalDate date1 = LocalDate.of(2017, 10, 28);

		LocalDate date2 = date1.with(temporal -> {
			//确认当前时间是星期几
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			} else if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}

			return temporal.plus(dayToAdd, ChronoUnit.DAYS);

		});
		System.out.println(date2);
	}

	private static void testCustomTemporalAdjuster2() {

		TemporalAdjuster adjuster = temporal -> {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			} else if (dow == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}

			return temporal.plus(dayToAdd, ChronoUnit.DAYS);

		};

		// 如果使用Lambda表达式定义TemporalAdjuster，推荐使用TemporalAdjusters的静态工厂方法ofDateAdjuster
		TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
			temporal -> {
				DayOfWeek dow =
					DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
				int dayToAdd = 1;
				if (dow == DayOfWeek.FRIDAY)
					dayToAdd = 3;
				if (dow == DayOfWeek.SATURDAY)
					dayToAdd = 2;
				return temporal.plus(dayToAdd, ChronoUnit.DAYS);
			});
	}

}
