

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


//Need to randomly create an plane emergency. If the plane has an emergency,
// once the plane is dequeued from Approach enqueue it to RTL and have it at the top of the queue

public class Application {
	
	public static void main(String[] args) {
		
		
		Timer timer = new Timer();
		TimerTask task = new Helper();
		
 		timer.schedule(task, 0, 1000);
 		
	}

}

// ---------------------------------------------------------------------------------------------------

class Helper extends TimerTask{
	Airport sdf = new Airport();
	int numberOfPlanesGenerated = 0;
	
	private JLabel planesGeneratedLabel;
	private JLabel planeAdded;
	private JLabel currentTimeLabel;
	private JLabel runwayOne;
	private JLabel runwayTwo;
	private JLabel runwayThree;
	private JFrame frame;
	private JPanel panel;
	private JPanel middlePanel;
	private JPanel approachPanel;
	private JPanel rtlPanel;
	private JPanel topPanel;
	private JTextArea approach;
	private JTextArea rtl;
	private JScrollPane approachScroll;
	private JScrollPane rtlScroll;
	
	Color lightBlue = new Color(11, 146, 249);
	Color offWhite = new Color(220, 228, 232);
	
	public Helper() {
		frame = new JFrame();
		
		currentTimeLabel = new JLabel("Current Time: ");
		currentTimeLabel.setFont(new Font ("Segio UI", Font.PLAIN, 14));
		currentTimeLabel.setForeground(offWhite);
		
		planesGeneratedLabel = new JLabel("Number of planes generated : 0   ");
		planesGeneratedLabel.setFont(new Font("Segio UI", Font.PLAIN, 14));
		planesGeneratedLabel.setForeground(offWhite);
		
		
		planeAdded = new JLabel("New Flight: ");
		planeAdded.setFont(new Font("Segio UI", Font.PLAIN, 14));
		
		
		runwayOne = new JLabel("First Available RW Time to Clear: 0 sec" );
		runwayOne.setFont(new Font("Segio UI", Font.PLAIN, 14));
		
		
		runwayTwo = new JLabel("Second Available RW Time to Clear: 0 sec");
		runwayTwo.setFont(new Font("Segio UI", Font.PLAIN, 14));
		
		runwayThree = new JLabel("Third Available RW Time to Clear: 0 sec");
		runwayThree.setFont(new Font("Segio UI", Font.PLAIN, 14));
	
		panel = new JPanel();
		panel.setBounds(0, 0, 1400, 75);
		panel.setBackground(lightBlue);
		panel.add(planesGeneratedLabel);
		panel.add(currentTimeLabel);

		topPanel = new JPanel();
		topPanel.setBounds(0, 75, 1400, 100);
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(offWhite);
		topPanel.add(planeAdded);
		
		middlePanel = new JPanel();
		middlePanel.setBounds(0, 175, 1400, 75);
		middlePanel.setLayout(new FlowLayout());
		middlePanel.setBackground(offWhite);
		middlePanel.add(runwayOne);
		middlePanel.add(runwayTwo);
		middlePanel.add(runwayThree);
		
		approachPanel = new JPanel();
		approachPanel.setBounds(0, 250, 700, 550);
		approachPanel.setLayout(new BorderLayout());
		approachPanel.setBackground(lightBlue);
		
		
		rtlPanel = new JPanel();
		rtlPanel.setBounds(700,250,700,550);
		rtlPanel.setLayout(new BorderLayout());
		rtlPanel.setBackground(offWhite);
		
		
		approach = new JTextArea("", 5, 20);
		approach.setEditable(false);
		approach.setText("Approach size: " + Approach.size());
		approach.setFont(new Font("Segio UI", Font.PLAIN, 10));
		approachScroll = new JScrollPane(approach);
		approachScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		approachPanel.add(approachScroll);
		
		
		rtl = new JTextArea("", 5, 20);
		rtl.setEditable(false);
		rtl.setText("RTL size: " + RTL.size());
		rtl.setFont(new Font("Segio UI", Font.PLAIN, 10));
		rtlScroll = new JScrollPane(rtl);
		rtlScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rtlPanel.add(rtlScroll);


		frame.add(panel);
		frame.add(topPanel);
		frame.add(middlePanel);
		frame.add(approachPanel);
		frame.add(rtlPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Airport Simulation - Brent Reynolds");
		frame.setLayout(null);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1400,800);
		
		
		
	}
	
	public void Graphics() {
		new Helper();
	}
	
