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
 * @author Mike & Josh
 */
public class Bomb extends LethalItem {
	/**
	 * bomberman bombs until grass shows countdown to explosrion 4s - 1s
	 */

	private static final Image BOMB_GRAPHIC = new Image("images/ItemGraphics/BombGraphic.png");
	private static final int COUNTDOWN = 8;
	private int remainingTime;
	private GraphicsContext graphicsContext;

	/**
	 * constructor
	 */
	public Bomb(TileInteractable tileTheItemIsOn) {
		super(BOMB_GRAPHIC, tileTheItemIsOn);
		this.remainingTime = COUNTDOWN;
	}

	@Override
	public void itemAction() {
		remainingTime--;
		if (remainingTime == 0) {
			createExplosion();
			ArrayList<TileInteractable> explodedTiles = findExplodedTiles();
			Stack<Rat> ratsToKill = RatManager.ratsOnTiles(explodedTiles);
			while (!ratsToKill.isEmpty()) {
				RatManager.removeRat(ratsToKill.pop());
			}
			Stack<Item> itemsToDestroy = ItemManager.itemsOnTiles(explodedTiles);
			while (!itemsToDestroy.isEmpty()) {
				ItemManager.removeItem(itemsToDestroy.pop());
			}
			try {
				SoundManager.playSound("audio/explosion.wav");
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
			this.createExplosion();
			ItemManager.removeItem(this);
		}
	}

	private ArrayList<TileInteractable> findExplodedTiles() {
		ArrayList<TileInteractable> explodedTiles = new ArrayList<>();
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

	/**
	 * method to create explosion using javaFX
	 */
	private void createExplosion() {
		draw(graphicsContext);
	}

}