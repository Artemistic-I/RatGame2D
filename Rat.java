import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    private Timer maturityTimer;

    public Rat(RatSex ratSex, RatMaturity ratMaturity, Boolean isPregnant, TileInteractable tileTheRatIsOn, String direction) {
        this.ratSex = ratSex;
        this.ratMaturity = ratMaturity;
        this.isPregnant = isPregnant;
        this.isSterile = false;
        this.tileTheRatIsOn = tileTheRatIsOn;
        this.direction = direction;
        if (ratMaturity == RatMaturity.ADULT) {
        		ratGraphic = ratSex.getGraphic();
        } else {
        	ratGraphic = ratMaturity.getGraphic();
        	maturityTimer = new Timer(); 
        	maturityTimer.schedule(new TimerTask() {
        		  @Override
        		  public void run() {
        		    mature();
        		  }
        		}, 20*1000); // @aes remember to remove magic number
        }
    }
    
    private void mature() {
    	ratMaturity = RatMaturity.ADULT;
    	ratGraphic = ratSex.getGraphic();
    }

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
            this.direction = possibleMoves.get(rand.nextInt(possibleMoves.size()));
            this.tileTheRatIsOn = (TileInteractable) tileTheRatIsOn.getAdjacentTile(direction);
        }
    }

    private void draw(Canvas canvas) {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.drawImage(ratGraphic, this.tileTheRatIsOn.getyCoordinate()*25, this.tileTheRatIsOn.getxCoordinate()*25);
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
        RatManager.addRat(new Rat(babyRatSex, RatMaturity.BABY, false, this.tileTheRatIsOn, this.direction));
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

    public String getDirection() {
        return this.direction;
    }
}