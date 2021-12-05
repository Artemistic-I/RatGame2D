import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * subclass of LethalItem and therefore Item to describe a Bomb object
 * 
 * @author Mike, Josh and Alex Gingureanu
 */
public class Bomb extends LethalItem {
	/**
	 * bomberman bombs until grass shows countdown to explosrion 4s - 1s
	 */

	private static final Image BOMB_GRAPHIC = new Image("images/ItemGraphics/BombGraphic.png");
	private static final Image EXPLOSION_GRAPHIC = new Image("images/Explosion.png");
	private static final int COUNTDOWN = 16;
	private int remainingTime;
	private GraphicsContext graphicsContext;

	/**
	 * constructor
	 */
	public Bomb(TileInteractable tileTheItemIsOn) {
		super(BOMB_GRAPHIC, tileTheItemIsOn);
		this.remainingTime = COUNTDOWN;
	}
	// constructor for loading
	public Bomb(TileInteractable tileTheItemIsOn, int remainingTime) {
		super(BOMB_GRAPHIC, tileTheItemIsOn);
		this.remainingTime = remainingTime;
	}

	
	@Override
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		this.graphicsContext = graphicsContext;
		draw(graphicsContext);
		this.itemAction();
	}

	@Override
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ItemManager.removeItem(this);
		}
	}

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
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
		this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
		graphicsContext.fillText(String.valueOf(this.remainingTime / 2), this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(), this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize(), Gameboard.getTileSize());
	}
	
	private void createExplosion(ArrayList<TileInteractable> explodedTiles) {
		for (TileInteractable tile: explodedTiles) {
			if (!(tile instanceof TileTunnel)) {
				this.graphicsContext.drawImage(EXPLOSION_GRAPHIC, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
			}
		}
	}

	@Override
	public String toString() {
		String textEquivalent = super.toString();
		textEquivalent = String.format("%s %d", textEquivalent, this.remainingTime);
		return textEquivalent;
	}

}