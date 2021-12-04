import javafx.scene.image.Image;

public abstract class Tile {

    private boolean isInteractable;
	
	private Image tileGraphic;
	
	private Tile northTile;
	private Tile southTile;
	private Tile eastTile;
	private Tile westTile;
	
	private int xCoordinate;
	private int yCoordinate;
    
    private int width, height;
    // co-ordinate of tile position on gameboard
    private int[] tileCoordinates = new int[2];
    // RGB
    // Value between 0 - 255
    private int[] colour = {0,0,0};
    // stores tiles adjecent to itself
    // index 0 = North, index 3 = West
    Tile[] adjacentTiles = new Tile[4];

    public Tile(Image tileGraphic, int row, int column) {
    	this.tileGraphic = tileGraphic;
    	this.xCoordinate = row;
    	this.yCoordinate = column;
        this.isInteractable = false;
    }

    public int[] getSizeOfTile() {
        return (new int[] {this.width, this.height});
    }

    public Tile getTileType() {
        return this;
    }

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

    public void setNorthTile (Tile northTile) {
    	this.northTile = northTile;
    }
    public void setSouthTile (Tile southTile) {
    	this.southTile = southTile;
    }
    public void setEastTile (Tile eastTile) {
    	this.eastTile = eastTile;
    }
    public void setWestTile (Tile westTile) {
    	this.westTile = westTile;
    }


    public int[] getTileCoordinates() {
        return this.tileCoordinates;
    }
    
    public Image getTileGraphic() {
    	return this.tileGraphic;
    }

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

    public void setIsInteractable(boolean interactable) {
		this.isInteractable = interactable;
	}

	public boolean isInteractable() {
		return this.isInteractable;
	}

}