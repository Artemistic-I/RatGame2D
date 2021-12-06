import javafx.scene.image.Image;

public abstract class Tile {
    
    private boolean isInteractable; // determines if tile can interact with Items and Rats
	
	private Image tileGraphic; // Image used for tile
	
	private Tile northTile; // tile to north of this tile
	private Tile southTile; // tile to south of this tile
	private Tile eastTile; // tile to east of this tile
	private Tile westTile; // tile to west of this tile
	
	private int xCoordinate; // x position of this tile
	private int yCoordinate; // y position of this tile
    
    private int width, height; // pixel width & height of this tile
    
    private int[] colour = {0,0,0}; // RGB values between 0- 255

    private NoEntry noEntrySign; // used when No Entry Sign placed on this tile

    /**
     * 
     * @param tileGraphic the image of this tile type (what it will look like when drawn)
     * @param row the rows it's in, in the gameboard 2d array
     * @param column the column it's in, in the gameboard 2d array
     */
    public Tile(Image tileGraphic, int row, int column) {
    	this.tileGraphic = tileGraphic;
    	this.xCoordinate = row;
    	this.yCoordinate = column;
        this.isInteractable = false;
        this.noEntrySign = null;
    }

    /**
     * 
     * @return returns pixel size of tile
     */
    public int[] getSizeOfTile() {
        return (new int[] {this.width, this.height});
    }

    /**
     * 
     * @return returns this object
     */
    public Tile getTileType() {
        return this;
    }

    /**
     * RGB colour system
     * @param r red 0 - 255
     * @param g green 0 - 255
     * @param b blue 0 -255
     */
    public void setColour(int r, int g, int b) {
        if (r >= 0 && r <= 255) {
            this.colour[0] = r;
        }
        if (g >= 0 && g <= 255) {
            this.colour[1] = g;
        }
        if (b >= 0 && g <= 255) {
            this.colour[2] = b;
        }
    }

    /**
     * 
     * @param direction compass direction
     * @return returns tile to the compass direction of this tile
     */
    public Tile getAdjacentTile(String direction) {
        switch (direction.toLowerCase()) {
            case "north":
                return this.northTile;
            case "east":
                return this.eastTile;
            case "south":
                return this.southTile;
            case "west":
                return this.westTile;
        }
        return null;
    }

    /**
     * 
     * @param northTile tile to the north of this tile
     */
    public void setNorthTile (Tile northTile) {
    	this.northTile = northTile;
    }
    /**
     * 
     * @param southTile tile to the south of this tile
     */
    public void setSouthTile (Tile southTile) {
    	this.southTile = southTile;
    }
    /**
     * 
     * @param eastTile tile to the east of this tile
     */
    public void setEastTile (Tile eastTile) {
    	this.eastTile = eastTile;
    }
    /**
     * 
     * @param westTile tile to the west of this tile
     */
    public void setWestTile (Tile westTile) {
    	this.westTile = westTile;
    }
    
    /**
     * 
     * @return returns tile image
     */
    public Image getTileGraphic() {
    	return this.tileGraphic;
    }

    /**
     * 
     * @return returns x position of tile
     */
	public int getxCoordinate() {
		return xCoordinate;
	}

    /**
     * 
     * @return returns y position of tile
     */
	public int getyCoordinate() {
		return yCoordinate;
	}

    /**
     * setter for isInteractable
     * @param isInteractable true if items/rats can be placed/walk on them
     */
    protected void setIsInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    /**
     * getter for isInteractable
     * @return returns if tile is interactable
     */
	public boolean isInteractable() {
		return this.isInteractable;
	}

    /**
     * If Tile has No Entry Sign placed on it returns the Item
     * Else returns null
     * @return returns No Entry Sign Item
     */
    public NoEntry getNoEntrySign() {
		return this.noEntrySign;
	}

    /**
     * setter for NoEntrySign
     * @param sign No Entry Sign Item
     */
	public void setNoEntrySign(NoEntry sign) {
		this.noEntrySign = sign;
	}
}