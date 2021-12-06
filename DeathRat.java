import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author Aidan English Stephen
 *
 */
public class DeathRat extends LethalItem {

	private static final Image DEATH_RAT_GRAPHIC = new Image("images/ItemGraphics/DeathRatGraphic.png");
	private static final int KILL_LIMIT = 5;
	private static final int WAIT_TIME = 8;
	private int age;
	private int ratsKilled;
	private String direction;

	/**
	 * Constructor for newly created Death Rats.
	 * @param tileTheItemIsOn Tile the death rat is being created on.
	 * @param direction Direction that the death rat is facing.
	 */
	public DeathRat(TileInteractable tileTheItemIsOn, String direction) {
		super(DEATH_RAT_GRAPHIC, tileTheItemIsOn);
		this.direction = direction;
		this.ratsKilled = 0;
	}

	/**
	 * Constructor for already existing rats e.g. previously saved.
	 * @param tileTheItemIsOn Tile the death rat is currently on.
	 * @param direction Direction that the death rat is facing.
	 * @param ratsKilled How many rats the death rat has killed so far.
	 * @param age How long the rat has existed.
	 */
	public DeathRat(TileInteractable tileTheItemIsOn, String direction, int ratsKilled, int age) {
		super(DEATH_RAT_GRAPHIC, tileTheItemIsOn);
		this.direction = direction;
		this.ratsKilled = ratsKilled;
		this.age = age;
	}

	@Override
	/**
	 * Update the death rat.
	 * @param graphicsContext Where the death rat should be drawn.
	 * @param gameDuration The milliseconds that have elapsed since the game started.
	 */
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		itemAction();
		if ((gameDuration % 500) == 0 && age > WAIT_TIME) {
			this.move();
		}
		this.draw(graphicsContext);
		itemAction();
		age++;
	}

	/**
	 * Move the death rat to a new tile.
	 */
	private void move() {
		ArrayList<String> possibleMoves = new ArrayList<String>(this.tileTheItemIsOn.possibleMoves());
		while (true) {
			if (possibleMoves.size() == 1) {
				this.tileTheItemIsOn = (TileInteractable) tileTheItemIsOn.getAdjacentTile(turnAround(direction));
				this.direction = turnAround(direction);
				break;
			} else if (possibleMoves.size() > 1) {
				Random rand = new Random();
				String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
				TileInteractable newTile = (TileInteractable) tileTheItemIsOn.getAdjacentTile(randomDirection);
				if (newTile.getNoEntrySign() == null) {
					if (randomDirection != turnAround(direction)) {
						this.tileTheItemIsOn = newTile;
						this.direction = randomDirection;
					}
					break;
				} else {
					newTile.getNoEntrySign().degradeHealth();
					possibleMoves.remove(randomDirection);
				}
			}
		}
	}

	/**
	 * Determine the reverse of the current direction.
	 * @return The new direction if the rat was to turn around.
	 */
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


	/**
	 * Draw the death rat.
	 * @param graphicsContext Where to draw.
	 */
	public void draw(GraphicsContext graphicsContext) {
		if (!(this.tileTheItemIsOn instanceof TileTunnel)) {
			graphicsContext.drawImage(this.itemGraphic, this.tileTheItemIsOn.getyCoordinate() * Gameboard.getTileSize(),
					this.tileTheItemIsOn.getxCoordinate() * Gameboard.getTileSize());
		}
	}

	@Override
	/**
	 * Kill rats that come into contact as long as the Death Rat is within it's killing limits
	 */
	public void itemAction() {
    	Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheItemIsOn)));
    	while (!ratsOnTile.isEmpty() && (ratsKilled < KILL_LIMIT)) {
    		Rat rat = ratsOnTile.pop();
	    		RatManager.removeRat(rat);
	    		ratsKilled++;
    	}
    	if (ratsKilled >= KILL_LIMIT) {
    		ItemManager.removeItem(this);
    	}

	}

	@Override
	/**
	 * Provide a string representation of the death rat.
	 */
	public String toString() {
		String textEquivalent = super.toString();
		textEquivalent = String.format("%s %d %s %d", textEquivalent, this.ratsKilled, this.direction, this.age);
		return textEquivalent;
	}

}