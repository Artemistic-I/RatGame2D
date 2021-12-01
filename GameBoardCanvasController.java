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
	private Canvas canvas;
	
	private GraphicsContext graphicsContext;

	@FXML
	private ProgressBar winLoseIndicator;

	@FXML
	private Button pauseButton;

	@FXML
	private Button saveButton;
	
	@FXML
	private ImageView bombDragable;

	@FXML
	private ImageView gasDragable;

	@FXML
	private ImageView poisonDragable;

	@FXML
	private ImageView sexChFeDragable;

	@FXML
	private ImageView sexChMaDragable;

	@FXML
	private ImageView noEntrySignDragable;

	@FXML
	private ImageView deathRatDragable;

	@FXML
	private ImageView sterilisationDragable;

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
		graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.GRAY); // # @aes do we need to do this now?
		graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());				
		
	}



	@FXML
	void dragBombDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = bombDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("bombDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragGasDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = gasDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("gasDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragPoisonDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = poisonDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("poisonDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragSexChFeDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = sexChFeDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("sexChFeDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragSexChMaDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = sexChMaDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("sexChMaDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragNoEntrySignDragable(MouseEvent event) throws IOException {
		Dragboard db = noEntrySignDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("noEntrySignDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragDeathRatDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = deathRatDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("deathRatDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void dragSterilisationDragable(MouseEvent event) throws IOException{
		//im.removeItem
		Dragboard db = sterilisationDragable.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("sterilisationDragable");
		db.setContent(content);
		event.consume();
	}

	@FXML
	void canvasDragOver(DragEvent event) {
		if (event.getGestureSource() == bombDragable || 
					event.getGestureSource() == gasDragable || 
					event.getGestureSource() == poisonDragable || 
					event.getGestureSource() == sexChFeDragable || 
					event.getGestureSource() == sexChMaDragable || 
					event.getGestureSource() == noEntrySignDragable || 
					event.getGestureSource() == deathRatDragable || 
					event.getGestureSource() == sterilisationDragable) {
	    	// Mark the drag event as acceptable by the canvas.
    		event.acceptTransferModes(TransferMode.ANY);
	    	// Consume the event. This means we mark it as dealt with.
    		event.consume();
		}
	}
	
	@FXML
	void canvasDragDropOccured(DragEvent event) {
		System.out.println("------drop"); // # just for testing
		
		double xCoordinate = event.getX();
        double yCoordinate = event.getY(); 
        int xGridRef = (int) xCoordinate / 25;
        int yGridRef = (int) yCoordinate / 25;
        Tile droppedOnTile = Gameboard.getBoard()[yGridRef][xGridRef];
        System.out.println(droppedOnTile);
		if (event.getGestureSource() == bombDragable) {
			// # create bomb
		} else if (event.getGestureSource() == gasDragable) {
			// # create gas
		} else if (event.getGestureSource() == poisonDragable) {
			// # create poison
		} else if (event.getGestureSource() == sexChFeDragable) {
			// # create female sex change
		} else if (event.getGestureSource() == sexChMaDragable) {
			// # create male sex change
		} else if (event.getGestureSource() == noEntrySignDragable) {
			// # create no entry sign
		} else if (event.getGestureSource() == deathRatDragable) {
			// # create death rat
		} else if (event.getGestureSource() == sterilisationDragable) {
			// # create sterilisation
		}
		event.consume();
	}


	public GraphicsContext getGraphicContext() {
		return graphicsContext;
	}
	
	public void drawWinLoseIndicator(double winLoseRatio) {
		this.winLoseIndicator.setProgress(winLoseRatio);
	}
}
