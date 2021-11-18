
public class NoEntry extends Item{

    static final int SHORTCUT_KEY = 115; //bound to F4
    static final int HEALTH = 5;

    /**
     * constructor method
     */
    public NoEntry() {

        super(this.SHORTCUT_KEY)

    }

    //change direction method

    /**
     * method to degrade health of sign
     */
    public void degradeHealth(this.HEALTH) {

        private int health = this.HEALTH

        health -= 1;

        if (health == 0) {
            this.removeItem();
        }

    }

}