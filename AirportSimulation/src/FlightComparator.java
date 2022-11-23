import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight plane1, Flight plane2) {
		
		//int comparison = 0;
		//comparison = plane1.getEmergency().compareTo(plane2.getEmergency());
		//if(comparison == 0) {
			//comparison = plane1.getDate().compareTo(plane2.getDate());
			//comparison = Long.compare(plane1.getDate().getTime(), plane1.getDate().getTime());
			
//			if(comparison < 0) {
//				comparison = 1;
//			}
		//}
		 //return comparison;
		
		
		if(plane1.getEmergency() && !plane2.getEmergency()) {
			return -1;
		}
		if(!plane1.getEmergency() && plane2.getEmergency()) {
			return 1;
		}
	
		return Long.compare(plane1.getDate().getTime(), plane2.getDate().getTime());
		 
		 
	}

}
