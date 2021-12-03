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

    private RatSex ratSex;
    private RatMaturity ratMaturity;
    private Boolean isPregnant;
    private Boolean isSterile;
    private TileInteractable tileTheRatIsOn;
    private String direction;
    private Image ratGraphic;
    private final static int AGE_WHEN_MATURE = 40;
    private final static int PREGNANCY_LENGTH = 10;
    private int ageToGiveBirth;
    private int ratAge;
    private int unbornRatsCount;
    private final static String BABY_RAT_IMAGE_URL = "images/uglyBabyRat.png";
    private final static String MALE_RAT_IMAGE_URL = "images/MaleRat.png";
    private final static String FEMALE_RAT_IMAGE_URL = "images/FemaleRat.png";
    private final static String PREGNANT_RAT_IMAGE_URL = "images/PregnantRat.png";
    
    /**
     * 
     * @param ratSex
     * @param tileTheRatIsOn
     * @param direction
     */
    public Rat(RatSex ratSex, TileInteractable tileTheRatIsOn, String direction) {
        this.ratSex = ratSex;
        this.ratMaturity = RatMaturity.BABY;
        this.isPregnant = false;
        this.isSterile = false;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
        this.ratAge = 0;
        this.updateGraphic();
    }
    
    /**
     * 
     * @param ratSex
     * @param ratMaturity
     * @param isPregnant
     * @param isSterile
     * @param tileTheRatIsOn
     * @param direction
     * @param ratAge
     */
    public Rat(RatSex ratSex, RatMaturity ratMaturity, Boolean isPregnant, Boolean isSterile,  TileInteractable tileTheRatIsOn, String direction, int ratAge) {
        this.ratSex = ratSex;
        this.ratMaturity = ratMaturity;
        this.isPregnant = isPregnant;
        this.isSterile = isSterile;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
        this.ratAge = ratAge;
        this.updateGraphic();
    }
    
    /**
     * 
     */
    public String toString() {
    	String textEquivalent = String.format("%s %s isPregnant:%s isSterile:%s xPosition:%d yPosition:%d direction:%s age:%d" , ratSex, ratMaturity, isPregnant, isSterile, tileTheRatIsOn.getxCoordinate(), tileTheRatIsOn.getyCoordinate(), direction, ratAge);
		return textEquivalent;
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
    	this.ratAge++;
        this.move();
        if (this.canBreed()) {
        	this.attemptBreeding();
        }
        this.draw(graphicsContext);
        
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
    			if (this.ratSex == RatSex.FEMALE) {
					this.Breed();
				} else {
					rat.Breed();
				}
    			hasBred = true;
    		}
    	}
    }	

    /**
     * 
     */
    private void move() {
        ArrayList<String> possibleMoves = new ArrayList<String>(tileTheRatIsOn.possibleMoves());
        if (possibleMoves.size() == 1) {
            this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(turnAround(direction));
            this.direction = turnAround(direction);
        } else {
            possibleMoves.remove(turnAround(direction));
            Random rand = new Random();
            String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
            this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(randomDirection);
            this.direction = randomDirection;
        }
    }
    
    /**
     * 
     * @param direction
     * @return
     */
    private String turnAround(String direction) {
        switch(direction){
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
    private void draw(GraphicsContext graphicsContext) {
        if (!(tileTheRatIsOn instanceof TileTunnel)) {
        	graphicsContext.drawImage(ratGraphic, this.tileTheRatIsOn.getyCoordinate()*Gameboard.getTileSize(), this.tileTheRatIsOn.getxCoordinate()*Gameboard.getTileSize()); 
        }
    }

    /**
     * 
     * @return
     */
    public Boolean canBreed() {
        return ((ratMaturity == RatMaturity.ADULT) && !isPregnant && !isSterile);
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
    	RatManager.addRat(new Rat(generateRandomBabyRatSex(), this.tileTheRatIsOn, this.direction));
    	this.unbornRatsCount--;
    }

    /**
     * 
     * @param targetSex
     */
    public void changeSex(RatSex targetSex) {
        if  (this.ratSex != targetSex) {
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
            		this.ratGraphic = new Image(PREGNANT_RAT_IMAGE_URL);
            	} else {
            		this.ratGraphic = new Image(FEMALE_RAT_IMAGE_URL);
            	}
            } else {
            	this.ratGraphic = new Image(MALE_RAT_IMAGE_URL);
            }
        } else {
        	this.ratGraphic = new Image(BABY_RAT_IMAGE_URL);
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
}