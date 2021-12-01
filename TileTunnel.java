import javafx.scene.image.Image;

public class TileTunnel extends TileInteractable {

	private static final Image TUNNEL_GRAPHIC = new Image("images/tunnel_v2.png");
	
    public TileTunnel(int row, int column) {
    	super(TUNNEL_GRAPHIC, row, column);
    }
    
}
