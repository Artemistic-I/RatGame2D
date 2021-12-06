import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Artem
 */
public class Gameboard {
    private static int boardHeight;
    private static int boardWidth;
    private static int ratPopulationToLose;
    private static int levelCompletionTime;
    private static final int TILE_SIZE = 25;
    private static Tile[][] board;
    private static PlayerProfile currentPlayer;
    private static Boolean isLoadingGame = false;

    /**
     * Switches the loading mode for generating tile grid on or off
     * @param value boolean value true if game is loading
     */
    public static void setIsLoadingGame(Boolean value) {
        isLoadingGame = value;
    }
    /**
     * load tiles from the file describing the level
     * @param filename name of file where tiles/level is stored
     * @throws FileNotFoundException thrown if filename isn't a valid file
     */
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
        }
        //read second line
        if (in.hasNext()) {
            line = new Scanner(in.nextLine());
            ratPopulationToLose = line.nextInt();
            levelCompletionTime = line.nextInt();
        }
        int[][] ratPositions = new int[boardHeight][boardWidth];
        int row = 0;
        //reading rats positions
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
                if (!isLoadingGame) {
                    board[row][column] = createTile(line.next(), ratPositions[row][column], row, column);
                } else {
                    board[row][column] = createTileOnly(line.next(), row, column);
                }
                
            }
            row++;
        }
        line.close();
        in.close();
        
        addAdjacentTiles();
        
        System.out.println("Gameboard created levelComplTime = " + levelCompletionTime + " ratPopToLOse = " + ratPopulationToLose);
    }

    /**
     * sets adjacent tiles for each tile in gamebaord array
    */
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

    /**
     * Only used when game is loading to add baby rats to tiles
     * @param tileType determines what tile class to initilise
     * @param ratIndex used to specify gender of rat 0 - no rat, 1 - female, 2 - male
     * @param row what row the tile is at in the gameboard 2d array
     * @param column what column the tile is at in the gameboard 2d array
     * @return created tile
     */
	private static Tile createTile(String tileType, int ratIndex, int row, int column){
        final int femaleBabyRat = 1;
        final int maleBabyRat = 2;
        if (tileType.equalsIgnoreCase("G")) {
            Tile grass = new TileGrass(row, column);
            return grass;
        } else if (tileType.equalsIgnoreCase("T")) {
            TileInteractable tunnel = new TileTunnel(row, column);
            if (ratIndex == femaleBabyRat){
                RatManager.addRat(new Rat(RatSex.FEMALE, tunnel, "North", RatManager.incrimentRatsAdded()));
            } else if (ratIndex == maleBabyRat) {
                RatManager.addRat(new Rat(RatSex.MALE, tunnel, "North", RatManager.incrimentRatsAdded()));
            }
            return tunnel;
        } else if (tileType.equalsIgnoreCase("P")) {
            TileInteractable path = new TilePath(row, column);
            if (ratIndex == femaleBabyRat){
                RatManager.addRat(new Rat(RatSex.FEMALE, path, "North", RatManager.incrimentRatsAdded()));
            } else if (ratIndex == maleBabyRat) {
                RatManager.addRat(new Rat(RatSex.MALE, path, "North", RatManager.incrimentRatsAdded()));
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * Only creates tile without any rats on it
     * @param tileType determines what tile class to initilise
     * @param row what row the tile is at in the gameboard 2d array
     * @param column what column the tile is at in the gameboard 2d array
     * @return created tile
     */
    public static Tile createTileOnly(String tileType, int row, int column) {
        if (tileType.equalsIgnoreCase("G")) {
            Tile grass = new TileGrass(row, column);
            return grass;
        } else if (tileType.equalsIgnoreCase("T")) {
            TileInteractable tunnel = new TileTunnel(row, column);
            return tunnel;
        } else if (tileType.equalsIgnoreCase("P")) {
            TileInteractable path = new TilePath(row, column);
            return path;
        } else {
            return null;
        }
    }
    
    /**
     * score uses how long it took to complete the level and the amount of rats killed to calculate score
     * @return returns the score as int
     */
    public static int calculateScore() {
    	int ratKillPoints = RatManager.getKilledRatCount() * 10;
    	int timePoints = levelCompletionTime - (int) Menu.getTimelineManager().getDuration()/1000; // convert duration from milliseconds to seconds
    	return ratKillPoints + timePoints;
    }
    
    /**
     * 
     * @return returns a fraction 1 means lose, 0 means win
     */
    public static double calculateWinLose() {
    	return (double) (RatManager.countMaleRats() + RatManager.countFemaleRats()) / (double) ratPopulationToLose;
    }

    /**
     * getter for height
     * @return returns height of gameboard
     */
    public static int getHeight() {
        return boardHeight;
    }
    /**
     * getter for width
     * @return returns width of gameboard
     */
    public static int getWidth() {
        return boardWidth;
    }

    /**
     * Gets all the tiles
     * @return returns all tiles
     */
    public static Tile[][] getBoard() {
        return board;
    }
    
    /**
     * Draws the gameboard
     * @param graphicsContext
     */
    public static void drawGameboard(GraphicsContext graphicsContext) {
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j<board[i].length; j++) {
    			graphicsContext.drawImage(board[j][i].getTileGraphic(), i*getTileSize(), j*getTileSize());
    		}
    	}
    }

    /**
     * 
     * @return returns size of tile in pixels
     */
	public static int getTileSize() {
		return TILE_SIZE;
	}
	
    /**
     * 
     * @return returns the mount of rats needed to lose game
     */
	public static int getRatPopulationToLose() {
		return ratPopulationToLose;
	}

    /**
     * setter for currentPlayer
     * @param player sets which profile is currently playing
     */
    public static void setCurrentPlayer(PlayerProfile player) {
        currentPlayer = player;
    }

    /**
     * getter for currentPlayer
     * @return gets the current profile playing
     */
    public static PlayerProfile getCurrentPlayer() {
        return currentPlayer;
    }
    
}
