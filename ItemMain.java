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
    int lethalTimer = 4;
    bool gameRunning = True;

//loop to add a lethal item
    do {
        if (lethalTimer == 0) {
            int randomLethal = nextInt(3);
            switch (randomLethal) {
                case 0:
                    StoredItems.add(bomb.java);
                    break;
                case 1:
                    StoredItems.add(Gas.java);
                    break;
                case 2:
                    StoredItems.add(poison.java);
                    break;
            }
        }
        while (gameRunning = True);

    }

}