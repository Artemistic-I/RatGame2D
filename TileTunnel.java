import javafx.scene.image.Image;

public class TileTunnel extends TileInteractable {

	private static final Image TUNNEL_GRAPHIC = new Image("images/tunnel_v3.png"); // image for this tile
	
    /**
     * 
     * @param row the rows it's in, in the gameboard 2d array
     * @param column the column it's in, in the gameboard 2d array
     */
    public TileTunnel(int row, int column) {
    	super(TUNNEL_GRAPHIC, row, column);
    }
    
}
