package drone.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

class UtilsServiceTest {

	
	public Date setTime(int hours, int minutes, int seconds) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		return cal.getTime();
	}
	@Test
	void testSetTime() {
		
		UtilService utilService = new UtilService();
		Date result = utilService.setTime(7, 30, 23);
		
		Calendar expected = Calendar.getInstance();
		expected.setTimeZone(TimeZone.getDefault());
		expected.set(Calendar.HOUR_OF_DAY, 7);
		expected.set(Calendar.MINUTE, 30);
		expected.set(Calendar.SECOND, 0);
		assertEquals(expected.getTimeInMillis(), result.getTime(), 100);

	}
	
	@Test
	void testSetTime_negative() {
		
		UtilService utilService = new UtilService();
		Date result = utilService.setTime(7, 30, 23);
		
		Calendar unexpected = Calendar.getInstance();
		unexpected.setTimeZone(TimeZone.getDefault());
		unexpected.set(Calendar.HOUR_OF_DAY, 6);
		unexpected.set(Calendar.MINUTE, 30);
		unexpected.set(Calendar.SECOND, 0);
		assertNotEquals(unexpected, result);

	}
	
	@Test
	void testAddTime() {
		
		UtilService utilService = new UtilService();
		Calendar current = Calendar.getInstance();
		current.setTimeZone(TimeZone.getDefault());
		current.set(Calendar.HOUR_OF_DAY, 6);
		current.set(Calendar.MINUTE, 30);
		current.set(Calendar.SECOND, 0);
		
		Calendar timePassed = Calendar.getInstance();
		timePassed.setTimeZone(TimeZone.getDefault());
		timePassed.set(Calendar.HOUR_OF_DAY, 1);
		timePassed.set(Calendar.MINUTE, 30);
		timePassed.set(Calendar.SECOND, 0);
		Date result = utilService.addTime(current.getTime(), timePassed.getTime());
		
		Calendar expected = Calendar.getInstance();
		expected.setTimeZone(TimeZone.getDefault());
		expected.set(Calendar.HOUR_OF_DAY, 8);
		expected.set(Calendar.MINUTE, 0);
		expected.set(Calendar.SECOND, 0);
		assertEquals(expected.getTimeInMillis(), result.getTime(), 100);

	}
	
	@Test
	void testAddTime_negative() {
		
		UtilService utilService = new UtilService();
		Calendar current = Calendar.getInstance();
		current.setTimeZone(TimeZone.getDefault());
		current.set(Calendar.HOUR_OF_DAY, 6);
		current.set(Calendar.MINUTE, 30);
		current.set(Calendar.SECOND, 0);
		
		Calendar timePassed = Calendar.getInstance();
		timePassed.setTimeZone(TimeZone.getDefault());
		timePassed.set(Calendar.HOUR_OF_DAY, 1);
		timePassed.set(Calendar.MINUTE, 30);
		timePassed.set(Calendar.SECOND, 0);
		Date result = utilService.addTime(current.getTime(), timePassed.getTime());
		
		Calendar unexpected = Calendar.getInstance();
		unexpected.setTimeZone(TimeZone.getDefault());
		unexpected.set(Calendar.HOUR_OF_DAY, 7);
		unexpected.set(Calendar.MINUTE, 30);
		unexpected.set(Calendar.SECOND, 0);
		assertNotEquals(unexpected.getTimeInMillis(), result.getTime());

	}

}
