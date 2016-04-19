import java.util.Random;

public class Philosopher implements Runnable {
	// Used to vary how long a philosopher thinks before eating and how long the
	// philosopher eats
	private Random numGenerator = new Random();
	
	// The philosopher's unique id
	private int id;
	
	// Controls when a philosopher can pick up chopsticks
	private DiningPhilosopherExplicit monitor;
	
	/**
	 * Constructs a new philosopher
	 * @param id the unique id
	 * @param monitor the object that controls picking up chopsticks
	 */
	public Philosopher (int id, DiningPhilosopherExplicit monitor) {
		this.id = id;
		this.monitor = monitor;
	}
	
	/**
	 * Repeatedly think, pick up chopsticks, eat and put down chopsticks
	 */
	public void run() {
		try {
			while (true) {
				think();
				monitor.pickUpChopsticks(id);
				eat();
				monitor.putDownChopsticks(id);
			}
		} catch (InterruptedException e) {
			System.out.println("Philosopher " + id + " was interrupted.\n");			
		}
	}

	/**
	 * Lets a random amount of time pass to model thinking.
	 * @throws InterruptedException
	 */
	private void think() throws InterruptedException {
		System.out.println("Philosopher " + id + " is thinking.\n");
		System.out.flush();
		Thread.sleep (numGenerator.nextInt(10));
	}
	
	/**
	 * Lets a random amount of time pass to model eating.
	 * @throws InterruptedException
	 */
	private void eat() throws InterruptedException {
		Thread.sleep (numGenerator.nextInt(10));
	}
}
