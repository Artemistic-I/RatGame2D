import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class TimelineMangaer {

	private Timeline tickTimeline;
	private GraphicsContext graphicsContext;
	private GameBoardCanvasController gameboardCanvasController;
	private long totalDuration;
	public static final int DELAY = 250; //milliseconds

	public TimelineMangaer(GameBoardCanvasController gameboardCanvasController) {
		this.gameboardCanvasController = gameboardCanvasController;
		this.graphicsContext = gameboardCanvasController.getGraphicContext();
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(DELAY), event -> {
			try {
				tick();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		totalDuration = 0;
		tickTimeline.play();
	}
	public TimelineMangaer(GameBoardCanvasController gameboardCanvasController, long savedDuration) {
		this.gameboardCanvasController = gameboardCanvasController;
		this.graphicsContext = gameboardCanvasController.getGraphicContext();
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(DELAY), event -> {
			try {
				tick();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		totalDuration = savedDuration;
		tickTimeline.play();
	}

	private void tick() throws IOException {
		Inventory.update();
		Gameboard.drawGameboard(this.graphicsContext);
		RatManager.updateRats(this.graphicsContext, this.totalDuration);
		ItemManager.updateItems(graphicsContext, this.totalDuration);
		gameboardCanvasController.drawWinLoseIndicator(Gameboard.calculateWinLose());
		gameboardCanvasController.updateRatCounts(RatManager.countMaleRats(), RatManager.countFemaleRats(), Gameboard.getRatPopulationToLose());
		totalDuration += DELAY;
	}

	public GameBoardCanvasController getGameboardCanvassController() {
		return gameboardCanvasController;
	}
	
	public void stopTime() {
		tickTimeline.stop();
	}
	public void resumeTime() {
		tickTimeline.play();
	}
	public long getDuration() {
		return totalDuration;
	}
}
