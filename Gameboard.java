import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gameboard {
    private int boardHeight;
    private int boardWidth;
    private int ratPopulationToLose;
    private int levelCompletionTime;
    private int numLivingRats;
    private int tileSize;           //I assumed each tile is a square
    private Tile[][] board;
    private PlayerProfile currentPlayer;

    public Gameboard(String filename){
        try {
            board = generateBoard(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + filename);
            System.exit(0);
        }
    }
    //load tiles from the file describing the level ()
    private Tile[][] generateBoard(String filename) throws FileNotFoundException {
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
            this.boardWidth = line.nextInt();
            this.boardHeight = line.nextInt();
            board = new Tile[boardHeight][boardWidth];
            line.close();
        }
        //read second line
        if (in.hasNext()) {
            line = new Scanner(in.nextLine());
            this.ratPopulationToLose = in.nextInt();
            this.levelCompletionTime = in.nextInt();
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
        return board;
    }
    private Tile createTile(String tileType, int rat){
        if (tileType.equalsIgnoreCase("G")) {
            Tile grass = new GrassTile(tileSize, tileSize);
            return grass;
        } else if (tileType.equalsIgnoreCase("T")) {
            Tile tunnel = new TunnelTile(tileSize, tileSize);
            //code to insert rats
            return tunnel;
        } else if (tileType.equalsIgnoreCase("P")) {
            Tile path = new PathTile(tileSize, tileSize);
            //code to insert rats
            return path;
        } else {
            return null;
        }
    }
    public Tile[][] getTiles(){
        return board;
    }
}
