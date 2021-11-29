import javafx.scene.image.Image;

public class TileGrass extends Tile {
	
    public TileGrass(int row, int column) {
    	super(new Image("images/grass_v2.png"), row, column);
        // Dark Green
        setColour(0, 130, 0);
        setInteractable(false);
    }
    
}
