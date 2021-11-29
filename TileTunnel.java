import javafx.scene.image.Image;

public class TileTunnel extends TileInteractable {

    public TileTunnel() {
    	super(new Image("images/tunnel.png"));
        // Dark Brown
        setColour(158, 107, 30);
        setIsHidden(true);
    }
    
}
