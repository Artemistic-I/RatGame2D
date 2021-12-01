import javafx.scene.image.Image;

public class TileGrass extends Tile {
	
	private static final Image GRASS_GRAPHIC = new Image("images/grass_v2.png");

    public TileGrass(int row, int column) {
    	super(GRASS_GRAPHIC, row, column);
    }
    
}
