import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Class that manages the scoreboard's contents
 * 
 * @author Artem Iakovlev
 */
public class Scoreboard {
    
    private static ArrayList<Score> scores = new ArrayList<>();
    private static final int MAX_NUMBER_OF_SCORES = 10; // only top 10 scores are saved

    /**
     * Reads scores from a file and ititialises the scoreboard
     * @param levelNumber the level we want to load scoreboard for
     */
    public static void loadScores(int levelNumber) {
        scores.clear();
        File file = new File("scoreboards/scores_level" + levelNumber + ".txt");
        if (file.exists() && file.isFile()) {
            Scanner in = null;
            try {
                in = new Scanner(file);
            } catch (FileNotFoundException e){
                System.out.println("Could not find " + "scoreboards/scores_level" + levelNumber + ".txt");
                System.exit(0);
            }
            Scanner line = null;
            while (in.hasNext()) {
                line = new Scanner(in.nextLine());
                String username = line.next();
                int scoreNum = line.nextInt();
                System.out.println("////" + " " + username + " " + scoreNum);
                scores.add(new Score(PlayerProfile.getPlayerByUsername(username), scoreNum));
            }
            in.close();
            line.close();
        }
    }

    /**
     * Get all the scores from the scoreboard
     * @return all score entries
     */
    public static ArrayList<Score> getScores() {
        return scores;
    }

    /**
     * Adds new score to the scoreboard if appropriate
     * @param newScore the score to be added
     */
    public static void addScore(Score newScore) {
        int existingScore = getScoreNumByPlayer(newScore);
        Boolean isInScoreboard = getScoreNumByPlayer(newScore) != -1;
        Boolean isFull = scores.size() > (MAX_NUMBER_OF_SCORES - 1);
        if (scores.isEmpty()) {
            scores.add(newScore);
        } else {
            if (isInScoreboard) {
                if (existingScore <= newScore.getScoreNum()) {
                    removeScore(newScore.getPlayer());
                    insertScore(newScore);
                }
            } else {
                if (newScore.getScoreNum() >= getSmallest().getScoreNum()) {
                    if (isFull){
                        removeScore(getSmallest().getPlayer());
                        insertScore(newScore);
                    } else {
                        insertScore(newScore);
                    }
                } else {
                    if (!isFull) {
                        scores.add(newScore);
                    }
                }
            }
        }
        saveScoreBoard();
    }

    /**
     * Removes the score of a particular player from scoreboard
     * @param player the player whose score is getting deleted
     */
    public static void removeScore(PlayerProfile player) {
        for (Score score : scores) {
            if (score.getPlayer().equals(player)) {
                scores.remove(score);
            }
        }
        saveScoreBoard();
    }
    /**
     * Gets the smallest score that is currently in the scoreboard
     * @return the smallest score
     */
    private static Score getSmallest() {
        return scores.get(scores.size() - 1);
    }
    
    /**
     * Gets the existing score value of a player who just got a new score
     * @param scoreToCheck the new score of a player we want to get an existing (old) score from
     * @return score value (a number)
     */
    private static int getScoreNumByPlayer(Score scoreToCheck) {
        for (Score score : scores) {
            if (score.getPlayer().getPlayerUsername().equals(scoreToCheck.getPlayer().getPlayerUsername())) {
                return score.getScoreNum();
            }
        }
        return -1;
    }
    
    /**
     * Inserts scores into scoreboard in descending order
     * @param scoreToInsert the score to be inserted
     */
    private static void insertScore(Score scoreToInsert) {
        int i = scores.size() - 1;
        while ((i > -1) && (scoreToInsert.getScoreNum() > scores.get(i).getScoreNum())) {
            i--;
        }
        scores.add(i + 1, scoreToInsert);
    }
    
    /**
     * The method to permanently save the scoreboard.
     */
    public static void saveScoreBoard() {
        File scoreboard = new File("scoreboards/scores_level" + Level.getSelectedLevel().getLevelNumber() + ".txt");
        PrintWriter myWriter = null;
        try {
            myWriter = new PrintWriter(scoreboard);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + "scoreboards/scores_level" + Level.getSelectedLevel().getLevelNumber() + ".txt");
            System.exit(0);
        }
		for (Score scoreEntry : scores) {
            myWriter.println(scoreEntry.getPlayer().getPlayerUsername() + " " + scoreEntry.getScoreNum());
        }
		myWriter.close();
    }
}
