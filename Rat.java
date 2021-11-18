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

	public void move() {
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

	public Boolean canBreed() {
		return (isAdult && !isPregnant && !isSterile);
	}
	
	public void Breed() {
		
	}
	
	public void giveBirth() {
		
	}
	
	public void changeSex() {
		
	}
	
	public Tile getLocation() {
		return this.tileTheRatIsOn;
	}
	
	public Boolean getSex() {
		return isMale;
	}
}
