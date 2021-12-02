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

    //not much in this subclass as it is death on contact

}