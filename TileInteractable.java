import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class TileInteractable extends Tile {

    private boolean isHidden;
    private boolean isMating;
    // stores a list of rats currently on tile
    // private ArrayList<Rat> ratsOnTile;
    // // stores a list of items currently on tile
    // private ArrayList<Item> itemOnTile;

    public TileInteractable(Image tileGraphic) {
    	super(tileGraphic);
    	setInteractable(true);
	}

	public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean getIsHidden() {
        return this.isHidden;
    }

    public ArrayList<String> possibleMoves() {
        ArrayList <String> moves = new ArrayList<>();
		if (this.getAdjacentTile("North") instanceof TileInteractable) {
			moves.add("North");
		}
		if (this.getAdjacentTile("East") instanceof TileInteractable) {
			moves.add("East");
		}
		if (this.getAdjacentTile("South") instanceof TileInteractable) {
			moves.add("South");
		}
		if (this.getAdjacentTile("West") instanceof TileInteractable) {
			moves.add("West");
		}
		return moves;
    }
    
    // isMating depends on if Tile stores the rats that are on it
    // if so isMating can be set from inside the tile as it just has to check
    // if there's two rats and if so are they of opposite gender and able to mate    
}
