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
    public final int MAX_ITEM = 4;

    inventory[0][0]= Bomb();
    inventory[1][0]= Gas();
    inventory[2][0]= Poison();
    inventory[3][0]= SexChangeFemale();
    inventory[4][0]= SexChangeMale();
    inventory[5][0]= NoEntry();
    inventory[6][0]= DeathRat();
    inventory[7][0]= Sterilisation();


    //temporary variables
    int itemTimer = 4;
    Boolean gameRunning = true;



//loop to add a Random item to inventory
    for(gameRunning = True){
        if (itemTimer == 0) {
            int randomItem = nextInt(8);
            switch (randomItem) {
                case 0:
                    if(inventory[0][1]< MAX_ITEM){
                    inventory[0][1] += 1];
                    } else {
                        break;
                    }
                case 1:
                    if(inventory[1][1]< MAX_ITEM){
                        inventory[1][1] += 1];
                    } else {
                        break;
                    }
                case 2:
                    if(inventory[2][1]< MAX_ITEM){
                        inventory[2][1] += 1];
                    } else {
                        break;
                    }
                case 3:
                    if(inventory[3][1]< MAX_ITEM){
                        inventory[3][1] += 1];
                    } else {
                        break;
                    }
                case 4:
                    if(inventory[4][1]< MAX_ITEM){
                        inventory[4][1] += 1];
                    } else {
                        break;
                    }
                case 5:
                    if(inventory[5][1]< MAX_ITEM){
                        inventory[5][1] += 1];
                    } else {
                        break;
                    }
                case 6:
                    if(inventory[6][1]< MAX_ITEM){
                        inventory[6][1] += 1];
                    } else {
                        break;
                    }
                case 7:
                    if(inventory[7][1]< MAX_ITEM){
                        inventory[7s][1] += 1];
                    } else {
                        break;
                    }

            }
        }


    }

}