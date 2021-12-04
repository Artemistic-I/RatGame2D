import java.util.ArrayList;
import java.util.Stack;

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
		// countdown in here
	}

	@Override
	public String toString() {
		String textEquivalent = super.toString();
		textEquivalent = String.format("%s %d", textEquivalent, this.remainingTime);
		return textEquivalent;
	}

}