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
public class Rat {

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
	private final static int AGE_WHEN_MATURE = 40;
	private final static int PREGNANCY_LENGTH = 10;
	private final static int SEX_LENGTH = 5;
	//private final static String BABY_RAT_IMAGE_URL = "images/uglyBabyRat.png";
	//private final static String MALE_RAT_IMAGE_URL = "images/MaleRat.png";
	//private final static String FEMALE_RAT_IMAGE_URL = "images/FemaleRat.png";
	//private final static String PREGNANT_RAT_IMAGE_URL = "images/PregnantRat.png";
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

	/**
	 *
	 * @param ratSex
	 * @param tileTheRatIsOn
	 * @param direction
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
	 *
	 * @param ratSex
	 * @param ratMaturity
	 * @param isPregnant
	 * @param isSterile
	 * @param isHavingSex
	 * @param tileTheRatIsOn
	 * @param direction
	 * @param ratAge
	 * @param ageToGiveBirth
	 * @param ageToFinishHavingSex
	 * @param unbornRatsCount
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
	 *
	 */
	private void mature() {
		ratMaturity = RatMaturity.ADULT;
		this.updateGraphic();
	}

	/**
	 *
	 * @param graphicsContext
	 */
	public void update(GraphicsContext graphicsContext) {
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
					this.Breed();
				}
			}
		} else {
			this.move();
		}
		this.ratAge++;
		if (this.canBreed()) {
			this.attemptBreeding();
		}
		this.draw(ratGraphic, graphicsContext);

	}

	/**
	 *
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

	private void isNowHavingSex() {
		this.isHavingSex = true;
		this.ageToFinishHavingSex = this.ratAge + SEX_LENGTH;

	}

	/**
	 *
	 */
	private void move() {
		ArrayList<String> possibleMoves = new ArrayList<String>(tileTheRatIsOn.possibleMoves());
		if (possibleMoves.size() == 1) {
			this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(turnAround(direction));
			this.direction = turnAround(direction);
		} else if (possibleMoves.size() > 1) {
			possibleMoves.remove(turnAround(direction));
			Random rand = new Random();
			String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
			this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(randomDirection);
			this.direction = randomDirection;
		}
		updateGraphic();
	}

	/**
	 *
	 * @param direction
	 * @return
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
	 *
	 * @param graphicsContext
	 */
	private void draw(Image graphic, GraphicsContext graphicsContext) {
		if (!(tileTheRatIsOn instanceof TileTunnel)) {
			graphicsContext.drawImage(graphic, this.tileTheRatIsOn.getyCoordinate() * Gameboard.getTileSize(),
					this.tileTheRatIsOn.getxCoordinate() * Gameboard.getTileSize());
		}
	}

	/**
	 *
	 * @return
	 */
	public Boolean canBreed() {
		return ((ratMaturity == RatMaturity.ADULT) && !isPregnant && !isSterile && !isHavingSex);
	}

	/**
	 *
	 */
	public void Breed() {
		this.isPregnant = true;
		this.updateGraphic();
		this.unbornRatsCount = 3;
		this.ageToGiveBirth = this.ratAge + PREGNANCY_LENGTH;
	}

	/**
	 *
	 * @return
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
	 *
	 */
	public void giveBirth() {
		RatManager.addRat(new Rat(generateRandomBabyRatSex(), this.tileTheRatIsOn, this.direction, RatManager.incrimentRatsAdded()));
		this.unbornRatsCount--;
	}

	/**
	 *
	 * @param targetSex
	 */
	public void changeSex(RatSex targetSex) {
		if (this.ratSex != targetSex) {
			this.ratSex = targetSex;
			this.updateGraphic();
		}

	}

	/**
	 *
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
					//this.ratGraphic = new Image(PREGNANT_RAT_IMAGE_URL);
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
					//this.ratGraphic = new Image(FEMALE_RAT_IMAGE_URL);
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
				//this.ratGraphic = new Image(MALE_RAT_IMAGE_URL);
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
			//this.ratGraphic = new Image(BABY_RAT_IMAGE_URL);
		}
	}

	/**
	 *
	 * @return
	 */
	public Tile getLocation() {
		return this.tileTheRatIsOn;
	}

	/**
	 *
	 * @return
	 */
	public RatSex getSex() {
		return ratSex;
	}

	/**
	 *
	 */
	public void sterilise() {
		this.isSterile = true;
	}

	/**
	 *
	 * @return
	 */
	public RatMaturity getRatMaturity() {
		return this.ratMaturity;
	}

	/**
	 *
	 * @return
	 */
	public Boolean getIsPregnant() {
		return this.isPregnant;
	}

	/**
	 *
	 * @return
	 */
	public Boolean getIsSterile() {
		return this.isSterile;
	}

	/**
	 *
	 * @return
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 *
	 * @return
	 */
	public int getUnbornRatCount() {
		return this.unbornRatsCount;
	}

	public int getUniqueIdentifier() {
		return this.uniqueIdentifier;
	}

	/**
	 *
	 */
	public String toString() {
		String textEquivalent = String.format("%s %s %s %s %s %d %d %s %d %d %d %d %d", ratSex, ratMaturity, isPregnant,
				isSterile, isHavingSex, tileTheRatIsOn.getxCoordinate(), tileTheRatIsOn.getyCoordinate(), direction,
				ratAge, ageToGiveBirth, ageToFinishHavingSex, unbornRatsCount, uniqueIdentifier);
		return textEquivalent;
	}
}