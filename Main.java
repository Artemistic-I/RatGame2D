import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 1000;

	public void start(Stage primaryStage) throws Exception {
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane buildGUI() {
		return new BorderPane();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
