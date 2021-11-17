import java.util.ArrayList;

public class TileInteractable extends Tile {

    private boolean isHidden;
    private boolean isMating;
    // stores a list of rats currently on tile
    private ArrayList<Rat> ratsOnTile;
    // stores a list of items currently on tile
    private ArrayList<Item> itemOnTile;
    // stores tiles adjecent to itself
    private Tile northTile, eastTile, southTile, westTile;

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

    // takes compass direction as string and tile as input
    public void setAdjacentTile(String direction, Tile adjacentTile) {
        switch (direction.toLowerCase()) {
            case "north":
                this.northTile = adjacentTile;
                break;
            case "east":
                this.eastTile = adjacentTile;
                break;
            case "south":
                this.southTile = adjacentTile;
                break;
            case "west":
                this.westTile = adjacentTile;
                break;
        }
    }
    
    // isMating depends on if Tile stores the rats that are on it
    // if so isMating can be set from inside the tile as it just has to check
    // if there's two rats and if so are they of opposite gender and able to mate    
}
