import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

public class SexChangeMale extends Item{
	
    private static final Image MALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/MaleSexChangeGraphic.png");

    /**
     * Constructor Method
     */
    public SexChangeMale(TileInteractable tileTheItemIsOn) {

        super(MALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    @Override
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