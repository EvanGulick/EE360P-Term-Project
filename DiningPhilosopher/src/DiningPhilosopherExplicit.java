
public class DiningPhilosopherExplicit {
	// The states a philosopher can be in
	private enum State {THINKING, HUNGRY, EATING};
	
	// The state of each philosopher
	private State[] philosopherState;
	
	/**
	 * Creates a monitor for the right number of philosophers.
	 * Initially all philosophers are thinking.
	 * @param numPhilosophers number of philosophers
	 */
	public DiningPhilosopherExplicit (int numPhilosophers) {
		philosopherState = new State[numPhilosophers];
		for (int i = 0; i < philosopherState.length; i++) {
			philosopherState[i] = State.THINKING;
		}
	}
	
	/**
	 * A philosopher picks up both chopsticks.  The philosopher waits if either
	 * neighbor is eating
	 * 
	 * @param philosopherId the philosopher who wants to eat
	 * @throws InterruptedException the thread was interrupted
	 */
	public synchronized void pickUpChopsticks(int philosopherId) throws InterruptedException {
		// Remember this philosopher wants to eat
		philosopherState[philosopherId] = State.HUNGRY;
		System.out.println("Philosopher " + philosopherId + " is hungry.\n");
		System.out.flush();
		
		// Wait until neither neighbor is eating
		while (someNeighborIsEating(philosopherId)) {
			wait();
		}
		
		// Record that this philosopher is now eating
		philosopherState[philosopherId] = State.EATING;
		System.out.println("Philosopher " + philosopherId + " is eating.\n");
		System.out.flush();
	}

	/**
	 * Return true if either neighbor is currently eating
	 * @param philosopherId the philosopher whose neighbors are checked
	 * @return true if either neighbor is currently eating
	 */
	private boolean someNeighborIsEating(int philosopherId) {
		// Check philosopher to the one side
		if (philosopherState[(philosopherId + 1) % philosopherState.length] == State.EATING){
			return true;
		}

		// Check philosopher on the other side
		if (philosopherState[(philosopherId + philosopherState.length - 1) % philosopherState.length] == State.EATING){
			return true;
		}
		
		// Neither is eating
		return false;
	}

	/**
	 * Put down both chopsticks.  Notify all the waiting philosophers in case one of them
	 * can now eat.
	 *  
	 * @param philosopherId the philosopher who is done eating
	 */
	public synchronized void putDownChopsticks(int philosopherId) {
		philosopherState[philosopherId] = State.THINKING;
		notifyAll();
	}

}
