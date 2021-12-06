import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NoEntry extends Item{

    static final int SHORTCUT_KEY = 115; //bound to F4
    private int health;
    private static Image noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic.png");

    /**
     * constructor method
     */
    public NoEntry(TileInteractable tileTheItemIsOn) {

        super(noEntryGraphic, tileTheItemIsOn);
        setHealth();

    }
    // constructor for loading
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
    /*
    also needs to change image to reflect how damaged it is
    suggest using an array the length of it's health and 
    changing image to the new one stored at the index of the new health
    */
    public void degradeHealth(int hp) {
        this.health = hp - 1;
        switch(this.health){
            case 4:
                noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic4.png");
                break;
            case 3:
                noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic3.png");
                break;
            case 2:
                noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic2.png");
                break;
            case 1:
                noEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic1.png");
                break;
        }

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