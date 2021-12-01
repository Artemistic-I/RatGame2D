import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Gameboard {
    private static int boardHeight;
    private static int boardWidth;
    private static int ratPopulationToLose;
    private static int levelCompletionTime;
    private static int numLivingRats;
    private static final int TILE_SIZE = 25;           //I assumed each tile is a square
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
        	System.out.println(inputfile.getAbsolutePath());
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
                board[row][column] = createTile(line.next(), ratPositions[row][column], row, column);
            }
            row++;
        }
        line.close();
        in.close();
        
        addAdjacentTiles();
        
        System.out.println("Gameboard created");
    }
    private static void addAdjacentTiles() {
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j<board[i].length; j++) {
    			if (j+1 < board.length) {
    				board[j][i].setNorthTile(board[j+1][i]);
    			} else {
    				board[j][i].setNorthTile(null);
    			}
    			if (0 <= j-1) {
    				board[j][i].setSouthTile(board[j-1][i]);
    			} else {
    				board[j][i].setSouthTile(null);
    			}
    			if (i+1 < board[j].length) {
    				board[j][i].setEastTile(board[j][i+1]);
    			} else {
    				board[j][i].setEastTile(null);
    			}
    			if (0 <= i-1) {
    				board[j][i].setWestTile(board[j][i-1]);
    			} else {
    				board[j][i].setWestTile(null);
    			}
    		}
    	}
	
    }
	private static Tile createTile(String tileType, int ratIndex, int row, int column){
        final int femaleBabyRat = 1;
        final int maleBabyRat = 2;
        if (tileType.equalsIgnoreCase("G")) {
            Tile grass = new TileGrass(row, column);
            return grass;
        } else if (tileType.equalsIgnoreCase("T")) {
            TileInteractable tunnel = new TileTunnel(row, column);
            if (ratIndex == femaleBabyRat){
                RatManager.addRat(new Rat(RatSex.FEMALE, tunnel, "North"));
            } else if (ratIndex == maleBabyRat) {
                RatManager.addRat(new Rat(RatSex.MALE, tunnel, "North"));
            }
            return tunnel;
        } else if (tileType.equalsIgnoreCase("P")) {
            TileInteractable path = new TilePath(row, column);
            if (ratIndex == femaleBabyRat){
                RatManager.addRat(new Rat(RatSex.FEMALE, path, "North"));
            } else if (ratIndex == maleBabyRat) {
                RatManager.addRat(new Rat(RatSex.MALE, path, "North"));
            }
            return path;
        } else {
            return null;
        }
    }
    
    public int calculateScore() {
    	int ratKillPoints = RatManager.getKilledRatCount() * 10;
    	int timePoints = levelCompletionTime;// - levelEndTime;
    	return ratKillPoints + timePoints;
    }
    
    public static double calculateWinLose() {
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
    
    public static void drawGameboard(GraphicsContext graphicsContext) {
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j<board[i].length; j++) {
    			graphicsContext.drawImage(board[j][i].getTileGraphic(), i*getTileSize(), j*getTileSize());
    		}
    	}
    }
	public static int getTileSize() {
		return TILE_SIZE;
	}
    
}
