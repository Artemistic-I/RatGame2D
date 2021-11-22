import java.util.ArrayList;

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
	
	public static void moveRats() {
		for (Rat rat : liveRats) {
			rat.move();
		}
	}
	
	public static int getKilledRatCount() {
		return killedRatCount;
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