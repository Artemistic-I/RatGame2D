import java.lang.Math;
import java.util.ArrayList;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    /**
     * inventory to store items
     * columns are for amount of different items
     * rows are for the amount of different items
     */
    static int[] inv = new int[8];

    /**
     * constant for the max amount of items
     */
    private final static int MAX_ITEM = 4;

    /**
     * timer class for adding items
     * start at 0 to add random item at the start
     */
    static int itemTimer = 0;

    /**
     * variables to show how many of each item is stored
     */
    static int bombAmount = 0;
    static int gasAmount = 0;
    static int poisonAmount = 0;
    static int sexChFeAmount = 0;
    static int sexChMaAmount = 0;
    static int noEntryAmount = 0;
    static int deathRatAmount = 0;
    static int sterilisationAmount = 0;

    /**
     * timeline manager import for the tick method
     */
    TimelineMangaer tm = new TimelineMangaer();

    RatManager rm = new RatManager();


    /**
     * add a random item to inventory
     */
    public static void addItem(){
        for(rm.) {
            if (itemTimer == 0) {
                int randomItem = (int) (Math.random() * (8 - 1 + 1) + 1);
                switch (randomItem) {
                    case 1:
                        if (bombAmount < MAX_ITEM) {
                            bombAmount += 1;
                            inv[0] = bombAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (gasAmount < MAX_ITEM) {
                            gasAmount += 1;
                            inv[1] = gasAmount;
                            gasAmount += 1;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (poisonAmount < MAX_ITEM) {
                            poisonAmount += 1;
                            inv[2] = poisonAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (sexChFeAmount < MAX_ITEM) {
                            sexChFeAmount += 1;
                            inv[3] = sexChFeAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (sexChMaAmount < MAX_ITEM) {
                            sexChMaAmount += 1;
                            inv[4] = sexChMaAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (noEntryAmount < MAX_ITEM) {
                            noEntryAmount += 1;
                            inv[5] = noEntryAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (deathRatAmount < MAX_ITEM) {
                            deathRatAmount += 1;
                            inv[6] = dr;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (sterilisationAmount < MAX_ITEM) {
                            sterilisationAmount += 1;
                            inv[7] = sterilisationAmount;
                            findTimer(itemTimer);
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
    }

    /**
     * remove an item from inventory
     * @param item Item to be removed
     */
    public static void removeItem(Item item) {

        int shortcutKey = item.getSCKey();

        switch (shortcutKey) {
            case 112://bomb
                bombAmount -=1;
                inv[0]= bombAmount;
                break;
            case 114://gas
                gasAmount -=1;
                inv[1] = gasAmount;
                break;
            case 115://no entry
                noEntryAmount -=1;
                inv[2] = noEntryAmount;
                break;
            case 116://poison
                poisonAmount -=1;
                inv[3] = poisonAmount;
                break;
            case 117://f to m
                sexChMaAmount -=1;
                inv[4] = sexChMaAmount;
                break;
            case 118://m to f
                sexChFeAmount -=1;
                inv[5] = sexChFeAmount;
                break;
            case 119://sterilize
                sterilisationAmount -=1;
                inv[6] = sterilisationAmount;
                break;
            case 113://death rat
                deathRatAmount -=1;
                inv[7] = deathRatAmount;
                break;
        }
    }

    /**
     * determine timer for items to arrive depending on level
     * @param levelNo Level number of current level
     * @return timer Timer for items to regenerate
     */
    public static int findTimer(int levelNo) {

        levelNo = l.getLevelNum();
        int timer = 0;

        switch (levelNo) {
            case 1:
                timer = 10;
                break;
            case 2:
                timer = 9;
                break;
            case 3:
                timer = 8;
                break;
            case 4:
                timer = 7;
                break;
            case 5:
                timer = 5;
                break;
        }

        return timer;
    }

    public static int[] getInv() {
        return inv;
    }

}
