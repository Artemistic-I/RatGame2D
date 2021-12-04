import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

	/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Walid Mohamed and Alex Gingureanu
	 * @version 1.0
	 */
	public class Menu implements Initializable {
		
		private GameBoardCanvasController gameBoardCanvasController;
		private Parent root;
		private Scene scene;
		private Stage stage;
		private static TimelineMangaer timelineManager;
		public static Boolean startButtonSwitch = true;
		
		@FXML
    	private StackPane container;
		
		@FXML
		private Button startBtn;

		@FXML
		private Button selectLevelBtn;

		@FXML
		private Button loadBtn;
	
		@FXML
		private Button settingsBtn;

		@FXML
		private Button exitBtn;
	
		@FXML
	    private Label msgDayMessage;
		
		@FXML
		private Button credsBtn;
		
		@FXML
		private Label messageOfTheDay;

		
	    /**
	     * Handles the Quit button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void quitClicked(ActionEvent event) {
			System.exit(0);
		}

	    /**
	     * Handles the Delete Profile button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdDeleteProfileClick(MouseEvent mouseEvent) {
	        //PlayerProfile.showDelete();
	    }

	    /**
	     * Handles the Create Profile button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdCreateProfileClick(MouseEvent mouseEvent) {
	        //PlayerProfile.showCreate();
	    }

		// Start
		@FXML
		void onStartBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
			Parent root = (Parent)fxmlLoader.load();          
			gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();
			Gameboard.generateBoard(Level.getSelectedLevel().getLevelFile());

			timelineManager = new TimelineMangaer(gameBoardCanvasController);

			//Parent root = (Parent)fxmlLoader.load();   
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			SoundManager.stopSound();
			SoundManager.playSound("audio/Soft Knives - SefChol.wav");
			
		}

		@FXML
		void loadBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
			Parent root = (Parent)fxmlLoader.load();          
			gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();

			GameFileManager.loadGame();

			timelineManager = new TimelineMangaer(gameBoardCanvasController, GameFileManager.getSavedDuration());
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			SoundManager.stopSound();
			SoundManager.playSound("audio/Soft Knives - SefChol.wav");
			
		}

		// Select level
		@FXML
		void selectLevelBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/levels.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

		// Settings
		@FXML
		void settingsBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/settings.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

		@FXML
    	void credsClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/credits.fxml"));
			Scene scene = credsBtn.getScene();

			root.translateYProperty().set(scene.getHeight());
			container.getChildren().add(root);

			Timeline time = new Timeline();
			KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
			KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
			time.getKeyFrames().add(kf);
			time.play();

    	}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			startBtn.setDisable(startButtonSwitch);
			String username = Gameboard.getCurrentPlayer().getPlayerUsername();
			File savedGame = new File("gamesaves/" + username + ".txt");
			if (savedGame.exists() && savedGame.isFile()) {
				Gameboard.getCurrentPlayer().setHasSavedGame(true);
			} else {
				loadBtn.setDisable(true);
			}
			this.messageOfTheDay.setText(MessageOfTheDay.getMessage());
		}
		public static TimelineMangaer getTimelineManager() {
			return timelineManager;
		}
	}
