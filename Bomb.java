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
	private static final Image EXPLOSION_GRAPHIC = new Image("images/Explosion.png");
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
	public void update(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
		super.draw(graphicsContext);
		this.itemAction();
	}

	public String toString(){
		String textEquivalent = String.format("%s", tileTheItemIsOn);
		return textEquivalent;
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

	/**
	 * method to create explosion using javaFX
	 */
	private void createExplosion(ArrayList<TileInteractable> explodedTiles) {
		for (TileInteractable tile: explodedTiles) {
			if (!(tile instanceof TileTunnel)) {
				this.graphicsContext.drawImage(EXPLOSION_GRAPHIC, tile.getyCoordinate() * Gameboard.getTileSize(), tile.getxCoordinate() * Gameboard.getTileSize());
			}
		}
	}

}