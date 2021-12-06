import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class models a rat and provides all the rats functionality.
 * @author Aidan English Stephen
 */
public class Rat {

	private final static String RAT_SEX_IMAGE_URL = "images/RatSex.png";
	private final static String BABY_RAT_NORTH_IMAGE_URL = "images/babyRatNorth.png";
	private final static String BABY_RAT_EAST_IMAGE_URL = "images/babyRatEast.png";
	private final static String BABY_RAT_SOUTH_IMAGE_URL = "images/babyRatSouth.png";
	private final static String BABY_RAT_WEST_IMAGE_URL = "images/babyRatWest.png";
	private final static String MALE_RAT_NORTH_IMAGE_URL = "images/MaleRatNorth.png";
	private final static String MALE_RAT_EAST_IMAGE_URL = "images/MaleRatEast.png";
	private final static String MALE_RAT_SOUTH_IMAGE_URL = "images/MaleRatSouth.png";
	private final static String MALE_RAT_WEST_IMAGE_URL = "images/MaleRatWest.png";
	private final static String FEMALE_RAT_NORTH_IMAGE_URL = "images/FemaleRatNorth.png";
	private final static String FEMALE_RAT_EAST_IMAGE_URL = "images/FemaleRatEast.png";
	private final static String FEMALE_RAT_SOUTH_IMAGE_URL = "images/FemaleRatSouth.png";
	private final static String FEMALE_RAT_WEST_IMAGE_URL = "images/FemaleRatWest.png";
	private final static String PREGNANT_RAT_NORTH_IMAGE_URL = "images/PregnantRatNorth.png";
	private final static String PREGNANT_RAT_EAST_IMAGE_URL = "images/PregnantRatEast.png";
	private final static String PREGNANT_RAT_SOUTH_IMAGE_URL = "images/PregnantRatSouth.png";
	private final static String PREGNANT_RAT_WEST_IMAGE_URL = "images/PregnantRatWest.png";
	private final static int AGE_WHEN_MATURE = 80;
	private final static int PREGNANCY_LENGTH = 20;
	private final static int SEX_LENGTH = 10;
	private int uniqueIdentifier;
	private RatSex ratSex;
	private RatMaturity ratMaturity;
	private Boolean isPregnant;
	private Boolean isSterile;
	private Boolean isHavingSex;
	private TileInteractable tileTheRatIsOn;
	private String direction;
	private int ratAge;
	private Image ratGraphic;
	private int ageToGiveBirth;
	private int ageToFinishHavingSex;
	private int unbornRatsCount;

	/**
	 * Construct a newly born baby rat.
	 * @param ratSex The sex of the rat (male / female).
	 * @param tileTheRatIsOn Reference to the tile the the rat is currently positioned on.
	 * @param direction Which direction the rat is currently facing.
	 */
	public Rat(RatSex ratSex, TileInteractable tileTheRatIsOn, String direction, int uniqueIdentifier) {
		this.ratSex = ratSex;
		this.ratMaturity = RatMaturity.BABY;
		this.isPregnant = false;
		this.isSterile = false;
		this.isHavingSex = false;
		this.tileTheRatIsOn = tileTheRatIsOn;
		this.direction = direction;
		this.updateGraphic();
		this.ratAge = 0;
		this.uniqueIdentifier = uniqueIdentifier;
	}

	/**
	 * Construct a rat that has already been born - providing all information required to model any rat.
	 * @param ratSex The sex of the rat (male / female).
	 * @param ratMaturity
	 * @param isPregnant Whether or not the rat is currently pregnant.
	 * @param isSterile Whether or not the rat is sterile.
	 * @param isHavingSex Whether or not the rat is currently having sex.
	 * @param tileTheRatIsOn Reference to the tile the the rat is currently positioned on.
	 * @param direction Which direction the rat is currently facing.
	 * @param ratAge
	 * @param ageToGiveBirth The relative age when the rat should begin giving birth if it is pregnant.
	 * @param ageToFinishHavingSex The relative age when the rat should conclude sex if it is having sex.
	 * @param unbornRatsCount Number of unborn baby rats in a rat that are due to be born.
	 */
	public Rat(RatSex ratSex, RatMaturity ratMaturity, Boolean isPregnant, Boolean isSterile, Boolean isHavingSex,
			TileInteractable tileTheRatIsOn, String direction, int ratAge, int ageToGiveBirth, int ageToFinishHavingSex,
			int unbornRatsCount, int uniqueIdentifier) {
		this.ratSex = ratSex;
		this.ratMaturity = ratMaturity;
		this.isPregnant = isPregnant;
		this.isSterile = isSterile;
		this.isHavingSex = isHavingSex;
		this.tileTheRatIsOn = tileTheRatIsOn;
		this.direction = direction;
		this.ratAge = ratAge;
		this.ageToGiveBirth = ageToGiveBirth;
		this.ageToFinishHavingSex = ageToFinishHavingSex;
		this.unbornRatsCount = unbornRatsCount;
		this.updateGraphic();
		this.uniqueIdentifier = uniqueIdentifier;
	}

