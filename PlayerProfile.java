import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that models player profiles
 * 
 * @author Artem Iakovlev
 */
public class PlayerProfile {

	private String username;
	private File savedDetails;
	private Boolean hasSavedGame;
	private static ArrayList<PlayerProfile> profiles = new ArrayList<>();

	/**
	 * Creates Player Profile
	 * @param username
	 * @param isNew
	 */
	public PlayerProfile(String username, Boolean isNew) {
		this.savedDetails = new File("players/player_" + username + ".txt");
		hasSavedGame = false;
		if (isNew) {
			setPlayerUsername(username);
		} else {
			this.username = username;
		}
	}
	/**
	 * Checks if a player has saved a game
	 * @return
	 */
	public Boolean hasSavedGame() {
		return hasSavedGame;
	}
	/**
	 * 
	 * @param value
	 */
	public void setHasSavedGame(Boolean value) {
		hasSavedGame = value;
	}
	/**
	 * Removes player
	 */
	public void removePlayer() {
		this.savedDetails.delete();
		profiles.remove(this);
		if (hasSavedGame) {
			File file = new File("gamesaves/" + username + ".txt");
			file.delete();
		}
	}
	/**
	 * Gets player with a specific username
	 * @param username username of a player to be found
	 * @return
	 */
	public static PlayerProfile getPlayerByUsername(String username) {
		for (PlayerProfile player : profiles) {
			if (player.getPlayerUsername().equals(username)) {
				return player;
			}
		}
		return null;
	}

	/**
	 * Gets player's username
	 * @return username
	 */
	public String getPlayerUsername() {
		return username;
	}

	/**
	 * Gets saved details
	 * @return
	 */
	public File getSavedDetails() {
		return savedDetails;
	}
	/**
	 * Gets all profiles
	 * @return all profiles
	 */
	public static ArrayList<PlayerProfile> getProfiles() {
		return profiles;
	}
	/**
	 * Checks if a username is unique
	 * @param username username to be found
	 * @return is unique or not 
	 */
	public static Boolean isUniqueUsername(String username) {
		for (PlayerProfile player : profiles) {
			if (player.getPlayerUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Changes player's username
	 * @param username new username
	 */
	public void setPlayerUsername(String username) {
		if (!isUniqueUsername(username)) {
			throw new InvalidParameterException();
		} else {
			savedDetails.delete();
			this.username = username;
			savedDetails = new File("players/player_" + username + ".txt");
			savePlayerDetails();
		}
	}
	/**
	 * Loads saved profiles to memory
	 */
	public static void loadProfiles() {
		File playersFolder = new File("players/");
		File levelsFolder = new File("levels/");

		File[] playerFiles = playersFolder.listFiles();
		File[] levelFiles = levelsFolder.listFiles();
		
		int i = 1;
		for (File file : levelFiles) {
			Level.getLevels().add(new Level(i, "levels/" + file.getName()));
			i++;
		}
		Level.setSelectedLevel(Level.getLevels().get(0)); // initialisation is required
		Scanner in = null;
		for (File file : playerFiles) {
			if (file.isFile()){
				try{
					in = new Scanner(file);
				} catch (FileNotFoundException e) {
					System.out.println("Cannot find " + file.getName());
					System.exit(0);
				}
				String username = in.next();
        		PlayerProfile.getProfiles().add(new PlayerProfile(username, false));
			}
		}
		in.close();
	}
	/**
	 * Permanently saves player's details
	 */
	public void savePlayerDetails() {
        PrintWriter myWriter = null;
        try {
            myWriter = new PrintWriter(savedDetails);

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + "players/player_" + username + ".txt");
            System.exit(0);
        }
		String levelsAsText = "";
		for (Level lvl : Level.getLevels()) {
			if (!lvl.isLocked()){
				levelsAsText += Integer.toString(lvl.getLevelNumber()) + " ";
			}
		}
		myWriter.println(username + " " + levelsAsText);
		myWriter.close();
	}
}
