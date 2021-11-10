
public class Rat {

	private Boolean isMale;
	private Boolean isAdult;
	private Boolean isPregnant;
	private Boolean isSterile;
	private int speed;
	private int colour;
	private Tile tileTheRatIsOn;

	public Tile move() {
		return tileTheRatIsOn; //Just for testing
	}
	
	public Boolean getIsMale() {
		return isMale;
	}
	
	public Boolean getIsAdult() {
		return isAdult;
	}
	
	public Boolean getIsPregnant() {
		return isPregnant;
	}
	
	public Boolean getIsSterile() {
		return isSterile;
	}
	
	public void breed() {
		
	}
	
	public void giveBirth() {
		
	}
	
	public void changeSex() {
		
	}
}
