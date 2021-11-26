import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gameboard {
    private static int boardHeight;
    private static int boardWidth;
    private static int ratPopulationToLose;
    private static int levelCompletionTime;
    private static int numLivingRats;
    private static int tileSize;           //I assumed each tile is a square
    private static Tile[][] board;
    private static PlayerProfile currentPlayer;

/*    public Gameboard(String filename){ //I am gonna leave the constructor for now just in case
        try {
            board = generateBoard(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + filename);
            System.exit(0);
        }
    }*/
    //load tiles from the file describing the level ()
    public static void generateBoard(String filename) throws FileNotFoundException {
        File inputfile = new File(filename);
        Scanner in = null;
        try {
            in = new Scanner(inputfile);
        } catch (FileNotFoundException e){
            System.out.println("Could not find " + filename);
            System.exit(0);
        }
        Scanner line = null;
        //read first line
        if (in.hasNext()) {
            line = new Scanner(in.nextLine());
            boardWidth = line.nextInt();
            boardHeight = line.nextInt();
            board = new Tile[boardHeight][boardWidth];
            line.close();
        }
        //read second line
        if (in.hasNext()) {
            line = new Scanner(in.nextLine());
            ratPopulationToLose = in.nextInt();
            levelCompletionTime = in.nextInt();
        }
        //third line is skipped because it is irrelevant for Gameboard.
        if (in.hasNext()) {
            in.nextLine();
        }
        int[][] ratPositions = new int[boardHeight][boardWidth];
        int row = 0;
        //reading the rats positions
        while (in.hasNext() && row < boardHeight) {
            line = new Scanner(in.nextLine());
            for (int column = 0; column < boardWidth; column++) {
                ratPositions[row][column] = line.nextInt();
            }
            row++;
        }
        //empty line
        if (in.hasNext()) {
            in.nextLine();
        }
        row = 0;
        //reading the Tiles
        while (in.hasNext() && row < boardHeight) {
            line = new Scanner(in.nextLine());
            for (int column = 0; column < boardWidth; column++) {
                board[row][column] = createTile(line.next(), ratPositions[row][column]);
            }
            row++;
        }
        line.close();
        in.close();
        System.out.println("Gameboard created");
    }
    private static Tile createTile(String tileType, int rat){
        if (tileType.equalsIgnoreCase("G")) {
            Tile grass = new TileGrass(tileSize, tileSize);
            return grass;
        } else if (tileType.equalsIgnoreCase("T")) {
            Tile tunnel = new TileTunnel(tileSize, tileSize);
            //code to insert rats
            return tunnel;
        } else if (tileType.equalsIgnoreCase("P")) {
            Tile path = new TilePath(tileSize, tileSize);
            //code to insert rats
            return path;
        } else {
            return null;
        }
    }
    
    public int calculateScore() {
    	int ratKillPoints = RatManager.getKilledRatCount() * 10;
    	int timePoints = levelCompletionTime - levelEndTime;
    	return ratKillPoints + timePoints;
    }
    
    public double calculateWinLose() {
    	return (double) numLivingRats / (double) ratPopulationToLose;
    }
    public static int getHeight() {
        return boardHeight;
    }
    public static int getWidth() {
        return boardWidth;
    }
    public static Tile[][] getBoard() {
        return board;
    }
    
}
