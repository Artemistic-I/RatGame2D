import java.util.ArrayList;

public class Tile {
    
    private int width, height;
    // RGB
    // Value between 0 - 255
    private int[] colour = {0,0,0};
    // determines if a rat/item can interact with the tile
    private boolean interactable;

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

    public int[] getColour() {
        return this.colour;
    }

    public void setInteractable(boolean isInteractable) {
        this.interactable = isInteractable;
    }

    public boolean getInteractable() {
        return this.interactable;
    }
}
