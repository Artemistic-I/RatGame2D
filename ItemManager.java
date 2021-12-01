import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ItemManager {

	private static CopyOnWriteArrayList<Item> currentlyPlacedItems = new CopyOnWriteArrayList<>();
	
	public static void addItem(Item itemToAdd) {
		currentlyPlacedItems.add(itemToAdd);
	}

	public static void removeItem(Item itemToRemove) {
		currentlyPlacedItems.remove(itemToRemove);
	}
	
	public static void updateItems(GraphicsContext graphicsContext) {
		for (Item item : currentlyPlacedItems) {
			item.update(graphicsContext);
		}
	}
	
	public static ArrayList<Item> itemsOnTiles(ArrayList<Tile> setOfTiles) {
		ArrayList<Item> itemsThatArePresent = new ArrayList<>();
		for(Item item: currentlyPlacedItems) {
			 if (setOfTiles.contains(item.getItemLoc())) {
				 itemsThatArePresent.add(item);
			 }
		 }
		return itemsThatArePresent;
	}
}