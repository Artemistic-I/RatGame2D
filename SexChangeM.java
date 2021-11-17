public class SexChangeM extends Item{

    public SexChangeM(Rat rat) {

        super(rat);
        changeSex(super.getAffectedRat());

    }

    /**
     * method to change rats gender from male to female
     * @param rat to change gender
     */
    public void changeSex(Rat r) {
        private boolean rSex = r.getIsMale();

        if (rSex == True) {
            r.setIsMale(False);  //setIsMale method required in Rat class
        }

    }

}