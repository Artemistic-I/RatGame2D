import java.util.ArrayList;
import java.util.Random;

/**
 * keeps track of the players inventory
 * as well as handling adding and removing of items from it
 * @author Josh/Mike
 * @version 1.0
 */
public class Inventory {

	private final static int MAX_ITEM = 4; // constant for the max amount of items
    private static int[] inv = new int[8];
    private static int itemTimer = 0; // timer for when to add next item

    public static void update() {
        /**
         * adds items to inventory spaces in increments according to the level
         */
        if (Menu.getTimelineManager().getDuration() % getTimer(itemTimer) == 0) {
            addItem();
        }
    }

    /**
     * looks at level numbers and returns a number to decide on how long an item should take to spawn
     * @param timer
     * @return
     */
    public static int getTimer(int timer) {
        int levelNumber = Level.getSelectedLevel().getLevelNumber();
        switch (levelNumber){
            case 1:
                timer = 5000;
                break;
            case 2:
                timer = 4500;
                break;
            case 3:
                timer = 4000;
                break;
            case 4:
                timer = 3500;
                break;
            case 5:
                timer = 3000;
                break;
        }
        return timer;
    }

    /**
     * add a random item to inventory
     * @param inventoryIndexesNotFull stores valid inv index
     * @param randomIndex chooses random inv index from inventoryIndexesNotFull
     */
    private static void addItem(){
        ArrayList<Integer> inventoryIndexesNotFull = new ArrayList<>();
        // gets all inv spaces that can be added to
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] < MAX_ITEM) {
                inventoryIndexesNotFull.add(i);
            }
        }
        Random rand = new Random();
        if (inventoryIndexesNotFull.size() > 0) {
        	int randomIndex = inventoryIndexesNotFull.get(rand.nextInt(inventoryIndexesNotFull.size()));
        	inv[randomIndex] += 1;
        	Menu.getTimelineManager().getGameboardCanvassController().updateItemCounts();
        }
    }

    public static void reset() {
        for (int i = 0; i < inv.length; i++) {
            inv[i] = 0;
        }
    }

    /**
     * remove an item from inventory
     */
    public static void removeItem(int index) {
        if (index < inv.length && index >= 0) {
            if (inv[index] > 0) {
                inv[index] -= 1;
            }
        }
        Menu.getTimelineManager().getGameboardCanvassController().updateItemCounts();
    }

    public static int[] getInv() {
        return inv;
    }
    public static void setInv(int i, int value) {
        inv[i] = value;
    }

    public static int getInv(int index) {
        return inv[index];
    }
}
