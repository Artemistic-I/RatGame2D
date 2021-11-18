import java.util.ArrayList;
import java.util.Random;

public class Rat {

	private Boolean isMale;
	private Boolean isAdult;
	private Boolean isPregnant;
	private Boolean isSterile;
	private int speed;
	private int colour;
	private Tile tileTheRatIsOn;
	private String direction;

	public Tile move() {
		ArrayList<String> possibleMoves = tileTheRatIsOn.possibleMoves();
		if (possibleMoves.contains(direction)) {
			this.tileTheRatIsOn = tileTheRatIsOn.getNextTile(direction);
		} else {
			Random rand = new Random();
			this.direction = possibleMoves.get(rand.nextInt(possibleMoves.size() - 1));
			this.tileTheRatIsOn = tileTheRatIsOn.getNextTile(direction);
		}
		this.draw();
		
	}
	
	private void draw() {
		// TODO Auto-generated method stub
		
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
