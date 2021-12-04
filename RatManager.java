import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;

/**]
 * 
 * @author Aidan English Stephen
 *
 */
public class RatManager {

	private static CopyOnWriteArrayList<Rat> liveRats = new CopyOnWriteArrayList<>();
	private static int killedRatCount = 0;
	
	/**
	 * 
	 * @param ratToAdd
	 */
	public static void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	/**
	 * 
	 * @param ratToRemove
	 */
	public static void removeRat(Rat ratToRemove) {
		liveRats.remove(ratToRemove);
		killedRatCount++;
	}

	/**
	 * 
	 * @return
	 */
	public static int countMaleRats() {
		int maleRats = 0;
		for (Rat rat : liveRats) {
			if (rat.getSex() == RatSex.MALE) {
				maleRats++;
			}
		}
		return maleRats;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int countFemaleRats() {
		int femaleRats = 0;
		for (Rat rat : liveRats) {
			if (rat.getSex() == RatSex.FEMALE) {
				femaleRats++;
			}
		}
		return femaleRats;
	}
	
	/**
	 * 
	 * @param graphicsContext
	 */
	public static void updateRats(GraphicsContext graphicsContext) {
		for (Rat rat : liveRats) {
			rat.update(graphicsContext);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getKilledRatCount() {
		return killedRatCount;
	}

	public static void setKilledRatCount(int savedCount) {
		killedRatCount = savedCount;
	}
	
	/**
	 * 
	 * @param setOfTiles
	 * @return
	 */
	public static Stack<Rat> ratsOnTiles(ArrayList<TileInteractable> setOfTiles) {
		Stack<Rat> ratsThatArePresent = new Stack<>();
		for(Rat rat: liveRats) {
			 if (setOfTiles.contains(rat.getLocation())) {
				 ratsThatArePresent.push(rat);
			 }
		 }
		return ratsThatArePresent;
	}

	/**
	 * 
	 * @return
	 */
	public static Rat[] getRatPopulation() {
		Rat[] rats = new Rat[liveRats.size()];
		for (int i = 0; i < liveRats.size(); i++) {
			rats[i] = liveRats.get(i);
		}
		return rats;
	}
}