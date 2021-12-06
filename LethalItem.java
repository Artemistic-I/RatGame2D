import javafx.scene.image.Image;

/**
 * Models a lethal item
 * @author Josh / Mike
 *
 */
public abstract class LethalItem extends Item{
    public LethalItem(Image itemGraphic, TileInteractable tileTheItemIsOn){
        super(itemGraphic, tileTheItemIsOn);
    }
}