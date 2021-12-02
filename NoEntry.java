import javafx.scene.image.Image;

public class NoEntry extends Item{

    static final int SHORTCUT_KEY = 115; //bound to F4
    private int health;
    private static final Image NO_ENTRY_GRAPHIC = new Image("images/ItemGraphics/NoEntrySignGraphic.png");

    /**
     * constructor method
     */
    public NoEntry(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, NO_ENTRY_GRAPHIC, tileTheItemIsOn);
        setHealth();

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

    }

}