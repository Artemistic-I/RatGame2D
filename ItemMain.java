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
    static Item[][] inv = new Item[8][4];

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
     * add a random item to inventory
     */
    public static void addItem(){
        if (itemTimer == 0) {
            int randomItem = (int) (Math.random() * (8 - 1 + 1) + 1);
            switch (randomItem) {
                case 1:
                    if (bombAmount < MAX_ITEM) {
                        inv[0][bombAmount] = b;
                        bombAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 2:
                    if (gasAmount < MAX_ITEM) {
                        inv[1][gasAmount] = g;
                        gasAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 3:
                    if (poisonAmount < MAX_ITEM) {
                        inv[2][gasAmount] = p;
                        poisonAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 4:
                    if (sexChFeAmount < MAX_ITEM) {
                        inv[3][sexChFeAmount - 1] = scf;
                        sexChFeAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 5:
                    if (sexChMaAmount < MAX_ITEM) {
                        inv[4][sexChMaAmount] = scm;
                        sexChMaAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 6:
                    if (noEntryAmount < MAX_ITEM) {
                        inv[5][noEntryAmount] = ne;
                        noEntryAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 7:
                    if (deathRatAmount < MAX_ITEM) {
                        inv[6][deathRatAmount] = dr;
                        deathRatAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 8:
                    if (sterilisationAmount < MAX_ITEM) {
                        inv[7][sterilisationAmount] = s;
                        sterilisationAmount += 1;
                        findTimer(itemTimer);
                    } else {
                        break;
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
                inv[0][bombAmount] = null;
                break;
            case 114://gas
                gasAmount -=1;
                inv[1][gasAmount] = null;
                break;
            case 115://no entry
                noEntryAmount -=1;
                inv[2][noEntryAmount] = null;
                break;
            case 116://poison
                poisonAmount -=1;
                inv[3][poisonAmount] = null;
                break;
            case 117://f to m
                sexChMaAmount -=1;
                inv[4][sexChMaAmount] = null;
                break;
            case 118://m to f
                sexChFeAmount -=1;
                inv[5][sexChFeAmount] = null;
                break;
            case 119://sterilize
                sterilisationAmount -=1;
                inv[6][sterilisationAmount] = null;
                break;
            case 113://death rat
                deathRatAmount -=1;
                inv[7][deathRatAmount] = null;
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

    public static Item[][] getInv() {
        return inv;
    }

}
