package com.adminrightsmanager.csvreader;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class DateFunctions.
 */
public class DateFunctions {

	/**
	 * Sets the time to midnight.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date setTimeToMidnight(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Gets the present date.
	 *
	 * @return the present date
	 */
	public static Date getPresentDate() {
		Date mPresentDate =
				DateFunctions.setTimeToMidnight(Calendar.getInstance().getTime());
		return mPresentDate;
	}

	/**
	 * Gets the present date in format DDMMMYYY.
	 *
	 * @return the present date in format DDMMMYYY
	 */
	public static String getPresentDateInFormatDDMMMYYY() {
		LocalDate currentDate = LocalDate.now();
		String month = currentDate.getMonth().toString().substring(0, 3); // FEB
		int date = currentDate.getDayOfMonth(); // 15
		int year = currentDate.getYear(); // 2018
		return date + month + year;

	}

	/**
	 * Gets the present date in formatddmmyyyy.
	 *
	 * @return the present date in formatddmmyyyy
	 */
	public static String getPresentDateInFormatddmmyyyy() {// dd-mm-yyyy eg.12-02-2018
		return DateFunctions.getPresentDate().getDate() + "-"
				+ (DateFunctions.getPresentDate().getMonth() + 1) + "-"
				+ (DateFunctions.getPresentDate().getYear() + 1900);

	}
}