import java.util.ArrayList;
import java.util.Random;

public class Rat {

    private Boolean isMale;
    private Boolean isAdult;
    private Boolean isPregnant;
    private Boolean isSterile;
    private int speed;
    private int colour;
    private Tile tileTheRatIsOn;
    private String direction;

    public Rat(Boolean isMale, Boolean isAdult, Boolean isPregnant, int speed, int colour, Tile tileTheRatIsOn, String direction) {
        this.isMale = isMale;
        this.isAdult = isAdult;
        this.isPregnant = isPregnant;
        this.isSterile = false;
        this.speed = speed;
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
        return (isAdult && !isPregnant && !isSterile);
    }

    public void Breed() {
        this.isPregnant = true;
    }

    public void giveBirth() {
        Random rand = new Random();
        Boolean babyRatIsMale;
        if (rand.nextInt(1) == 0) {
            babyRatIsMale = true;
        } else {
        	babyRatIsMale = false;
        }
        RatManager.addRat(new Rat(babyRatIsMale, false, false, 5, 7, this.tileTheRatIsOn, this.direction));
    }

    public void changeSex() {
        if  (this.isMale = true) {
            this.isMale = false;
        } else {
            this.isMale = true;
        }

    }

    public Tile getLocation() {
        return this.tileTheRatIsOn;
    }

    public Boolean getSex() {
        return isMale;
    }
}