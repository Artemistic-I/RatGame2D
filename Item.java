import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This is the super class to define an item object
 * @author Josh & Mike
 * @version 1.0
 */
public abstract class Item{
    // TODO
    // Create toString() method that returns all information needed for saving that item to a file

    //key to select item from menu
    protected int shortcutKey;
    protected TileInteractable tileTheItemIsOn;
    protected boolean isTouchingRat = false;
    protected Rat affectedRat;
    protected Image itemGraphic;

    /**
     * constructor to create a new item
     * @param scKey Keycode for key related with specific item
     */
    public Item(int scKey, Image itemGraphic, TileInteractable tileTheItemIsOn){
    	this.itemGraphic = itemGraphic;
    	this.tileTheItemIsOn = tileTheItemIsOn;
        setSCKey(scKey);
    }

    public void update(GraphicsContext graphicsContext) {
    	this.draw(graphicsContext);
        ItemMain.checkTiles();
    }

    abstract void itemAction();
    
    public void draw(GraphicsContext graphicsContext) {
    	graphicsContext.drawImage(itemGraphic, this.tileTheItemIsOn.getyCoordinate()*Gameboard.getTileSize(), this.tileTheItemIsOn.getxCoordinate()*Gameboard.getTileSize());
    }
    
    /**
     * set shortcut key
     * @param key Keycode for key related with specific item
     */
    private void setSCKey(int key) { shortcutKey = key; }

    /**
     * set status for whether item is touching a rat
     * @param status Whether rat is touching the item
     */
    public void setTouchStatus(boolean status) { isTouchingRat = status; }

    /**
     * method to set rat affected by item
     * @param rat Rat which has contacted item
     */
    public void setAffectedRat(Rat rat) {
        affectedRat = rat;
    }

    /**
     * get shortcut key assigned to item
     * @return shortcutKey Shortcut key assigned to item
     */
    public int getSCKey() { return shortcutKey; }

    /**
     * get tile where item is located
     * @return itemLocation Tile that item occupies
     */
    public Tile getItemLoc() { return tileTheItemIsOn; }

    /**
     * get boolean telling whether a rat is touching the item
     * @return isTouchingRat Whether rat is touching item
     */
    public boolean getTouchingStatus() { return isTouchingRat; }

    /**
     * method to get rat affected by item
     * @return rat Rat that will be affected by item
     */
    public Rat getAffectedRat() {
        return affectedRat;
    }

    /**
     * method to find rats to kill/affect
     * @param origin Tile to start looking for rats from (where item is placed)
     * @param area Area in which rats should be found
     * @return ratsFound Arraylist of rats to kill
     */
    protected ArrayList<Rat> findRats(Tile origin, int area) {

        ArrayList<Rat> ratsFound = new ArrayList<Rat>();

        int oX = (origin.getTileCoordinates())[0];
        int oY = (origin.getTileCoordinates())[1];

        Tile[][] board = Gameboard.getBoard();
        Tile currentTile;

        //for bomb
        if (area == 0) {

            //goes right
            for (int i = oX; i < Gameboard.getWidth(); i++) {

                currentTile = board[i][oY];

                while (currentTile.getInteractable()) {

                    if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                        //Rat r = rat on tile
                        ratsFound.add(r);
                    }

                }

            }

            //goes left
            for (int j = oX; j < Gameboard.getWidth(); j--) {

                currentTile = board[j][oY];

                while (currentTile.getInteractable()) {

                    if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                        //Rat r = rat on tile
                        ratsFound.add(r);
                    }

                }

            }

            //goes up
            for (int k = oY; k < Gameboard.getWidth(); k++) {

                currentTile = board[oX][k];

                while (currentTile.getInteractable()) {

                    if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                        //Rat r = rat on tile
                        ratsFound.add(r);
                    }

                }

            }

            //goes down
            for (int m = oY; m < Gameboard.getWidth(); m--) {

                currentTile = board[oX][m];

                while (currentTile.getInteractable()) {

                    if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                        //Rat r = rat on tile
                        ratsFound.add(r);
                    }

                }

            }

        } else {    //for other items

            int xBounds[] = {oX - Math.round(area/2), oX + Math.round(area/2)};
            int yBounds[] = {oY - Math.round(area/2), oY + Math.round(area/2)};

            //for each column in range of item
            for (int i = xBounds[0]; i < (xBounds[1] + 1); i++) {
                //for each tile in column
                for (int j = yBounds[0]; i < (yBounds[1] + 1); i++) {
                    currentTile = board[i][j];
                    if (currentTile.containsRat) {  //obviously this has to be changed but couldn't see how to identify whether a rat is on a tile
                        //Rat r = rat on tile
                        ratsFound.add(r);
                    }
                }
            }
        }

        return ratsFound;
    }

    /**
     * method for rat making contact with item
     * @param rat Rat for which actions are to be performed upon
     */
    public void ratContact(Rat rat, Item itemInUse) {

        itemInUse.setAffectedRat(rat);
        itemInUse.setTouchStatus(true);

        int shortcutKey = itemInUse.getSCKey();

        //triggers correct method depending on type of item denoted by shortcut key
        switch (shortcutKey) {

            case 114://gas
                Gas gasItem = (Gas) itemInUse;
                gasItem.expand();
                break;
            case 115://no entry
                NoEntry noEntItem = (NoEntry) itemInUse;
                while (noEntItem.getHealth() != 0) {//while sign is still active
                    noEntItem.getAffectedRat().changeDirection();//make tile non-interactable
                    noEntItem.degradeHealth(noEntItem.getHealth());//degrade health
                    itemInUse.setTouchStatus(false);
                }
                ItemMain.removeItem(itemInUse);
                break;

            

        }

    }

}