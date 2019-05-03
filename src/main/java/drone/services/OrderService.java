package drone.services;

import static drone.entities.DroneConstants.NEUTRAL_THRESHOLD_IN_SECS;
import static drone.entities.DroneConstants.ONE_WAY;
import static drone.entities.DroneConstants.PROMOTER_THRESHOLD_IN_SECS;

import java.util.Date;
import java.util.List;

import drone.entities.Coordinate;
import drone.entities.Order;

public class OrderService {

	DroneService droneService = new DroneService();
	UtilService utilService = new UtilService();

	public List<Order> getAvailableOrders(List<Order> fileOrders, List<Order> availableOrders, Date currentTime) {
		availableOrders.clear();
		for (Order fileOrder : fileOrders) {
			if (fileOrder.getDateIn().before(currentTime)) {
				availableOrders.add(fileOrder);
			}
		}
		return availableOrders;
	}

	public void placeOrder(List<Order> fileOrders, Order order, Date currentTime, List<Order> outputOrders) {

		order.setDateOut(new Date(currentTime.getTime()));
		outputOrders.add(order);

	}

	public int calculateNPS(List<Order> orders) {

		long totalOrders = orders.size();
		long promoterOrders = 0;
		long demoterOrders = 0;

		for (Order o : orders) {
			Date deliveredTime = utilService.addTime(o.getDateOut(),
					droneService.calculateTimeTaken(o.getCoordinates(), ONE_WAY));

			if (Math.abs(
					deliveredTime.getTime() / 1000 - o.getDateIn().getTime() / 1000) < PROMOTER_THRESHOLD_IN_SECS) {
				promoterOrders++;
			} else if (Math
					.abs(deliveredTime.getTime() / 1000 - o.getDateIn().getTime() / 1000) > NEUTRAL_THRESHOLD_IN_SECS) {
				demoterOrders++;
			}

			// logger.info(o.toString());
		}
		return (int) Math.ceil(UtilService.calculatePercentage(promoterOrders, totalOrders)
				- UtilService.calculatePercentage(demoterOrders, totalOrders));
	}

	public Coordinate convertStringToCoordinate(String stringCoordinate) {

		Coordinate coordinate = new Coordinate();
		String[] coordinateInfo = stringCoordinate.split("(?<=\\d)(?=\\D)|(?=\\d)(?<=\\D)");
		coordinate.setxDirection(coordinateInfo[0]);
		coordinate.setX(Integer.parseInt(coordinateInfo[1]));
		coordinate.setyDirection(coordinateInfo[2]);
		coordinate.setY(Integer.parseInt(coordinateInfo[3]));

		return coordinate;
	}

}
