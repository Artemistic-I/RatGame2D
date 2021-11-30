
public class Sterilisation extends Item{
    //works within small radius
    //rats cannot reproduce

    static final int SHORTCUT_KEY = 119; //bound to F8
    static final int AREA = 5;
    static final int TIME = 3;

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

        Arraylist<Rat> rats = findRats(itemLocation, AREA);

        for (int i = 0; i < rats.size(); i++) {
            Rat rat = rats.get(i);
            rat.sterilise();
        }

    }

}