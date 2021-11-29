import javafx.scene.image.Image;

public class TileTunnel extends TileInteractable {

    public TileTunnel(int row, int column) {
    	super(new Image("images/tunnel_v2.png"), row, column);
        // Dark Brown
        setColour(158, 107, 30);
        setIsHidden(true);
    }
    
}
