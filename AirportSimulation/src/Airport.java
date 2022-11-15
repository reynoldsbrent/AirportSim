
import java.util.*;
public class Airport {

	Runway rw1129;
	int windDirection = (int)(Math.random() * 360);
	int windSpeed = (int)(Math.random() * 20);
	
	public Airport(ArrayList<Runway> runway) {
		Runway rw1 = new Runway();
		Runway rw2 = new Runway();
		Runway rw3 = new Runway();
	
		List<Runway> runways = new ArrayList<Runway>();
		runways.add(rw1);
		runways.add(rw2);
		runways.add(rw3);
		
	}

}

class Helper extends TimerTask{
	Queue<Flight> RTL = new PriorityQueue<>();
	Queue<Flight> Approach = new LinkedList<>();
	
	
	
	public void run() {
		int target = 1;
		int randomNum = (int)(Math.random() * 2);
		if(randomNum == target) {
		Flight flight = new Flight();
		
	
		System.out.println("FLIGHT ADDED");
		System.out.println(flight);
		System.out.println("------------------------------------");
		

		if(flight.getDistanceFromAirport() > 5) {
			Approach.add(flight);
		}
		Flight newFlight = new Flight();
		for(Flight plane : Approach) {
			plane.fly();
			if(plane.getDistanceFromAirport() > 5) {
			System.out.println("Approach: " + plane.getDistanceFromAirport());
			}
			
			if(plane.getDistanceFromAirport() == 5) {
				newFlight = plane;
				RTL.add(newFlight);
			}
		}
		
		Approach.remove(newFlight);

		
		
		for(Flight plane : RTL) {
			plane.fly();
			System.out.println("RTL: " + plane.getDistanceFromAirport());
			
		}
		
		System.out.println("Number Of flights In Approach: " + Approach.size());
		System.out.println("------------------------------------");
		System.out.println("Number Of Flights Ready To Land: " + RTL.size());
		System.out.println("------------------------------------");
		
		}
	
	}
	
}


