import javafx.scene.image.Image;

public class Sterilisation extends Item{
    //works within small radius
    //rats cannot reproduce

	private static final Image STERILISATION_GRAPHIC = new Image("images/ItemGraphics/SterilisationGraphic.png");
    static final int SHORTCUT_KEY = 119; //bound to F8
    static final int AREA = 5;
    static final int TIME = 3;

    /**
     * constructor
     */
    public Sterilisation(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, STERILISATION_GRAPHIC, tileTheItemIsOn);

    }

    /**
     * method to sterilize rats
     */
    public void sterilize() {

        Arraylist<Rat> rats = findRats(tileTheItemIsOn, AREA);

        for (int i = 0; i < rats.size(); i++) {
            Rat rat = rats.get(i);
            rat.sterilise();
        }

    }

}