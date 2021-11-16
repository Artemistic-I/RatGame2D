public class InteractableTile extends Tile {

    private boolean isHidden;
    private boolean isMating;

    public InteractableTile(int width, int height) {
        super(width, height);
        setInteractable(true);
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean getIsHidden() {
        return this.isHidden;
    }
    
    // isMating depends on if Tile stores the rats that are on it
    // if so isMating can be set from inside the tile as it just has to check
    // if there's two rats and if so are they of opposite gender and able to mate    
}
