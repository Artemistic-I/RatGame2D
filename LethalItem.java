
//lethal items - kills rats
// findRats method here as it appears in bomb and gas

public class LethalItem extends Item{

    /**
     * constructor method
     */
    public LethalItem(int scKey){

        super(scKey);

    }

    /**
     * method to find rats to kill
     * @return arraylist of rats to kill
     */
    private ArrayList<Rat> findRats(int x, int y) {

        ArrayList<Rat> ratsFound = new ArrayList<Rat>();
        //find rats in given area
        return ratsFound;
    }

    /**
     * method to kill rat and remove from board
     * @param rat to be removed
     */
    private void killRat(Rat rat) {

    }

}