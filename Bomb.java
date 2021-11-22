
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
    private ArrayList<Item> findItems(int x, int y) {

       ArrayList<Item> itemsFound = new ArrayList<Item>();
       //find items in given area
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