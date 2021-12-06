import javafx.scene.image.Image;

public class TileGrass extends Tile {
	
	private static final Image GRASS_GRAPHIC = new Image("images/grass_v2.png"); // image for this tile

    /**
     * 
     * @param row the rows it's in, in the gameboard 2d array
     * @param column the column it's in, in the gameboard 2d array
     */
    public TileGrass(int row, int column) {
    	super(GRASS_GRAPHIC, row, column);
    }
    
}
