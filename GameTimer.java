import java.util.concurrent.TimeUnit;

public class GameTimer {
	
	private long startTime;
	
	public GameTimer() {
			this.startTime = System.nanoTime();
	}
	
	public int getDuration() {
		long nanosecondDifference = Math.abs(System.nanoTime() - startTime);
		int secondDifference = (int) TimeUnit.NANOSECONDS.toSeconds(nanosecondDifference);
		return secondDifference;
	}
}
