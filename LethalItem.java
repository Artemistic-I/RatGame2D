import javafx.scene.image.Image;

public abstract class LethalItem extends Item{

    public LethalItem(Image itemGraphic, TileInteractable tileTheItemIsOn){
        super(itemGraphic, tileTheItemIsOn);
    }
}