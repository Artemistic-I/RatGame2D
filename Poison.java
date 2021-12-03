import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

public class Poison extends LethalItem{

	private static final Image POISON_GRAPHIC = new Image("images/ItemGraphics/PoisonGraphic.png");
    static final int SHORTCUT_KEY = 116; //bound to F5

    /**
     * constructor
     */
    public Poison(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, POISON_GRAPHIC, tileTheItemIsOn);

    }

	@Override
	void itemAction() {
		/**
		Boolean hasPoisoned = false;
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && !hasPoisoned) {
    		Rat rat = ratsOnTile.pop();
    		RatManager.removeRat(rat);
    		hasPoisoned = true;
    	}
		 */
		Rat ratToKill = getAffectedRat();
		RatManager.removeRat(ratToKill);
		ItemManager.removeItem(this);

		/**
    	if (hasPoisoned) {
    		ItemManager.removeItem(this);
    	}
		 */
		
	}


}