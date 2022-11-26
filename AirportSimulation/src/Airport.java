
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
public class Airport {

	List<Runway> runways = new ArrayList<Runway>();
	BlockingQueue<Runway> runway = new DelayQueue<Runway>();
	
	public Airport() {
		Runway rw1 = new Runway();
		rw1.setDelayTime(0);
		Runway rw2 = new Runway();
		rw2.setDelayTime(0);
		Runway rw3 = new Runway();
		rw3.setDelayTime(0);
		
		runways.add(rw1);
		runways.add(rw2);
		runways.add(rw3);
		
		runway.add(rw1);
		runway.add(rw2);
		runway.add(rw3);
		
		
	}
	
	public void removeRunway(Runway rw) {
		if(runway.poll() != null) {
			System.out.println("Runway is clear for landing.");
		}
	}
	
	public void addRunway(Runway rw, int delayTime) {
		if(runway.size() < 3) {
		rw.setDelayTime(delayTime);
		runway.add(rw);
		System.out.println("Runway is occupied.");
		}
	}

}




