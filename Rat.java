import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.Canvas;

public class Rat {
	
    private RatSex ratSex;
    private RatMaturity ratMaturity;
    private Boolean isPregnant;
    private Boolean isSterile;
    private int colour;
    private TileInteractable tileTheRatIsOn;
    private String direction;

    public Rat(RatSex ratSex, RatMaturity ratMaturity, Boolean isPregnant, int colour, TileInteractable tileTheRatIsOn, String direction) {
        this.ratSex = ratSex;
        this.ratMaturity = ratMaturity;
        this.isPregnant = isPregnant;
        this.isSterile = false;
        this.colour = colour;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
    } // # When the rat is created it needs to be added to the arraylist in RatManager

    public void update(Canvas canvas) {
    	this.move();
    	this.draw(canvas);
    }
    
    private void move() {
        ArrayList<String> possibleMoves = tileTheRatIsOn.possibleMoves();
        if (possibleMoves.contains(direction)) {
            this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(direction);
        } else {
            Random rand = new Random();
            this.direction = possibleMoves.get(rand.nextInt(possibleMoves.size() - 1));
            this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(direction);
        }
    }

    private void draw(Canvas canvas) {
        // # TODO Auto-generated method stub
    }

    public Boolean canBreed() {
        return ((ratMaturity == RatMaturity.ADULT) && !isPregnant && !isSterile);
    }

    public void Breed() {
        this.isPregnant = true;
    }

    public void giveBirth() {
        Random rand = new Random();
        RatSex babyRatSex;
        if (rand.nextInt(1) == 0) {
            babyRatSex = RatSex.MALE;
        } else {
        	babyRatSex = RatSex.FEMALE;
        }
        RatManager.addRat(new Rat(babyRatSex, RatMaturity.BABY, false, 7, this.tileTheRatIsOn, this.direction));
    }

    public void changeSex(RatSex targetSex) {
        if  (this.ratSex != targetSex) {
            this.ratSex = targetSex;
        }

    }

    public Tile getLocation() {
        return this.tileTheRatIsOn;
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

    public int getColour() {
        return this.colour;
    }

    public String getDirection() {
        return this.direction;
    }
}