package drone.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import drone.entities.Coordinate;
import drone.entities.Order;

class OrderServiceTest {
	

	@Test
	void testAvailableOrders() {
		OrderService orderService = new OrderService();
		UtilService utilService = new UtilService();
		
		 List<Order> fileOrders = new ArrayList<>(); 
		 List<Order> expectedOrders = new ArrayList<>(); 
		 
		 fileOrders.add(new Order("WM001", new Coordinate(3,  4,  "N",  "S"), utilService.setTime(5, 11, 50), new Date().getTime())); 
		 fileOrders.add(new Order("WM002", new Coordinate(3,  4,  "N",  "S"), utilService.setTime(6, 11, 50), new Date().getTime())); 
		 fileOrders.add(new Order("WM003", new Coordinate(3,  4,  "N",  "S"), utilService.setTime(7, 11, 50), new Date().getTime())); 
		 
		 expectedOrders = orderService.getAvailableOrders(fileOrders, expectedOrders, utilService.setTime(6, 30, 00));
		 assertEquals(2, expectedOrders.size());
		 
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
