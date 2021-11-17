
public class Sterilisation extends Item{
    //works within small radius
    //rats cannot reproduce

    //static final int SHORTCUT_KEY = ;
    //static final int AREA = ;
    //static final int TIME = ;

    /**
     * constructor
     */
    public Sterilisation() {

        super(SHORTCUT_KEY);

    }

    /**
     * method to sterilize rats
     */
    public void sterilize() {

        rats = findRats(this.AREA, this.AREA);//find rats needs coords

        for (int i = 0; i < rats.size(); i++) {
            Rat r = rats(i);
            r.setIsSterile(True);//this method is required in rat class
        }

    }

}