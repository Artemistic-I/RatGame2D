import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * used to save/load games
 * @author Sam/Artem
 * 
 */
public class GameFileManager {

    private static long duration;
    // first line stores level number
    // next lines store each individual rats attributes seperated by white space.
    // each rats attributes are seperated by a new line "\n
    
    /**
     * takes all the values it needs and saves everything from a paused game
     * this means a player can cme back later and resume the level where they left it
     * @param username the player username - used to determine filename
     * @param duration how long the level has been played for
     */
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

        // player inventory
        int[] inv = Inventory.getInv();
        for (int item : inv) {
            // prints item details to file
            myWriter.println(item);
        }
        // items
        // array of items on the board
        Item[] items = ItemManager.getCurrentlyPlacedItems();
        for (Item item : items) {
            // prints item details to file
            myWriter.println(item.toString());
        }
        myWriter.close();
    }

    /**
     * loads all values from saved file
     * constructs new instances of classes that are needed 
     * ensuring they are the same as when the game was saved
     */
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
            System.out.println("level number = " + levelNumber);
            Level.setSelectedLevel(levelNumber);
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
                int ratID = line.nextInt();
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
                RatManager.addRat(new Rat(enumRatSex, enumMaturity, isPregnant, isSterile, isHavingSex, tileTheRatIsOn, direction, age, ageToGiveBirth, ageToFinishHavingSex, unbornRatsCount, ratID));
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
            in.nextLine();
            for (int i = 0; i < 8; i++) {
                Inventory.setInv(i, in.nextInt());
                in.nextLine(); // fucking new line character. I hate it
            }
            while (in.hasNext()) {
                line = new Scanner(in.nextLine());
                line.next(); // reads and ignores "class"
                String itemType = line.next();
                ///////////Poison//////////
                if (itemType.equals("Poison")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    ItemManager.addItem(new Poison((TileInteractable) Gameboard.getBoard()[xPosition][yPosition]));
                ///////////Gas////////////
                } else if (itemType.equals("Gas")) {
                    int xPosition = line.nextInt(); // tileTheItemIsOn x position
                    int yPosition = line.nextInt(); // tileTheItemIsOn y position
                    int gasTimeElapsed = line.nextInt();
                    int gassedxPosition = 0; // to avoid NullPointerException
                    CopyOnWriteArrayList<TileInteractable> gassedTiles = new CopyOnWriteArrayList<>();
                    ArrayList<Rat> gassedRats = new ArrayList<>();
                    // Adding gassed tiles to the corresponding list
                    if (line.hasNext()) {
                        while (line.hasNext() && (gassedxPosition != -1)) {
                            gassedxPosition = line.nextInt(); // gassed tile x position
                            int gassedyPosition = line.nextInt(); // gassed tile y position
                            if (gassedxPosition != -1) {  // this is not a redundant check. Don't remove it!
                                TileInteractable gassedTile = (TileInteractable) Gameboard.getBoard()[gassedxPosition][gassedyPosition];
                                gassedTiles.add(gassedTile);
                            }
                        }
                    }

                    // Adding gassed rats to the corresponding list
                    int maxpossibleID = 300; // ASSUMPTION
                    int[] exposureCounts; // stores counts for each ratID where ratID is index
                    ArrayList<Integer> ratIDs; // stores unique ratIDs
                    if ((gassedxPosition == -1) && line.hasNext()) {
                        exposureCounts = new int[maxpossibleID];
                        for (int j = 0; j < maxpossibleID; j++) {
                            exposureCounts[j] = -1;
                        }
                        ratIDs = new ArrayList<>();
                        int expCountTemp;
                        // calculating exposure for each rat
                        while (line.hasNext()) {
                            int ratID = line.nextInt();
                            if (exposureCounts[ratID] == -1) {
                                exposureCounts[ratID] = 1;
                                ratIDs.add(ratID);
                            } else {
                                expCountTemp = exposureCounts[ratID];
                                expCountTemp++;
                                exposureCounts[ratID] = expCountTemp;
                            }
                        }
                        // adding each gassed rat exposureCount number of times to the list
                        // e.g. ratID = 3 with exposureCount = 2 is added twice and so on.
                        for (Integer ratID : ratIDs) {
                            expCountTemp = exposureCounts[ratID];
                            for (int i = 0; i < expCountTemp; i++) {
                                gassedRats.add(RatManager.getRatByID(ratID));
                            }
                        }
                    }
                    TileInteractable tileTheItemIsOn = (TileInteractable) Gameboard.getBoard()[xPosition][yPosition];
                    ItemManager.addItem(new Gas(tileTheItemIsOn, gasTimeElapsed, gassedTiles, gassedRats));
                //////////////Bomb///////////////
                } else if (itemType.equals("Bomb")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    int remainingTime = line.nextInt();
                    ItemManager.addItem(new Bomb((TileInteractable) Gameboard.getBoard()[xPosition][yPosition], remainingTime));
                ////////////Sterilisation/////////
                } else if (itemType.equals("Sterilisation")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    ItemManager.addItem(new Sterilisation((TileInteractable) Gameboard.getBoard()[xPosition][yPosition]));
                ///////////NoEntry///////////////
                } else if (itemType.equals("NoEntry")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    int health = line.nextInt();
                    ItemManager.addItem(new NoEntry((TileInteractable) Gameboard.getBoard()[xPosition][yPosition], health));
                ///////////DeathRat///////////////
                } else if (itemType.equals("DeathRat")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    int ratsKilled = line.nextInt();
                    String direction = line.next();
                    int age = line.nextInt();
                    TileInteractable tile = (TileInteractable) Gameboard.getBoard()[xPosition][yPosition];
                    ItemManager.addItem(new DeathRat(tile, direction, ratsKilled, age));
                /////////SexChangeFemale////////////
                } else if (itemType.equals("SexChangeFemale")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    ItemManager.addItem(new SexChangeFemale((TileInteractable) Gameboard.getBoard()[xPosition][yPosition]));
                //////////SexChangeMale////////////
                } else if (itemType.equals("SexChangeMale")) {
                    int xPosition = line.nextInt();
                    int yPosition = line.nextInt();
                    ItemManager.addItem(new SexChangeMale((TileInteractable) Gameboard.getBoard()[xPosition][yPosition]));
                } else {
                    System.out.println("Item name doesn't match any existing items. Failed to load item " + itemType);
                }
            }
            line.close();
            in.close();
        }
    }

    /**
     * 
     * @return returns game duration
     */
    public static long getSavedDuration() {
        return duration;
    }
}
