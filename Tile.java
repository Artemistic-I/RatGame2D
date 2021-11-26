import java.util.ArrayList;

public class Tile {
    
    private int width, height;
    // co-ordinate of tile position on gameboard
    private int[] tileCoordinates = new int[2];
    // RGB
    // Value between 0 - 255
    private int[] colour = {0,0,0};
    // determines if a rat/item can interact with the tile
    private boolean interactable;
    // stores tiles adjecent to itself
    // index 0 = North, index 3 = West
    Tile[] adjacentTiles = new Tile[4];

    public Tile(int width, int height) {
        this.width = width;
        this.height = height;
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
                return this.adjacentTiles[0];
            case "east":
                return this.adjacentTiles[1];
            case "south":
                return this.adjacentTiles[2];
            case "west":
                return this.adjacentTiles[3];
        }
        return null;
    }

    private void setAdjacentTiles() {
        Tile tile;
        for (int i = 0; i < Gameboard.getHeight(); i++) {
            for (int j = 0; j < Gameboard.getWidth(); j++) {
                Tile[][] gameboard = Gameboard.getBoard();
                tile = gameboard[i][j];
                for (int x = 0; x < this.adjacentTiles.length; x++) {
                    switch (x) {
                        // North
                        case 0:
                            try {
                                this.adjacentTiles[0] = gameboard[i+1][j];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                return;
                            }
                        // East
                        case 1:
                            try {
                                this.adjacentTiles[1] = gameboard[i][j+1];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                return;
                            }
                        // South
                        case 2:
                            try {
                                this.adjacentTiles[2] = gameboard[i-1][j];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                return;
                            }
                        // West
                        case 3:
                            try {
                                this.adjacentTiles[3] = gameboard[i][j-1];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                return;
                            }
                    }
                }
            }
        }
    }

    public int[] getColour() {
        return this.colour;
    }

    public void setInteractable(boolean isInteractable) {
        this.interactable = isInteractable;
    }

    public boolean getInteractable() {
        return this.interactable;
    }

    public int[] getTileCoordinates() {
        return this.tileCoordinates;
    }

    public void setTileCoordinates(int[] tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }
}