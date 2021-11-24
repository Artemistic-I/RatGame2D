import java.util.*;
import java.Math;

/**
 * This is the super class to define an item object
 * @author Josh/Mike
 * @version 1.0
 */
public abstract class Item{

    //key to select item from menu
    private int shortcutKey;
    Tile itemLocation;
    boolean isTouchingRat = false;
    Rat affectedRat;

    /**
     * constructor to create a new item
     * @param scKey Keycode for key related with specific item
     */
    public Item(int scKey){

        shortcutKey = setSCKey(scKey);
    }

    /**
     * set shortcut key
     * @param key Keycode for key related with specific item
     * @return key Keycode for key related with specific item
     */
    private int setSCKey(key) {
        return key;
    }

    /**
     * set item location by tile
     * @return loc Tile that item occupies
     */
    public Tile setItemLoc(loc) {
        return loc;
    }

    /**
     * get tile where item is located
     * @return itemLocation Tile that item occupies
     */
    public Tile getItemLoc() {
        return itemLocation;
    }

    /**
     * method for rat making contact with item
     * @param rat Rat for which actions are to be performed upon
     */
    public void ratContact(Rat rat) {

        affectedRat = setAffectedRat(rat);
        this.isTouchingRat = true;

        //triggers correct method depending on type of item denoted by shortcut key
        //shortcut keys to be ammended once set
        switch (shortcutKey) {
            case 112://bomb
                this.detonate();
                break;
            case 114://gas
                this.expand();
                break;
            case 115://no entry
                this.changeDirection(getAffectedRat());
                this.degradeHealth();
                this.isTouchingRat = False;
                break;
            case 116://poison
                this.killRat(getAffectedRat());
                this.removeItem();
                break;
            case 117://f to m
                this.changeSex(getAffectedRat());
                this.removeItem();
                break;
            case 118://m to f
                this.changeSex(getAffectedRat());
                this.removeItem();
                break;
            case 119://sterilize
                this.sterilize();
                this.removeItem();
                break;
            case 113://death rat
                this.killRat(getAffectedRat());
                this.ratCounter();
                break
        }

    }

    /**
     * method to set rat affected by item
     * @param rat Rat which has contacted item
     * @return rat Rat which has contacted item
     */
    private Rat setAffectedRat(Rat rat) {
        return rat;
    }

    /**
     * method to get rat affected by item
     * @return rat Rat that will be affected by item
     */
    public Rat getAffectedRatRat() {
        return affectedRat;
    }

    /**
     * method to find rats to kill/affect
     * @param origin Tile to start looking for rats from (where item is placed)
     * @param area Area in which rats should be found
     * @return ratsFound Arraylist of rats to kill
     */
    private ArrayList<Rat> findRats(Tile origin, int area) {

        ArrayList<Rat> ratsFound = new ArrayList<Rat>();

        //       find rats in given area
        //for each column in originx - area/2 to originx + area/2
        //  for each tile in column within originy + area/2 to originy - area/2
        //      find rat and add to arraylist

        int oX = (origin.getTileCoordinates())[0];
        int oY = (origin.getTileCoordinates())[1];
        int xBounds[] = [oX - area/2, oX + Math.round(area/2)];
        int yBounds[] = [oY - area/2, oY + Math.round(area/2)];

        board = getBoard()  //NOT SURE HOW TO ACCESS GAMEBOARD, this is just a placeholder

        //for each column in range of item
        for (int i = xBounds[0], i < (xBounds[1] + 1), i++) {
            //for each tile in column
            for (int j = yBounds[0], i < (yBounds[1] + 1), i++) {
                Tile currentTile = board[i][j];
                if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                    //Rat r = rat on tile
                    ratsFound.add(r);
                }
            }
        }

        return ratsFound;
    }

    /**
     * method to remove item once used
     */
    public void removeItem() {
        //remove this item
    }
}