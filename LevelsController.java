import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class of Level scene
 * 
 * @author Alex Gingureanu
 * @version 1.0.0
 */

public class LevelsController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
	private ListView<String> levelsList;

    @FXML
	private Button showScoreBoardBtn;

	@FXML
	private Button backToMenulvlSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
		for (Level lvl : Level.getLevels()) {
			levelsList.getItems().add(lvl.toString());
        }
    }
    
    //after selecting a level the start button gets enabled.
    @FXML
    void backToMenulvlSelectedClicked(ActionEvent event) throws IOException {
        int selectedIndex = levelsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if (Level.getLevels().get(selectedIndex).isLocked()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("This level is locked");
                alert.setHeaderText(null);
                alert.setContentText("Nice try, but you haven't unlocked this level yet. Try harder.");
                alert.showAndWait();
            } else {
                Level.setSelectedLevel(Level.getLevels().get(selectedIndex));
                Menu.startButtonSwitch = false;
                Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Please select a level");
            alert.setHeaderText(null);
            alert.setContentText("No level selected. Please select a level first.");
            alert.showAndWait();
        }
       
    }

    @FXML
    void showScoreBoardBtnClicked(ActionEvent event) throws IOException {
        int selectedIndex = levelsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot show scoreboard");
            alert.setHeaderText(null);
            alert.setContentText("Cannot show scoreboard. No level selected. Please select a level first.");
            alert.showAndWait();
            return;
        }
        Level.setSelectedLevel(Level.getLevels().get(selectedIndex));
        Parent root = FXMLLoader.load(getClass().getResource("scenes/scoreboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
