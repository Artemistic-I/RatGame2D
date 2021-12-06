import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Class of the losing window in game
 * 
 * @author Alex Gingureanu
 */

public class LoseScreenController {

	//Variables and scene's view
	Stage stage;
	Scene scene;

    @FXML
    private Button menuBtn;

    @FXML
    private Button retryBrn;

    
	/** Method to return back to Menu
	 * @param event --when the button is clicked
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	@FXML
    private void onMenuBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		AudioManager.playMenuMusic();
		Inventory.reset();
		AudioManager.setVol(Settings.getVolume());
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

    
	/** Method to retry the level and restart
	 * @param event --when the button is clicked
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	@FXML
    private void onRetryBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		AudioManager.playGameMusic();
		Inventory.reset();
		AudioManager.setVol(Settings.getVolume());
		ItemManager.removeAllItems();
		for (Rat rat : RatManager.getRatPopulation()) {
			RatManager.removeRat(rat);
		}
		Menu.startButtonSwitch = true;
		RatManager.setKilledRatCount(0);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
		Parent root = (Parent)fxmlLoader.load();          
		GameBoardCanvasController gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();
		Gameboard.setIsLoadingGame(false);
		Gameboard.generateBoard(Level.getSelectedLevel().getLevelFile());
		TimelineMangaer timelineManager = new TimelineMangaer(gameBoardCanvasController);  
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();


    }

}
