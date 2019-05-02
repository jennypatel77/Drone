package drone.services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import drone.entities.Coordinate;
import drone.services.DroneService;

class DroneServiceTest {

	static DroneService droneService;
	static Calendar cal;

	@BeforeAll
	public static void before() {
System.out.println("@#$%^&*()(*&^%$#");
		droneService = new DroneService();
		cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getDefault());
	}

	@Test
	void testCalculateTimeTaken_oneWay() {
		
		Coordinate coordinate =  new Coordinate(5, 11, "S", "E");

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 12);
		cal.set(Calendar.SECOND, 5);

		assertEquals(cal.getTime(), droneService.calculateTimeTaken(coordinate, 1));

	}

	@Test
	void testCalculateTimeTaken_roundTrip() {

		Coordinate coordinate =  new Coordinate(5, 11, "S", "E");
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 24);
		cal.set(Calendar.SECOND, 10);

		assertEquals(cal.getTime(), droneService.calculateTimeTaken(coordinate, 2));

	}

}
