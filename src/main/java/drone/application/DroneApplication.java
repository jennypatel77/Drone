package drone.application;

import static drone.entities.DroneConstants.ROUND_TRIP;
import static drone.entities.DroneConstants.ONE_WAY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import drone.entities.Order;
import drone.services.DroneService;
import drone.services.FileService;
import drone.services.OrderService;
import drone.services.UtilService;;

public class DroneApplication {

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {

		FileService fileService = new FileService();
		OrderService orderService = new OrderService();
		DroneService droneService = new DroneService();
		UtilService utilService = new UtilService();
		List<Order> fileOrders = fileService.readOrdersFromFile(Paths.get(args[0]));

		for (Order fileOrder : fileOrders) {
			fileOrder.setOneWay((droneService.calculateTimeTaken(fileOrder.getCoordinates(), ONE_WAY)).getTime());
		}

		// drone delivery start time
		Date currentTime = utilService.setTime(6, 0, 0);
		Date startTime = utilService.setTime(6, 0, 0);
		Date endTime = utilService.setTime(22, 0, 0);

		List<Order> availableOrders = new ArrayList<>();
		List<Order> outputOrders = new ArrayList<>();
		Order orderOnDrone = null;

		while (!fileOrders.isEmpty()) {

			// drone is off hours
			if (!currentTime.equals(startTime) && (currentTime.before(startTime) || currentTime.after(endTime))) {
				return;
			}

			availableOrders = orderService.getAvailableOrders(fileOrders, availableOrders, currentTime); //get orders that were already placed

			if (availableOrders.isEmpty()) { // drone can rest since there are no orders yet to be fulfilled in current time
				Collections.sort(fileOrders);
				currentTime.setTime(fileOrders.get(0).getDateIn().getTime()); // grab the earliest order and increment
																				// the current time to that
			}
			Collections.sort(availableOrders); //sort the already placed orders based on nearest distance to warehouse
			orderOnDrone = availableOrders.get(0);
			orderService.placeOrder(fileOrders, orderOnDrone, currentTime, outputOrders);
			currentTime.setTime(utilService
					.addTime(currentTime, droneService.calculateTimeTaken(orderOnDrone.getCoordinates(), ROUND_TRIP))
					.getTime());
			fileOrders.remove(orderOnDrone);
		}

		fileService.writeFile(outputOrders, orderService.calculateNPS(outputOrders));

		System.out.println("NPS " + orderService.calculateNPS(outputOrders));
	}

}
