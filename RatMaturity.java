
public enum RatMaturity {
	ADULT(1), BABY(2);
	
	private final int speed;
	
	RatMaturity(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
}