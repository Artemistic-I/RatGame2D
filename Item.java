import java.util.*;

/**
 * This is the super class to define an item object
 * @author Josh/Mike
 * @version 1.0
 */
public abstract class Item{

    //key to select item from menu
    private int shortcutKey;
    boolean isTouchingRat = False;
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
     * method for rat making contact with item
     * @param rat Rat for which actions are to be performed upon
     */
    public void ratContact(Rat rat) {

        affectedRat = setAffectedRat(rat);
        this.isTouchingRat = True;

        //triggers correct method depending on type of item denoted by shortcut key
        //shortcut keys to be ammended once set
        switch (shortcutKey) {
            case 0://bomb
                this.detonate();
                break;
            case 1://gas
                this.expand();
                break;
            case 2://no entry
                this.changeDirection(getAffectedRat());
                this.degradeHealth();
                this.isTouchingRat = False;
                break;
            case 3://poison
                this.killRat(getAffectedRat());
                this.removeItem();
                break;
            case 4://f to m
                this.changeSex(getAffectedRat());
                this.removeItem();
                break;
            case 5://m to f
                this.changeSex(getAffectedRat());
                this.removeItem();
                break;
            case 6://sterilize
                this.sterilize();
                this.removeItem();
                break;
            case 7://death rat
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
     * method to find rats to kill
     * @return arraylist of rats to kill
     */
    private ArrayList<Rat> findRats(int x, int y) {

        ArrayList<Rat> ratsFound = new ArrayList<Rat>();
        //find rats in given area
        return ratsFound;
    }

    /**
     * method to remove item once used
     * @param item to be removed
     */
    public void removeItem() {
        //remove this item
    }
}