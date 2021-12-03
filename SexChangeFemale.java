import javafx.scene.image.Image;

public class SexChangeFemale extends Item{

    static final int SHORTCUT_KEY = 117; //bound to F6
    private static final Image FEMALE_SEX_CHANGE_GRAPHIC = new Image("images/ItemGraphics/FemaleSexChangeGraphic.png");

    RatManager rm = new RatManager();

    /**
     *Constructor method
     */
    public SexChangeFemale(TileInteractable tileTheItemIsOn) {

        super(SHORTCUT_KEY, FEMALE_SEX_CHANGE_GRAPHIC, tileTheItemIsOn);

    }

    @Override
    void itemAction() {
        changeSex(getAffectedRat());
        ItemManager.removeItem(this);
    }

    /**
     * method to change rats gender from female to male
     * @param rat.getSex() to change gender
     */
    public void changeSex(Rat rat) {
        if (rat.getSex() == RatSex.FEMALE) {
        	rat.changeSex(RatSex.MALE);
        }
    }
}