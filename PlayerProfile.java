import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class PlayerProfile {

	private String username;
	private ArrayList<Level> unlockedLevels;
	private File savedDetails;
	private static ArrayList<String> uniqueUsernames;

	// don't forget to add playerCount to game save
	public PlayerProfile(String username) {
		setPlayerUsername(username);
		this.username = username;
		savedDetails = new File("players/player_" + username + ".txt");
		savePlayerDetails();
	}
	public void removePlayer(PlayerProfile player) {
		savedDetails.delete(); // possible IO Exception
	}

	public String getPlayerUsername() {
		return username;
	}
	public void setPlayerUsername(String username) {
		if (uniqueUsernames.contains(username)) {
			throw new InvalidParameterException();
		} else {
			this.username = username;
			savePlayerDetails();
		}
	}
	public void addToUnlocked(Level lvl) {
		unlockedLevels.add(lvl);
	}
	public Boolean isLocked(Level lvl) {
		return !unlockedLevels.contains(lvl);
	}
	public void savePlayerDetails() {
        PrintWriter myWriter = null;
        try {
            myWriter = new PrintWriter(savedDetails);

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + "players/player_" + username + ".txt");
            System.exit(0);
        }
		String levelsAsText = "";
		for (int i = 0; i < unlockedLevels.size(); i++) {
			levelsAsText += Integer.toString(unlockedLevels.get(i).getLevelNumber()) + " ";
		}
		myWriter.println(username + " " + levelsAsText);
		myWriter.close();
	}
}
