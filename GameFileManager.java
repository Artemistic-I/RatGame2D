import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GameFileManager {

    // first line stores level number
    // next lines store each individual rats attributes seperated by white space.
    // each rats attributes are seperated by a new line "\n
    
    public static void saveGame(String outputFile, long duration) {
        
        File myFile = new File(outputFile);
        PrintWriter myWriter = null;
        try {
            myWriter = new PrintWriter(outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + outputFile);
            System.exit(0);
        }
        int levelNumber = Level.getSelectedLevel().getLevelNumber();
        Rat[] ratPopulation = RatManager.getRatPopulation();
        // level number;
        myWriter.println(levelNumber);
        // rats
        for (Rat rat : ratPopulation) {
            String ratDetails = rat.toString();
            myWriter.println(ratDetails);
        }
        // number of killed rats
        myWriter.println(RatManager.getKilledRatCount());
        // length of time since the start of the level in milliseconds
        myWriter.println(duration);
        myWriter.close();
    }
}
