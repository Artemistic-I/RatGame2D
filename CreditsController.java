
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Class to create the Credits window
 * 
 * @author Alex Gingureanu
 */

public class CreditsController {

    //Variables for the scene 
    @FXML
    private Text creds;

    @FXML
    private Button backToMenuBtn;

    @FXML
    private StackPane container;
    
    /** 
     * Method to return back to menu
     * @param event --when button is pressed
     * @throws IOException if stream to file cannot be written to or closed.
     */
    @FXML
    private void backToMenuBtnClicked(ActionEvent event) throws IOException {

        //create the scene
        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
		Scene scene = backToMenuBtn.getScene();
		root.translateYProperty().set(scene.getHeight());
		container.getChildren().add(root);

        //Animation for the scene
		Timeline time = new Timeline();
		KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		time.getKeyFrames().add(kf);
		time.play();
    }
}
    
