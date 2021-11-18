
public class SexChangeF extends Item{

    static final int SHORTCUT_KEY = 117; //bound to F6

    public SexChangeF() {

        super(SHORTCUT_KEY);

    }

    /**
     * method to change rats gender from female to male
     * @param rat to change gender
     */
    public void changeSex(Rat r) {
        private boolean rSex = r.getIsMale();

        if (rSex == False) {
            r.setIsMale(True);  //setIsMale method required in Rat class
        }

    }

}