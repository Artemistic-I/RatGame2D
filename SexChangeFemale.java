import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

public class SexChangeFemale extends Item{

    private static final Image FEMALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/FemaleSexChangeGraphic.png");

    /**
     *Constructor method
     */
    public SexChangeFemale(TileInteractable tileTheItemIsOn) {

        super(FEMALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    @Override
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