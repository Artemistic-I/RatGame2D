import javafx.scene.image.Image;

public enum RatSex {
	MALE("images/MaleRat.png"), FEMALE("images/FemaleRat.png");
	
	private Image graphic;
	
	RatSex(String fileName) {
		this.graphic = new Image(fileName);
	}
	
	public Image getGraphic() {
		return this.graphic;
	}
}