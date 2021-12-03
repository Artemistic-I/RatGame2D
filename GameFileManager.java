import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GameFileManager {

    // first line stores level number
    // next lines store each individual rats attributes seperated by white space.
    // each rats attributes are seperated by a new line "\n
    
    public static void saveGame(String username, long duration) {
        // saves file to a directory with the name of the players username
        String outputFile = "gamesaves/" + username + ".txt";
        Gameboard.getCurrentPlayer().setHasSavedGame(true);
        File myFile = new File(outputFile);
        PrintWriter myWriter = null;
        try {
            myWriter = new PrintWriter(myFile);

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + outputFile);
            //myWriter.close(); No need to close beacuse exception means we haven't opened it
            System.exit(0);
        }

        // level number;
        int levelNumber = Level.getSelectedLevel().getLevelNumber();
        myWriter.println(levelNumber);
        // letting load file know this section is finished
        myWriter.println();
        
        // rats
        // array of rats on the board
        Rat[] ratPopulation = RatManager.getRatPopulation();
        for (Rat rat : ratPopulation) {
            String ratDetails = rat.toString();
            myWriter.println(ratDetails);
        }
        // letting load file know this section is finished
        myWriter.println();

        // number of killed rats
        myWriter.println(RatManager.getKilledRatCount());
        // letting load file know this section is finished
        myWriter.println();

        // Something in here causes errors
       /*  // items
        // array of items on the board
        Item[] items = ItemManager.getCurrentlyPlacedItems();
        for (Item item : items) {
            // prints item details to file
            myWriter.println(item.toString());
        }
        // player inventory
        Item[][] inv = ItemMain.getInv(); // ItemMain needs to be changed to static if possible
        for (Item[] itemRow : inv) {
            for (Item item : itemRow) {
                // prints item details to file
                myWriter.println(item.toString());
            }
        } */
        // letting load file know this section is finished
        myWriter.println();

        // length of time since the start of the level in milliseconds
        myWriter.println(duration);

        myWriter.close();
    }
}
