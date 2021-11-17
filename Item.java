import java.util.*;

/**
 * This is the super class to define an item object
 * @author Josh/Mike
 * @version 1.0
 */
public abstract class Item{

    protected int shortcutKey;
    protected int spawnTime;
    boolean isTouchingRat = False;
    Rat affectedRat;

    /**
     * constructor
     * @param rat that has come into contact with item
     */
    public Item(Rat rat){

        affectedRat = setAffectedRat(Rat rat);
        this.isTouchingRat = True;

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
}