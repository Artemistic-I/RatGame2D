import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

/**
 * Models the change sex to female item
 * @author aidan
 *
 */
public class SexChangeFemale extends Item{

    private static final Image FEMALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/FemaleSexChangeGraphic.png");

    /**
     * Create a sex change to female item.
     * @param tileTheItemIsOn Tile that the sex change to female item is placed on.
     */
    public SexChangeFemale(TileInteractable tileTheItemIsOn) {

        super(FEMALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    @Override
    /** 
     * Attempt to change the sex of the first rat that comes into contact with it to female.
     */
    public void itemAction() {
		Boolean hasBeenUsed = false;
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && !hasBeenUsed) {
    		Rat rat = ratsOnTile.pop();
    		rat.changeSex(RatSex.FEMALE);
    		hasBeenUsed = true;
    	}
    	if (hasBeenUsed) {
    		ItemManager.removeItem(this);
    	}
	}
}