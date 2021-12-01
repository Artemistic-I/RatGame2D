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
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));	

		Level.getLevels().add(new Level(1, "levels/level1.txt", false));
		Level.getLevels().add(new Level(2, "levels/level2.txt", false));
		Level.setSelectedLevel(Level.getLevels().get(0));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		SoundManager.playSound("audio/Spring Field - Godmode.wav");
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
