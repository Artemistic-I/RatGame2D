import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DeathRat extends LethalItem {

	private int ratsKilled;
	private static final int KILL_LIMIT = 5;
	private String direction;
	private static final Image DEATH_RAT_GRAPHIC = new Image("images/ItemGraphics/DeathRatGraphic.png");

	/**
	 * constructor
	 */
	public DeathRat(TileInteractable tileTheItemIsOn, String direction) {
		super(DEATH_RAT_GRAPHIC, tileTheItemIsOn);
		this.direction = direction;
		this.ratsKilled = 0;
	}
	// constructor for loading
	public DeathRat(TileInteractable tileTheItemIsOn, String direction, int ratsKilled) {
		super(DEATH_RAT_GRAPHIC, tileTheItemIsOn);
		this.direction = direction;
		this.ratsKilled = ratsKilled;
	}

	@Override
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		itemAction();
		if ((gameDuration % 500) == 0) {
			this.move();
		}
		this.draw(graphicsContext);
		itemAction();
	}

	private void move() {
		ArrayList<String> possibleMoves = new ArrayList<String>(this.tileTheItemIsOn.possibleMoves());
		if (possibleMoves.size() == 1) {
			this.tileTheItemIsOn = (TileInteractable) tileTheItemIsOn.getAdjacentTile(turnAround(direction));
			this.direction = turnAround(direction);
		} else if (possibleMoves.size() > 1) {
			possibleMoves.remove(turnAround(direction));
			Random rand = new Random();
			String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
			this.tileTheItemIsOn = (TileInteractable) tileTheItemIsOn.getAdjacentTile(randomDirection);
			this.direction = randomDirection;
		}
	}

	private String turnAround(String direction) {
		switch (direction) {
		case "North":
			return "South";
		case "South":
			return "North";
		case "West":
			return "East";
		case "East":
			return "West";
		default:
			return "";
		}
	}

	public void draw(GraphicsContext graphicsContext) {
		if (!(this.tileTheItemIsOn instanceof TileTunnel)) {
			graphicsContext.drawImage(this.itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
					this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
		}
	}

	@Override
	public void itemAction() {
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && (ratsKilled < KILL_LIMIT)) {
    		Rat rat = ratsOnTile.pop();
	    		RatManager.removeRat(rat);
    	}
    	if (ratsKilled >= KILL_LIMIT) {
    		ItemManager.removeItem(this);
    	}

	}

	@Override
	public String toString() {
		String textEquivalent = super.toString();
		textEquivalent = String.format("%s %d %s", textEquivalent, this.ratsKilled, this.direction);
		return textEquivalent;
	}

}