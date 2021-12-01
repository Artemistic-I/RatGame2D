import java.io.IOException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.ResourceBundle;

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
    @FXML
    void showScoreBoardBtnClicked() {

    }
    //after selecting a level the start button gets enabled.
    @FXML
    void backToMenulvlSelectedClicked(ActionEvent event) throws IOException {
        int selectedIndex = levelsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Level.setSelectedLevel(Level.getLevels().get(selectedIndex));
            //Menu.startButtonSwitch = false;  half baked feature
        }
        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
