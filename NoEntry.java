
public class NoEntry extends Item{

    static final int SHORTCUT_KEY = 115; //bound to F4
    private int health;

    /**
     * constructor method
     */
    public NoEntry() {

        super(this.SHORTCUT_KEY);
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