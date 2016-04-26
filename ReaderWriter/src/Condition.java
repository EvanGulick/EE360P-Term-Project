
public class Condition {

	private PredicateVariable left;
	private PredicateVariable left2;
	private String comparator;
	private PredicateVariable right;
	
	private long threadID;
	
	
	
	Condition(PredicateVariable left, String comparator, PredicateVariable right) {
		this.left = left;
		this.left2 = new PredicateVariable(-100);
		this.comparator = comparator;
		this.right = right;
		this.threadID = Thread.currentThread().getId();
	}
	
	Condition(PredicateVariable left, PredicateVariable left2, String comparator, PredicateVariable right) {
		this.left = left;
		this.left2 = left2;
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
		if(this.left2.get() == -100){
			
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
		}else{
			if(comparator.equals("==")) {
				return (left.get() == right.get() && left2.get() == right.get());
			} else if (comparator.equals("!=")) {
				return left.get() != right.get() && left2.get() != right.get();
			} else if (comparator.equals("<")) {
				return left.get() < right.get() && left2.get() < right.get();
			} else if (comparator.equals("<=")) {
				return left.get() <= right.get() && left2.get() <= right.get();
			} else if (comparator.equals(">")) {
				return left.get() > right.get() && left2.get() > right.get();
			} else if (comparator.equals(">=")) {
				return left.get() >= right.get() && left2.get() >= right.get();
			} else {
				throw new IllegalArgumentException();
			}
			}
	}
	
	public boolean isMine() {
		return Thread.currentThread().getId() == threadID;
	}
}
