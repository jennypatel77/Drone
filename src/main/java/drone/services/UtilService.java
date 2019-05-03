package drone.services;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UtilService {

	public Date setTime(int hours, int minutes, int seconds) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	public Date addTime(Date currentTime, Date timePassed) {

		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		Calendar passedCal = Calendar.getInstance();
		passedCal.setTime(timePassed);
		currentCal.setTimeZone(TimeZone.getDefault());
		currentCal.set(Calendar.HOUR_OF_DAY, currentCal.get(Calendar.HOUR) + passedCal.get(Calendar.HOUR));
		currentCal.set(Calendar.MINUTE, currentCal.get(Calendar.MINUTE) + passedCal.get(Calendar.MINUTE));
		currentCal.set(Calendar.SECOND, currentCal.get(Calendar.SECOND) + passedCal.get(Calendar.SECOND));
		return currentCal.getTime();
	}

	public static double calculatePercentage(double obtained, double total) {
		return obtained * 100 / total;
	}

}
