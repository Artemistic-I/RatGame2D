
public enum RatMaturity {
	ADULT("adult"), BABY("baby");
	
	private final String textEquivalent;
	
	RatMaturity(String textEquivalent) {
		this.textEquivalent = textEquivalent;
	}
	
	public String toString() {
		return textEquivalent;
	}
}