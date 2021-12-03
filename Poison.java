import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import javafx.scene.image.Image;

public class Poison extends LethalItem{

	private static final Image POISON_GRAPHIC = new Image("images/ItemGraphics/PoisonGraphic.png");

    /**
     * constructor
     */
    public Poison(TileInteractable tileTheItemIsOn) {

        super(POISON_GRAPHIC, tileTheItemIsOn);

    }

	@Override
	public void itemAction() {
		Boolean hasPoisoned = false;
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && !hasPoisoned) {
    		Rat rat = ratsOnTile.pop();
    		RatManager.removeRat(rat);
    		hasPoisoned = true;
    	}
    	if (hasPoisoned) {
    		ItemManager.removeItem(this);
    	}
		
	}


}