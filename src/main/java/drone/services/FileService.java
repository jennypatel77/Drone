package drone.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import drone.entities.Order;

public class FileService {

	UtilService utilService = new UtilService();
	OrderService orderService = new OrderService();

	public List<Order> readOrdersFromFile(Path path) throws IOException {
		List<Order> orders = new ArrayList<>();
		List<String> lines;
		try {
		lines = Files.readAllLines(path.toAbsolutePath());
		} catch (IOException e) {
			System.out.println( "Please check if the location you provided is correct");
			throw e;
		}
		
		try {
		lines.forEach(line -> {
			orders.add(createOrderFromLine(line));
		});
		
		return orders;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println( "Please check you input file");
			throw e;
		}
	}

	private Order createOrderFromLine(String line) {
		Order order = new Order();
		String[] params = line.split(" ");
		order.setOrderNumber(params[0]);

		String[] time = params[2].split(":");
		order.setDateIn(
				utilService.setTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])));

		order.setCoordinates(orderService.convertStringToCoordinate(params[1]));

		return order;
	}

	public String writeFile(List<Order> orders, int nps) throws IOException {

		Calendar cal = Calendar.getInstance();
		String fileLocation = "C:\\Users\\Jenny\\Desktop\\outputorders-" + cal.getTimeInMillis()+ ".txt";
		Path path;
		try {
			path = Files.createFile(Paths.get(fileLocation));
			for (Order order : orders) {
				Files.write(path.toAbsolutePath(), order.toOutputString().getBytes(), StandardOpenOption.APPEND);
			}

			Files.write(path, String.format("NPS:  %s", nps).getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		return path.toString();
	}
}
