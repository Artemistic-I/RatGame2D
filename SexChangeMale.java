public class SexChangeMale extends Item{

    static final int SHORTCUT_KEY = 118; //bound to F7

    public SexChangeMale() {

        super(SHORTCUT_KEY);

    }

    /**
     * method to change rats gender from male to female
     * @param r Rat to change gender
     */
    public void changeSex(Rat rat) {
        if (rat.getSex() == RatSex.male) {
        	rat.changeSex(RatSex.male);
        }
    }

}