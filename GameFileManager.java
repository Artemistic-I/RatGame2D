import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameFileManager {

    private static long duration;
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

        // level number
        int levelNumber = Level.getSelectedLevel().getLevelNumber();
        myWriter.println(levelNumber);

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
        // length of time since the start of the level in milliseconds
        myWriter.println(duration);

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
        myWriter.close();
    }
    public static void loadGame() {
        String username = Gameboard.getCurrentPlayer().getPlayerUsername();
        File savedGame = new File("gamesaves/" + username + ".txt");
        if (!(savedGame.exists() && savedGame.isFile())) {
            System.out.println("SavedGame file does not exist");
        } else {
            Scanner in = null;
            try {
                in = new Scanner(savedGame);
            } catch (FileNotFoundException e){
                System.out.println("Could not find " + "gamesaves/" + username + ".txt");
                System.exit(0);
            }
            Scanner line = new Scanner(in.nextLine());
            // line 1 is level number
            int levelNumber = line.nextInt();
            Gameboard.setIsLoadingGame(true); //loading mode ignores initialisation of baby rats
            try{
                Gameboard.generateBoard("levels/level" + levelNumber + ".txt");
            } catch (FileNotFoundException e) {
                //already checked
            }
            Gameboard.setIsLoadingGame(false);
            Tile[][] board = Gameboard.getBoard();
            // line 2..n rats
            line = new Scanner(in.nextLine());
            String ratSex = line.next();
            while (line.hasNext() && (ratSex.equals("male") || ratSex.equals("female"))) {
                String maturity = line.next();
                Boolean isPregnant = line.nextBoolean();
                Boolean isSterile = line.nextBoolean();
                Boolean isHavingSex = line.nextBoolean();
                int xPosition = line.nextInt();
                int yPosition = line.nextInt();
                String direction = line.next();
                int age = line.nextInt();
                int ageToGiveBirth = line.nextInt();
                int ageToFinishHavingSex = line.nextInt();
                int unbornRatsCount = line.nextInt();
                RatSex enumRatSex;
                if (ratSex.equals("male")) {
                    enumRatSex = RatSex.MALE;
                } else {
                    enumRatSex = RatSex.FEMALE;
                }
                RatMaturity enumMaturity;
                if (maturity.equals("adult")) {
                    enumMaturity = RatMaturity.ADULT;
                } else {
                    enumMaturity = RatMaturity.BABY;
                }
                TileInteractable tileTheRatIsOn = (TileInteractable)board[xPosition][yPosition];
                RatManager.addRat(new Rat(enumRatSex, enumMaturity, isPregnant, isSterile, isHavingSex, tileTheRatIsOn, direction, age, ageToGiveBirth, ageToFinishHavingSex, unbornRatsCount));
                line = new Scanner(in.nextLine());
                if (line.hasNext()) {
                    ratSex = line.next();
                } else {
                    ratSex = "lemon"; //just to exit the loop
                }
                
            }
            int killedRatCount = in.nextInt();
            in.nextLine();
            duration = in.nextLong();
            RatManager.setKilledRatCount(killedRatCount);
            line.close();
            in.close();
        }
    }
    public static long getSavedDuration() {
        return duration;
    }
}
