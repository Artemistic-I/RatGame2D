import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;
	
	private Timeline tickTimeline; 

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));	
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
		Parent gameBoard = (Parent)fxmlLoader.load();          
		GameBoardCanvasController gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();
		
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		tickTimeline.play();
		
		gameBoardCanvasController.drawGame();
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setScene(new Scene(gameBoard));
		primaryStage.show();
	}
	
	private void tick() {
		System.out.println("It's working...(Just for testing)");
		RatManager.moveRats();
	}

	private Pane buildGUI() {
		return new BorderPane();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
