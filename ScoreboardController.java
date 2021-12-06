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
 * Class that shows the scoreboard
 * 
 * @author Alex Gingureanu
 * @author Artem Iakovlev
 */

public class ScoreboardController implements Initializable {

    //Variables and the scene's objects
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backToLevelsBtn;

    @FXML
    private ListView<String> scoreList;

    
    /** Intialize the scoreboard scene
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scoreboard.loadScores(Level.getSelectedLevel().getLevelNumber());
        scoreList.getItems().add("Place - Usermane - Score");
        for (int i = 0; i < Scoreboard.getScores().size(); i++) {
            int position = i + 1;
            System.out.println("Scoreboard row should apear");
            String lineEntry = position + " - " + Scoreboard.getScores().get(i).toString();
            scoreList.getItems().add(lineEntry);
        }
    }

    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    private void backToLevelsBtnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/levels.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
