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

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));	
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
		Parent gameBoard = (Parent)fxmlLoader.load();          
		gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();
		
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		tickTimeline.play();
		
		gameBoardCanvasController.drawGame();
		Gameboard.generateBoard("src/levels/level1.txt"); // # testing
		Gameboard.drawGameboard(gameBoardCanvasController.getCanvas()); // # testing
		
		//primaryStage.setScene(new Scene(root));
		primaryStage.setScene(new Scene(gameBoard)); // # testing
		primaryStage.show();
	}
	
	private void tick() {
		System.out.println("It's working...(Just for testing)");
		RatManager.updateRats(gameBoardCanvasController.getCanvas());
		
		gameBoardCanvasController.drawWinLoseIndicator(Gameboard.calculateWinLose());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
