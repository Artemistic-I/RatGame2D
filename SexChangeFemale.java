import javafx.scene.image.Image;

public class SexChangeFemale extends Item{

    static final int SHORTCUT_KEY = 117; //bound to F6
    private static final Image FEMALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/FemaleSexChangeGraphic.png");

    public SexChangeFemale(Tile tileTheItemIsOn) {

        super(SHORTCUT_KEY, FEMALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    /**
     * method to change rats gender from female to male
     * @param r Rat to change gender
     */
    public void changeSex(Rat rat) {
        if (rat.getSex() == FEMALE) {
        	rat.changeSex(MALE);
        }
    }
}