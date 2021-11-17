
 public class Bomb extends LethalItem{
    /**bomberman bombs until grass
     * shows countdown to explosrion 4s - 1s
    */

    //static final int SHORTCUT_KEY = ;
    private int countdown;

    /**
    * constructor
    */
    public Bomb() {

       super(this.SHORTCUT_KEY);

    }

    /**
    * method to detonate bomb (bring all methods together
    */
    public void detonate() {

       //if contact with rat is true:
       //   create explosion
       //   find rats and items in range
       //   kill rats and destroy items

       if (super.isTouchingRat == True) {

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

    }

    /**
    * method to find items placed on map to destroy
    * @return arraylist of items to destroy
    */
    private ArrayList<Item> findItems(int x, int y) {

       ArrayList<Item> itemsFound = new ArrayList<Item>();
       //find items in given area
       return itemsFound;
    }

    /**
    * method to destroy items in range of explosion
    * @param araylist of items to destroy
    */
    private void destroyItem(Item items) {

    }


 }