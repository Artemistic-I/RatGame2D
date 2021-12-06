import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class to keep track of all items in existence and provide associated operations.
 * @author Aidan English Stephen
 */
public class ItemManager {

	private static CopyOnWriteArrayList<Item> currentlyPlacedItems = new CopyOnWriteArrayList<>();
	
	/**
	 * Allow a new item to be added.
	 * @param itemToAdd The rat you want to add.
	 */
	public static void addItem(Item itemToAdd) {
		currentlyPlacedItems.add(itemToAdd);
	}

	/**
	 * Allow a item to be removed.
	 * @param item ToRemove The rat you want to remove.
	 */
	public static void removeItem(Item itemToRemove) {
		currentlyPlacedItems.remove(itemToRemove);
	}
	
	/**
	 * For each item in existence, update it.
	 * @param graphicsContext Where items should be drawn.
	 * @param gameDuration The amount of time in milliseconds that the game has been running.
	 */
	public static void updateItems(GraphicsContext graphicsContext, long gameDuration) {
		for (Item item : currentlyPlacedItems) {
			item.update(graphicsContext, gameDuration);
		}
	}
	
	/**
	 * Find out what items are present on a particular set of tiles.
	 * @param setOfTiles The tiles that you want to be checked.
	 * @return All the items that are present on the provided tiles.
	 */
	public static Stack<Item> itemsOnTiles(ArrayList<TileInteractable> setOfTiles) {
		Stack<Item> itemsThatArePresent = new Stack<>();
		for(Item item: currentlyPlacedItems) {
			 if (setOfTiles.contains(item.getItemLoc())) {
				 itemsThatArePresent.push(item);
			 }
		 }
		return itemsThatArePresent;
	}
	
	/**
	 * Remove all the items from existence.
	 */
	public static void removeAllItems() {
		currentlyPlacedItems.clear();
	}
	
	/**
	 * Get all the items that are currently in existance.
	 * @return All items.
	 */
	public static Item[] getCurrentlyPlacedItems() {
		Item[] items = new Item[currentlyPlacedItems.size()];
		for (int i = 0; i < currentlyPlacedItems.size(); i++) {
			items[i] = currentlyPlacedItems.get(i);
		}
		return items;
	}
}