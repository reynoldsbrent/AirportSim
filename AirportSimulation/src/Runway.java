import java.util.concurrent.*;
import java.util.*;
/**
 * This class creates new runways
 * and contains the methods responsible for runway sorting and delay time
 * @author brentreynolds
 * @version Fall 2022
 */
public class Runway implements Delayed{
	
	boolean occupied = true;
	int waitTime = 60;
	
	private Runway runway;
	private long time;
	private long currentTime = System.currentTimeMillis();
	private long delayTimeInput;
	
	/**
	 * No arg constructor for the runway
	 */
	public Runway() {
	
	}
	
	/**
	 * @param delayTime is the amount of time the runway will be occupied when an airplane lands on it
	 */
	public void setDelayTime(long delayTime) {
		delayTimeInput = delayTime;
		this.time = System.currentTimeMillis() + delayTime;
	}
	

	/**
	 * This method prioritizes the runways in the delayed queue
	 * @param a runway in the delayed queue
	 * @return the runways position in the delayed queue
	 */
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

	/**
	 * This method returns the amount of delay time left on each runway 
	 * which is used to order the runways in the queue
	 * @param TimeUnit unit the time unit the delay is returned in
	 * @return the amount of delay time left
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long diff = time - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * This method returns the time when runway's delay will expire
	 * @return the delay time on the runway
	 */
	public long getDelayTime() {
		return this.time;
	}
	
	/** This method returns the remaining delay time on the runway in seconds
	 * @return a String representation of the amount of remaining delay on the runway
	 */
	public String getRemainingTime() {
		if(delayTimeInput == 0) {
			return String.valueOf(delayTimeInput);
		}
		long seconds = (time - System.currentTimeMillis())/1000l;
		String remainingTime = String.valueOf(seconds);
		return remainingTime;
	}

}

