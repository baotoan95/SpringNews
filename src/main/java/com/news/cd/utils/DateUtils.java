package com.news.cd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 14, 2016
 */

public class DateUtils {
	public static final SimpleDateFormat FORMAT_FOR_DB = new SimpleDateFormat("yyyy-MM-dd");

	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat formator = new SimpleDateFormat(pattern);
		return formator.format(date);
	}

	public static int currentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	public static int currentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int currentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	public static Date getDate(int day, int month, int year) {
		if (day > 0 || day <= 31 || month > 0 || month <= 12 || year > 0) {
			if (isLeapYear(year) && day > 29) {
				day = 29;
			}
			if (!isLeapYear(year) && day > 28) {
				day = 28;
			}

			if (day <= 0) {
				day = 1;
				month--;
			}
			if (month <= 0) {
				month = 12;
				year--;
			}
			if (year <= 0) {
				year = 1;
			}
		}
		try {
			return FORMAT_FOR_DB.parse(year + "-" + month + "-" + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}
	
	public static int getDayOfMonth(int month, int year) {
		switch (month) {
		case 2:
			if(isLeapYear(year)) {
				return 28;
			}
			return 29;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;			
		default:
			return 31;
		}
	}
	
}
