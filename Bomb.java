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

    //THIS SHOULD BE IN BOMBERMAN STYLE!!!
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

       for (int i = oX; i < Gameboard.getWidth(); i++) {

          Tile currentTile = board[][];

          while () {

          }

       }

       /**
       int xBounds[] = {oX - area/2, oX + Math.round(area/2)};
       int yBounds[] = {oY - area/2, oY + Math.round(area/2)};

       Tile[][] board = Gameboard.getGameboard();

       //for each column in range of item
       for (int i = xBounds[0]; i < (xBounds[1] + 1); i++) {
          //for each tile in column
          for (int j = yBounds[0]; i < (yBounds[1] + 1); i++) {
             Tile currentTile = board[i][j];
             if (currentTile.containsItem) {  //obviously this has to be changed but couldn't see how to identify whether an item is on a tile
                //Item item = item on tile
                itemsFound.add(item);
             }
          }
       }
        */

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