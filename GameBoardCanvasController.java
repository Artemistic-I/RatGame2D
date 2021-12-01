import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.Dragboard;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.image.Image;

public class GameBoardCanvasController implements Initializable {

	@FXML 
	Canvas canvas;

	@FXML
	ProgressBar winLoseIndicator;

	@FXML
	private Button pauseButton;

	@FXML
	private Button saveButton;
	
	@FXML
	private ImageView dragBombDragable;

	@FXML
	private ImageView dragGasDragable;

	@FXML
	private ImageView dragPoisonDragable;

	@FXML
	private ImageView dragSexChFeDragable;

	@FXML
	private ImageView dragSexChMaDragable;

	@FXML
	private ImageView noEntrySignDragable;

	@FXML
	private ImageView dragDeathRatDragable;

	@FXML
	private ImageView dragsterilisationDragable;

	@FXML
		void pauseButtonClicked(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			if (pauseButton.getText().equals("Pause")) {
				pauseButton.setText("Resume");
				Menu.getTimelineManager().stopTime();
				SoundManager.stopSound();
				saveButton.setDisable(false);
			} else {
				pauseButton.setText("Pause");
				saveButton.setDisable(true);
				Menu.getTimelineManager().resumeTime();
				SoundManager.playSound("audio/Soft Knives - SefChol.wav");
			}
		}

		@FXML
		void saveButtonClicked(ActionEvent event) {
			GameFileManager.saveGame("SavedGame.txt", 1, RatManager.getRatPopulation());
			System.exit(0);
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saveButton.setDisable(true);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());				
		
	}



	@FXML
	void dragBombDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragGasDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragPoisonDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragSexChFeDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragSexChMaDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragNoEntrySignDragable(MouseEvent event) throws IOException {
		// Mark the drag as started.
		// We do not use the transfer mode (this can be used to indicate different forms
		// of drags operations, for example, moving files or copying files).
		Dragboard db = noEntrySignDragable.startDragAndDrop(TransferMode.ANY);
		System.out.println("-----drag event"); // # just for testing
		// We have to put some content in the clipboard of the drag event.
		// We do not use this, but we could use it to store extra data if we wished.
		ClipboardContent content = new ClipboardContent();
		content.putString("Hello");
		db.setContent(content);

		// Consume the event. This means we mark it as dealt with.
		event.consume();

	}

	@FXML
	void dragDeathRatDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void dragsterilisationDragable(MouseEvent event) throws IOException{
		im.removeItem
	}

	@FXML
	void canvasDragOver(DragEvent event) {
		System.out.println("------drag_over"); // # just for testing
		if (event.getGestureSource() == noEntrySignDragable) {
	    	// Mark the drag event as acceptable by the canvas.
    		event.acceptTransferModes(TransferMode.ANY);
	    	// Consume the event. This means we mark it as dealt with.
    		event.consume();
		}
	}
	
	@FXML
	void canvasDragDropOccured(DragEvent event) {
		System.out.println("------drop"); // # just for testing
		event.consume();
	}


	public Canvas getCanvas() {
		return canvas;
	}
	
	public void drawWinLoseIndicator(double winLoseRatio) {
		this.winLoseIndicator.setProgress(winLoseRatio);
	}
}
