package edu.ro.utcn.threads;

public class Client {
	
	private int arrivalTime;
	private int processingTime;
	private int id;
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
	public void decProcessingTime() {
		this.processingTime--;
	}
	
	public int getId() {
		return id;
	}
	
	public Client(int arrivalTime, int processingTime, int id) {
		this.arrivalTime=arrivalTime;
		this.processingTime=processingTime;
		this.id=id;
	}

}
