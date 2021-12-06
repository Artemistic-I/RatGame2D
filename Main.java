import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Alex Gingureanu
	 * 
	 */

public class Main extends Application { 	
	/** 
	 * Runs the program and open the profiles window
	 * @param primaryStage
	 * @throws IOException
	 */
	public void start(Stage primaryStage) throws IOException{ 
		PlayerProfile.loadProfiles();
		Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));

		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		Settings.setVolume(0.5);
		AudioManager.createPlaylist();
		AudioManager.playMenuMusic();

	}
		
	/** Launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
