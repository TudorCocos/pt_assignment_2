package edu.ro.utcn.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import edu.ro.utcn.gui.Gui;

public class SimulationManager implements Runnable{
	private int clientsNumber;
	private int queuesNumber;
	private int clientsPerQueue;
	private int minArrivalTime, maxArrivalTime;
	private int minServiceTime, maxServiceTime;
	private int simulationInterval;
	private boolean okToStart;
	private Gui frame;
	private ArrayList<Client> clients;
	private List<Queue> queues;
	private Thread t;
	
	public void getData() {
		clientsNumber = frame.getClientsNumber();
		queuesNumber=frame.getQueuesNumber();
		minArrivalTime=frame.getMinArrivalTime();
		maxArrivalTime=frame.getMaxArrivalTime();
		minServiceTime=frame.getMinProcessingTime();
		maxServiceTime=frame.getMaxProcessingTime();
		simulationInterval=frame.getSimulationTime();
		clientsPerQueue=frame.getClientsPerQueue();
		if(maxArrivalTime+maxServiceTime<=simulationInterval && queuesNumber<=5)
			okToStart=true;
	}
	public SimulationManager() {
		okToStart=false;
		frame = new Gui();
		frame.setVisible(true);
		t = new Thread(this);
		frame.getStartButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getData();
				if(t.isAlive()==false) {
					if(okToStart==true) {
						t.start();
						frame.getStartButton().setEnabled(false);
						frame.getStopButton().setEnabled(true);
					}
					else
						frame.setTextArea("WARNING - INVALID INPUT DATA!\n You either set the maxArrivalTime and maxServiceTime too high or you inputted too many queues.\nmaxArrivalTIme + maxServiceTime should be less or equal to simulationInterval while the number of queues should be less or equal to five.\n");
				}
				else {
					t.resume();
					frame.getStartButton().setEnabled(false);
					frame.getStopButton().setEnabled(true);
				}
			}
		});
		frame.getStopButton().addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(t.isAlive())
					t.suspend();
				frame.addToTextArea("WARNING!\nYou stopped the simulation!\n");
				frame.getStartButton().setEnabled(true);
				frame.getStopButton().setEnabled(false);
			}
		});
	}
	public void clientGeneration() {
		clients = new ArrayList<Client>();
		ClientGenerator cg = new ClientGenerator(minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime,simulationInterval);
		for(int i=1;i<=clientsNumber;i++) {
			clients.add(cg.generateClient(i));
		}
		for(Client c: clients) {
			System.out.println(c.getId()+" "+c.getArrivalTime()+" "+c.getProcessingTime());
		}
	}
	private synchronized void addClient(Client c, Queue q) {
		try {
			q.getQueue().put(c);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		q.setWaitingPeriod(c.getProcessingTime());
	}
	private List<Queue> createQueues(){
		List<Queue> queues = new ArrayList<Queue>();
		for(int i=0;i<queuesNumber;i++) {
			Queue q = new Queue(i+1,clientsPerQueue);
			queues.add(q);
		}
		return queues;
	}
	private Queue getQueue(List<Queue> queues) {
		int time=Integer.MAX_VALUE;
		Queue result = new Queue(0,clientsPerQueue);
		for(Queue q:queues) {
			if(q.getWaitingPeriod().get()<time) {
				result=q;
				time=q.getWaitingPeriod().get();
			}
		}
		return result;
	}
	private void displayQueues() {
		for(Queue q:queues) {
			StringBuilder sb = new StringBuilder();
			for(Client c:q.getQueue()) {
				sb.append(c.getId()+"  ");
			}
			frame.getQueueField(q.getId()).setText(sb.toString());
		}
	}
	private boolean areQueuesWorking() {
		for(Queue q:queues) {
			if(q.isWorking())
				return true;
		}
		return false;
	}
	private void displayTextArea() {
		for(Queue q: queues) {
			if(q.getArrivalBoolean()) {
				frame.addToTextArea(q.getArrivalMessage());
				if(q.getCurrentClient()!=null)
					frame.setTextRegisters(q.getId(), "Client "+q.getCurrentClient().getId());
				q.resetArrivalMessage();
			}
			if(q.getFinishBoolean()) {
				frame.addToTextArea(q.getFinishMessage());
				if(q.isDone())
					frame.setTextRegisters(q.getId(), "Empty");
				q.resetFinishMessage();
			}
		}
	}
	@Override
	public void run() {
			int timePassed=0;
			queues=createQueues();
			clientGeneration();
			for(Queue q: queues) {
				Thread t = new Thread(q);
				t.start();
			}
			List<Client> toBeRemembered = new ArrayList<Client>();
			while(timePassed<simulationInterval) {
				displayTextArea();
				for(Client c:clients) {
					if(c.getArrivalTime()<=timePassed) {
						Queue destination = getQueue(queues);
						if(destination.getQueue().size()<clientsPerQueue) {
							addClient(c,destination);
							destination.setWorking(true);
							toBeRemembered.add(c);
						}
					}
				}
				for(Client c:toBeRemembered) {
					clients.remove(c);
				}
				frame.setTextClients(Integer.toString(clients.size()));
				displayQueues();
				frame.setProgressBar(timePassed, simulationInterval);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				timePassed++;
			}
			frame.setProgressBar(timePassed, simulationInterval);
			while(areQueuesWorking()) {
				displayQueues();
				displayTextArea();
			}
	}
}