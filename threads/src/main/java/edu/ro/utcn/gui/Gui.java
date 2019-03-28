package edu.ro.utcn.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Gui extends JFrame{
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField clientsField;
	private JTextField simTimeField;
	private JTextField queuesField;
	private JTextField minArrivalField;
	private JTextField maxArrivalField;
	private JTextField minProcessField;
	private JTextField maxProcessField;
	private JTextField clientPerQueueField;
	private JTextArea textArea;
	private JButton btnStart, btnStop;
	private JFormattedTextField textClients;
	private JFormattedTextField queue1, queue2, queue3, queue4, queue5;
	private JLabel lbl_reg1,lbl_reg2,lbl_reg3,lbl_reg4,lbl_reg5;
	private JProgressBar progressBar;
	
	public int getClientsNumber() {
		if(clientsField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(clientsField.getText());
	}

	public int getSimulationTime() {
		if(simTimeField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(simTimeField.getText());
	}
	
	public int getQueuesNumber() {
		if(queuesField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(queuesField.getText());
	}
	
	public int getClientsPerQueue() {
		if(clientPerQueueField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(clientPerQueueField.getText());
	}
	
	public int getMinArrivalTime() {
		if(minArrivalField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(minArrivalField.getText());
	}
	
	public int getMaxArrivalTime() {
		if(maxArrivalField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(maxArrivalField.getText());
	}
	
	public int getMinProcessingTime() {
		if(minProcessField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(minProcessField.getText());
	}
	
	public int getMaxProcessingTime() {
		if(maxProcessField.getText()=="") {
			return 0;
		}
		return Integer.parseInt(maxProcessField.getText());
	}
	
	public void setTextArea(String s) {
		textArea.setText(s);
	}
	
	public void addToTextArea(String s) {
		textArea.append(s);
		textArea.append("\n");
	}
	
	public JButton getStartButton() {
		return btnStart;
	}
	
	public JButton getStopButton() {
		return btnStop;
	}
	
	public void setTextClients(String s) {
		textClients.setText(s);
	}
	
	public void setTextRegisters(int i, String s) {
		if(i==1)
			lbl_reg1.setText(s);
		if(i==2)
			lbl_reg2.setText(s);
		if(i==3)
			lbl_reg3.setText(s);
		if(i==4)
			lbl_reg4.setText(s);
		if(i==5)
			lbl_reg5.setText(s);
	}
	
	public JFormattedTextField getQueueField(int i) {
		if(i==2)
			return queue2;
		if(i==3)
			return queue3;
		if(i==4)
			return queue4;
		if(i==5)
			return queue5;
		return queue1;
	}
	
	public void setProgressBar(int val, int total) {
		progressBar.setMaximum(total);
		progressBar.setValue(val);
	}
	
	public Gui() {
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 540, 1300, 180);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(20, 540, 1300, 180);
		scrollPane.add(textArea);
		scrollPane.getViewport ().setView ( textArea );
		
		JLabel lbl1 = new JLabel("Register 1");
		lbl1.setBounds(20, 20, 80, 20);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("Register 2");
		lbl2.setBounds(20, 100, 80, 20);
		contentPane.add(lbl2);
		
		JLabel lbl3 = new JLabel("Register 3");
		lbl3.setBounds(20, 180, 80, 20);
		contentPane.add(lbl3);
		
		JLabel lbl4 = new JLabel("Register 4");
		lbl4.setBounds(20, 260, 80, 20);
		contentPane.add(lbl4);
		
		JLabel lbl5 = new JLabel("Register 5");
		lbl5.setBounds(20, 340, 80, 20);
		contentPane.add(lbl5);
		
		queue1 = new JFormattedTextField();
		queue1.setEditable(false);
		queue1.setBackground(SystemColor.control);
		queue1.setBounds(200, 40, 800, 30);
		contentPane.add(queue1);
		
		queue2 = new JFormattedTextField();
		queue2.setEditable(false);
		queue2.setBackground(SystemColor.menu);
		queue2.setBounds(200, 120, 800, 30);
		contentPane.add(queue2);
		
		queue3 = new JFormattedTextField();
		queue3.setEditable(false);
		queue3.setBackground(SystemColor.menu);
		queue3.setBounds(200, 200, 800, 30);
		contentPane.add(queue3);
		
		queue4 = new JFormattedTextField();
		queue4.setEditable(false);
		queue4.setBackground(SystemColor.menu);
		queue4.setBounds(200, 280, 800, 30);
		contentPane.add(queue4);
		
		queue5 = new JFormattedTextField();
		queue5.setEditable(false);
		queue5.setBackground(SystemColor.menu);
		queue5.setBounds(200, 360, 800, 30);
		contentPane.add(queue5);
		
		textClients = new JFormattedTextField();
		textClients.setEditable(false);
		textClients.setBackground(SystemColor.control);
		textClients.setBounds(20, 430, 150, 30);
		contentPane.add(textClients);
		
		JLabel lblClients = new JLabel("Clients left in the shop");
		lblClients.setBounds(20, 410, 150, 20);
		contentPane.add(lblClients);
		
		JLabel lblQ1 = new JLabel("Queue 1");
		lblQ1.setBounds(200, 20, 80, 20);
		contentPane.add(lblQ1);
		
		JLabel lblQ2 = new JLabel("Queue 2");
		lblQ2.setBounds(200, 100, 80, 20);
		contentPane.add(lblQ2);
		
		JLabel lblQ3 = new JLabel("Queue 3");
		lblQ3.setBounds(200, 180, 80, 20);
		contentPane.add(lblQ3);
		
		JLabel lblQ4 = new JLabel("Queue 4");
		lblQ4.setBounds(200, 260, 80, 20);
		contentPane.add(lblQ4);
		
		JLabel lblQ5 = new JLabel("Queue 5");
		lblQ5.setBounds(200, 340, 80, 20);
		contentPane.add(lblQ5);
		
		clientsField = new JTextField();
		clientsField.setBackground(SystemColor.control);
		clientsField.setBounds(1060, 40, 100, 30);
		contentPane.add(clientsField);
		clientsField.setColumns(10);
		
		simTimeField = new JTextField();
		simTimeField.setColumns(10);
		simTimeField.setBackground(SystemColor.menu);
		simTimeField.setBounds(1200, 40, 100, 30);
		contentPane.add(simTimeField);
		
		queuesField = new JTextField();
		queuesField.setColumns(10);
		queuesField.setBackground(SystemColor.menu);
		queuesField.setBounds(1060, 280, 100, 30);
		contentPane.add(queuesField);
		
		minArrivalField = new JTextField();
		minArrivalField.setColumns(10);
		minArrivalField.setBackground(SystemColor.menu);
		minArrivalField.setBounds(1059, 120, 100, 30);
		contentPane.add(minArrivalField);
		
		maxArrivalField = new JTextField();
		maxArrivalField.setColumns(10);
		maxArrivalField.setBackground(SystemColor.menu);
		maxArrivalField.setBounds(1200, 120, 100, 30);
		contentPane.add(maxArrivalField);
		
		minProcessField = new JTextField();
		minProcessField.setColumns(10);
		minProcessField.setBackground(SystemColor.menu);
		minProcessField.setBounds(1057, 200, 100, 30);
		contentPane.add(minProcessField);
		
		maxProcessField = new JTextField();
		maxProcessField.setColumns(10);
		maxProcessField.setBackground(SystemColor.menu);
		maxProcessField.setBounds(1205, 200, 100, 30);
		contentPane.add(maxProcessField);
		
		JLabel lblClientsInput = new JLabel("Input nr of clients");
		lblClientsInput.setBounds(1060, 20, 100, 20);
		contentPane.add(lblClientsInput);
		
		JLabel lblSimTimeInput = new JLabel("Input simulation time");
		lblSimTimeInput.setBounds(1190, 20, 120, 20);
		contentPane.add(lblSimTimeInput);
		
		JLabel lblQueuesInput = new JLabel("Input nr of queues");
		lblQueuesInput.setBounds(1060, 260, 110, 20);
		contentPane.add(lblQueuesInput);
		
		JLabel lblMinArrivalInput = new JLabel("Input MIN arrival time");
		lblMinArrivalInput.setBounds(1049, 100, 120, 20);
		contentPane.add(lblMinArrivalInput);
		
		JLabel lblMaxArrivalInput = new JLabel("Input MAX arrival time");
		lblMaxArrivalInput.setBounds(1190, 100, 130, 20);
		contentPane.add(lblMaxArrivalInput);
		
		JLabel lblMinProcessingInput = new JLabel("Input MIN processing time");
		lblMinProcessingInput.setBounds(1025, 180, 150, 20);
		contentPane.add(lblMinProcessingInput);
		
		JLabel lblMaxProcessingInput = new JLabel("Input MAX processing time");
		lblMaxProcessingInput.setBounds(1190, 180, 160, 20);
		contentPane.add(lblMaxProcessingInput);
		
		btnStart = new JButton("Start");
		btnStart.setForeground(Color.WHITE);
		btnStart.setBackground(Color.GREEN);
		btnStart.setBounds(1060, 360, 100, 30);
		contentPane.add(btnStart);
		
		JLabel lblStart = new JLabel("Start simulation");
		lblStart.setBounds(1060, 340, 110, 20);
		contentPane.add(lblStart);
		
		btnStop = new JButton("Stop");
		btnStop.setForeground(Color.WHITE);
		btnStop.setBackground(Color.RED);
		btnStop.setBounds(1205, 360, 100, 30);
		contentPane.add(btnStop);
		
		JLabel lblEndSimulation = new JLabel("End simulation");
		lblEndSimulation.setBounds(1205, 340, 110, 20);
		contentPane.add(lblEndSimulation);
		
		JLabel lblClientsqueue = new JLabel("Input clients/queue");
		lblClientsqueue.setBounds(1205, 260, 110, 20);
		contentPane.add(lblClientsqueue);
		
		clientPerQueueField = new JTextField();
		clientPerQueueField.setColumns(10);
		clientPerQueueField.setBackground(SystemColor.menu);
		clientPerQueueField.setBounds(1205, 280, 100, 30);
		contentPane.add(clientPerQueueField);
		
		JLabel lblSimulationInterval = new JLabel("Simulation ");
		lblSimulationInterval.setBounds(20, 470, 150, 20);
		contentPane.add(lblSimulationInterval);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(20, 490, 1300, 30);
		contentPane.add(progressBar);
		
		lbl_reg1 = new JLabel("Empty");
		lbl_reg1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg1.setBackground(SystemColor.menu);
		lbl_reg1.setBounds(20, 40, 150, 30);
		contentPane.add(lbl_reg1);
		
		lbl_reg2 = new JLabel("Empty");
		lbl_reg2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg2.setBackground(SystemColor.menu);
		lbl_reg2.setBounds(20, 120, 150, 30);
		contentPane.add(lbl_reg2);
		
		lbl_reg3 = new JLabel("Empty");
		lbl_reg3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg3.setBackground(SystemColor.menu);
		lbl_reg3.setBounds(20, 200, 150, 30);
		contentPane.add(lbl_reg3);
		
		lbl_reg4 = new JLabel("Empty");
		lbl_reg4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg4.setBackground(SystemColor.menu);
		lbl_reg4.setBounds(20, 280, 150, 30);
		contentPane.add(lbl_reg4);
		
		lbl_reg5 = new JLabel("Empty");
		lbl_reg5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reg5.setBackground(SystemColor.menu);
		lbl_reg5.setBounds(20, 360, 150, 30);
		contentPane.add(lbl_reg5);
	}
}
