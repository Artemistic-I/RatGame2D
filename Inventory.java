import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

/**
 * keeps track of the players inventory
 * as well as handling adding and removing of items from it
 * @author Josh/Mike
 * @version 1.0
 */
public class Inventory {

    /**
     * inventory to store items
     * index 0: Bombs
     * index 1: Gas
     * index 2: Sterilisation
     * index 3: Poison
     * index 4: Male Sex Change
     * index 5: Female Sex Change
     * index 6: No Entry Sign
     * index 7: Death Rats
     */
    static int[] inv = new int[8];

    private final static int MAX_ITEM = 4; // constant for the max amount of items

    static int itemTimer = 0; // timer for when to add next item

    public static void update() {
        // adds item every 5 seconds (5000 milliseconds)
        if (Menu.getTimelineManager().getDuration() % 5000 == 0) {
            addItem();
        }
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
            if (inv[i] < 4) {
                inventoryIndexesNotFull.add(i);
            }
        }
        Random rand = new Random();
        int randomIndex = inventoryIndexesNotFull.get(rand.nextInt(inventoryIndexesNotFull.size()));
        inv[randomIndex] += 1;
        GameBoardCanvasController.updateItemCounts();
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
        GameBoardCanvasController.updateItemCounts();
    }

    public static int[] getInv() {
        return inv;
    }

    public static int getInv(int index) {
        return inv[index];
    }
}
