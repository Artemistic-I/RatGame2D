
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    //inventory - array of items
    //inv didn't work for some reason
    Item[][] inventory = new Item[8][2];

    //initialising objects of each class
    Bomb b = new Bomb();
    Gas g = new Gas();
    Poison p = new Poison();
    SexChangeFemale scf = new SexChangeFemale();
    SexChangeMale scm = new SexChangeMale();
    NoEntry ne = new NoEntry();
    DeathRat dr = new DeathRat();
    Sterilisation s = new Sterilisation();

    //any limits on the random number??
    Random rand = new Random();
    private final int MAX_ITEM = 4;
    GameFileManager gfm = new GameFileManager();
    Gameboard gb = new Gameboard();

    int itemTimer = 0;
    int bombAmount = 0;
    int gasAmount = 0;
    int poisonAmount = 0;
    int sexChFeAmount = 0;
    int sexChMaAmount = 0;
    int noEntryAmount = 0;
    int deathRatAmount = 0;
    int sterilisationAmount = 0;

    //methods being called dont exist??
    //e.g. inventory[0][0] = b; - however i'm not certain this is how to add to an array
    inv[1][0] = g.Gas();
    inv[2][0] = p.Poison();
    inv[3][0] = scf.SexChangeFemale();
    inv[4][0] = scm.SexChangeMale();
    inv[5][0] = ne.NoEntry();
    inv[6][0] = dr.DeathRat();
    inv[7][0] = s.Sterilisation();

    //not too sure why we have getters and setters that aren't being used
    private Item getDeathRat(){
        return dr.DeathRat();
    }

    private Item getSterilisation(){
        return s.Sterilisation();
    }


    public void setBomb(Item bomb){
        b.Bomb()= bomb;
    }

    public void setGas(Item gas){
        g.Gas()= gas;
    }

    public void setPoison(Item poison){
        p.Poison()= poison;
    }

    public void setSexChangeFemale(Item SexChF){
        scf.SexChangeFemale()= SexChF;
    }

    public void setSexChangeMale(Item SexChM){
        scm.SexChangeMale()= SexChM;
    }

    public void setNoEntry(Item noEntry){
        ne.NoEntry()= noEntry;
    }

    public void setDeathRat(Item deathRat){
        dr.DeathRat()= deathRat;
    }

    public void setSterilise(Item sterilise){
        s.Sterilisation()= sterilise;
    }

    //loop to add a Random item to inventory
    public void addItem(Item item){
        if (itemTimer == 0) {
            int randomItem = nextRandom(8);
            switch (randomItem) {
                case 0:
                    if(bombAmount < MAX_ITEM){
                        bombAmount +=1;
                        inv[0][1] = bombAmount;//should be set to an item object, not an integer as the array holds items
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 1:
                    if(gasAmount< MAX_ITEM){
                        gasAmount +=1;
                        inv[1][1] = gasAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 2:
                    if(poisonAmount< MAX_ITEM){
                        poisonAmount +=1;
                        inv[2][1] = poisonAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 3:
                    if(sexChFeAmount< MAX_ITEM){
                        sexChFeAmount +=1;
                        inv[3][1] = sexChFeAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 4:
                    if(sexChMaAmount< MAX_ITEM){
                        sexChMaAmount +=1;
                        inv[4][1] = sexChMaAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 5:
                    if(noEntryAmount< MAX_ITEM){
                        noEntryAmount +=1;
                        inv[5][1] = noEntryAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 6:
                    if(deathRatAmount< MAX_ITEM){
                        deathRatAmount +=1;
                        inv[6][1] = deathRatAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }
                case 7:
                    if(sterilisationAmount< MAX_ITEM){
                        sterilisationAmount +=1;
                        inv[7][1] = sterilisationAmount;
                        findTimer(itemTimer);
                    } else {
                        break;
                    }

            }
        }


    }

    //public remove item method as there is no isUsed attribute in item class
    public void removeItem(Item item) {

        switch (shortcutKey) {
            case 112://bomb
                bombAmount -=1;
                inv[0][1] = bombAmount;//see above
                break;
            case 114://gas
                gasAmount -=1;
                inv[0][1] = gasAmount;
                break;
            case 115://no entry
                noEntryAmount -=1;
                inv[0][1] = noEntryAmount;
                break;
            case 116://poison
                poisonAmount -=1;
                inv[0][1] = poisonAmount;
                break;
            case 117://f to m
                sexChMaAmount -=1;
                inv[0][1] = sexChMaAmount;
                break;
            case 118://m to f
                sexChFeAmount -=1;
                inv[0][1] = sexChFeAmount;
                break;
            case 119://sterilize
                sterilisationAmount -=1;
                inv[0][1] = sterilisationAmount;
                break;
            case 113://death rat
                deathRatAmount -=1;
                inv[0][1] =deathRatAmount;
                break;
        }

    }

    //loop to see how much time to add to the item counter
    public int findTimer(int timer) {
        if (g.levelNumber == 1) {
            timer = 10;
        }
        if (g.levelNumber == 2) {
            timer = 9;
        }
        if (g.levelNumber == 3) {
            timer = 8;
        }
        if (g.levelNumber == 4) {
            timer = 7;
        }
        if (g.levelNumber == 5) {
            timer = 5;
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
