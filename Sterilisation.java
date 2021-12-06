import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author Aiden
 * 
 */
public class Sterilisation extends Item{
	
	private static final Image STERILISATION_GRAPHIC = new Image("images/ItemGraphics/SterilisationGraphic.png");
    private static final int STERILISATION_RADIUS = 2;
    private static final int STERILISATION_TIME = 10;
    private int timeSpentSterilising;
    private CopyOnWriteArrayList<Tile> sterilisedTiles = new CopyOnWriteArrayList<>();;

	/**
	 * used for new game
	 * @param tileTheItemIsOn tile the item has been placed on
	 */
    public Sterilisation(TileInteractable tileTheItemIsOn) {
        super(STERILISATION_GRAPHIC, tileTheItemIsOn);
        setSterilisationTiles();
    }
    
	/**
	 * used when loading
	 * @param tileTheItemIsOn tile the item has been placed on
	 * @param timeSpentSterilising how long item has been on board for
	 */
    public Sterilisation(TileInteractable tileTheItemIsOn, int timeSpentSterilising) {
        super(STERILISATION_GRAPHIC, tileTheItemIsOn);
        this.timeSpentSterilising = timeSpentSterilising;
        setSterilisationTiles();
    }
    
	/**
	 * makes surrounding tiles also sterilised
	 * any rats on these sterilised tiles becomes sterile
	 * removes item once it has been on tile for x amount of time
	 */
    @Override
    public void itemAction() { 
    	timeSpentSterilising++;
    	ArrayList<TileInteractable> sterilisedRatTiles = new ArrayList<>();
    	for (Tile tile: sterilisedTiles) {
    		if (tile instanceof TileInteractable) {
    			sterilisedRatTiles.add((TileInteractable) tile);
    		}
    	}
    	for (Rat rat: RatManager.ratsOnTiles(sterilisedRatTiles)) {
    		rat.sterilise();
    	}
    	if (timeSpentSterilising >= STERILISATION_TIME) {
    		ItemManager.removeItem(this);
    	}
    }
    
	/**
	 * sets the tiles around the item to sterile as well
	 */
    private void setSterilisationTiles() {
    	sterilisedTiles.add((Tile) tileTheItemIsOn);
    	for (int i = 0; i < STERILISATION_RADIUS; i++) {
    		for (Tile tile: sterilisedTiles) {
    			if (!sterilisedTiles.contains(tile.getAdjacentTile("North")) && tile.getAdjacentTile("North") != null) {
    				sterilisedTiles.add(tile.getAdjacentTile("North"));
    			}
    			if (!sterilisedTiles.contains(tile.getAdjacentTile("East")) && tile.getAdjacentTile("East") != null) {
    				sterilisedTiles.add(tile.getAdjacentTile("East"));
    			}
    			if (!sterilisedTiles.contains(tile.getAdjacentTile("South")) && tile.getAdjacentTile("South") != null) {
    				sterilisedTiles.add(tile.getAdjacentTile("South"));
    			}
    			if (!sterilisedTiles.contains(tile.getAdjacentTile("West")) && tile.getAdjacentTile("West") != null) {
    				sterilisedTiles.add(tile.getAdjacentTile("West"));
    			}
    		}
    		
    	}
    }

	/**
	 * draws the item on the canvas
	 */
    public void draw(GraphicsContext graphicsContext) {
    	for (Tile tile: this.sterilisedTiles) {
    		graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
    	}
    }

	/**
	 * outputs item information in string form
	 */
	public String toString() {
		String textEquivalent = String.format("%s %d %d %d" , this.getClass().toString(), tileTheItemIsOn.getxCoordinate(), tileTheItemIsOn.getyCoordinate(), this.timeSpentSterilising);
		return textEquivalent;
	}
}