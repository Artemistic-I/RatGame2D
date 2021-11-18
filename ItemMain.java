import java.util.ArrayList;
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain{

    ArrayList<Item> StoredItems = new ArrayList<>();
    Random rand = new Random();
    public int itemAmount;
    public final int maxItem = 4;

    //temporary variables
    int itemTimer = 4;
    bool gameRunning = True;

//loop to add a Random item to inventory
    do {
        if (itemTimer == 0) {
            int randomLethal = nextInt(8);
            switch (randomLethal) {
                case 0:
                    StoredItems.add(Bomb.java);
                    break;
                case 1:
                    StoredItems.add(Gas.java);
                    break;
                case 2:
                    StoredItems.add(Poison.java);
                    break;
                case 3:
                    StoredItems.add(SexChangeF.java);
                    break;
                case 4:
                    StoredItems.add(SexChangeM.java);
                    break;
                case 5:
                    StoredItems.add(NoEntry.java);
                    break;
                case 6:
                    StoredItems.add(DeathRat.java);
                    break;
                case 7:
                    StoredItems.add(Sterilisation.java);
                    break;

            }
        }
        while (gameRunning = True);

    }

}