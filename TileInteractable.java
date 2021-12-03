import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class TileInteractable extends Tile {

    public TileInteractable(Image tileGraphic, int row, int column) {
    	super(tileGraphic, row, column);
		this.setIsInteractable(true);
	}

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
