import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class TimelineMangaer {

	private Timeline tickTimeline;
	private GraphicsContext graphicsContext;
	private GameBoardCanvasController gameboardCanvasController;

	public TimelineMangaer(GameBoardCanvasController gameboardCanvasController) {
		this.gameboardCanvasController = gameboardCanvasController;
		this.graphicsContext = gameboardCanvasController.getGraphicContext();
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		tickTimeline.play();
	}

	private void tick() {
		Gameboard.drawGameboard(this.graphicsContext);
		ItemManager.updateItems(graphicsContext);
		RatManager.updateRats(this.graphicsContext);
		RatManager.breedRats();
		gameboardCanvasController.drawWinLoseIndicator(Gameboard.calculateWinLose());
		gameboardCanvasController.updateRatCounts(RatManager.countMaleRats(), RatManager.countFemaleRats(), Gameboard.getRatPopulationToLose());
		
	}
	public void stopTime() {
		tickTimeline.stop();
	}
	public void resumeTime() {
		tickTimeline.play();
	}
}
