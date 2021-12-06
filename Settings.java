import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class of game settings
 * 
 * @author Alex Gingureanu
 * @version 1.0.0
 */

public class Settings implements Initializable {
    
    //Variables for UI of the scene and other

    @FXML
    private Button backToMenuBtn;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private Button deleteProfileBtn;

    @FXML
    private TextField editUsernameBox;

    @FXML
    private Label currentUsernameLabel;

    private Stage stage;
    private Scene scene;
    public static double volume;

    
    /** 
     * Method to return back to menu when button is clicked
     * @param event -- when button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    void backToMenuBtnClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    
    /** 
     * Method to save the changes
     * @param event -- when the button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    void saveChangesBtnClicked(ActionEvent event) throws IOException {
        String newUsername = editUsernameBox.getText();
        String oldUsername =  Gameboard.getCurrentPlayer().getPlayerUsername();
        if (PlayerProfile.isUniqueUsername(newUsername)) {
            Gameboard.getCurrentPlayer().setPlayerUsername(newUsername);
            if (Gameboard.getCurrentPlayer().hasSavedGame()) {
                File file = new File("gamesaves/" + oldUsername + ".txt");
                file.renameTo(new File("gamesaves/" + newUsername + ".txt"));
            }
            updateLabel();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot change username");
            alert.setHeaderText(null);
            alert.setContentText("A profile with this username already exists./n Please try another username.");
            alert.showAndWait();
        }
    }

    
    /** 
     * Method to delete the profile from the system
     * @param event --when the button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    void deleteProfileBtnClicked(ActionEvent event) throws IOException {
        //ScoreboardController.removeScore(Gameboard.getCurrentPlayer()); needs to remove from every scoreboard level
        Gameboard.getCurrentPlayer().removePlayer();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Your profile was successfully deleted.");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void updateLabel() {
        currentUsernameLabel.setText("Your current username: " + Gameboard.getCurrentPlayer().getPlayerUsername());
    }


    
    /** 
     * Method when the scene is initialized
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLabel();
        volumeSlider.valueProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                double vol = volumeSlider.getValue() * 0.01;
                AudioManager.setVol(vol);
                setVolume(vol);
               
            }
            
        });
        
    }

    
    /** 
     * Method to set the volume of the settings
     * @param volume the number from the slider
     */
    public static void setVolume(double volume){
        Settings.volume = volume;
    }

    
    /** 
     * Return the volume from settings
     * @return double
     */
    public static double getVolume(){
        return volume;
    }


}
