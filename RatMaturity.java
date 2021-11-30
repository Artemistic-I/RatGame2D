import javafx.scene.image.Image;

public enum RatMaturity {
	ADULT(1), BABY(2, "images/uglyBabyRat.png");
	
	private final int speed;
	private Image graphic;
	
	RatMaturity(int speed, String fileName) {
		this.speed = speed;
		graphic = new Image(fileName);
	}
	
	RatMaturity(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return this.speed;
	}
	
	public Image getGraphic() {
		return this.graphic;
	}
}