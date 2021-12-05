import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class to keep track of all rats in existence and provide associated operations.
 * @author Aidan English Stephen
 */
public class RatManager {

	private static CopyOnWriteArrayList<Rat> liveRats = new CopyOnWriteArrayList<>();
	private static int killedRatCount = 0;
	private static int totalRatsAdded = 0;
	
	/**
	 * Allow a new rat to be added.
	 * @param ratToAdd The rat you want to add.
	 */
	public static void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	/**
	 * Return the rat with a given ID
	 * @param ratID ID of the rat you want.
	 * @return Rat corresponding to provided ID if it exists and null otherwise.
	 */
	public static Rat getRatByID(int ratID) {
		for (Rat rat : liveRats) {
			if (rat.getUniqueIdentifier() == ratID) {
				return rat;
			}
		}
		return null;
	}

	/**
	 * Allow a rat to be removed.
	 * @param ratToRemove The rat you want to remove.
	 */
	public static void removeRat(Rat ratToRemove) {
		if (!(ratToRemove == null)){
			killedRatCount += 1 + ratToRemove.getUnbornRatCount();
			liveRats.remove(ratToRemove);
		}
	}

	/**
	 * Count the number of male rats amounts the rats that are alive.
	 * @return Number of male rats.
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
	 * Count the number of female rats amounts the rats that are alive.
	 * @return Number of female rats.
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
	 * For each rat in existence, update it.
	 * @param graphicsContext Where rats should be drawn.
	 */
	public static void updateRats(GraphicsContext graphicsContext, long gameDuration) {
		for (Rat rat : liveRats) {
			rat.update(graphicsContext, gameDuration);
		}
	}
	
	/**
	 * Get the number of rats that have been killed
	 * @return Number of rats that have been killed.
	 */
	public static int getKilledRatCount() {
		return killedRatCount;
	}

	public static void setKilledRatCount(int savedCount) {
		killedRatCount = savedCount;
	}
	
	/**
	 * Find out what rats are present on a particular set of tiles.
	 * @param setOfTiles The tiles that you want to be checked.
	 * @return All the rats that are present on the provided tiles.
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
	 * Get all the rats that are currently alive.
	 * @return All rats.
	 */
	public static Rat[] getRatPopulation() {
		Rat[] rats = new Rat[liveRats.size()];
		for (int i = 0; i < liveRats.size(); i++) {
			rats[i] = liveRats.get(i);
		}
		return rats;
	}

	/**
	 * Increment the total number of rats.
	 * @return new total number of rats.
	 */
	public static int incrimentRatsAdded() {
		totalRatsAdded += 1;
		return totalRatsAdded;
	}
}