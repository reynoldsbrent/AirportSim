import java.util.Comparator;

/**
 * This class contains the compare method that orders the planes in the queue
 * @author brentreynolds
 * @version Fall 2022
 */
public class FlightComparator implements Comparator<Flight> {

	/**
	 * This method orders the planes in the queue by emergency status first, then the time the plane was generated second
	 * @param Flight plane1 is the first plane to be compared. Flight plane2 is the second plane to be compared
	 * @return if plane1 should be ordered above or below plane2
	 */
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
