package drone.application;

//import  static com.interview.drone.entities.DroneConstants.ROUND_TRIP;

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

	public static final long PROMOTER_THRESHOLD_IN_SECS = 7199; // 1hr 59 mins
	public static final long NEUTRAL_THRESHOLD_IN_SECS = 14339; // 3hr 59 mins
	public static final int ONE_WAY = 1;
	public static final int ROUND_TRIP = 2;
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
		
		FileService fileService = new FileService();
		OrderService orderService = new OrderService();
		DroneService droneService = new DroneService();
		UtilService utilService = new UtilService();
		List<Order> fileOrders = fileService.readOrdersFromFile(Paths.get(args[0]));
		
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
			availableOrders = orderService.getAvailableOrders(fileOrders, availableOrders, currentTime);
			Collections.sort(availableOrders);
			orderOnDrone = availableOrders.get(0);
			orderService.placeOrder(fileOrders, orderOnDrone, currentTime, outputOrders);
			currentTime.setTime(utilService
					.addTime2(currentTime,
							droneService.calculateTimeTaken(orderOnDrone.getCoordinates(), ROUND_TRIP))
					.getTime());
			fileOrders.remove(orderOnDrone);
		}

		System.out.println("NPS " + orderService.calculateNPS(outputOrders));
	}
 
}
