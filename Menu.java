import java.awt.Label;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

	/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Walid Mohamed and Alex Gingureanu
	 * @version 1.0
	 */
	@SuppressWarnings("unused")
	public class Menu implements Initializable {

		private Parent root;
		private Scene scene;
		private Stage stage;
		private Canvas canvas;

		@FXML
		private Button loadBtn;
	
		@FXML
		private Button profilesBtn;
	
		@FXML
		private Button scoreboardBtn;
	
		@FXML
		private Button settingsBtn;

	    @FXML
	    private Label msgDayMessage;

		@FXML
		private Button exitBtn;
	
		@FXML
		private Button submitBtn;

		@FXML
   		private Button backToMenuBtn;

		@FXML
		private Button addProfBtn;

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

	    /**
	     * Handles the Scoreboard button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdScoreboardClick(MouseEvent mouseEvent) {
	        changeScene(Window.SCOREBOARD);
	    }

	    /**
	     * Handles the Load button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdLoadClick(MouseEvent mouseEvent) {
	        changeScene(Window.LOAD);
	    }

	    /**
	     * Handles the new game button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdNewClick(MouseEvent mouseEvent) {
	        changeScene(Window.SETUP);
	    }

	
		public void loadBtnClckd(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/levels.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

		@FXML
		void onSubmitBtnClick(ActionEvent event) throws IOException {
			Gameboard level1 = new Gameboard("levels/level1.txt");

			Parent root = FXMLLoader.load(getClass().getResource("scenes/gameBoard.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

		@FXML
		void profilesBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/profiles.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

		@FXML
		void scoreboardBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/scoreboard.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

		@FXML
		void settingsBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/settings.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}	

		@FXML
   		void backToMenuBtnClicked(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

   		}
		
		@FXML
		void addProfBtnClicked(ActionEvent event) {
	   
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
			// TODO Auto-generated method stub
			
		}
	}
