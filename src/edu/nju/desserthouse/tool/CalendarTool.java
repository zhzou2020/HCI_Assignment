package edu.nju.desserthouse.tool;

import java.util.Calendar;
import java.util.Date;

public class CalendarTool {
	public static Date getNextWeekFirstDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day != Calendar.SUNDAY)
			cal.add(Calendar.WEEK_OF_MONTH, 1);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	public static Date getNextWeekLastDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day != Calendar.SUNDAY)
			cal.add(Calendar.WEEK_OF_MONTH, 2);
		else
			cal.add(Calendar.WEEK_OF_MONTH, 1);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return cal.getTime();
	}
}
