
import java.util.*;
public class Airport {

	List<Runway> runways = new ArrayList<Runway>();
	
	public Airport() {
		Runway rw1 = new Runway();
		Runway rw2 = new Runway();
		Runway rw3 = new Runway();
		
		runways.add(rw1);
		runways.add(rw2);
		runways.add(rw3);
		
	}

}




