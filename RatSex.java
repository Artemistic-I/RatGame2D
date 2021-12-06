/**
 * Model rat sex as male and female.
 * @author Aidan English Stephen
 */
public enum RatSex {
	MALE("male"), FEMALE("female");
	
	private final String textEquivalent;
	
	/**
	 * RatSex Constructor. 
	 * @param textEquivalent A written equivalent to the enumeration sex.
	 */
	RatSex(String textEquivalent) {
		this.textEquivalent = textEquivalent;
	}
	
	/**
	 * Provide the enumeration in text form.
	 * @return A written equivalent to the enumeration sex.
	 */
	public String toString() {
		return textEquivalent;
	}
}