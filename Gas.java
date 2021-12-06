import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Aidan English Stephen
 *
 */
public class Gas extends LethalItem{

	private static final Image GAS_GRAPHIC = new Image("images/ItemGraphics/GasGraphic.png");
	private static final String GAS_SOUND_URL = "audio/Lit Fus.wav";
	private static final int RAT_EXPOSURE_LIMIT = 3;
	private static final int GAS_EXPAND_TIME = 5;
	private static final int GAS_EXIST_TIME = 15;
	private int gasTimeElapsed;
    private CopyOnWriteArrayList<TileInteractable> gassedTiles;
    private ArrayList<Rat> gassedRats;

    /**
     * Constructor for new gas.
     * @param tileTheItemIsOn Tile that the gas is placed on.
     */
    public Gas(TileInteractable tileTheItemIsOn) {
        super(GAS_GRAPHIC, tileTheItemIsOn);
        gassedTiles = new CopyOnWriteArrayList<>();
        gassedTiles.add(tileTheItemIsOn);
        gassedRats = new ArrayList<Rat>();
        this.gasTimeElapsed = 0;
    }

    /**
     * Constructor for a pre-created (e.g. saved) gas.
     * @param tileTheItemIsOn Tile the gas is places on.
     * @param gasTimeElapsed How long since the gas was first released.
     * @param gassedTiles All the tiles that are subject to the gas.
     * @param gassedRats Rats each time they are exposed to the gas.
     */
    public Gas(TileInteractable tileTheItemIsOn, int gasTimeElapsed, CopyOnWriteArrayList<TileInteractable> gassedTiles, ArrayList<Rat> gassedRats) {
        super(GAS_GRAPHIC, tileTheItemIsOn);
        this.gassedTiles = gassedTiles;
        this.gassedRats = gassedRats;
        this.gasTimeElapsed = gasTimeElapsed;
    }
    
    @Override
    /**
     * If the gas is still able to expand it should expand. Rats that have been exposed to too much gas should be killed.
     */
    void itemAction() { 
        try {
            SoundManager.playSound(GAS_SOUND_URL);
            SoundManager.setVolume(Settings.volume);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
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

    /**
     * Expand the tiles that are covered by the gas.
     */
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
    
    /**
     * Draw on each of the tiles tiles subject to gassing.
     * @param graphicsContext Where the gas should be drawn.
     */
    public void draw(GraphicsContext graphicsContext) {
    	for (TileInteractable tile: gassedTiles) {
    		graphicsContext.drawImage(itemGraphic, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
    	}
    }

    @Override
	/**
	 * Provide a text representation of the bomb.
	 * @return text representation
	 */
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