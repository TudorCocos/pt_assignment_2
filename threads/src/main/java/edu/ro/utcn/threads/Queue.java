package edu.ro.utcn.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{

	private volatile ArrayBlockingQueue<Client> clients;
	private AtomicInteger waitingPeriod;
	private volatile boolean working, hasArrivalMessage, hasFinishMessage, isDone;
	private int id;
	private String arrivalMessage, finishMessage;
	private Client currentClient;
	
	public Queue(int id, int size) {
		this.clients=new ArrayBlockingQueue<Client>(size);
		this.waitingPeriod = new AtomicInteger(0);
		this.id=id;
		this.isDone=false;
	}
	public String getQueueName() {
		return this.toString();
	}
	
	public ArrayBlockingQueue<Client> getQueue() {
		return clients;
	}
	
	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}
	
	public void setWaitingPeriod(int clientProcessingTime) {
		waitingPeriod.addAndGet(clientProcessingTime);
	}
	
	public boolean isWorking() {
		return this.working;
	}
	
	public void setWorking(boolean state) {
		this.working=state;
	}
	
	public int getId() {
		return this.id;
	}

	public String getArrivalMessage() {
		return this.arrivalMessage;
	}
	
	public void resetArrivalMessage() {
		hasArrivalMessage=false;
	}
	
	public boolean getArrivalBoolean() {
		return this.hasArrivalMessage;
	}
	
	public String getFinishMessage() {
		return this.finishMessage;
	}
	
	public void resetFinishMessage() {
		hasFinishMessage=false;
	}
	
	public boolean getFinishBoolean() {
		return this.hasFinishMessage;
	}
	
	public Client getCurrentClient() {
		return this.currentClient;
	}
	
	public boolean isDone() {
		return this.isDone;
	}
	@Override
	public void run() {
		while(true) {
			while(working) {
				this.currentClient=clients.poll();
				int waitTime=currentClient.getProcessingTime();
				if(currentClient!=null)
					arrivalMessage="[ Thread "+this.getId()+" ] started processing client "+currentClient.getId();
				this.hasArrivalMessage=true;
				try {
					Thread.sleep(waitTime*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				waitingPeriod.set(waitingPeriod.get()-waitTime);
				if(currentClient!=null)
					finishMessage="[ Thread "+this.getId()+" ] finished processing client "+currentClient.getId();
				this.hasFinishMessage=true;
				if(clients.isEmpty()) {
					isDone=true;
					working=false;
				}
			}
		}
	}
}