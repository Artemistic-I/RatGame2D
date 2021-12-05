
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Class to create the Credits window
 * 
 * @author Alex Gingureanu
 */

public class CreditsController implements Initializable {

    @FXML
    private Text creds;

    @FXML
    private Button backToMenuBtn;

    @FXML
    private StackPane container;

    @FXML
    void backToMenuBtnClicked(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
		Scene scene = backToMenuBtn.getScene();

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
        
            Pane root = new Pane();
    
            TextField enterText = new TextField();
            enterText.setFont(Font.font("SanSerif",20));
    
            creds = new Text(150,300,"");
            Font Sanserif = Font.font("Phosphate",50);
            creds.setFont(Sanserif);
            root.getChildren().add(creds);
    
    
            creds.textProperty().bind(enterText.textProperty());
    
            creds.setOnMouseDragged(e ->{
                creds.setX(e.getX());
                creds.setY(e.getY());
            });     
    
        }
       

    }
    
