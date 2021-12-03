import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Settings implements Initializable {

    @FXML
    private Button backToMenuBtn;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Button editUsernameBtn;

    @FXML
    private Button deleteProfileBtn;

    @FXML
    private TextField editUsernameBox;

    private Stage stage;
    private Scene scene;

    @FXML
    void backToMenuBtnClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void editUsernameBtnClicked(ActionEvent event) {
        
    }

    @FXML
    void deleteProfileBtnClicked(ActionEvent event) {
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        volumeSlider.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                
                SoundManager.setVolume(volumeSlider.getValue() * 0.01);
            }
            
        });
        
    }


}
