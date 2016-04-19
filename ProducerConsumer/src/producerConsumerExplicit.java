import java.util.Random;
class Producer implements Runnable {
    BoundedBufferMonitorExplicit b = null;
    public Producer(BoundedBufferMonitorExplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
        Double item;
        Random r = new Random();
        while (true) {
            item = r.nextDouble();
            System.out.println("produced item " + item);
            b.deposit(item);
        }
    }
}
class Consumer implements Runnable {
    BoundedBufferMonitorExplicit b = null;
    public Consumer(BoundedBufferMonitorExplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
        Double item;
        while (true) {
            item = (Double) b.fetch();
            System.out.println("fetched item " + item);
        }
    }
}


public class producerConsumerExplicit {

    public static void main(String[] args) {
        BoundedBufferMonitorExplicit buffer = new BoundedBufferMonitorExplicit();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
    }

}
