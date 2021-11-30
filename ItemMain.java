
import java.lang.Math;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    /**
     * item storage
     */
    Item[][] inv = new Item[8][4];

    //initialising objects of each class
    Bomb b = new Bomb();
    Gas g = new Gas();
    Poison p = new Poison();
    SexChangeFemale scf = new SexChangeFemale();
    SexChangeMale scm = new SexChangeMale();
    NoEntry ne = new NoEntry();
    DeathRat dr = new DeathRat();
    Sterilisation s = new Sterilisation();

    //constant for max of each item
    private final int MAX_ITEM = 4;

    int itemTimer = 0;

    int bombAmount = 0;
    int gasAmount = 0;
    int poisonAmount = 0;
    int sexChFeAmount = 0;
    int sexChMaAmount = 0;
    int noEntryAmount = 0;
    int deathRatAmount = 0;
    int sterilisationAmount = 0;

    /**
     * add a random item to inventory
     */
    public void addItem(){
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
    public void removeItem(Item item) {

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
     * @return timer
     */
    public int findTimer(int levelNo) {

        //int levelNo = gfm.getLevelNum();
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

    /**
     * method for rat making contact with item
     * @param rat Rat for which actions are to be performed upon
     */
    public void ratContact(Rat rat, Item itemInUse) {

        itemInUse.setAffectedRat(rat);
        itemInUse.setTouchStatus(true);

        int shortcutKey = itemInUse.getSCKey();

        //triggers correct method depending on type of item denoted by shortcut key
        switch (shortcutKey) {
            case 112://bomb
                itemInUse.detonate();
                break;
            case 114://gas
                itemInUse.expand();
                break;
            case 115://no entry
                itemInUse.changeDirection(getAffectedRat());
                itemInUse.degradeHealth();
                itemInUse.setTouchStatus(false);
                break;
            case 116://poison
                itemInUse.killRat(itemInUse.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 117://f to m
                itemInUse.changeSex(itemInUse.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 118://m to f
                itemInUse.changeSex(itemInUse.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 119://sterilize
                itemInUse.sterilize();
                removeItem(itemInUse);
                break;
            case 113://death rat
                itemInUse.killRat(itemInUse.getAffectedRat());
                itemInUse.ratCounter();
                break;
        }

    }

}
