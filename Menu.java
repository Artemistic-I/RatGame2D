import java.awt.Label;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

	/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Walid Mohamed and Alex Gingureanu
	 * @version 1.0
	 */
	@SuppressWarnings("unused")
	public class Menu implements Initializable {
		
		private GameBoardCanvasController gameBoardCanvasController;
		private Parent root;
		private Scene scene;
		private Stage stage;
		private static TimelineMangaer timelineManager;
		public static Boolean startButtonSwitch = true;
		

		@FXML
		private Button startBtn;

		@FXML
		private Button selectLevelBtn;
	
		@FXML
		private Button settingsBtn;

		@FXML
		private Button profilesBtn;

		@FXML
		private Button exitBtn;
	
		@FXML
	    private Label msgDayMessage;
		

		
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
	        PlayerProfile.showDelete();
	    }

	    /**
	     * Handles the Create Profile button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdCreateProfileClick(MouseEvent mouseEvent) {
	        PlayerProfile.showCreate();
	    }

		// Start
		@FXML
		void onStartBtnClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/gameBoard.fxml"));     
			Parent root = (Parent)fxmlLoader.load();          
			gameBoardCanvasController = fxmlLoader.<GameBoardCanvasController>getController();
			Gameboard.generateBoard(Level.getSelectedLevel().getLevelFile());

			timelineManager = new TimelineMangaer(gameBoardCanvasController.getGraphicContext());

			//Parent root = (Parent)fxmlLoader.load();   
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
		
		// Profiles
		@FXML
		void profilesBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
			
	    /**
	     * Called to initialise a controller after its root element has been completely processed.
	     *
	     * @param location  The location used to resolve relative paths for the root object, or
	     *                  <tt>null</tt> if the location is not known.
	     * @param resources The resources used to localise the root object, or
	     *                  <tt>null</tt> if
	     */
	    public void initialise(URL location, ResourceBundle resources) {
	        //this.msgDayMessage.setText((String) FindMessage.getMessage());
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			startBtn.setDisable(startButtonSwitch);
		}
		public static TimelineMangaer getTimelineManager() {
			return timelineManager;
		}
	}
