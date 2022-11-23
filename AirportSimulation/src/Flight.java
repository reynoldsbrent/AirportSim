import java.util.Comparator;
import java.util.Date;
import java.util.UUID;
public class Flight  {
	
	 private String FlightIdentifier;
	
	 private int distanceFromAirport;
	
	 private String airplaneModel;
	
	 public boolean emergency = false; 
	 
	 private int randomEmergency;
	 
	 private int target = 1;
	
	//public String emergency = "B";
	
	public Date dateCreated;
	
	public int waitTime = 0;
	
	// need to make getter and setter
	public int planeNumber = 1;

	String aircraftType [] = {"Airbus A220", "Airbus A320", "Airbus A330", 
							"Airbus A350","Boeing 737", "Airbus A380", "Boeing 747",
							"Boeing 767", "Boeing 787", "Boeing 757", "Boeing 717", 
							"CRJ100", "CRJ200", "CRJ440", "DC-8", "ERJ 175", "MD-11"};
	//changed ******
	public Flight() {
		this.FlightIdentifier = RandomUUID().toUpperCase();
		this.distanceFromAirport = 10;
		this.airplaneModel = aircraftType[(int)(Math.random() * 17)];
		this.emergency = false;
		this.waitTime = 0;
		this.dateCreated = new Date();
		this.planeNumber = planeNumber;
	
	}
	
	public Date getDate() {
		return this.dateCreated;
	}
	//changed ********
	public boolean getEmergency() {
		return this.emergency;
	}
	
	public void setWaitTime(Flight flight) {
		flight.waitTime++;
	}
	
	//chenaged *******
	// sets the planes status if it has an emergency or not
	public void setPlaneStatus(boolean flightStatus) {
		this.emergency = true;
	}
	
	public String RandomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}
	
	public int getDistanceFromAirport() {
		return this.distanceFromAirport;
	}
	
	public void setDistanceFromAirport(int distanceFromAirport) {
		this.distanceFromAirport = distanceFromAirport;
	}
	
	// changed **********
	public void flightEmergency() {
		if(emergency == false){
			emergency = true;
		}
	}
	
	public void fly() {
		distanceFromAirport = distanceFromAirport - 1;
		}
	
	public void randomEmergency() {
		randomEmergency = (int)(Math.random() * 25);
		if(randomEmergency == target) {
			emergency = true;
		}
	}
	
	@Override
	public String toString() {
		return "\nFlight #: " + this.FlightIdentifier + "\nAirplane Type: " 
	+ this.airplaneModel + "\nDistance From Airport: " + this.distanceFromAirport + " miles" + "\nFlight status: " 
	+ this.emergency /*+ " Wait time: " + this.waitTime*/ + " \nPLANE NUMBER: " + this.planeNumber + "\nTime: " 
	+ this.getDate().getTime() + "\n";
		
	}



}
