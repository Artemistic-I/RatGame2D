import java.awt.Button;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

	/**
	 * Controls the Main Menu {@link javafx.scene.Scene}
	 *
	 * @author Walid Mohamed
	 * @version 1.0
	 */
	@SuppressWarnings("unused")
	public class MainMenuController implements Initializable {

	    @FXML
	    private Button cmdNew;
	    @FXML
	    private Button cmdLoad;
	    @FXML
	    private Button cmdScoreboard;
	    @FXML
	    private Button cmdMakeProfile;
	    @FXML
	    private Button cmdDeleteProfile;
	    @FXML
	    private Button cmdQuit;
	    @FXML
	    private Label msgDayMessage;

	    /**
	     * Handles the Quit button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void quitClicked(MouseEvent mouseEvent) {
	        System.exit(0);
	    }

	    /**
	     * Handles the Delete Profile button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdDeleteProfileClick(MouseEvent mouseEvent) {
	        Profiles.showDelete();
	    }

	    /**
	     * Handles the Create Profile button click event
	     *
	     * @param mouseEvent Event
	     */
	    public void cmdCreateProfileClick(MouseEvent mouseEvent) {
	        Profiles.showCreate();
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

	    /**
	     * Called to initialise a controller after its root element has been completely processed.
	     *
	     * @param location  The location used to resolve relative paths for the root object, or
	     *                  <tt>null</tt> if the location is not known.
	     * @param resources The resources used to localise the root object, or
	     *                  <tt>null</tt> if
	     */
	    public void initialise(URL location, ResourceBundle resources) {
	        this.msgDayMessage.setText((String) FindMessage.getMessage());
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
	}
