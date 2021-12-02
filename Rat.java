import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rat {

    private RatSex ratSex;
    private RatMaturity ratMaturity;
    private Boolean isPregnant;
    private Boolean isSterile;
    private TileInteractable tileTheRatIsOn;
    private String direction;
    private Image ratGraphic;
    private final static int AGE_WHEN_MATURE = 40;
    private int ratAge;
    private int unbornRatsCount;
    
    public Rat(RatSex ratSex, TileInteractable tileTheRatIsOn, String direction) {
        this.ratSex = ratSex;
        this.ratMaturity = RatMaturity.BABY;
        this.isPregnant = false;
        this.isSterile = false;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
        this.ratGraphic = new Image("images/uglyBabyRat.png");
        this.ratAge = 0;
    }
    
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
    
    public String toString() {
    	String textEquivalent = String.format("%s %s isPregnant:%s isSterile:%s xPosition:%d yPosition:%d direction:%s age:%d" , ratSex, ratMaturity, isPregnant, isSterile, tileTheRatIsOn.getxCoordinate(), tileTheRatIsOn.getyCoordinate(), direction, ratAge);
		return textEquivalent;
    }

    private void mature() {
        ratMaturity = RatMaturity.ADULT;
        this.updateGraphic();
    }

    public void update(GraphicsContext graphicsContext) {
    	if (ratMaturity == RatMaturity.BABY && ratAge >= AGE_WHEN_MATURE) {
    		this.mature();
    	}
    	if (isPregnant && unbornRatsCount != 0) {
    		this.giveBirth();
    	} else if (isPregnant && unbornRatsCount == 0) {
    		this.isPregnant = false;
            this.updateGraphic();
    	}
    	this.ratAge++;
        this.move();
        this.draw(graphicsContext);
    }

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

    private void draw(GraphicsContext graphicsContext) {
        if (!(tileTheRatIsOn instanceof TileTunnel)) {
        	graphicsContext.drawImage(ratGraphic, this.tileTheRatIsOn.getyCoordinate()*Gameboard.getTileSize(), this.tileTheRatIsOn.getxCoordinate()*Gameboard.getTileSize()); 
        }
    }

    public Boolean canBreed() {
        return ((ratMaturity == RatMaturity.ADULT) && !isPregnant && !isSterile);
    }

    public void Breed() {
        this.isPregnant = true;
        this.updateGraphic();
        this.unbornRatsCount = 3;
    }
    
    private RatSex randomBabyRatSex() {
    	Random rand = new Random();
        RatSex babyRatSex;
        if (rand.nextInt(2) == 0) {
            babyRatSex = RatSex.MALE;
        } else {
            babyRatSex = RatSex.FEMALE;
        }
        return babyRatSex;
    }

    public void giveBirth() {
    	RatManager.addRat(new Rat(randomBabyRatSex(), this.tileTheRatIsOn, this.direction));
    	this.unbornRatsCount--;
    }

    public void changeSex(RatSex targetSex) {
        if  (this.ratSex != targetSex) {
            this.ratSex = targetSex;
            this.updateGraphic();
        }

    }
    
    private void updateGraphic() {
    	if (ratMaturity == RatMaturity.ADULT) {
            if (ratSex == RatSex.FEMALE) {
            	if (isPregnant) {
            		this.ratGraphic = new Image("images/PregnantRat.png");
            	} else {
            		this.ratGraphic = new Image("images/FemaleRat.png");
            	}
            } else {
            	this.ratGraphic = new Image("images/MaleRat.png");
            }
        } else {
        	this.ratGraphic = new Image("images/uglyBabyRat.png");
        }
    }

    public Tile getLocation() {
        return this.tileTheRatIsOn;
    }
    
    public void setLocation(TileInteractable tileTheRatIsOn) {
    	this.tileTheRatIsOn = tileTheRatIsOn;
    }

    public RatSex getSex() {
        return ratSex;
    }

    public void sterilise() {
        this.isSterile = true;
    }

    public RatMaturity getRatMaturity() {
        return this.ratMaturity;
    }

    public Boolean getIsPregnant() {
        return this.isPregnant;
    }

    public Boolean getIsSterile() {
        return this.isSterile;
    }

    public String getDirection() {
        return this.direction;
    }
}