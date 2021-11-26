import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class RatManager {

	private static ArrayList<Rat> liveRats = new ArrayList<>();
	private static int killedRatCount;
	
	public static void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	public static Boolean removeRat(Rat ratToRemove) {
		if (liveRats.remove(ratToRemove)) {
			killedRatCount++;
			return true;
		} else {
			return false;
		}
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
	
	public static void updateRats(Canvas canvas) {
		for (Rat rat : liveRats) {
			rat.update(canvas);
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
}