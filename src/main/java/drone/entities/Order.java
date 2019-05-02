package drone.entities;

import java.util.Date;

public class Order implements Comparable<Order> {

	private String orderNumber;
	private Coordinate coordinates;
	private Date dateIn;
	private Date dateOut;
	private Long oneWay;
	private Long roundTrip;

	public Order() {

	}
	
	// used to read the input
		public Order(String orderNumber, Coordinate coordinates, Date dateIn, Long oneWay) {
			super();
			this.orderNumber = orderNumber;
			this.coordinates = coordinates;
			this.dateIn = dateIn;
			this.oneWay = oneWay;
		}

	// used to read the input
	public Order(String orderNumber, Coordinate coordinates, Date dateIn, Long oneWay, Long roundTrip) {
		super();
		this.orderNumber = orderNumber;
		this.coordinates = coordinates;
		this.dateIn = dateIn;
		this.oneWay = oneWay;
		this.roundTrip = roundTrip;
	}

	// used to generate output
	public Order(String orderNumber, Date dateOut) {
		super();
		this.orderNumber = orderNumber;
		this.dateOut = dateOut;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Long getOneWay() {
		return oneWay;
	}

	public void setOneWay(Long oneWay) {
		this.oneWay = oneWay;
	}

	public Long getRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(Long roundTrip) {
		this.roundTrip = roundTrip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

	@Override
	public int compareTo(Order o) {
		if (this.oneWay.equals(o.getOneWay()))
			return 0;
		else if (this.oneWay > o.getOneWay())
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", coordinates=" + coordinates + ", dateIn=" + dateIn
				+ ", dateOut=" + dateOut + ", oneWay=" + oneWay + ", roundTrip=" + roundTrip + "]";
	}

}