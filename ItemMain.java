import java.util.ArrayList;
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    Item inventory[][] = new Item[8][2];
    Random rand = new Random();
    private final int MAX_ITEM = 4;
    GameFileManager g = new GameFileManager();
    Gameboard gb = new Gameboard();
    int itemTimer = 0;

    private item getBomb() { return Bomb();}

    private item getGas(){return Gas();}

    private item getPoison(){return Poison();}

    private item getSexChangeFemale(){return SexChangeFemale();}

    private item getSexChangeMale(){return SexChangeMale();}

    private item getNoEntry(){return NoEntry();}

    private item getDeathRat(){return DeathRat();}

    private item getSterilisation(){return Sterilisation();}


    public void setBomb(Item bomb){Bomb()= bomb;}

    public void setGas(Item gas){Gas()= gas;}

    public void setPoison(Item poison){Poison()= poison;}

    public void setSexChangeFemale(Item SexChF){SexChangeFemale()= SexChF;}

    public void setSexChangeMale(Item SexChM){SexChangeMale()= SexChM;}

    public void setNoEntry(Item noEntry){NoEntry()= noEntry;}

    public void setDeathRat(Item deathRat){DeathRat()= deathRat;}

    public void setSterilise(Item sterilise){Sterilisation()= sterilise;}

    inventory[0][0]= Item Bomb();
    inventory[1][0]= Item Gas();
    inventory[2][0]= Item Poison();
    inventory[3][0]= Item SexChangeFemale();
    inventory[4][0]= Item SexChangeMale();
    inventory[5][0]= Item NoEntry();
    inventory[6][0]= Item DeathRat();
    inventory[7][0]= Item Sterilisation();



    //loop to add a Random item to inventory
    for(gb.numLivingRats > 0){
        if (itemTimer == 0) {
            int randomItem = nextInt(8);
            switch (randomItem) {
                case 0:
                    if(inventory[0][1] < MAX_ITEM){
                        inventory[0][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 1:
                    if(inventory[1][1]< MAX_ITEM){
                        inventory[1][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 2:
                    if(inventory[2][1]< MAX_ITEM){
                        inventory[2][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 3:
                    if(inventory[3][1]< MAX_ITEM){
                        inventory[3][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 4:
                    if(inventory[4][1]< MAX_ITEM){
                        inventory[4][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 5:
                    if(inventory[5][1]< MAX_ITEM){
                        inventory[5][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 6:
                    if(inventory[6][1]< MAX_ITEM){
                        inventory[6][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }
                case 7:
                    if(inventory[7][1]< MAX_ITEM){
                        inventory[7][1] += 1];
                        findTimer;
                    } else {
                        break;
                    }

            }
        }


    }
    //loop to remove items from inventory
    for(gb.numLivingRats > 0){
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
}
