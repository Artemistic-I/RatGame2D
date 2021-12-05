import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.lang.Object;

public class Sterilisation extends Item{
    //works within small radius
    //rats cannot reproduce

	private static final Image STERILISATION_GRAPHIC = new Image("images/ItemGraphics/SterilisationGraphic.png");
    static final int SHORTCUT_KEY = 119; //bound to F8
    static final int AREA = 5;
    static final int TIME = 3;
    private CopyOnWriteArrayList<TileInteractable> sterilisedTiles;

    /**
     * constructor
     */
    public Sterilisation(TileInteractable tileTheItemIsOn) {
        super(STERILISATION_GRAPHIC, tileTheItemIsOn);
    }

    @Override
    void itemAction() { 
    }

    public void draw(GraphicsContext graphicsContext) {
    	for (TileInteractable tile: this.sterilisedTiles) {
    		if (!(tile instanceof TileTunnel)) {
    			graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
    		}
    	}
    }
}