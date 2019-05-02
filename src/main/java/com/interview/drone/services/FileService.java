package com.interview.drone.services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.interview.drone.Order;
import com.interview.drone.entities.DroneConstants;

public class FileService {
	
	UtilService utilService = new UtilService();
	OrderService orderService = new OrderService();

	public List<Order> readOrdersFromFile(Path path) throws IOException {
		List<Order> orders = new ArrayList<>();
		List<String> lines = Files.readAllLines(path);
		lines.forEach(line -> {
			orders.add(createOrderFromLine(line));
		});
		return orders;
	}

	private Order createOrderFromLine(String line) {
		System.out.println("Reading Order Line " + line);
		Order order = new Order();
		String[] params = line.split(" ");
		System.out.println(params[0] + ", " + params[1] + ", " + params[2]);
		order.setOrderNumber(params[0]);

		String[] time = params[2].split(":");
		order.setDateIn(
				utilService.setTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])));

		order.setCoordinates(orderService.convertStringToCoordinate(params[1]));

		System.out.println("Read Order " + order.toString());

		return order;
	}

	public String writeFile(List<Order> orders, int nps) throws IOException {

		FileWriter fileWriter = null;
		String fileLocation = "C:/Users/patelj/Downloads/outputorders.txt";

		try {
			fileWriter = new FileWriter(fileLocation);

			for (Order order : orders) {
				fileWriter.write(order.getOrderNumber() + " " + DroneConstants.SDF.format(order.getDateIn())
						+ System.getProperty("line.separator"));
			}

			fileWriter.write(String.format("NPS:  %s", nps));

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} finally {
			fileWriter.close();
		}

		return fileLocation;
	}
}
