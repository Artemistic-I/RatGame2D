import javafx.scene.image.Image;

public class Gas extends LethalItem{

	private static final Image GAS_GRAPHIC = new Image("images/ItemGraphics/GasGraphic.png");
    static final int SHORTCUT_KEY = 114; //bound to F3
    static final int AREA = 2;
    //static final int TIME = ; GET FROM ITEM MAIN
    static final int RAT_EXPOSURE_TIME = 3;

    /**
     * constructor method
     */
    public Gas(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, GAS_GRAPHIC, tileTheItemIsOn);

    }

    /**
     * method to expand gas to maximum area
     */
    public void expand() {

    }
}