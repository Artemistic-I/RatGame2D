
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    Object[][] inv = new Object[8][2];
    Bomb b = new Bomb();
    Gas g = new Gas();
    Poison p = new Poison();
    SexChangeFemale scf = new SexChangeFemale();
    SexChangeMale scm = new SexChangeMale();
    NoEntry ne = new NoEntry();
    DeathRat dr = new DeathRat();
    Sterilisation s = new Sterilisation();
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

    inv[0][0] = b.Bomb();
    inv[1][0] = g.Gas();
    inv[2][0] = p.Poison();
    inv[3][0] = scf.SexChangeFemale();
    inv[4][0] = scm.SexChangeMale();
    inv[5][0] = ne.NoEntry();
    inv[6][0] = dr.DeathRat();
    inv[7][0] = s.Sterilisation();

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
                        inv[0][1] = bombAmount;
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
                inv[0][1] = bombAmount;
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
    public void ratContact(Rat rat) {

        setAffectedRat(rat);
        setTouchStatus(true);

        //triggers correct method depending on type of item denoted by shortcut key
        switch (shortcutKey) {
            case 112://bomb
                detonate();
                break;
            case 114://gas
                expand();
                break;
            case 115://no entry
                changeDirection(getAffectedRat());
                degradeHealth();
                setTouchStatus(false);
                break;
            case 116://poison
                killRat(getAffectedRat());
                removeItem();
                break;
            case 117://f to m
                changeSex(getAffectedRat());
                removeItem();
                break;
            case 118://m to f
                changeSex(getAffectedRat());
                removeItem();
                break;
            case 119://sterilize
                sterilize();
                removeItem();
                break;
            case 113://death rat
                killRat(getAffectedRat());
                ratCounter();
                break;
        }

    }

}
