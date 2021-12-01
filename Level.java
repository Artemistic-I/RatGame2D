import java.util.ArrayList;

public class Level {

    private int levelNumber;
    private String levelFile;
    private Boolean isLocked;
    private static Level selectedLevel;
    private static ArrayList<Level> levels = new ArrayList<>();

    public Level(int levelNumber, String levelFile, Boolean isLocked) {
        this.levelNumber = levelNumber;
        this.levelFile = levelFile;
        this.isLocked = isLocked;
    }
    public int getLevelNumber() {
        return levelNumber;
    }
    public String getLevelFile() {
        return levelFile;
    }
    public Boolean getIsLocked() {
        return isLocked;
    }
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    public String toString() {
        String temp;
        if (isLocked) {
            temp = "Locked";
        } else {
            temp = "Unlocked";
        }
        return "Level " + levelNumber + " " + temp;
    }
    public static void setSelectedLevel(Level lvl) {
        selectedLevel = lvl;
    }
    public static ArrayList<Level> getLevels() {
        return levels;
    }
    public static Level getSelectedLevel() {
        return selectedLevel;
    }
}
