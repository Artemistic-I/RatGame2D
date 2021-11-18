import java.util.ArrayList;

public class RatManager {

	private ArrayList<Rat> liveRats = new ArrayList<>();
	
	public void addRat(Rat ratToAdd) {
		liveRats.add(ratToAdd);
	}

	public Boolean removeRat(Rat ratToRemove) {
		return liveRats.remove(ratToRemove);
	}
	
}
