import java.util.ArrayList;
import java.util.Random;

public class TileInteractable extends Tile {

    private boolean isHidden;
    private boolean isMating;
    // stores a list of rats currently on tile
    // private ArrayList<Rat> ratsOnTile;
    // // stores a list of items currently on tile
    // private ArrayList<Item> itemOnTile;

    public TileInteractable(int width, int height) {
        super(width, height);
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
		for (int i = 0; i < adjacentTiles.length; i++) {
            if (adjacentTiles[i] != null) {
                switch (i) {
                    case 0:
                        moves.add("North");
                        break;
                    case 1:
                        moves.add("East");
                        break;
                    case 2:
                        moves.add("South");
                        break;
                    default:
                        moves.add("West");
                        break;
                }
            }
        }
        return moves;
	}        
    
    // isMating depends on if Tile stores the rats that are on it
    // if so isMating can be set from inside the tile as it just has to check
    // if there's two rats and if so are they of opposite gender and able to mate    
}
