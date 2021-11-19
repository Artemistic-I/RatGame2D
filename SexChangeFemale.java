
public class SexChangeFemale extends Item{

    static final int SHORTCUT_KEY = 117; //bound to F6

    public SexChangeFemale() {

        super(SHORTCUT_KEY);

    }

    /**
     * method to change rats gender from female to male
     * @param r Rat to change gender
     */
    public void changeSex(Rat rat) {
        if (rat.getSex() == RatSex.female) {
        	rat.changeSex(RatSex.female);
        }
    }
}