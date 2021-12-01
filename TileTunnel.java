import javafx.scene.image.Image;

public class TileTunnel extends TileInteractable {

	public static final Image TUNNEL_GRAPHIC = new Image("images/path_v2.png");
	
    public TileTunnel(int row, int column) {
    	super(TUNNEL_GRAPHIC, row, column);
    }
    
}
