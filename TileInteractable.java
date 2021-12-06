import java.util.ArrayList;
import javafx.scene.image.Image;

public class TileInteractable extends Tile {

	/**
	 * 
	 * @param tileGraphic the image of this tile type (what it will look like when drawn)
     * @param row the rows it's in, in the gameboard 2d array
     * @param column the column it's in, in the gameboard 2d array
	 */
    public TileInteractable(Image tileGraphic, int row, int column) {
    	super(tileGraphic, row, column);
		this.setIsInteractable(true);
	}

	/**
	 * Used for rat movement
	 * @return returns all tiles adjacent which are interactable
	 */
    public ArrayList<String> possibleMoves() {
        ArrayList <String> moves = new ArrayList<>();
		if (this.getAdjacentTile("North").isInteractable()) {
			moves.add("North");
		}
		if (this.getAdjacentTile("East").isInteractable()) {
			moves.add("East");
		}
		if (this.getAdjacentTile("South").isInteractable()) {
			moves.add("South");
		}
		if (this.getAdjacentTile("West").isInteractable()) {
			moves.add("West");
		}
		return moves;
    }
}
