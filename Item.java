import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Models general items.
 * 
 * @author Josh & Mike
 * @version 1.0
 */
public abstract class Item {

	protected TileInteractable tileTheItemIsOn;
	protected Image itemGraphic;

	/**
	 * Constructor for a new item.
	 * @param itemGraphic Image that should be used for the item.
	 * @param tileTheItemIsOn Tile that the item is placed on.
	 */
	public Item(Image itemGraphic, TileInteractable tileTheItemIsOn) {
		this.itemGraphic = itemGraphic;
		this.tileTheItemIsOn = tileTheItemIsOn;
	}

	/**
	 * Update the item and perform any functions the item has.
	 * @param graphicsContext Where the item should be drawn.
	 * @param gameDuration How long in milliseconds since the game started.
	 */
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		this.draw(graphicsContext);
		this.itemAction();
	}

	/**
	 * Perform any functions that the item has
	 */
	abstract void itemAction();

	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
		this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
	}

	/**
	 * Get tile where item is located.
	 * @return itemLocation Tile that item occupies
	 */
	public Tile getItemLoc() {
		return tileTheItemIsOn;
	}
	
	/**
	 * Provide a text representation of the bomb.
	 * @return text representation
	 */
	public String toString() {
		String textEquivalent = String.format("%s %d %d" , this.getClass().toString(), tileTheItemIsOn.getxCoordinate(), tileTheItemIsOn.getyCoordinate());
		return textEquivalent;
	}
}