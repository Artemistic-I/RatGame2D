import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

public class TimelineMangaer {
	
	private Timeline tickTimeline; 
	private Canvas canvas;
	
	public TimelineMangaer(Canvas canvas) {
		this.canvas = canvas;
		tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
		tickTimeline.setCycleCount(Animation.INDEFINITE);
		tickTimeline.play();
	}
	
	private void tick() {
		System.out.println("It's working...(Just for testing)");
		Gameboard.drawGameboard(this.canvas);
	  	//RatManager.updateRats(this.canvas);
		//gameBoardCanvasController.drawWinLoseIndicator(Gameboard.calculateWinLose());
	}
}
