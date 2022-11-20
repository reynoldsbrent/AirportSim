import java.util.TimerTask;
import java.util.Timer;

public class Runway {
	
	boolean occupied = false;
	int waitTime = 60;
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied() {
		Timer timer = new Timer();
		TimerTask task = new Helper();
		
		if(occupied) {
			occupied = false;
		}
		else {
			occupied = true;
		}
	}
	
	public void waitTime() {
		if(occupied) {
			waitTime--;
		}
		if(waitTime == 0) {
			occupied = false;
			System.out.println("Runway is cleared");
		}
	}
	
}
