
public class DeathRat extends LethalItem {

 static final int SHORTCUT_KEY = 113; //bound to F2
 private int ratsKilled = 0;
 private String direction;

 /**
  * constructor
  */
 public DeathRat() {

  super(SHORTCUT_KEY);

 }

 /**
  * method to increment number of rats killed
  * after five rats are killed, the death rat dies
  */
 public void ratCounter() {
  this.ratsKilled += 1;

  if (this.ratsKilled == 5) {
   this.removeItem();
  }
 }

 //copied from rat class
 public void update(Canvas canvas) {
  this.move();
  this.draw(canvas);
 }

 private void move() {

  Tile currentTile = getItemLoc();

  ArrayList<String> possibleMoves = currentTile.possibleMoves();
  if (possibleMoves.contains(direction)) {
   currentTile = (TileInteractable) currentTile.getAdjacentTile(direction);
  } else {
   Random rand = new Random();
   this.direction = possibleMoves.get(rand.nextInt(possibleMoves.size() - 1));
   currentTile = (TileInteractable) currentTile.getAdjacentTile(direction);
  }
 }

 private void draw(Canvas canvas) {
// # TODO Auto-generated method stub
 }



}