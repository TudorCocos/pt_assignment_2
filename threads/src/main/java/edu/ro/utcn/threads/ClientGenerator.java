package edu.ro.utcn.threads;

import java.util.Random;

public class ClientGenerator {

	private int minArrivalTime, maxArrivalTime;
	private int minServiceTime, maxServiceTime;
	private int simulationInterval;
	
	public ClientGenerator(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int simulationInterval) {
		this.minArrivalTime=minArrivalTime;
		this.maxArrivalTime=maxArrivalTime;
		this.minServiceTime=minServiceTime;
		this.maxServiceTime=maxServiceTime;
		this.simulationInterval=simulationInterval;
	}
	
	public Client generateClient(int id) {
		int arrTime=0, servTime=0;
		Random rand = new Random();
		arrTime=rand.nextInt(maxArrivalTime)+minArrivalTime;
		servTime=rand.nextInt(maxServiceTime)+minServiceTime;
		while(servTime+arrTime>=simulationInterval) {
			arrTime=rand.nextInt(maxArrivalTime)+minArrivalTime;
			servTime=rand.nextInt(maxServiceTime)+minServiceTime;
		}
		Client c = new Client(arrTime, servTime, id);
		return c;
	}
}
