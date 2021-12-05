import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;

public class ItemManager {

	private static CopyOnWriteArrayList<Item> currentlyPlacedItems = new CopyOnWriteArrayList<>();
	
	public static void addItem(Item itemToAdd) {
		currentlyPlacedItems.add(itemToAdd);
	}

	public static void removeItem(Item itemToRemove) {
		currentlyPlacedItems.remove(itemToRemove);
	}
	
	public static void updateItems(GraphicsContext graphicsContext, long gameDuration) {
		for (Item item : currentlyPlacedItems) {
			item.update(graphicsContext, gameDuration);
		}
	}
	
	public static Stack<Item> itemsOnTiles(ArrayList<TileInteractable> setOfTiles) {
		Stack<Item> itemsThatArePresent = new Stack<>();
		for(Item item: currentlyPlacedItems) {
			 if (setOfTiles.contains(item.getItemLoc())) {
				 itemsThatArePresent.push(item);
			 }
		 }
		return itemsThatArePresent;
	}
	public static void removeAllItems() {
		currentlyPlacedItems.clear();
	}
	public static Item[] getCurrentlyPlacedItems() {
		Item[] items = new Item[currentlyPlacedItems.size()];
		for (int i = 0; i < currentlyPlacedItems.size(); i++) {
			items[i] = currentlyPlacedItems.get(i);
		}
		return items;
	}
}