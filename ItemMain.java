import java.util.ArrayList;
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    Object[][] inv = new Object[8][2];
    Item i = new Item();
    Random rand = new Random();
    private final int MAX_ITEM = 4;
    GameFileManager g = new GameFileManager();
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

    private Item getBomb() {return Bomb();}

    private Item getGas(){return Gas();}

    private Item getPoison(){return Poison();}

    private Item getSexChangeFemale(){return SexChangeFemale();}

    private Item getSexChangeMale(){return SexChangeMale();}

    private Item getNoEntry(){return NoEntry();}

    private Item getDeathRat(){return DeathRat();}

    private Item getSterilisation(){return Sterilisation();}


    public void setBomb(Item bomb){Bomb()= bomb;}

    public void setGas(Item gas){Gas()= gas;}

    public void setPoison(Item poison){Poison()= poison;}

    public void setSexChangeFemale(Item SexChF){SexChangeFemale()= SexChF;}

    public void setSexChangeMale(Item SexChM){SexChangeMale()= SexChM;}

    public void setNoEntry(Item noEntry){NoEntry()= noEntry;}

    public void setDeathRat(Item deathRat){DeathRat()= deathRat;}

    public void setSterilise(Item sterilise){Sterilisation()= sterilise;}

    inventory[0][0] = Item Bomb();      //could you explain what this is doing please
    inventory[1][0] = Item Gas();
    inventory[2][0] = Item Poison();
    inventory[3][0] = Item SexChangeFemale();
    inventory[4][0] = Item SexChangeMale();
    inventory[5][0] = Item NoEntry();
    inventory[6][0] = Item DeathRat();
    inventory[7][0] = Item Sterilisation();



    //loop to add a Random item to inventory
    for(gb.numLivingRats > 0){
        if (itemTimer == 0) {
            int randomItem = nextInt(8);
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
    /**
    //loop to remove items from inventory
    for(gb.getNumLivingRats() > 0){
        if (Bomb().****isUsed****){
            inventory[0][1] -= 1];
        }
        if (Gas().****isUsed****){
            inventory[1][1] -= 1];
        }
        if (Poison().****isUsed****){
            inventory[2][1] -= 1];
        }
        if (SexChangeFemale().****isUsed****){
            inventory[3][1] -= 1];
        }
        if (SexChangeMale().****isUsed****){
            inventory[4][1] -= 1];
        }
        if (NoEntry().****isUsed****){
            inventory[5][1] -= 1];
        }
        if (DeathRat().****isUsed****){
            inventory[6][1] -= 1];
        }
        if (Sterilisation().****isUsed****){
            inventory[7][1] -= 1];
        }

    }
     */

    //public remove item method as there is no isUsed attribute in item class
    public void removeItem(Item item) {

        switch (shortcutKey) {
            case 112://bomb
                inventory[0][1] -= 1;
                break;
            case 114://gas
                inventory[1][1] -= 1;
                break;
            case 115://no entry
                inventory[5][1] -= 1;
                break;
            case 116://poison
                inventory[2][1] -= 1;
                break;
            case 117://f to m
                inventory[3][1] -= 1;
                break;
            case 118://m to f
                inventory[4][1] -= 1;
                break;
            case 119://sterilize
                inventory[7][1] -= 1;
                break;
            case 113://death rat
                inventory[6][1] -= 1;
                break;
        }

    }

    //loop to see how much time to add to the item counter
    public int findTimer(int Timer) {
        if (g.levelNumber == 1) {
            itemTimer = 10;
        }
        if (g.levelNumber == 2) {
            itemTimer = 9;
        }
        if (g.levelNumber == 3) {
            itemTimer = 8;
        }
        if (g.levelNumber == 4) {
            itemTimer = 7;
        }
        if (g.levelNumber == 5) {
            itemTimer = 5;
        }
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
