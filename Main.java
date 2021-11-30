import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Alex Gingureanu
	 * @version 1.0
	 */

public class Main extends Application {

	GameBoardCanvasController gameBoardCanvasController;
	
	private Timeline tickTimeline; 

	public void start(Stage primaryStage) throws Exception { // # what exception?
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));	

		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		SoundManager.playSound("audio/Spring Field - Godmode.wav");
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
