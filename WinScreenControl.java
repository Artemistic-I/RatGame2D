import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Class showing the winning screen
 * 
 * @author Alex Gingureanu
 */

public class WinScreenControl {

    Stage stage;
    Scene scene;

    @FXML
    private Button menuBtn;

    
    /** Method to return to the menu and reset the gameboard
     * @param event when the button is clicked
     * @throws IOException if stream to file cannot be written to or closed.
 	 * @throws UnsupportedAudioFileException if incorrect audio file format
	 * @throws LineUnavailableException if a line is unavailable and cannot be opened
     */
    @FXML
    private void onMenuBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		Inventory.reset();
        ItemManager.removeAllItems();
		for (Rat rat : RatManager.getRatPopulation()) {
			RatManager.removeRat(rat);
		}
		Menu.startButtonSwitch = true;
		RatManager.setKilledRatCount(0);
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
