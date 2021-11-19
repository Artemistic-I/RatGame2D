import java.util.ArrayList;
import java.util.Random;

public class Rat {
	
    private RatSex ratSex;
    private RatMaturity ratMaturity;
    private Boolean isPregnant;
    private Boolean isSterile;
    private int colour;
    private Tile tileTheRatIsOn;
    private String direction;

    public Rat(RatSex ratSex, RatMaturity ratMaturity, Boolean isPregnant, int colour, Tile tileTheRatIsOn, String direction) {
        this.ratSex = ratSex;
        this.ratMaturity = ratMaturity;
        this.isPregnant = isPregnant;
        this.isSterile = false;
        this.colour = colour;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
    } // # When the rat is created it needs to be added to the arraylist in RatManager

    public void move() {
        ArrayList<String> possibleMoves = tileTheRatIsOn.possibleMoves();
        if (possibleMoves.contains(direction)) {
            this.tileTheRatIsOn = tileTheRatIsOn.getNextTile(direction);
        } else {
            Random rand = new Random();
            this.direction = possibleMoves.get(rand.nextInt(possibleMoves.size() - 1));
            this.tileTheRatIsOn = tileTheRatIsOn.getNextTile(direction);
        }
        this.draw();

    }

    private void draw() {
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
}