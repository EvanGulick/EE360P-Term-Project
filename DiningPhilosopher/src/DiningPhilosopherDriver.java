
public class DiningPhilosopherDriver {
	// The number of philosophers
	private static final int NUM_PHILOSOPHERS = 5;
	
	/**
	 * Test the dining philosophers solution
	 * @param args Not used
	 */
	public static void main (String[] args) {
		Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
		
		// Monitor will ensure that a philosopher picks up both chopsticks at the
		// same time
		DiningPhilosopherExplicit monitor = new DiningPhilosopherExplicit(NUM_PHILOSOPHERS);
		
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, monitor);
			new Thread(philosophers[i]).start();
		}
	}
}
