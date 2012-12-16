package com.ttsoftware.utilities.elevator;

public class Order {

	private String orderId;
	private int orderLayer;
	private boolean upstair = true;
	private long startTime;
	private long endTime;
	private String elevatorId;
	private ORDER_STATUS orderStatus;

	public enum ORDER_STATUS {
		SUBMIT, READY, RUNNING, COMPLETED;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderLayer() {
		return orderLayer;
	}

	public void setOrderLayer(int orderLayer) {
		this.orderLayer = orderLayer;
	}

	public boolean isUpstair() {
		return upstair;
	}

	public void setUpstair(boolean upstair) {
		this.upstair = upstair;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}

	public ORDER_STATUS getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ORDER_STATUS orderStatus) {
		this.orderStatus = orderStatus;
	}

}
