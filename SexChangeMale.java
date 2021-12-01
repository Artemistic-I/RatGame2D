import javafx.scene.image.Image;

public class SexChangeMale extends Item{

    static final int SHORTCUT_KEY = 118; //bound to F7
    private static final Image MALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/MaleSexChangeGraphic.png");
    
    public SexChangeMale(Tile tileTheItemIsOn) {

        super(SHORTCUT_KEY, MALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    /**
     * method to change rats gender from male to female
     * @param r Rat to change gender
     */
    public void changeSex(Rat rat) {
        if (rat.getSex() == MALE) {
        	rat.changeSex(FEMALE);
        }
    }

}