
public class PredicateVariable {

	private int value;
	
	public PredicateVariable(int newVal) {
		value = newVal;
	}
	
	public synchronized void increment() {
		value++;
	}
	
	public synchronized void incrementBy(int addition) {
		value += addition;
	}
	
	public synchronized void decrement() {
		value--;
	}
	
	public synchronized void set(int newVal) {
		value = newVal;
	}
	
	public synchronized int get() {
		return value;
	}
	
}
