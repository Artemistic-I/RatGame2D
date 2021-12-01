
import java.lang.Math;

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
    Item[][] inv = new Item[8][4];

    /**
     * import info from the level class
     */
    Level l = new Level();

    /**
     * import each of the items
     */
    Bomb b = new Bomb();
    Gas g = new Gas();
    Poison p = new Poison();
    SexChangeFemale scf = new SexChangeFemale();
    SexChangeMale scm = new SexChangeMale();
    NoEntry ne = new NoEntry();
    DeathRat dr = new DeathRat();
    Sterilisation s = new Sterilisation();

    /**
     * constant for the max amount of items
     */
    private final int MAX_ITEM = 4;

    /**
     * timer class for adding items
     * start at 0 to add random item at the start
     */
    int itemTimer = 0;

    /**
     * variables to show how many of each item is stored
     */
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
     * @return timer Timer for items to regenerate
     */
    public int findTimer(int levelNo) {

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
                Bomb bombItem = (Bomb) itemInUse;
                bombItem.detonate();
                ArrayList<Item> itemsToBomb = bombItem.getItemsToBomb();
                for (int i = 0; i < itemsToBomb.size(); i++) {
                    removeItem(itemsToBomb.get(i));
                }
                removeItem(itemInUse);
                break;
            case 114://gas
                Gas gasItem = (Gas) itemInUse;
                gasItem.expand();
                break;
            case 115://no entry
                NoEntry noEntItem = (NoEntry) itemInUse;
                while (noEntItem.getHealth() != 0) {//while sign is still active
                    noEntItem.getAffectedRat().changeDirection();//change direction of rat - need a public change direction method
                    noEntItem.degradeHealth(noEntItem.getHealth());//degrade health
                    itemInUse.setTouchStatus(false);
                }
                removeItem(itemInUse);
                break;
            case 116://poison
                Poison poisonItem = (Poison) itemInUse;
                poisonItem.killRat(poisonItem.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 117://f to m
                SexChangeFemale fToMItem = (SexChangeFemale) itemInUse;
                fToMItem.changeSex(fToMItem.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 118://m to f
                SexChangeMale mToFItem = (SexChangeMale) itemInUse;
                mToFItem.changeSex(mToFItem.getAffectedRat());
                removeItem(itemInUse);
                break;
            case 119://sterilize
                Sterilisation sterilizeItem = (Sterilisation) itemInUse;
                sterilizeItem.sterilize();
                removeItem(itemInUse);
                break;
            case 113://death rat
                DeathRat dRatItem = (DeathRat) itemInUse;
                while (dRatItem.getRatsKilled() != 5) {
                    dRatItem.killRat(itemInUse.getAffectedRat());
                    dRatItem.incrementRatCounter();
                }
                removeItem(itemInUse);


                break;
        }

    }

}
