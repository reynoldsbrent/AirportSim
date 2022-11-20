

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;


public class Application {

	public static void main(String[] args) {
		
		
		Timer timer = new Timer();
		TimerTask task = new Helper();
		
 		timer.schedule(task, 0, 1000);
 		
 		Timer timer1 = new Timer();
 		
 		
	}
}
class Helper extends TimerTask{

	Queue<Flight> RTL = new PriorityQueue<>();
	Queue<Flight> Approach = new LinkedList<>();
	
	Airport sdf = new Airport();
	int numberOfPlanesGenerated = 0;
	
	public void run() {
		
		int target = 1;
		int randomNum = (int)(Math.random() * 5);
		
		// Generates a new airplane if the randomly generated number is 1
		if(randomNum == target) {
		Flight flight = new Flight();
		numberOfPlanesGenerated++;
	
//		System.out.println("FLIGHT ADDED");
		//System.out.println(flight);
//		System.out.println("------------------------------------");
		

		if(flight.getDistanceFromAirport() > 5) {
			Approach.add(flight);
		}
	}// end
		
		// Decreases the distance of each plane in the approach queue. 
		//If the plane's distance is equal to 5, add it to the RTL queue and remove it from the Approach queue.
		Flight newFlight = new Flight();
		
		for(Flight plane : Approach) {
			plane.fly();
			if(plane.getDistanceFromAirport() > 5) {
			//System.out.println("Approach: " + plane.getDistanceFromAirport());
			}
			
			if(plane.getDistanceFromAirport() == 5) {
				newFlight = plane;
				RTL.add(newFlight);
			}
		}
		
		Approach.remove(newFlight);
		//end

		

		int planesLanded = 0;
		
		for(Flight plane : RTL) {
			
			//System.out.println("Number of planes Ready To Land: " + RTL.size());
			for(Runway runway : sdf.runways) {
				
				if(!runway.isOccupied()) {
					planesLanded++;
					
					//RTL.poll();
					runway.setOccupied();
					System.out.println("Flight has landed.");
					break;
				}
				else {
					runway.waitTime();
				}
			}
		}
		
		for(int i = 0; i < planesLanded; i++) {
			RTL.poll();
		}
		
		
//		System.out.println("Number Of Flights In Approach: " + Approach.size());
//		System.out.println("------------------------------------");
//		System.out.println("Number Of Flights Ready To Land: " + RTL.size());
//		System.out.println("------------------------------------");
		System.out.println("Number of planes generated: " + numberOfPlanesGenerated);
//		System.out.println("*******************************************************");
//		System.out.println("*******************************************************");
//		System.out.println("*******************************************************");
//		System.out.println("*******************************************************");
//		System.out.println("*******************************************************");
		}
	
	}
	


