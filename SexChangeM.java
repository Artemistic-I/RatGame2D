public class SexChangeM extends Item{

    //static final int SHORTCUT_KEY = ;

    public SexChangeM() {

        super(SHORTCUT_KEY);

    }

    /**
     * method to change rats gender from male to female
     * @param rat to change gender
     */
    public void changeSex(Rat r) {
        private boolean rSex = r.getIsMale();

        if (rSex == true) {
            r.setIsMale(false);  //setIsMale method required in Rat class
        }

    }

}