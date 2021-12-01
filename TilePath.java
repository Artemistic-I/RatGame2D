import javafx.scene.image.Image;

public class TilePath extends TileInteractable {
	
	public static final Image PATH_GRAPHIC = new Image("images/path_v2.png");
	
	public TilePath(int row, int column) {
        super(PATH_GRAPHIC, row, column);
    }
    
}
