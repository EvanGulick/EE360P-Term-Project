
public class Condition {

	private PredicateVariable left;
	private String comparator;
	private PredicateVariable right;
	
	//private threadID;
	
	Condition(PredicateVariable left, String comparator, PredicateVariable right) {
		this.left = left;
		this.comparator = comparator;
		this.right = right;
		//this.threadID = 
	}
	
	boolean evaluate() {
		if(comparator.equals("==")) {
			return left.get() == right.get();
		} else if (comparator.equals("!=")) {
			return left.get() != right.get();
		} else if (comparator.equals("<")) {
			return left.get() < right.get();
		} else if (comparator.equals("<=")) {
			return left.get() <= right.get();
		} else if (comparator.equals(">")) {
			return left.get() > right.get();
		} else if (comparator.equals(">=")) {
			return left.get() >= right.get();
		} else {
			throw new IllegalArgumentException();
		}
	}
}
