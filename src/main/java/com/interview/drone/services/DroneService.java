package com.interview.drone.services;

import java.util.Date;

import com.interview.drone.entities.Coordinate;

public class DroneService {
	
	public Date calculateTimeTaken(Coordinate coordinate, int oneWayOrRoundTrip) {
		
		UtilService utilService = new UtilService();

		Integer x = coordinate.getX() * 60;
		Integer y = coordinate.getY() * 60;
		long secondsToWarehouse = (new Double(Math.ceil(Math.sqrt(x * x + y * y))* oneWayOrRoundTrip)).longValue();

		int hours = (int) secondsToWarehouse / 3600;
		int remainder = (int) secondsToWarehouse - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;
		
		return utilService.setTime(hours, mins, secs);

	}
	
}
