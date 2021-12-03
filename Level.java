import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private int levelNumber;
    private String levelFile;
    private Boolean isLocked;
    private static Level selectedLevel;
    private static ArrayList<Level> levels = new ArrayList<>();

    public Level(int levelNumber, String levelFile) {
        this.levelNumber = levelNumber;
        this.levelFile = levelFile;
        if (levelNumber == 1) {
            this.isLocked = false;
        } else {
            this.isLocked = true;
        }
    }
    public static void unlock(int lvlNum) {
        for (Level lvl : levels) {
            if (lvl.getLevelNumber() == lvlNum) {
                lvl.unlock();
            }
        }
    }
    public void unlock() {
        isLocked = false;
    }
    public int getLevelNumber() {
        return levelNumber;
    }
    public String getLevelFile() {
        return levelFile;
    }
    public Boolean isLocked() {
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
