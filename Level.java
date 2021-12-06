import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to model levels
 * @author Artem Iakovlev
 */
public class Level {

    private int levelNumber;
    private String levelFile;
    private Boolean isLocked;
    private static Level selectedLevel;
    private static ArrayList<Level> levels = new ArrayList<>();

    /**
     * Creates a level
     * @param levelNumber level number
     * @param levelFile a file where the level is stored
     */
    public Level(int levelNumber, String levelFile) {
        this.levelNumber = levelNumber;
        this.levelFile = levelFile;
        if (levelNumber == 1) {
            this.isLocked = false;
        } else {
            this.isLocked = true;
        }
    }
    /**
     * unlocks some level
     * @param lvlNum the number of level to be unlocked
     */
    public static void unlock(int lvlNum) {
        for (Level lvl : levels) {
            if (lvl.getLevelNumber() == lvlNum) {
                lvl.unlock();
            }
        }
    }

    /**
     * unlockes current level
     */
    public void unlock() {
        isLocked = false;
    }
    /**
     * Gets level number
     * @return level number
     */
    public int getLevelNumber() {
        return levelNumber;
    }
    /**
     * Gets level file
     * @return level file
     */
    public String getLevelFile() {
        return levelFile;
    }
    /**
     * checks if a level is locked
     * @return locked or unlocked boolean value
     */
    public Boolean isLocked() {
        return isLocked;
    }
    /**
     * Sets level lock status
     * @param isLocked lock status
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    /**
     * Gets a text representation of a level
     */
    public String toString() {
        String temp;
        if (isLocked) {
            temp = "Locked";
        } else {
            temp = "Unlocked";
        }
        return "Level " + levelNumber + " " + temp;
    }
    /**
     * Marks a level as selected
     * @param lvl level to be marked as selected
     */
    public static void setSelectedLevel(Level lvl) {
        selectedLevel = lvl;
    }
    /**
     * Marks a level as selected
     * @param lvlNum level number of a level to be marked as selected
     */
    public static void setSelectedLevel(int lvlNum) {
        for (Level lvl : levels) {
            if (lvl.getLevelNumber() == lvlNum) {
                setSelectedLevel(lvl);
            }
        }
    }
    /**
     * Gets all levels
     * @return all levels
     */
    public static ArrayList<Level> getLevels() {
        return levels;
    }
    /**
     * Gets the selected level
     * @return selected level
     */
    public static Level getSelectedLevel() {
        return selectedLevel;
    }
    /**
     * Unlocks levels that are supposed to be unlocked
     */
    public static void loadLocks() {
        File selectedPlayer = Gameboard.getCurrentPlayer().getSavedDetails();
        Scanner in = null;
		try{
            in = new Scanner(selectedPlayer);
        } catch (FileNotFoundException e){
            System.out.println("Cannot find " + selectedPlayer.getName());
            System.exit(0);
        }
		in.next();
        while (in.hasNext()) {
            Level.unlock(in.nextInt());
        }
		in.close();
    }
}
