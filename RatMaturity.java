/**
 * Model rat maturity as adult and baby.
 * @author Aidan English Stephen
 */
public enum RatMaturity {
	ADULT("adult"), BABY("baby");
	
	private final String textEquivalent;
	
	/**
	 * RatMaturity Constructor. 
	 * @param textEquivalent A written equivalent to the enumeration maturity.
	 */
	RatMaturity(String textEquivalent) {
		this.textEquivalent = textEquivalent;
	}
	
	/**
	 * Provide the enumeration in text form.
	 * @return A written equivalent to the enumeration maturity.
	 */
	public String toString() {
		return textEquivalent;
	}
}