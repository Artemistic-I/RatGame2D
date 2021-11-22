import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameBoardCanvas implements Initializable {

	@FXML 
	Canvas canvas;
	
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// Set the background to gray.
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// Draw row of dirt images
		// We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
		// We draw the row at y value 2.
				
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Get the Graphic Context of the canvas. This is what we draw on.
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				// Clear canvas
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
				// Set the background to gray.
				gc.setFill(Color.GRAY);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				// Draw row of dirt images
				// We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
				// We draw the row at y value 2.
		
	}
}
