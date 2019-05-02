package com.interview.drone.services;
/*
import static com.interview.drone.entities.DroneConstants.NEUTRAL_THRESHOLD_IN_SECS;
import static com.interview.drone.entities.DroneConstants.ONE_WAY;
import  static com.interview.drone.entities.DroneConstants.PROMOTER_THRESHOLD_IN_SECS;*/

import java.util.Date;
import java.util.List;

import com.interview.drone.Order;
import com.interview.drone.entities.Coordinate;

public class OrderService {
	
	public static final long PROMOTER_THRESHOLD_IN_SECS = 7199; // 1hr 59 mins
	public static final long NEUTRAL_THRESHOLD_IN_SECS = 14339; // 3hr 59 mins
	public static final int ONE_WAY = 1;
	
	DroneService droneService = new DroneService();
	UtilService utilService = new UtilService();
	
	public List<Order> getAvailableOrders(List<Order> fileOrders, List<Order> availableOrders, Date currentTime) {
		availableOrders.clear();
		for(Order fileOrder : fileOrders) {
			if(fileOrder.getDateIn().before(currentTime)) {
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
		long demoterOrders= 0;
		
		for (Order o : orders) {
		long deliveredTime = utilService.addTime2(o.getDateOut(), droneService.calculateTimeTaken(o.getCoordinates(), ONE_WAY)).getTime();
		System.out.println("Ordernumber: " + new Date(deliveredTime));
		
		if(Math.abs(deliveredTime - o.getDateIn().getTime()) < PROMOTER_THRESHOLD_IN_SECS) {
			promoterOrders++;
		} else if(Math.abs(deliveredTime - o.getDateIn().getTime()) > NEUTRAL_THRESHOLD_IN_SECS) {
			demoterOrders++;
		}
				
		System.out.println(o.toString());
	}
	return (int) Math.ceil(UtilService.calculatePercentage(promoterOrders, totalOrders) - UtilService.calculatePercentage(demoterOrders, totalOrders));
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
