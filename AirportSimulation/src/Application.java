

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

	Queue<Flight> RTL = new PriorityQueue<Flight>(1000000, new FlightComparator());
	
	Queue<Flight> Approach = new LinkedList<>();
	Queue<Flight> planesToRemove = new PriorityQueue<Flight>(1000000, new FlightComparator());
	Queue<Flight> printPlanes = new PriorityQueue<Flight>(1000000, new FlightComparator());
	
	Airport sdf = new Airport();
	int numberOfPlanesGenerated = 0;
	
	
	
// ---------------------------------------------------------------------------------------------------
	
	public void run() {
		
		int target = 1;
		int randomNum = (int)(Math.random() * 5);
		//int randomEmergency = (int)(Math.random() * 25); 
		
		
		
		
		// Generates a new airplane if the randomly generated number is 1
		if(randomNum == target) {
		Flight flight = new Flight();
		numberOfPlanesGenerated++;
		flight.planeNumber = numberOfPlanesGenerated;
	
//		System.out.println("FLIGHT ADDED");
		//System.out.println(flight);
//		System.out.println("------------------------------------");
		

		if(flight.getDistanceFromAirport() > 5) {
			Approach.add(flight);
		}
	}// end
		
		
		
		
		
		// ---------------------------------------------------------------------------------------------------
		
		// Decreases the distance of each plane in the approach queue. 
		//If the plane's distance is equal to 5, add it to the RTL queue and remove it from the Approach queue.
		Flight newFlight = new Flight();
		
		for(Flight plane : Approach) {
			plane.randomEmergency();
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
		
		
		//System.out.println(RTL);
		//System.out.println("**************************************");
		int planesLanded = 0;
		for(Flight plane : RTL) {
			if(plane.getEmergency() == false) {
			plane.randomEmergency();
			}
			planesToRemove.add(plane);
			//plane.setWaitTime(plane);
			//System.out.println(count);
			//System.out.println(plane);
			//Generates random emergency
			
//			if((randomEmergency == target) && plane.getEmergency() != true) {
//				plane.flightEmergency();
//				planesToRemove.add(plane);
//				
//			}
//			if(plane.getEmergency() == true && !planesToRemove.contains(plane)) {
//				planesToRemove.add(plane);
//			}
			
			for(Runway rw : sdf.runway) {
				
				if(sdf.runway.poll() != null) {
					//System.out.println("Runway is clear for landing.");
					//System.out.println("Flight has landed.");
					// Need to change to 5 minutes for project requirements
					sdf.addRunway(rw, 60000);
					
					planesLanded++;
					
					//System.out.println(sdf.runway.size());
					break;
				}
			}
		}
		System.out.println("Number of planes Ready To Land: " + RTL.size());
		
		RTL.removeAll(planesToRemove);
		RTL.addAll(planesToRemove);
		planesToRemove.clear();
		
		
		for(int i = 0; i < planesLanded; i++) {
			RTL.poll();
		}
		
		printPlanes.addAll(RTL);
		
		
		//System.out.println("Number Of Flights In Approach: " + Approach.size());
		//System.out.println("------------------------------------");
		//System.out.println("Number Of Flights Ready To Land: " + RTL.size());
		
		while(!printPlanes.isEmpty()) {
			System.out.println(printPlanes.remove());
		}
		
		System.out.println("--------------------------------------");
		System.out.println(RTL);
		System.out.println("**************************************");
//		System.out.println("------------------------------------");
		//System.out.println("Number of planes generated: " + numberOfPlanesGenerated);
//		System.out.println("*******************************************************");

		
		}
	
	}
	


