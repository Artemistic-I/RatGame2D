public enum RatSex {
	MALE("male"), FEMALE("female");
	
	private final String textEquivalent;
	
	RatSex(String textEquivalent) {
		this.textEquivalent = textEquivalent;
	}
	
	public String toString() {
		return textEquivalent;
	}
}