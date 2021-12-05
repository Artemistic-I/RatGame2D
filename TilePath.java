import javafx.scene.image.Image;

public class TilePath extends TileInteractable {
	
	private static final Image PATH_GRAPHIC = new Image("images/path_v3.png");
	
	public TilePath(int row, int column) {
        super(PATH_GRAPHIC, row, column);
    }
    
}
