
public class PredicateVariable {

	private static int value;
	
	public PredicateVariable(int newVal) {
		value = newVal;
	}
	
	public void increment() {
		value++;
	}
	
	public void incrementBy(int addition) {
		value += addition;
	}
	
	public void decrement() {
		value--;
	}
	
	public void set(int newVal) {
		value = newVal;
	}
	
	public int get() {
		return value;
	}
	
}
