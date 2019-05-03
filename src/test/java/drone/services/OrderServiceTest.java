package drone.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
		List<Order> resultOrders = new ArrayList<>();

		fileOrders.add(new Order("WM001", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 11, 50), null,
				new Date().getTime()));
		fileOrders.add(new Order("WM002", new Coordinate(3, 4, "N", "S"), utilService.setTime(6, 11, 50), null,
				new Date().getTime()));
		fileOrders.add(new Order("WM003", new Coordinate(3, 4, "N", "S"), utilService.setTime(7, 11, 50), null,
				new Date().getTime()));

		resultOrders = orderService.getAvailableOrders(fileOrders, resultOrders, utilService.setTime(6, 30, 00));
		assertEquals(2, resultOrders.size());

	}
	
	@Test
	void testAvailableOrders_negative() {
		OrderService orderService = new OrderService();
		UtilService utilService = new UtilService();

		List<Order> fileOrders = new ArrayList<>();
		List<Order> resultOrders = new ArrayList<>();

		fileOrders.add(new Order("WM001", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 11, 50), null,
				new Date().getTime()));
		fileOrders.add(new Order("WM002", new Coordinate(3, 4, "N", "S"), utilService.setTime(6, 11, 50), null,
				new Date().getTime()));
		fileOrders.add(new Order("WM003", new Coordinate(3, 4, "N", "S"), utilService.setTime(7, 11, 50), null,
				new Date().getTime()));

		resultOrders = orderService.getAvailableOrders(fileOrders, resultOrders, utilService.setTime(6, 0, 00));
		assertNotEquals(2, resultOrders.size());

	}

	@Test
	void testConvertStringToCoordinate() {
		OrderService orderService = new OrderService();

		Coordinate result = orderService.convertStringToCoordinate("N30E55");
		Coordinate expected = new Coordinate(30, 55, "N", "E");
		assertEquals(expected.getX(), result.getX());
		assertEquals(expected.getY(), result.getY());
		assertEquals(expected.getxDirection(), result.getxDirection());
		assertEquals(expected.getyDirection(), result.getyDirection());

	}
	
	@Test
	void testConvertStringToCoordinate_negative() {
		OrderService orderService = new OrderService();

		Coordinate result = orderService.convertStringToCoordinate("N30E55");
		Coordinate unexpected = new Coordinate(31, 54, "S", "W");
		assertNotEquals(unexpected.getX(), result.getX());
		assertNotEquals(unexpected.getY(), result.getY());
		assertNotEquals(unexpected.getxDirection(), result.getxDirection());
		assertNotEquals(unexpected.getyDirection(), result.getyDirection());

	}
	
	@Test
	void testCalculateNPS() {
		List<Order> fileOrders = new ArrayList<>();
		UtilService utilService = new UtilService();
		OrderService orderService =  new OrderService();

		fileOrders.add(new Order("WM001", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 0, 0),	utilService.setTime(7, 0, 0), null));
		fileOrders.add(new Order("WM002", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 30, 0),	utilService.setTime(7, 0, 0), null));
		fileOrders.add(new Order("WM003", new Coordinate(3, 4, "N", "S"), utilService.setTime(6, 0, 0),	utilService.setTime(7, 0, 0), null));
		
		int result = orderService.calculateNPS(fileOrders);
		assertEquals(67, result);
		
	}
	
	@Test
	void testCalculateNPS_negative() {
		List<Order> fileOrders = new ArrayList<>();
		UtilService utilService = new UtilService();
		OrderService orderService =  new OrderService();

		fileOrders.add(new Order("WM001", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 0, 0),	utilService.setTime(7, 30, 0), null));
		fileOrders.add(new Order("WM002", new Coordinate(3, 4, "N", "S"), utilService.setTime(5, 30, 0),	utilService.setTime(7, 30, 0), null));
		fileOrders.add(new Order("WM003", new Coordinate(3, 4, "N", "S"), utilService.setTime(6, 0, 0),	utilService.setTime(7, 30, 0), null));
		
		int result = orderService.calculateNPS(fileOrders);
		assertNotEquals(67, result);
		
	}
}
