
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
     * method to kill rat and remove from board
     * @param rat Rat to be removed
     */
    protected void killRat(Rat rat) {
        //kill rat in rat manager
    }

}