	/**
	 * Bring the rat to maturity (adulthood).
	 */
	private void mature() {
		ratMaturity = RatMaturity.ADULT;
		this.updateGraphic();
	}

	/**
	 *
	 * @param graphicsContext
	 */
	public void update(GraphicsContext graphicsContext, long gameDuration) {
		if (ratMaturity == RatMaturity.BABY && ratAge >= AGE_WHEN_MATURE) {
			this.mature();
		}
		if (isPregnant && unbornRatsCount != 0 && this.ratAge >= this.ageToGiveBirth) {
			this.giveBirth();
		} else if (isPregnant && unbornRatsCount == 0) {
			this.isPregnant = false;
			this.updateGraphic();
		}
		if (isHavingSex) {
			this.draw(new Image(RAT_SEX_IMAGE_URL), graphicsContext);
			if (this.ratAge >= this.ageToFinishHavingSex) {
				this.isHavingSex = false;
				if (this.ratSex == RatSex.FEMALE) {
					this.breed();
				}
			}
		} else {
			if (this.ratMaturity == RatMaturity.BABY) {
				this.move();
			} else if ((gameDuration % TimelineMangaer.DELAY) == 0) {
				this.move();
			}
		}
		this.ratAge++;
		if (this.canBreed()) {
			this.attemptBreeding();
		}
		this.draw(ratGraphic, graphicsContext);

	}

	/**
	 * Search for any rats located in the same position and have sex with them if possible.
	 */
	private void attemptBreeding() {
		Boolean hasBred = false;
		Stack<Rat> ratsOnTile = RatManager.ratsOnTiles(new ArrayList<TileInteractable>(Arrays.asList(tileTheRatIsOn)));
		while (!ratsOnTile.isEmpty() && !hasBred) {
			Rat rat = ratsOnTile.pop();
			if (rat.canBreed() && rat.getSex() != this.ratSex) {
				this.isNowHavingSex();
				rat.isNowHavingSex();
				hasBred = true;
			}
		}
	}

	/**
	 * Initiate the process of a rat having sex.
	 */
	private void isNowHavingSex() {
		this.isHavingSex = true;
		this.ageToFinishHavingSex = this.ratAge + SEX_LENGTH;

	}

	/**
	 * Move the rat to a new tile.
	 */
	private void move() {
		ArrayList<String> possibleMoves = new ArrayList<String>(tileTheRatIsOn.possibleMoves());
		while (true) {
			if (possibleMoves.size() == 1) {
				this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(reverseDirection());
				this.direction = reverseDirection();
				break;
			} else if (possibleMoves.size() > 1) {
				Random rand = new Random();
				String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
				TileInteractable newTile = (TileInteractable) tileTheRatIsOn.getAdjacentTile(randomDirection);
				if (newTile.getNoEntrySign() == null) {
					if (randomDirection != reverseDirection()) {
						this.tileTheRatIsOn = newTile;
						this.direction = randomDirection;
						break;
					}
				} else {
					newTile.getNoEntrySign().degradeHealth();
					possibleMoves.remove(randomDirection);
				}
			}
		}
		updateGraphic();
	}

