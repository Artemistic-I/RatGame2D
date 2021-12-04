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


	//ItemMain im = new ItemMain(); //caused an error
	
	@FXML
	void pauseButtonClicked(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (pauseButton.getText().equals("Pause")) {
			pauseButton.setText("Resume");
			Menu.getTimelineManager().stopTime();
			SoundManager.pauseSound();
			saveButton.setDisable(false);
		} else {
			pauseButton.setText("Pause");
			saveButton.setDisable(true);
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

		//this code causes errors, but why???
		/* Item[] currentlyPlacedItems = ItemManager.getCurrentlyPlacedItems();
		for (Item item : currentlyPlacedItems) {
			ItemManager.removeItem(item);
		} */
		
		for (Rat rat : RatManager.getRatPopulation()) {
			RatManager.removeRat(rat);
		}
		Menu.startButtonSwitch = true;
		RatManager.setKilledRatCount(0);
		Parent root = FXMLLoader.load(getClass().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
		SoundManager.playSound("audio/Spring Field - Godmode.wav");
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
        int xGridRef = (int) xCoordinate / Gameboard.getTileSize();
        int yGridRef = (int) yCoordinate / Gameboard.getTileSize();
        TileInteractable droppedOnTile = (TileInteractable) Gameboard.getBoard()[yGridRef][xGridRef];
        System.out.println(droppedOnTile);
		if (event.getGestureSource() == bombDragable) {
			ItemManager.addItem(new Bomb(droppedOnTile));
		} else if (event.getGestureSource() == gasDragable) {
			ItemManager.addItem(new Gas(droppedOnTile));
		} else if (event.getGestureSource() == poisonDragable) {
			ItemManager.addItem(new Poison(droppedOnTile));
		} else if (event.getGestureSource() == sexChFeDragable) {
			ItemManager.addItem(new SexChangeFemale(droppedOnTile));
		} else if (event.getGestureSource() == sexChMaDragable) {
			ItemManager.addItem(new SexChangeMale(droppedOnTile));
		} else if (event.getGestureSource() == noEntrySignDragable) {
			ItemManager.addItem(new NoEntry(droppedOnTile));
		} else if (event.getGestureSource() == deathRatDragable) {
			ItemManager.addItem(new DeathRat(droppedOnTile, "North"));
		} else if (event.getGestureSource() == sterilisationDragable) {
			ItemManager.addItem(new Sterilisation(droppedOnTile));
		}
		event.consume();
	}


	public GraphicsContext getGraphicContext() {
		return graphicsContext;
	}
	
	public void drawWinLoseIndicator(double winLoseRatio) {
		this.winLoseIndicator.setProgress(winLoseRatio);
	}
	
	public void updateRatCounts(int maleRatCount, int femaleRatCount, int ratLimit) {
		this.maleRatCount.setText(String.valueOf(maleRatCount));
		this.femaleRatCount.setText(String.valueOf(femaleRatCount));
		this.ratLimit.setText(String.valueOf(ratLimit));
	}

	public void updateItemCounts(){
		this.bombAmount.setText(String.valueOf(im.bombAmount));
		this.gasAmount.setText(String.valueOf(im.gasAmount));
		this.poisonAmount.setText(String.valueOf(im.poisonAmount));
		this.sexChFeAmount.setText(String.valueOf(sexChFeAmount));
		this.sexChMaAmount.setText(String.valueOf(sexChMaAmount));
		this.noEntryAmount.setText(String.valueOf(im.noEntryAmount));
		this.deathRatAmount.setText(String.valueOf(im.deathRatAmount));
		this.sterilisationAmount.setText(String.valueOf(im.sterilisationAmount));
}
}
