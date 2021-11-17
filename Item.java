import java.util.*;

/**
 * This is the super class to define an item object
 * @author Josh/Mike
 * @version 1.0
 */
public abstract class Item{

    private int shortcutKey;
    boolean isTouchingRat = False;
    Rat affectedRat;

    /**
     * constructor
     * @param shortcut key for item
     */
    public Item(int scKey){

        shortcutKey = setSCKey(scKey);
    }

    private int setSCKey(key) {
        return key;
    }

    /**
     * method for when rat makes contact with item
     * @param rat
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
        }

    }

    /**
     * method to set rat affected by item
     * @param rat
     * @return rat
     */
    private Rat setAffectedRat(Rat rat) {
        return rat;
    }

    /**
     * method to get rat affected by item
     * @return rat
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
    public void removeItem(Item item) {

    }
}