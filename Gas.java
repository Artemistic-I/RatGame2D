import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Stack;

public class Gas extends LethalItem{

	private static final Image GAS_GRAPHIC = new Image("images/ItemGraphics/GasGraphic.png");
    static final int SHORTCUT_KEY = 114; //bound to F3
    static final int AREA = 2;
    static final int RAT_EXPOSURE_TIME = 3;
    int gasTime = 5;

    /**
     * constructor method
     */
    public Gas(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, GAS_GRAPHIC, tileTheItemIsOn);

    }

    /**
     * finds rats to be killed by gas
     */
    @Override
    void itemAction() {
        ArrayList<TileInteractable> gassedTiles = expand();
        Stack<Rat> ratsToKill = RatManager.ratsOnTiles(gassedTiles);;
        while (!ratsToKill.isEmpty()) {
            RatManager.removeRat(ratsToKill.pop());
        }
        ItemManager.removeItem(this);
    }

    /**
     * for the length that gas is on the board expands into different tiles
     * @return
     */
    private ArrayList<TileInteractable> expand(){
        ArrayList<TileInteractable> gassedTiles = new ArrayList<>();
        ArrayList<String> possibleMoves = new ArrayList<String>(tileTheItemIsOn.possibleMoves());
        for (String direction : possibleMoves) {
            TileInteractable tile = tileTheItemIsOn;
            do {
                gassedTiles.add((TileInteractable) tile.getAdjacentTile(direction));
                tile = (TileInteractable) tile.getAdjacentTile(direction);
            } while (gasTime > 0);
        }
        return gassedTiles;
    }
}