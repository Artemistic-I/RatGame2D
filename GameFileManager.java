import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameFileManager {

    // first line stores level number
    // next lines store each individual rats attributes seperated by ","
    // each rats attributes are seperated by a new line "\n"
    public static void saveGame(String filename, int levelNumber, Rat[] ratPopulation) {
        try {
            File myFile = new File(filename);
            // makes sure file can be written to
            myFile.setWritable(true);
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(levelNumber + "\n");
            for (Rat rat : ratPopulation) {
                myWriter.append(String.format("%s, %o, %s, %s, %o, %o, %s\n", 
                rat.getSex(), rat.getRatMaturity(), rat.getIsPregnant(), rat.getIsSterile(), 
                rat.getLocation().getTileCoordinates(), rat.getDirection()));
            }
            myWriter.close();
            // changes file to be read only so it can't be accidentally edited
            myFile.setReadOnly();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
