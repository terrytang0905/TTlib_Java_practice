package com.ttsoftware.utilities.elevator;

public class Elevator {

	private String elevatorId;
	
	private long loadWeight;
	
	private int currentLayer;
	
	private int targetLayer;
	
	private long runTime;
	
	private STATUS status;

	public enum STATUS{
		UPSTAIR,
		DOWNSTAIR,
		EMPTY;
	}
	

	public String getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(String elevatorId) {
		this.elevatorId = elevatorId;
	}

	public long getLoadWeight() {
		return loadWeight;
	}

	public void setLoadWeight(long loadWeight) {
		this.loadWeight = loadWeight;
	}

	public int getCurrentLayer() {
		return currentLayer;
	}

	public void setCurrentLayer(int currentLayer) {
		this.currentLayer = currentLayer;
	}

	public int getTargetLayer() {
		return targetLayer;
	}

	public void setTargetLayer(int targetLayer) {
		this.targetLayer = targetLayer;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
}
