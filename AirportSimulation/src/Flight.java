import java.util.Comparator;
import java.util.Date;
import java.util.UUID;
/**
 * This class creates new planes and contains the methods associated with the plane
 * @author brentreynolds
 * @version Fall 2022
 */
public class Flight  {
	
	 private String FlightIdentifier;
	
	 private int distanceFromAirport;
	
	 private String airplaneModel;
	
	 private boolean emergency = false; 
	 
	 private int randomEmergency;
	 
	 private String emergencyString = "";
	 
	 private int target = 1;
	
	 private Date dateCreated;
	
	 private int waitTime = 0;
	 
	 public int planeNumber = 1;

	String aircraftType [] = {"Airbus A220", "Airbus A320", "Airbus A330", 
							"Airbus A350","Boeing 737", "Airbus A380", "Boeing 747",
							"Boeing 767", "Boeing 787", "Boeing 757", "Boeing 717", 
							"CRJ100", "CRJ200", "CRJ440", "DC-8", "ERJ 175", "MD-11"};
	/**
	 * No arg constructor to make a new plane
	 */
	public Flight() {
		this.FlightIdentifier = RandomUUID().toUpperCase();
		this.distanceFromAirport = 10;
		this.airplaneModel = aircraftType[(int)(Math.random() * 17)];
		this.emergency = false;
		this.waitTime = 0;
		this.dateCreated = new Date();
		this.planeNumber = planeNumber;
	
	}
	
	/**
	 * This method returns the date that the plane was created
	 * @return the date the plane was created
	 */
	public Date getDate() {
		return this.dateCreated;
	}
	/**
	 * This method returns the planes emergency status
	 * @return the plane's emergency status
	 */
	public boolean getEmergency() {
		return this.emergency;
	}
	
	/**
	 * This method changes String emergencyString to "EMERGENCY" if the plane has an emergency
	 * It changes String emergencyString to "NORMAL" is the plane doesn't have an emergency
	 */
	public void emergencyStatus() {
		if(this.emergency == true) {
			emergencyString = "EMERGENCY";
		}
		else {
			emergencyString = "NORMAL";
		}
	}
	
		/**
		 * This method changes the planes emergency status to true if it is false when this mehtod is called
		 */
		public void flightEmergency() {
			if(emergency == false){
				emergency = true;
			}
		}
		
		/**
		 * This method generates a random emergency in a plane for each time tick in the simulation
		 */
		public void randomEmergency() {
			randomEmergency = (int)(Math.random() * 75);
			if(randomEmergency == target) {
				emergency = true;
			}
			this.emergencyStatus();
		}
	
	/**
	 * This method generates a random Flight Number such as #AF5567 for the plane
	 * @return a random String representation of a Flight Number
	 */
	public String RandomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}
	
	/**
	 * This method returns the plane's distance from the airport
	 * @return the plane's distance from the airport
	 */
	public int getDistanceFromAirport() {
		return this.distanceFromAirport;
	}
	
	/**
	 * This method sets the planes distance from the airport when it is generated
	 * @param distanceFromAirport the planes distance from the airport when generated
	 */
	public void setDistanceFromAirport(int distanceFromAirport) {
		this.distanceFromAirport = distanceFromAirport;
	}
	
	
	/**
	 * This method decrements the planes distance from the airport
	 */
	public void fly() {
		distanceFromAirport = distanceFromAirport - 1;
		}
	
	/**
	 *This method prints out the details of the flight
	 */
	@Override
	public String toString() {
		return "\nFlight #: " + this.FlightIdentifier + "\n---Airplane Type: " 
	+ this.airplaneModel + "\n---Distance From Airport: " + this.distanceFromAirport + " miles" + "\n---Flight status: " 
	+ this.emergencyString + " \n---Plane Number: " + this.planeNumber + "\n---Time: " 
	+ this.getDate() + "\n";
		
	}



}
