import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Gas extends LethalItem{

	private static final Image GAS_GRAPHIC = new Image("images/ItemGraphics/GasGraphic.png");
	private static final int RAT_EXPOSURE_LIMIT = 3;
	private static final int GAS_EXPAND_TIME = 5;
	private static final int GAS_EXIST_TIME = 10;
	private int gasTimeElapsed;
    private CopyOnWriteArrayList<TileInteractable> gassedTiles;
    private ArrayList<Rat> gassedRats;

    public Gas(TileInteractable tileTheItemIsOn) {
        super(GAS_GRAPHIC, tileTheItemIsOn);
        gassedTiles = new CopyOnWriteArrayList<>();
        gassedTiles.add(tileTheItemIsOn);
        gassedRats = new ArrayList<Rat>();
        this.gasTimeElapsed = 0;
    }
    
    @Override
    void itemAction() { 
    	if (gasTimeElapsed <= GAS_EXPAND_TIME) {
            expand();    		
    	}
        this.gassedRats.addAll(RatManager.ratsOnTiles(new ArrayList<TileInteractable>(gassedTiles)));
        for (Rat rat: gassedRats) {
        	if (Collections.frequency(gassedRats, rat) > 3) {
        		RatManager.removeRat(rat);
        	}
        }
        if (gasTimeElapsed > GAS_EXIST_TIME) {
        	ItemManager.removeItem(this);
        }
        gasTimeElapsed++;
    }

    private void expand(){
        for (TileInteractable tile: this.gassedTiles) {
        	ArrayList<String> possibleMoves = new ArrayList<String>(tile.possibleMoves());
            for (String direction : possibleMoves) {
            	if (!gassedTiles.contains(tile.getAdjacentTile(direction))) {
            		this.gassedTiles.add((TileInteractable) tile.getAdjacentTile(direction));
            	}
            }
        }
    }
    
    public void draw(GraphicsContext graphicsContext) {
    	for (TileInteractable tile: gassedTiles) {
    		graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(),
    				tile.getxCoordinate() * Gameboard.getTileSize());
    	}
    }
}