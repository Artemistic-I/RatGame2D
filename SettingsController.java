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
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button soundBtn;

    @FXML
    private Button graphicsBtn;

    @FXML
    private Button saveSettingsBtn;

    @FXML
    private Button backToMenuBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void soundBtnClicked(ActionEvent event) throws IOException {

    }

    @FXML
    void graphicsBtnClicked(ActionEvent event) throws IOException {
    
    }

    @FXML
    void saveSettingsBtnClicked(ActionEvent event) throws IOException {
        
    }

    @FXML
    void backToMenuBtnClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
