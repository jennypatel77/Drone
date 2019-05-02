package com.interview.drone.services;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.interview.drone.Order;
import com.interview.drone.entities.Coordinate;

class OrderServiceTest {
	

	@Test
	void testAvailableOrders() {
		OrderService orderService = new OrderService();
		UtilService utilService = new UtilService();
		
		 List<Order> fileOrders = new ArrayList<>(); 
		 Calendar cal = Calendar.getInstance();
		 
		 fileOrders.add(new Order("WM001",
		 new Coordinate(3,  4,  "N",  "S"), utilService.setTime(5, 11, 50), DroneService.calculateTimeTaken(OrderService.convertStringToCoordinate(
		 "N11W5"), DroneConstants.ONE_WAY).getTime() / 1000)); fileOrders.add(new
		 Order("WM002", OrderService.convertStringToCoordinate("S3E2"),
		 UtilService.setTime(5, 11, 55),
		 DroneService.calculateTimeTaken(OrderService.convertStringToCoordinate("S3E2"
		 ), DroneConstants.ONE_WAY).getTime() / 1000)); fileOrders.add(new
		 Order("WM003", OrderService.convertStringToCoordinate("N7E50"),
		 UtilService.setTime(5, 31, 50),
		 DroneService.calculateTimeTaken(OrderService.convertStringToCoordinate(
		 "N7E50"), DroneConstants.ONE_WAY).getTime() / 1000)); fileOrders.add(new
		 Order("WM004", OrderService.convertStringToCoordinate("N11E2"),
		 UtilService.setTime(6, 11, 50),
		 DroneService.calculateTimeTaken(OrderService.convertStringToCoordinate(
		 "N11E5"), DroneConstants.ONE_WAY).getTime() / 1000));
		fail("Not yet implemented");
	}

	@Test
	void testPlaceOrder() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCalculateNPS() {
		fail("Not yet implemented");
	}
	
	@Test
	void testConvertStringToCoordinate() {
		fail("Not yet implemented");
	}
}
