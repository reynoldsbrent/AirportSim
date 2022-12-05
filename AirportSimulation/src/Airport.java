
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
/**
 * This class generates a new airport and the methods used to run the airport runways
 * @author brentreynolds
 * @version Fall 2022
 */
public class Airport {

	BlockingQueue<Runway> runway = new DelayQueue<Runway>();
	
	/**
	 * No arg constructor creates a new airport with runways
	 */
	public Airport() {
		Runway rw1 = new Runway();
		rw1.setDelayTime(0);
		Runway rw2 = new Runway();
		rw2.setDelayTime(0);
		Runway rw3 = new Runway();
		rw3.setDelayTime(0);
		
		runway.add(rw1);
		runway.add(rw2);
		runway.add(rw3);
		
		
	}
	
	/**
	 * This method removes a runway from the queue
	 * @param rw the runway to remove from the airport
	 */
	public void removeRunway(Runway rw) {
		if(runway.poll() != null) {
			//System.out.println("Runway is clear for landing.");
		}
	}
	
	/**
	 * This method adds a runway to the queue
	 * @param rw the runway to add to the queue
	 * @param delayTime the amount of delay time to set the runway to
	 */
	public void addRunway(Runway rw, int delayTime) {
		if(runway.size() < 3) {
		rw.setDelayTime(delayTime);
		runway.add(rw);
		//System.out.println("Runway is occupied.");
		}
	}

}




