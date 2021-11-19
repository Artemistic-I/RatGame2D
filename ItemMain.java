import java.util.ArrayList;
import java.util.Random;

/**
 * place in order to keep track of the items and the amount
 * @author Josh/Mike
 * @version 1.0
 */
public class ItemMain {

    ArrayList<Item> StoredItems = new ArrayList<>();
    Random rand = new Random();
    public int itemAmount;
    public final int maxItem = 4;

    //temporary variables
    int itemTimer = 4;
    Boolean gameRunning = true;

//loop to add a Random item to inventory
    do {
        if (itemTimer == 0) {
            int randomLethal = nextInt(8);
            switch (randomLethal) {
                case 0:
                    StoredItems.add(new Bomb());
                    break;
                case 1:
                    StoredItems.add(new Gas());
                    break;
                case 2:
                    StoredItems.add(new Poison());
                    break;
                case 3:
                    StoredItems.add(new SexChangeFemale());
                    break;
                case 4:
                    StoredItems.add(new SexChangeMale());
                    break;
                case 5:
                    StoredItems.add(new NoEntry());
                    break;
                case 6:
                    StoredItems.add(new DeathRat());
                    break;
                case 7:
                    StoredItems.add(new Sterilisation());
                    break;

            }
        }
    } while (gameRunning = true);
}