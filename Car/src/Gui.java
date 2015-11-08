import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JFrame{
	
	JFrame j;
	boolean hasClickLeft=false;
	boolean hasClickMid=false;
	boolean hasClickRight=false;

	public Gui(){
		this.setTitle("JortNaiDee");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	public void initGUI(){
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
		int totalPay = 0;
		JLabel total = new JLabel("Total cash: "+totalPay+"$");
		Font totalFont = new Font("Tahoma",Font.BOLD,20);
		total.setFont(totalFont);
		total.setBackground(Color.WHITE);
		
		buttonPanel.add(total);
		buttonPanel.add(mapButton);
		buttonPanel.add(payButton);
		
		JPanel parkingPanel = new JPanel();
		parkingPanel.setBackground(Color.BLACK);
		parkingPanel.setBounds(0, 100, 800, 500);
		parkingPanel.setLayout(null);
		
		JPanel parkingPanelLeft = new JPanel();
		parkingPanelLeft.setBackground(Color.WHITE);
		parkingPanelLeft.setBounds(15,0, 250, 500);
		JPanel parkingPanelMid = new JPanel();
		parkingPanelMid.setBackground(Color.WHITE);
		parkingPanelMid.setBounds(275,0, 250, 500);
		JPanel parkingPanelRight = new JPanel();
		parkingPanelRight.setBackground(Color.WHITE);
		parkingPanelRight.setBounds(535,0, 250, 500);
		parkingPanelLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hasClickLeft){
                	System.out.println("LeaveL");
                	hasClickLeft=!hasClickLeft;
                }else{
                	System.out.println("ParkL");
                	hasClickLeft=!hasClickLeft;
                }
        }});
		parkingPanelMid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hasClickMid){
                	System.out.println("LeaveM");
                	hasClickMid=!hasClickMid;
                }else{
                	System.out.println("ParkM");
                	hasClickMid=!hasClickMid;
                }
        }});
		parkingPanelRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(hasClickRight){
                	System.out.println("LeaveR");
                	hasClickRight=!hasClickRight;
                }else{
                	System.out.println("ParkR");
                	hasClickRight=!hasClickRight;
                }
        }});
		
		
		namePanel.add(name);
		topPanel.add(namePanel);
		topPanel.add(buttonPanel);
		parkingPanel.add(parkingPanelLeft);
		parkingPanel.add(parkingPanelMid);
		parkingPanel.add(parkingPanelRight);
		this.add(topPanel);
		this.add(parkingPanel);
		this.setVisible(true);
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gui g=new Gui();
	}

}
