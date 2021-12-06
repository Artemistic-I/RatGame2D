import javafx.scene.image.Image;

public class TilePath extends TileInteractable {
	
	private static final Image PATH_GRAPHIC = new Image("images/path_v3.png"); // image for this tile
	
    /**
     * 
     * @param row the rows it's in, in the gameboard 2d array
     * @param column the column it's in, in the gameboard 2d array
     */
	public TilePath(int row, int column) {
        super(PATH_GRAPHIC, row, column);
    }
    
}
