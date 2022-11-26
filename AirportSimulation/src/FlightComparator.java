import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight plane1, Flight plane2) {
		
		if(plane1.getEmergency() && !plane2.getEmergency()) {
			return -1;
		}
		if(!plane1.getEmergency() && plane2.getEmergency()) {
			return 1;
		}
	
		return Long.compare(plane1.getDate().getTime(), plane2.getDate().getTime());
		 
		 
	}

}
