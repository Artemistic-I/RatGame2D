import javafx.scene.image.Image;

public class DeathRat extends LethalItem {

 static final int SHORTCUT_KEY = 113; //bound to F2
 private int ratsKilled = 0;
 private String direction;
 private static final Image DEATH_RAT_GRAPHIC = new Image("images/ItemGraphics/DeathRatGraphic.png");
 /**
  * constructor
  */
 public DeathRat(Tile tileTheItemIsOn) {

  super(SHORTCUT_KEY, DEATH_RAT_GRAPHIC, tileTheItemIsOn);
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

 //copied from rat class
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


}