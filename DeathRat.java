public class DeathRat extends LethalItem {

 static final int SHORTCUT_KEY = 113; //bound to F2
 private int ratsKilled = 0;

 /**
  * constructor method
  */
 public DeathRat() {

  super(this.SHORTCUT_KEY);

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

}