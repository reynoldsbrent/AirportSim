import java.util.UUID;
public class Flight implements Comparable<Flight> {
	
	private String FlightIdentifier;
	
	private int distanceFromAirport;
	
	private String airplaneModel;

	String aircraftType [] = {"Airbus A220", "Airbus A320", "Airbus A330", 
							"Airbus A350","Boeing 737", "Airbus A380", "Boeing 747",
							"Boeing 767", "Boeing 787", "Boeing 757", "Boeing 717", 
							"CRJ100", "CRJ200", "CRJ440", "DC-8", "ERJ 175", "MD-11"};
	
	public Flight() {
		this.FlightIdentifier = RandomUUID().toUpperCase();
		this.distanceFromAirport = 10;
		this.airplaneModel = aircraftType[(int)(Math.random() * 17)];
	
	}
	
	public String RandomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}
	
	public void generateFlight() {
		int target = 1;
		int randomNum = (int)(Math.random() * 6);
		
		if(randomNum == target) {
			Flight flight = new Flight();
			
		}
	}
	
	public int getDistanceFromAirport() {
		return this.distanceFromAirport;
	}
	
	public void setDistanceFromAirport(int distanceFromAirport) {
		this.distanceFromAirport = distanceFromAirport;
	}
	
	
	public boolean flightEmergency(Object flight) {
		int target = 1;
		int randomNumber = (int)(Math.random() * 6);
		if(target == randomNumber) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double glideSlope(Flight flight) {
		for(int i = flight.getDistanceFromAirport(); i > 0; i--) {
			flight.setDistanceFromAirport(i);
		}
		return flight.getDistanceFromAirport();
	}
	
	public void fly() {
		distanceFromAirport = distanceFromAirport - 1;
	}
	
	@Override
	public String toString() {
		return "Flight #: " + this.FlightIdentifier + "\nAirplane Type: " + this.airplaneModel + "\nDistance From Airport: " + this.distanceFromAirport + " miles";
	}

	@Override
	public int compareTo(Flight o) {
		// TODO Auto-generated method stub
		return this.distanceFromAirport - o.distanceFromAirport;
	}
}
