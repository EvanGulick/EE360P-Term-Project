import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class Manager {
	
	ArrayBlockingQueue<Condition> PredicatesList = new ArrayBlockingQueue<Condition>(100);

	// Returns false if thread just woke itself up (true is wake up of another thread)
	private void signalNextPredicate() {
		Iterator<Condition> predIt =  PredicatesList.iterator();
		while(predIt.hasNext()) {
			Condition currentPredicate = predIt.next();
			if(currentPredicate.evaluate()) {
				predIt.remove();
				currentPredicate.signal();
				break;
			}
		}
	}
	
	// Returns True if found yourself as the first true predicate
	private boolean nextPredicateIsMe() {
		Iterator<Condition> predIt =  PredicatesList.iterator();
		while(predIt.hasNext()) {
			Condition currentPredicate = predIt.next();
			if(currentPredicate.evaluate()) {
				if(currentPredicate.isMine()) {
					predIt.remove();
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	public void waituntil(Condition predicate) {
		// add self to PredicatesList
		PredicatesList.add(predicate);
		
		if(!nextPredicateIsMe()) {
			predicate.await();
		}
	}
	
	public void finishedCS() {
		signalNextPredicate();
	}
}
