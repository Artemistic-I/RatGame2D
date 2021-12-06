import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NoEntry extends Item{

    private int health;
    private static Image noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic.png");

    /**
     * constructor method
     */
    public NoEntry(TileInteractable tileTheItemIsOn) {

        super(noEntryGraphic, tileTheItemIsOn);
        setHealth();

    }

    public NoEntry(TileInteractable tileTheItemIsOn, int health) {

        super(noEntryGraphic, tileTheItemIsOn);
        this.health = health;

    }

    /**
     * health of no entry sign begins at 5
     */
    private void setHealth() {
        health = 5;
    }

    /**
     * get health
     * @return health How many more touches the sign can take from a rat
     */
    public int getHealth() {
        return health;
    }

    /**
     * method to degrade health of sign
     * @param hp Current health of sign
     */
    public void degradeHealth(int hp) {
        this.health = hp - 1;
        noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic" + health + ".png");
    }

    public void itemAction(){
        Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
        while (!ratsOnTile.isEmpty()){
            degradeHealth(this.health);
        }
        if (getItemLoc().isInteractable()) {
            getItemLoc().setIsInteractable(false);
        }
    }

    @Override
    public String toString() {
        String textEquivalent = super.toString();
        textEquivalent = String.format("%s %d", textEquivalent, this.health);
        return textEquivalent;
    }
}