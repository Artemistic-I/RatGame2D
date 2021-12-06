import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Model the bomb item. 
 * @author Mike, Josh, Aidan English Stephen and Alex Gingureanu
 */
public class Bomb extends LethalItem {

	private static final Image BOMB_GRAPHIC = new Image("images/ItemGraphics/BombGraphic.png");
	private static final Image EXPLOSION_GRAPHIC = new Image("images/Explosion.png");
	private static final int COUNTDOWN = 16;
	private int remainingTime;
	private GraphicsContext graphicsContext;

	/**
	 * Construct a new bomb
	 * @param tileTheItemIsOn Tile that the bomb is placed on.
	 */
	public Bomb(TileInteractable tileTheItemIsOn) {
		super(BOMB_GRAPHIC, tileTheItemIsOn);
		this.remainingTime = COUNTDOWN;
	}

	/**
	 * Construct a pre-existing bomb.
	 * @param tileTheItemIsOn Tile that the bomb is placed on.
	 * @param remainingTime How long is left on the bomb's countdown timer.
	 */
	public Bomb(TileInteractable tileTheItemIsOn, int remainingTime) {
		super(BOMB_GRAPHIC, tileTheItemIsOn);
		this.remainingTime = remainingTime;
	}

	
	@Override
	/**
	 * Update the bomb.
	 * @param graphicsContext Where the bomb should be drawn.
	 * @param gameDuration How long the game has been going on in milliseconds.
	 */
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		this.graphicsContext = graphicsContext;
		draw(graphicsContext);
		this.itemAction();
	}

	@Override
	/**
	 * If the bomb timer runs out the bomb should detonate, killing any rats in the blast area.
	 */
	public void itemAction() {
		remainingTime--;
		if (remainingTime == 0) {
			ArrayList<TileInteractable> explodedTiles = findExplodedTiles();
			createExplosion(explodedTiles);
			Stack<Rat> ratsToKill = RatManager.ratsOnTiles(explodedTiles);
			while (!ratsToKill.isEmpty()) {
				RatManager.removeRat(ratsToKill.pop());
			}
			Stack<Item> itemsToDestroy = ItemManager.itemsOnTiles(explodedTiles);
			while (!itemsToDestroy.isEmpty()) {
				ItemManager.removeItem(itemsToDestroy.pop());
			}
			try {
				SoundManager.playSound("audio/0_explosion.wav");
				SoundManager.setVolume(Settings.volume);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			ItemManager.removeItem(this);
		}
	}

	/**
	 * Determine which tiles the explosion will cover.
	 * @return List of tiles subject to the explosion. 
	 */
	private ArrayList<TileInteractable> findExplodedTiles() {
		ArrayList<TileInteractable> explodedTiles = new ArrayList<>();;
		explodedTiles.add(tileTheItemIsOn);
		ArrayList<String> possibleMoves = new ArrayList<String>(tileTheItemIsOn.possibleMoves());
		for (String direction : possibleMoves) {
			TileInteractable tile = tileTheItemIsOn;
			do {
				explodedTiles.add((TileInteractable) tile.getAdjacentTile(direction));
				tile = (TileInteractable) tile.getAdjacentTile(direction);
			} while (tile.possibleMoves().contains(direction));
		}
		return explodedTiles;
	}

	@Override
	/**
	 * Draw the bomb.
	 * @param graphicsContext Where the bomb should be drawn.
	 */
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
		this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
		graphicsContext.fillText(String.valueOf(this.remainingTime / 4), this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(), this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize(), Gameboard.getTileSize());
	}
	
	/**
	 * Draw an explosion graphic on the affected tiles when the bomb is detonated. 
	 * @param explodedTiles Which tiles were included in the blast.
	 */
	private void createExplosion(ArrayList<TileInteractable> explodedTiles) {
		for (TileInteractable tile: explodedTiles) {
			if (!(tile instanceof TileTunnel)) {
				this.graphicsContext.drawImage(EXPLOSION_GRAPHIC, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
			}
		}
	}

	@Override
	/**
	 * Provide a text representation of the bomb.
	 * @return text representation
	 */
	public String toString() {
		String textEquivalent = super.toString();
		textEquivalent = String.format("%s %d", textEquivalent, this.remainingTime);
		return textEquivalent;
	}

}