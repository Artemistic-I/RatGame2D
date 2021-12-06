import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

/**
 * Models the change sex to male item
 * @author aidan
 *
 */
public class SexChangeMale extends Item{
	
    private static final Image MALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/MaleSexChangeGraphic.png");

    /**
     * Create a sex change to male item.
     * @param tileTheItemIsOn Tile that the sex change to male item is placed on.
     */
    public SexChangeMale(TileInteractable tileTheItemIsOn) {
        super(MALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);
    }

    @Override
    /** 
     * Attempt to change the sex of the first rat that comes into contact with it to male.
     */
    public void itemAction() {
		Boolean hasBeenUsed = false;
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && !hasBeenUsed) {
    		Rat rat = ratsOnTile.pop();
    		rat.changeSex(RatSex.MALE);
    		hasBeenUsed = true;
    	}
    	if (hasBeenUsed) {
    		ItemManager.removeItem(this);
    	}
	}

}