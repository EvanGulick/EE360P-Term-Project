
public class Condition {

	private PredicateVariable left;
	private String comparator;
	private PredicateVariable right;
	
	private long threadID;
	
	
	
	Condition(PredicateVariable left, String comparator, PredicateVariable right) {
		this.left = left;
		this.comparator = comparator;
		this.right = right;
		this.threadID = Thread.currentThread().getId();
	}
	
	public synchronized void await() {
		try {
			wait();
		} catch (InterruptedException e) {
			
		} catch (IllegalMonitorStateException e) {
			Thread.yield();
		}
	}
	
	public synchronized void signal() {
		notify();
	}

	public boolean evaluate() {
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
	
	public boolean isMine() {
		return Thread.currentThread().getId() == threadID;
	}
}
