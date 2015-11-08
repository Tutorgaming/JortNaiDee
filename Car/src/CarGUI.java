import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarGUI extends JFrame {
	private JFrame j;
	private CarObject thisCarObject;
	private JLabel balance;
	private JPanel slotPanels[];

	public CarGUI(){
		initGUI();
	}
	
	public CarGUI(CarObject thisCarObject){
		this.thisCarObject = thisCarObject;
		initGUI();
	}
	
	public void initGUI(){
		
		// set title, frame
		this.setTitle("JortNaiDee");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set layout, ui
		
		this.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.ORANGE);
		topPanel.setBounds(0, 0, 800, 100);
		topPanel.setLayout(null);
		
		JPanel namePanel = new JPanel();
		namePanel.setBackground(Color.ORANGE);
		namePanel.setBounds(25, 25, 200, 50);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.ORANGE);
		buttonPanel.setBounds(400, 35, 400, 50);
		buttonPanel.setLayout(new FlowLayout());
		
		JLabel name=new JLabel("JortNaiDee");
		Font nameFont = new Font("Tahoma",Font.BOLD,30);
		name.setFont(nameFont);
		
		JButton mapButton = new JButton("Reload map");
		JButton payButton = new JButton("Pay");
		balance = new JLabel("Total cash: " + 0 + "$");
		Font totalFont = new Font("Tahoma",Font.BOLD,20);
		balance.setFont(totalFont);
		balance.setBackground(Color.WHITE);
		
		buttonPanel.add(balance);
		buttonPanel.add(mapButton);
		buttonPanel.add(payButton);
		
		JPanel parkingPanel = new JPanel();
		parkingPanel.setBackground(Color.BLACK);
		parkingPanel.setBounds(0, 100, 800, 500);
		parkingPanel.setLayout(null);
		
		// map button
		mapButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt){
				SenderCar s = new SenderCar();
				s.sendMapRequest();
				s.start();
			}
		});
		
		// pay button
		payButton.addMouseListener(new MouseAdapter(){
			private CarGUI gui;
			public void mouseClicked(java.awt.event.MouseEvent evt){
				gui.thisCarObject.setBalance(0);
				gui.setBalanceLabel(0);
			}
			private MouseAdapter init(CarGUI gui){
            	this.gui = gui;
            	return this;
            }
		}.init(this));
		
		// for each slot in parking area
		slotPanels = new JPanel[3];
		for(int i = 0; i < slotPanels.length; i++){
			slotPanels[i] = new JPanel();
			slotPanels[i].setBackground(Color.GREEN);
			slotPanels[i].addMouseListener(new MouseAdapter() {
				private int slotNo;
				private CarObject thisCarObject;
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                if(!thisCarObject.isParking()){
	                	SenderCar s = new SenderCar(thisCarObject);
	                	s.sendParkRequest(slotNo);
	                	s.start();
	                	thisCarObject.setCurrentParkingSlot(slotNo);
	                	
	                } else if(thisCarObject.getCurrentParkingSlot() == slotNo){
	                	SenderCar s = new SenderCar(thisCarObject);
	                	s.sendByeRequest();
	                	s.start();
	                	thisCarObject.setCurrentParkingSlot(0);
	                }
	            }
	            private MouseAdapter init(int slot, CarObject car){
	            	slotNo = slot;
	            	thisCarObject = car;
	                return this;
	            }
	        }.init(i+1, this.thisCarObject));
			parkingPanel.add(slotPanels[i]);
		}
		slotPanels[0].setBounds(15,0, 250, 500);
		slotPanels[1].setBounds(275,0, 250, 500);
		slotPanels[2].setBounds(535,0, 250, 500);
		
		
		namePanel.add(name);
		topPanel.add(namePanel);
		topPanel.add(buttonPanel);
		this.add(topPanel);
		this.add(parkingPanel);
		this.setVisible(true);
		}
	
	public void setBalanceLabel(int currentCash){
		balance.setText("Total cash: " + currentCash + "$");
	}
	
	public void drawMap(boolean[] map){
		for(int i = 0; i < slotPanels.length; i++){
			if(map[i+1] == true){ // there's a car parking at this slot
				System.out.println("parked");
				slotPanels[i].setBackground(Color.RED);
			} else { // slot is empty
				slotPanels[i].setBackground(Color.GREEN);
			}
		}
	}
	
	//public void 
}
