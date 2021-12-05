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

    // for new item
    public Gas(TileInteractable tileTheItemIsOn) {
        super(GAS_GRAPHIC, tileTheItemIsOn);
        gassedTiles = new CopyOnWriteArrayList<>();
        gassedTiles.add(tileTheItemIsOn);
        gassedRats = new ArrayList<Rat>();
        this.gasTimeElapsed = 0;
    }

    // for loaded items
    public Gas(TileInteractable tileTheItemIsOn, int gasTimeElapsed, CopyOnWriteArrayList<TileInteractable> gassedTiles, ArrayList<Rat> gassedRats) {
        super(GAS_GRAPHIC, tileTheItemIsOn);
        this.gassedTiles = gassedTiles;
        this.gassedRats = gassedRats;
        this.gasTimeElapsed = gasTimeElapsed;
    }
    
    @Override
    void itemAction() { 
    	if (gasTimeElapsed <= GAS_EXPAND_TIME) {
            expand();    		
    	}
        this.gassedRats.addAll(RatManager.ratsOnTiles(new ArrayList<TileInteractable>(gassedTiles)));
        for (Rat rat: gassedRats) {
        	if (Collections.frequency(gassedRats, rat) > RAT_EXPOSURE_LIMIT) {
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
    		if (!(tile instanceof TileTunnel)) {
    			graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
    		}
    	}
    }

    @Override
    public String toString() {
        String textEquivalent = super.toString();
        textEquivalent = String.format("%s %d", textEquivalent, gasTimeElapsed);
        for (TileInteractable tile : gassedTiles) {
            textEquivalent += String.format(" %d %d", tile.getxCoordinate(), tile.getyCoordinate());
        }
        textEquivalent += " -1"; // this is to indicate the end of this section
        for (Rat rat : gassedRats) {
            textEquivalent += " " + rat.getUniqueIdentifier(); // only add rat ID so gas knows which rats had been exposed to gas
        }
        return textEquivalent;
    }
}