import java.util.concurrent.*;
import java.util.*;
public class Runway implements Delayed{
	
	boolean occupied = true;
	int waitTime = 60;
	
	private Runway runway;
	private long time;
	
	public Runway(long delayTime) {
		this.time = System.currentTimeMillis() + delayTime;
	}
	
	public void setDelayTime(long delayTime) {
		this.time = System.currentTimeMillis() + delayTime;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setOccupied() {
		
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
	

	@Override
	public int compareTo(Delayed obj) {
		if(this.time < ((Runway)obj).time) {
			return -1;
		}
		if(this.time > ((Runway)obj).time) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long diff = time - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public long getDelayTime() {
		return this.time;
	}
}

