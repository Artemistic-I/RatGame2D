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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Stage stage;
	private Scene scene;

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
	private Label maleRatCount;
	
	@FXML
	private Label femaleRatCount;
	
	@FXML
	private Label ratLimit;

	@FXML
	private Label bombAmount;

	@FXML
	private Label gasAmount;

	@FXML
	private Label poisonAmount;

	@FXML
	private Label sexChFeAmount;

	@FXML
	private Label sexChMaAmount;

	@FXML
	private Label noEntryAmount;

	@FXML
	private Label deathRatAmount;

	@FXML
	private Label sterilisationAmount;
	
	@FXML
	void pauseButtonClicked(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (pauseButton.getText().equals("Pause")) {
			pauseButton.setText("Resume");
			Menu.getTimelineManager().stopTime();
			AudioManager.pauseMusic();
			saveButton.setDisable(false);
		} else {
			pauseButton.setText("Pause");
			saveButton.setDisable(true);
			AudioManager.resumeMusic();
			Menu.getTimelineManager().resumeTime();
			SoundManager.resumeSound();
		}
	}

	@FXML
	void saveButtonClicked(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		String username = Gameboard.getCurrentPlayer().getPlayerUsername();
		long totalDuration = Menu.getTimelineManager().getDuration();
		GameFileManager.saveGame(username, totalDuration);

		//removing all rats and items from memory
		Inventory.reset();
		ItemManager.removeAllItems();
		for (Rat rat : RatManager.getRatPopulation()) {
			RatManager.removeRat(rat);
		}
		Menu.startButtonSwitch = true;
		RatManager.setKilledRatCount(0);
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
		AudioManager.playMenuMusic();
		AudioManager.setVol(Settings.getVolume());
        stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saveButton.setDisable(true);
		graphicsContext = canvas.getGraphicsContext2D();
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
    		event.acceptTransferModes(TransferMode.ANY);
    		event.consume();
		}
	}
	
	@FXML
	void canvasDragDropOccured(DragEvent event) {
		System.out.println("------drop"); // # just for testing
		
		double xCoordinate = event.getX();
        double yCoordinate = event.getY(); 
        int xGridRef = (int) xCoordinate / Gameboard.getTileSize();
        int yGridRef = (int) yCoordinate / Gameboard.getTileSize();
        if (Gameboard.getBoard()[yGridRef][xGridRef] instanceof TilePath) {
	        TileInteractable droppedOnTile = (TileInteractable) Gameboard.getBoard()[yGridRef][xGridRef];
			if (event.getGestureSource() == bombDragable) {
				if (Inventory.getInv(0) > 0) {
					ItemManager.addItem(new Bomb(droppedOnTile));
					Inventory.removeItem(0);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == gasDragable && Inventory.getInv(1) > 0) {
				if (Inventory.getInv(1) > 0) {
					ItemManager.addItem(new Gas(droppedOnTile));
					Inventory.removeItem(1);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == poisonDragable && Inventory.getInv(3) > 0) {
				if (Inventory.getInv(3) > 0) {
					ItemManager.addItem(new Poison(droppedOnTile));
					Inventory.removeItem(3);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == sexChFeDragable && Inventory.getInv(5) > 0) {
				if (Inventory.getInv(5) > 0) {
					ItemManager.addItem(new SexChangeFemale(droppedOnTile));
					Inventory.removeItem(5);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == sexChMaDragable && Inventory.getInv(4) > 0) {
				if (Inventory.getInv(4) > 0) {
					ItemManager.addItem(new SexChangeMale(droppedOnTile));
					Inventory.removeItem(4);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == noEntrySignDragable && Inventory.getInv(6) > 0) {
				if (Inventory.getInv(6) > 0) {
					ItemManager.addItem(new NoEntry(droppedOnTile));
					Inventory.removeItem(6);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == deathRatDragable && Inventory.getInv(7) > 0) {
				if (Inventory.getInv(7) > 0) {
					ItemManager.addItem(new DeathRat(droppedOnTile, "North"));
					Inventory.removeItem(7);
				} else {
					displayMissingInventoryMessage();
				}
			} else if (event.getGestureSource() == sterilisationDragable && Inventory.getInv(2) > 0) {
				if (Inventory.getInv(2) > 0) {
					ItemManager.addItem(new Sterilisation(droppedOnTile));
					Inventory.removeItem(2);
				} else {
					displayMissingInventoryMessage();
				}
			}
        } else {
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cannot Place Item Here");
            alert.setHeaderText(null);
            alert.setContentText("Sorry, items can only be placed on paths.");
            alert.showAndWait();
        }
		event.consume();
	}

	private void displayMissingInventoryMessage() {
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Insufficient Inventory Stock");
        alert.setHeaderText(null);
        alert.setContentText("Please choose a different item or wait until inventory increases.");
        alert.showAndWait();
	}

	public GraphicsContext getGraphicContext() {
		return graphicsContext;
	}
	
	public void drawWinLoseIndicator(double winLoseRatio) {
		this.winLoseIndicator.setProgress(winLoseRatio);
	}
	
	public void updateRatCounts(int maleRatCount, int femaleRatCount, int ratLimit) throws IOException {
		this.maleRatCount.setText(String.valueOf(maleRatCount));
		this.femaleRatCount.setText(String.valueOf(femaleRatCount));
		this.ratLimit.setText(String.valueOf(ratLimit));
		if(maleRatCount + femaleRatCount > ratLimit){
			Menu.getTimelineManager().stopTime();	
			AudioManager.playLoseMusic();
			AudioManager.setVol(Settings.getVolume());
			ScoreboardController.addScore(Gameboard.calculateScore());
			Parent root = FXMLLoader.load(getClass().getResource("scenes/loseScreen.fxml"));
        	Stage window = (Stage) pauseButton.getScene().getWindow();
        	scene = new Scene(root);
        	window.setScene(scene);	
		}
		if(maleRatCount + femaleRatCount == 0){
			Menu.getTimelineManager().stopTime();	
			AudioManager.playWinMusic();
			AudioManager.setVol(Settings.getVolume());
			ScoreboardController.addScore(Gameboard.calculateScore());
			Parent root = FXMLLoader.load(getClass().getResource("scenes/winScreen.fxml"));
        	Stage window = (Stage) pauseButton.getScene().getWindow();
        	scene = new Scene(root);
        	window.setScene(scene);	
		}
	}

	public void updateItemCounts(){
		this.bombAmount.setText(String.valueOf(Inventory.getInv(0)));
		this.gasAmount.setText(String.valueOf(Inventory.getInv(1)));
		this.poisonAmount.setText(String.valueOf(Inventory.getInv(3)));
		this.sexChFeAmount.setText(String.valueOf(Inventory.getInv(5)));
		this.sexChMaAmount.setText(String.valueOf(Inventory.getInv(4)));
		this.noEntryAmount.setText(String.valueOf(Inventory.getInv(6)));
		this.deathRatAmount.setText(String.valueOf(Inventory.getInv(7)));
		this.sterilisationAmount.setText(String.valueOf(Inventory.getInv(2)));
	}
}
