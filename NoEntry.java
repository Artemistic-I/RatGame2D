import javafx.scene.image.Image;

/**
 * Models the NoEntry sign item.
 * @author Sam Beard
 *
 */
public class NoEntry extends Item{

    private int health;
    private static Image startingNoEntryGraphic = new Image("images/ItemGraphics/NoEntrySignGraphic.png");

    /**
     * Construct a new No Entry Sign item.
     * @param tileTheItemIsOn Tile that the item is on.
     */
    public NoEntry(TileInteractable tileTheItemIsOn) {
        super(startingNoEntryGraphic, tileTheItemIsOn);
        setHealth();
        getItemLoc().setNoEntrySign(this);

    }

    /**
     * Construct a pre-existing (e.g. saved) item.
     * @param tileTheItemIsOn The tile the item is on.
     * @param health How much health the sign has remaining.
     */
    public NoEntry(TileInteractable tileTheItemIsOn, int health) {
        super(startingNoEntryGraphic, tileTheItemIsOn);
        this.health = health;
        getItemLoc().setNoEntrySign(this);
    }

    /**
     * Health of no entry sign begins at 5
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
	/**
	 * Provide a text representation of the bomb.
	 * @return text representation
	 */
    public String toString() {
        String textEquivalent = super.toString();
        textEquivalent = String.format("%s %d", textEquivalent, this.health);
        return textEquivalent;
    }
    
    @Override
    void itemAction() { 
    }
}