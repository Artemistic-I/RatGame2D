import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DeathRat extends LethalItem {

 static final int SHORTCUT_KEY = 113; //bound to F2
 private int ratsKilled = 0;
 private String direction;
 private static final Image DEATH_RAT_GRAPHIC = new Image("images/ItemGraphics/DeathRatGraphic.png");
 /**
  * constructor
  */
 public DeathRat(TileInteractable tileTheItemIsOn, String direction) {

  super(SHORTCUT_KEY, DEATH_RAT_GRAPHIC, tileTheItemIsOn);
  this.direction = direction;
  setRatsKilled();

 }

 /**
  * rats killed set to 0
  */
 private void setRatsKilled() {
  ratsKilled = 0;
 }

 /**
  * get number of rats killed
  * @return ratsKilled Number of rats killed by death rat
  */
 public int getRatsKilled() {
  return ratsKilled;
 }

 /**
  * method to increment number of rats killed
  * after five rats are killed, the death rat dies
  */
 public void incrementRatCounter() {
  ratsKilled += 1;
 }

 //copied from rat class - still not sure how it works
 
 public void update(GraphicsContext graphicsContext) {
	 this.move();
	 this.draw(graphicsContext);
 }
 
 private void move() {
  ArrayList<String> possibleMoves = new ArrayList<String>(this.tileTheItemIsOn.possibleMoves());
  if (possibleMoves.size() == 1) {
   this.tileTheItemIsOn = (TileInteractable) tileTheItemIsOn.getAdjacentTile(turnAround(direction));
   this.direction = turnAround(direction);
  } else {
   possibleMoves.remove(turnAround(direction));
   Random rand = new Random();
   String randomDirection = possibleMoves.get(rand.nextInt(possibleMoves.size()));
   this.tileTheItemIsOn = (TileInteractable) tileTheItemIsOn.getAdjacentTile(randomDirection);
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

 public void draw(GraphicsContext graphicsContext) {
  if (!(this.tileTheItemIsOn instanceof TileTunnel)) {
   graphicsContext.drawImage(this.itemGraphic, this.tileTheItemIsOn.getyCoordinate()*Gameboard.getTileSize(), this.tileTheItemIsOn.getxCoordinate()*Gameboard.getTileSize());
  }
 }


}