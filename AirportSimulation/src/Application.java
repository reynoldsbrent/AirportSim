
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Application {

	public static void main(String[] args) {
		ArrayList<Runway> rw = new ArrayList<Runway>();
		
		Airport sdf = new Airport(rw);
		
		int second = 1000;
		int fpsLimit = 1;
		
		Timer timer = new Timer();
		TimerTask task = new Helper();
		
 		timer.schedule(task, 0, 2000);
 		
 		Timer timer1 = new Timer();
 		
 		
	}
}

