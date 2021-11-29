import java.util.ArrayList;

/**
 * subclass of LethalItem and therefore Item to describe a Bomb object
 * @author Mike & Josh
 */
public class Bomb extends LethalItem {
   /**bomberman bombs until grass
    * shows countdown to explosrion 4s - 1s
    */

   static final int SHORTCUT_KEY = 112;  //bound to F1
   static final int COUNTDOWN = 4;

   /**
    * constructor
    */
   public Bomb() {

      super(SHORTCUT_KEY);

   }

   /**
    * method to detonate bomb (bring all methods together)
    */
   public void detonate() {

      //if contact with rat is true:
      //   create explosion
      //   find rats and items in range
      //   kill rats and destroy items

      if (getTouchingStatus()) {

         createExplosion();

         ArrayList<Item> items = findItems(getItemLoc());

         for (int i = 0; i < items.size(); i++) {
            destroyItem(items.get(i));
         }

         ArrayList<Rat> rats = findRats(getItemLoc(), 0);

         for (int i = 0; i < rats.size(); i++) {
            killRat(rats.get(i));
         }

         itemMain.removeItem();
      }

   }

   /**
    * method to create explosion using javaFX
    */
   private void createExplosion() {
      //countdown in here
   }

   /**
    * method to find items placed on map to destroy
    * @param origin Location of item
    * @return itemsFound Arraylist of items to destroy
    */
   private ArrayList<Item> findItems(Tile origin) {

      ArrayList<Item> itemsFound = new ArrayList<Item>();

      int oX = (origin.getTileCoordinates())[0];
      int oY = (origin.getTileCoordinates())[1];

      Tile[][] board = Gameboard.getBoard();

      //goes right
      for (int i = oX; i <= Gameboard.getWidth(); i++) {

         Tile currentTile = board[i][oY];

         while (currentTile.getInteractable()) {

            if (currentTile.conatinsItem()) { //obviously this has to be changed but couldn't see how to identify whether an item is on a tile

               //Item item = item on tile
               itemsFound.add(item);

            }
         }

      }

      //goes left
      for (int j = oX; j >= 0; j--) {

         Tile currentTile = board[j][oY];

         while (currentTile.getInteractable()) {

            if (currentTile.conatinsItem()) { //obviously this has to be changed but couldn't see how to identify whether an item is on a tile

               //Item item = item on tile
               itemsFound.add(item);

            }
         }

      }

      //goes up
      for (int k = oY; k <= Gameboard.getWidth(); k++) {

         Tile currentTile = board[oX][k];

         while (currentTile.getInteractable()) {

            if (currentTile.conatinsItem()) { //obviously this has to be changed but couldn't see how to identify whether an item is on a tile

               //Item item = item on tile
               itemsFound.add(item);

            }
         }

      }

      //goes down
      for (int m = oY; m >= 0; m--) {

         Tile currentTile = board[oX][m];

         while (currentTile.getInteractable()) {

            if (currentTile.conatinsItem()) { //obviously this has to be changed but couldn't see how to identify whether an item is on a tile

               //Item item = item on tile
               itemsFound.add(item);

            }
         }

      }

      return itemsFound;
   }

   /**
    * method to destroy items in range of explosion
    * @param item Item to destroy
    */
   private void destroyItem(Item item) {
      //may be similar to remove item method
      //look in item main
   }


}