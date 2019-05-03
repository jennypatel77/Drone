package drone.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import drone.entities.Coordinate;

class DroneServiceTest {

	@Test
	void testCalculateTimeTaken_oneWay() {
		DroneService droneService = new DroneService();
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		Coordinate coordinate = new Coordinate(5, 11, "S", "E");
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 12);
		cal.set(Calendar.SECOND, 5);
		assertEquals(cal.getTimeInMillis(), droneService.calculateTimeTaken(coordinate, 1).getTime(), 100);

	}

	@Test
	void testCalculateTimeTaken_roundTrip() {
		DroneService droneService = new DroneService();
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
		Coordinate coordinate = new Coordinate(5, 11, "S", "E");
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 24);
		cal.set(Calendar.SECOND, 10);

		assertEquals(cal.getTimeInMillis(), droneService.calculateTimeTaken(coordinate, 2).getTime(), 100);

	}

}
