import javafx.scene.image.Image;

public class TilePath extends TileInteractable {
	
	public TilePath(int row, int column) {
        super(new Image("images/path_v2.png"), row, column);
        // Light Brown
        setColour(219, 149, 44);
        setIsHidden(false);
    }
    
}
