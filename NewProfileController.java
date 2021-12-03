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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewProfileController implements Initializable {


    Stage stage;
    Scene scene;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField usernameBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void confirmBtnClicked(ActionEvent event) throws IOException {
        String userInput = usernameBox.getText();
        if (PlayerProfile.isUniqueUsername(userInput)) {
            PlayerProfile.getProfiles().add(new PlayerProfile(userInput, true));
            Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot create account");
            alert.setHeaderText(null);
            alert.setContentText("A profile with this username already exists. Please try another username.");
            alert.showAndWait();
        }
    }

    @FXML
    void cancelBtnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
