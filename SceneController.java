import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SceneController {

    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    @FXML
    void onStartBtnClick(ActionEvent event) {

    }

    @FXML
    void quitClicked(ActionEvent event) {
        System.exit(0);
    }

}

