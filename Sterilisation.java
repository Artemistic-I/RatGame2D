import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sterilisation extends Item{
	
	private static final Image STERILISATION_GRAPHIC = new Image("images/ItemGraphics/SterilisationGraphic.png");
    private static final int STERILISATION_RADIUS = 2;
    private static final int STERILISATION_TIME = 3;
    private int timeSpentSterilising;
    private CopyOnWriteArrayList<Tile> sterilisedTiles = new CopyOnWriteArrayList<>();;

    /**
     * constructor
     */
    public Sterilisation(TileInteractable tileTheItemIsOn) {
        super(STERILISATION_GRAPHIC, tileTheItemIsOn);
        setSterilisationTiles();
    }

    @Override
    void itemAction() { 
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
    

    public void draw(GraphicsContext graphicsContext) {
    	for (Tile tile: this.sterilisedTiles) {
    		graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
    	}
    }
}