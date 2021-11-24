
 public class Bomb extends LethalItem {
    /**bomberman bombs until grass
     * shows countdown to explosrion 4s - 1s
    */

    static final int SHORTCUT_KEY = 112;  //bound to F1
    private int countdown = 4;

    /**
    * constructor
    */
    public Bomb() {

       super(this.SHORTCUT_KEY);

    }

    /**
    * method to detonate bomb (bring all methods together)
    */
    public void detonate() {

       //if contact with rat is true:
       //   create explosion
       //   find rats and items in range
       //   kill rats and destroy items

       if (super.isTouchingRat == true) {

          createExplosion();

          items = findItems();//find items needs coords

          for (int i = 0; i < items.size(); i++) {
             destroyItem(items(i));
          }

          rats = findRats();//find rats needs coords

          for (int i = 0; i < rats.size(); i++) {
             killRat(rats(i));
          }

          this.removeItem();
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
     * @param x Row of bomb
     * @param y Column of bomb
    * @return itemsFound Arraylist of items to destroy
    */
    private ArrayList<Item> findItems(Tile origin) {

       ArrayList<Item> itemsFound = new ArrayList<Item>();

       int oX = (origin.getTileCoordinates())[0];
       int oY = (origin.getTileCoordinates())[1];
       int xBounds[] = [oX - area/2, oX + Math.round(area/2)];
       int yBounds[] = [oY - area/2, oY + Math.round(area/2)];

       board = getBoard()  //NOT SURE HOW TO ACCESS GAMEBOARD, this is just a placeholder

       //for each column in range of item
       for (int i = xBounds[0], i < (xBounds[1] + 1), i++) {
          //for each tile in column
          for (int j = yBounds[0], i < (yBounds[1] + 1), i++) {
             Tile currentTile = board[i][j];
             if (currentTile.containsItem) {  //obviously this has to be changed but couldn't see how to identify whether an item is on a tile
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
    }


 }