	Queue<Flight> Approach = new LinkedList<>();
	Queue<Flight> printApproach = new LinkedList<>();
	Queue<Flight> RTL = new PriorityQueue<Flight>(1000000, new FlightComparator());
	Queue<Flight> planesToRemove = new PriorityQueue<Flight>(1000000, new FlightComparator());
	Queue<Flight> printPlanes = new PriorityQueue<Flight>(1000000, new FlightComparator());

// ---------------------------------------------------------------------------------------------------
	
	public void run() {
		
		int target = 1;
		int randomNum = (int)(Math.random() * 10);

		Date date = new Date();
		currentTimeLabel.setText("Current Time: " + date);
		
		int count = 1;
		for(Runway rw : sdf.runway) {
			if(count == 1) {
				runwayOne.setText("First Available RW Time to Clear: " + rw.getRemainingTime() + " sec--");
			}
			if(count == 2) {
				runwayTwo.setText("Second Availabel RW Time to Clear: " + rw.getRemainingTime() + " sec--");
			}
			if(count == 3) {
				runwayThree.setText("Third Available RW Time To Clear: " + rw.getRemainingTime() + " sec");
			}
			count++;
		}
		
		
		
		
		
		String showApproach = "Approach size: " + Approach.size();
		approach.setText(showApproach);
		
 		while(!printApproach.isEmpty()) {
			showApproach = showApproach + printApproach.remove();
			approach.setText(showApproach);
		}
		
		// Generates a new airplane if the randomly generated number is 1
		if(randomNum == target) {
		Flight flight = new Flight();
		flight.randomEmergency();
		numberOfPlanesGenerated++;
		planesGeneratedLabel.setText("Number of planes generated: " + numberOfPlanesGenerated + "   ");
		flight.planeNumber = numberOfPlanesGenerated;
		planeAdded.setText("New Flight: " + flight);
		

		if(flight.getDistanceFromAirport() > 5) {
			Approach.add(flight);
		}
	}// end
		
		
		
		
		
		// ---------------------------------------------------------------------------------------------------
		
		// Decreases the distance of each plane in the approach queue. 
		//If the plane's distance is equal to 5, add it to the RTL queue and remove it from the Approach queue.
		Flight newFlight = new Flight();
		
		for(Flight plane : Approach) {
			if(plane.getEmergency() == false) {
				plane.randomEmergency();
			}
			plane.fly();
			if(plane.getDistanceFromAirport() > 5) {
			//System.out.println("Approach: " + plane.getDistanceFromAirport());
			}
			
			if((plane.getDistanceFromAirport() == 5)) {
				newFlight = plane;
				RTL.add(newFlight);
			}
		}
		
		
		Approach.remove(newFlight);
		//end
		
		
		// ---------------------------------------------------------------------------------------------------		
		
		int planesLanded = 0;
		for(Flight plane : RTL) {
			if(plane.getEmergency() == false) {
			plane.randomEmergency();
			}
			planesToRemove.add(plane);

			Runway runway = new Runway();
			for(Runway rw : sdf.runway) {
				if(sdf.runway.poll() != null) {
					System.out.println("Runway is clear for landing.");
					System.out.println("Flight has landed.");
					// Need to change to 5 minutes for project requirements
					sdf.addRunway(runway, 60000);
					planesLanded++;
					
					System.out.println(sdf.runway.size());
					
					break;
				}
			}
			
		}
		
		//System.out.println("Number of planes Ready To Land: " + RTL.size());
		
		RTL.removeAll(planesToRemove);
		RTL.addAll(planesToRemove);
		planesToRemove.clear();
		
		
		for(int i = 0; i < planesLanded; i++) {
			RTL.poll();
		}
		
		printPlanes.addAll(RTL);
		
		printApproach.addAll(Approach);
		
		
		
		String rtlPrint = "RTL size: " + RTL.size();
		
		while(!printPlanes.isEmpty()) {
			rtlPrint = rtlPrint + printPlanes.remove();
			rtl.setText(rtlPrint);
			//System.out.println(printPlanes.remove());
		}
		
		//System.out.println("Number Of Flights In Approach: " + Approach.size());
		//System.out.println("------------------------------------");
		//System.out.println("Number Of Flights Ready To Land: " + RTL.size());
		//System.out.println("--------------------------------------");
		//System.out.println(RTL);
		//System.out.println("**************************************");
//		System.out.println("------------------------------------");
		//System.out.println("Number of planes generated: " + numberOfPlanesGenerated);
//		System.out.println("*******************************************************");

		}
	
	}
	


