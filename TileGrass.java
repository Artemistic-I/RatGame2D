import javafx.scene.image.Image;

public class TileGrass extends Tile {
	
    public TileGrass() {
    	super(new Image("images/grass.png"));
        // Dark Green
        setColour(0, 130, 0);
        setInteractable(false);
    }
    
}
