import javafx.scene.image.Image;

//lethal items - kills rats
// findRats method here as it appears in bomb and gas

public abstract class LethalItem extends Item{

    /**
     * constructor method
     */
    public LethalItem(int scKey, Image itemGraphic, TileInteractable tileTheItemIsOn){
        super(scKey, itemGraphic, tileTheItemIsOn);
    }

    /**
     * method to kill rat and remove from board
     * @param rat Rat to be removed
     */
    protected void killRat(Rat rat) {
        RatManager.removeRat(rat);
    }

}