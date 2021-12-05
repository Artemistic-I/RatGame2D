import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This is the super class to define an item object
 * 
 * @author Josh & Mike
 * @version 1.0
 */
public abstract class Item {
	// TODO
	// Create toString() method that returns all information needed for saving that
	// item to a file

	protected TileInteractable tileTheItemIsOn;
	protected Image itemGraphic;

	/**
	 * constructor to create a new item
	 */
	public Item(Image itemGraphic, TileInteractable tileTheItemIsOn) {
		this.itemGraphic = itemGraphic;
		this.tileTheItemIsOn = tileTheItemIsOn;
	}

	public void update(GraphicsContext graphicsContext, long gameDuration) {
		this.draw(graphicsContext);
		this.itemAction();
	}

	abstract void itemAction();

	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
		this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
	}

	/**
	 * get tile where item is located
	 * 
	 * @return itemLocation Tile that item occupies
	 */
	public Tile getItemLoc() {
		return tileTheItemIsOn;
	}
	
	public String toString() {
		String textEquivalent = String.format("%s %d %d" , this.getClass().toString(), tileTheItemIsOn.getxCoordinate(), tileTheItemIsOn.getyCoordinate());
		return textEquivalent;
	}

}