	/**
	 * Determine the reverse of the current direction.
	 * @return The new direction if the rat was to turn around.
	 */
	private String reverseDirection() {
		switch (this.direction) {
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
	 * Draw the rat onto the gameboard.
	 * @param graphicsContext what the drawing should be done upon.
	 */
	private void draw(Image graphic, GraphicsContext graphicsContext) {
		if (!(tileTheRatIsOn instanceof TileTunnel)) {
			graphicsContext.drawImage(graphic, this.tileTheRatIsOn.getyCoordinate() * Gameboard.getTileSize(),
					this.tileTheRatIsOn.getxCoordinate() * Gameboard.getTileSize());
		}
	}

	/**
	 * Whether or not the rat is capable of breeding.
	 * @return true if the rat is capable of breeding and false otherwise.
	 */
	public Boolean canBreed() {
		return ((ratMaturity == RatMaturity.ADULT) && !isPregnant && !isSterile && !isHavingSex);
	}

	/**
	 * Initiate the breeding process for a female rat.
	 */
	public void breed() {
		this.isPregnant = true;
		this.updateGraphic();
		this.unbornRatsCount = 3;
		this.ageToGiveBirth = this.ratAge + PREGNANCY_LENGTH;
	}

	/**
	 * Randomly generate the sex of a new baby rat.
	 * @return Generated sex (male / female).
	 */
	private RatSex generateRandomBabyRatSex() {
		Random rand = new Random();
		RatSex babyRatSex;
		if (rand.nextInt(2) == 0) {
			babyRatSex = RatSex.MALE;
		} else {
			babyRatSex = RatSex.FEMALE;
		}
		return babyRatSex;
	}

	/**
	 * Give birth to a baby rat.
	 */
	public void giveBirth() {
		RatManager.addRat(new Rat(generateRandomBabyRatSex(), this.tileTheRatIsOn, this.direction,
				RatManager.incrimentRatsAdded()));
		this.unbornRatsCount--;
	}

	/**
	 * Change the sex of the rat.
	 * @param targetSex The sex that the rat should be changed to.
	 */
	public void changeSex(RatSex targetSex) {
		if (this.ratSex != targetSex) {
			this.ratSex = targetSex;
			if (this.ratSex == RatSex.MALE && isPregnant) {
				this.isPregnant = false;
				this.unbornRatsCount = 0;
			}
			this.updateGraphic();
		}

	}

	/**
	 * Update the graphic to used for the rat based on it's state.
	 */
	private void updateGraphic() {
		if (ratMaturity == RatMaturity.ADULT) {
			if (ratSex == RatSex.FEMALE) {
				if (isPregnant) {
					if (direction.equals("North")) {
						this.ratGraphic = new Image(PREGNANT_RAT_SOUTH_IMAGE_URL);
					} else if (direction.equals("East")) {
						this.ratGraphic = new Image(PREGNANT_RAT_EAST_IMAGE_URL);
					} else if (direction.equals("South")) {
						this.ratGraphic = new Image(PREGNANT_RAT_NORTH_IMAGE_URL);
					} else {
						this.ratGraphic = new Image(PREGNANT_RAT_WEST_IMAGE_URL);
					}
				} else {
					if (direction.equals("North")) {
						this.ratGraphic = new Image(FEMALE_RAT_SOUTH_IMAGE_URL);
					} else if (direction.equals("East")) {
						this.ratGraphic = new Image(FEMALE_RAT_EAST_IMAGE_URL);
					} else if (direction.equals("South")) {
						this.ratGraphic = new Image(FEMALE_RAT_NORTH_IMAGE_URL);
					} else {
						this.ratGraphic = new Image(FEMALE_RAT_WEST_IMAGE_URL);
					}
				}
			} else {
				if (direction.equals("North")) {
					this.ratGraphic = new Image(MALE_RAT_SOUTH_IMAGE_URL);
				} else if (direction.equals("East")) {
					this.ratGraphic = new Image(MALE_RAT_EAST_IMAGE_URL);
				} else if (direction.equals("South")) {
					this.ratGraphic = new Image(MALE_RAT_NORTH_IMAGE_URL);
				} else {
					this.ratGraphic = new Image(MALE_RAT_WEST_IMAGE_URL);
				}
			}
		} else {
			if (direction.equals("North")) {
				this.ratGraphic = new Image(BABY_RAT_SOUTH_IMAGE_URL);
			} else if (direction.equals("East")) {
				this.ratGraphic = new Image(BABY_RAT_EAST_IMAGE_URL);
			} else if (direction.equals("South")) {
				this.ratGraphic = new Image(BABY_RAT_NORTH_IMAGE_URL);
			} else {
				this.ratGraphic = new Image(BABY_RAT_WEST_IMAGE_URL);
			}
		}
	}

	/**
	 * Get the location of the rat.
	 * @return Rat's current location.
	 */
	public Tile getLocation() {
		return this.tileTheRatIsOn;
	}

	/**
	 * Get the sex of the rat.
	 * @return Either it is a male or a female.
	 */
	public RatSex getSex() {
		return ratSex;
	}

	/**
	 * Sterilise the rat.
	 */
	public void sterilise() {
		this.isSterile = true;
	}

	/**
	 * Get the maturity  of the rat.
	 * @return Either it is an adult or a baby.
	 */
	public RatMaturity getRatMaturity() {
		return this.ratMaturity;
	}

	/**
	 * Get whether or not the rat is pregnant.
	 * @return true for pregnant, false otherwise.
	 */
	public Boolean getIsPregnant() {
		return this.isPregnant;
	}

	/**
	 * Get whether or not the rat is sterile.
	 * @return true for sterile, false otherwise.
	 */
	public Boolean getIsSterile() {
		return this.isSterile;
	}

	/**
	 * Get the current direction that the rat is facing.
	 * @return rat's direction.
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 * Get how many unborn rats are in this rat.
	 * @return Number of unborn rats.
	 */
	public int getUnbornRatCount() {
		return this.unbornRatsCount;
	}

	public int getUniqueIdentifier() {
		return this.uniqueIdentifier;
	}

	/**
	 * Provide a string representation of the rat.
	 */
	public String toString() {
		String textEquivalent = String.format("%s %s %s %s %s %d %d %s %d %d %d %d %d", ratSex, ratMaturity, isPregnant,
				isSterile, isHavingSex, tileTheRatIsOn.getxCoordinate(), tileTheRatIsOn.getyCoordinate(), direction,
				ratAge, ageToGiveBirth, ageToFinishHavingSex, unbornRatsCount, uniqueIdentifier);
		return textEquivalent;
	}
}