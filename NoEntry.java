import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NoEntry extends Item{

    static final int SHORTCUT_KEY = 115; //bound to F4
    private int health;
    private static Image startingNoEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic.png");

    /**
     * constructor method
     */
    public NoEntry(TileInteractable tileTheItemIsOn) {
        super(startingNoEntryGraphic, tileTheItemIsOn);
        setHealth();
        getItemLoc().setNoEntrySign(this);

    }
    // constructor for loading
    public NoEntry(TileInteractable tileTheItemIsOn, int health) {
        super(startingNoEntryGraphic, tileTheItemIsOn);
        this.health = health;
        getItemLoc().setNoEntrySign(this);
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
    public void degradeHealth() {
        this.health = health - 1;
        System.out.println(health);
        if (health == 0) {
            tileTheItemIsOn.setNoEntrySign(null);
            ItemManager.removeItem(this);
        } else {
            itemGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic" + health + ".png");
        }
    }

    @Override
    public String toString() {
        String textEquivalent = super.toString();
        textEquivalent = String.format("%s %d", textEquivalent, this.health);
        return textEquivalent;
    }
    @Override
    void itemAction() {
        // TODO Auto-generated method stub
        
    }
}