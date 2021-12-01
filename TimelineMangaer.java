import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class TimelineMangaer {

	private Timeline tickTimeline;
	private GraphicsContext graphicsContext;

	public TimelineMangaer(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		tickTimeline.play();
	}

	private void tick() {
		System.out.println("It's working...(Just for testing)");
		Gameboard.drawGameboard(this.graphicsContext);
		ItemManager.updateItems(graphicsContext);
		RatManager.updateRats(this.graphicsContext);
		RatManager.breedRats();
		//gameBoardCanvasController.drawWinLoseIndicator(Gameboard.calculateWinLose());
	}
	public void stopTime() {
		tickTimeline.stop();
	}
	public void resumeTime() {
		tickTimeline.play();
	}
}
