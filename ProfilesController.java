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
 * Class of Profiles window
 * 
 * @author Alex Gingureanu
 */

public class ProfilesController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button addProfileBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private ListView<String> profileList;

    
    /** Profiles scene initialized
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshProfileList();
    }
    
    
    /** Add a new profile
     * @param event when the button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    private void addProfileBtnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/addNewProfile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        refreshProfileList();
    }

    
    /** Continue to the game with the selected profile
     * @param event when the button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    private void nextBtnClicked(ActionEvent event) throws IOException {
        int selectedIndex = profileList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot continue");
            alert.setHeaderText(null);
            alert.setContentText("Cannot continue. Please select an existing player profile or create a new one.");
            alert.showAndWait();
        } else {
            PlayerProfile selectedProfile = PlayerProfile.getProfiles().get(selectedIndex);
            Gameboard.setCurrentPlayer(selectedProfile);
            Level.loadLocks();
            Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //Method to refresh the list of profiles
    private void refreshProfileList() {
        profileList.getItems().clear();
        for (PlayerProfile player : PlayerProfile.getProfiles()) {
            profileList.getItems().add(player.getPlayerUsername());
        }
    }
}
