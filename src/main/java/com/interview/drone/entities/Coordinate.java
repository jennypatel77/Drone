package com.interview.drone.entities;

public class Coordinate {

	private int x;
	private int y;
	private String xDirection;
	private String yDirection;
	
	public Coordinate() {
		
	}

	public Coordinate(int x, int y, String xDirection, String yDirection) {
		super();
		this.x = x;
		this.y = y;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getxDirection() {
		return xDirection;
	}

	public void setxDirection(String xDirection) {
		this.xDirection = xDirection;
	}

	public String getyDirection() {
		return yDirection;
	}

	public void setyDirection(String yDirection) {
		this.yDirection = yDirection;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + ", xDirection=" + xDirection + ", yDirection=" + yDirection + "]";
	}

}
