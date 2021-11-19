import java.util.ArrayList;

public class RatManager {

	private static ArrayList<Rat> liveRats = new ArrayList<>();
	
	public static void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	public static Boolean removeRat(Rat ratToRemove) {
		return liveRats.remove(ratToRemove);
	}

	public static void moveRats() {
		for (Rat rat : liveRats) {
			rat.move();
		}
	}
	
	public static void breedRats() {
		for (Rat firstRat : liveRats) {
			for (Rat secondRat : liveRats) {
				if (firstRat.getLocation() == secondRat.getLocation() && firstRat != secondRat) {
					if (firstRat.canBreed() && secondRat.canBreed() && (firstRat.getSex() != secondRat.getSex())) {
						if (firstRat.getSex() == RatSex.female) {
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