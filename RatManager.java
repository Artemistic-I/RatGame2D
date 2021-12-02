import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class RatManager {

	private static CopyOnWriteArrayList<Rat> liveRats = new CopyOnWriteArrayList<>();

	private static int killedRatCount = 0;
	
	public static void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	public static void removeRat(Rat ratToRemove) {
		liveRats.remove(ratToRemove);
		killedRatCount++;
	}

	public static int countMaleRats() {
		int maleRats = 0;
		for (Rat rat : liveRats) {
			if (rat.getSex() == RatSex.MALE) {
				maleRats++;
			}
		}
		return maleRats;
	}
	
	public static int countFemaleRats() {
		int femaleRats = 0;
		for (Rat rat : liveRats) {
			if (rat.getSex() == RatSex.FEMALE) {
				femaleRats++;
			}
		}
		return femaleRats;
	}
	
	public static void updateRats(GraphicsContext graphicsContext) {
		for (Rat rat : liveRats) {
			rat.update(graphicsContext);
		}
	}
	
	public static int getKilledRatCount() {
		return killedRatCount;
	}
	
	public static ArrayList<Rat> ratsOnTiles(ArrayList<Tile> setOfTiles) {
		ArrayList<Rat> ratsThatArePresent = new ArrayList<>();
		for(Rat rat: liveRats) {
			 if (setOfTiles.contains(rat.getLocation())) {
				 ratsThatArePresent.add(rat);
			 }
		 }
		return ratsThatArePresent;
	}
	
	public static void breedRats() {
		for (Rat firstRat : liveRats) {
			for (Rat secondRat : liveRats) {
				if (firstRat.getLocation() == secondRat.getLocation() && firstRat != secondRat) {
					if (firstRat.canBreed() && secondRat.canBreed() && (firstRat.getSex() != secondRat.getSex())) {
						if (firstRat.getSex() == RatSex.FEMALE) {
							firstRat.Breed();
						} else {
							secondRat.Breed();
						}
					}
				}
			}
		}
	}
	// A method to connect RatManager to GameFileManager
	public static Rat[] getRatPopulation() {
		Rat[] rats = new Rat[liveRats.size()];
		for (int i = 0; i < liveRats.size(); i++) {
			rats[i] = liveRats.get(i);
		}
		return rats;
	}
}