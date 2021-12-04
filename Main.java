import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Alex Gingureanu
	 * @version 1.0
	 */

public class Main extends Application { 

	public void start(Stage primaryStage) throws Exception { // # what exception?
		PlayerProfile.loadProfiles();
		Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));

		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		SoundManager.playSound("audio/Spring Field - Godmode.wav");
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
