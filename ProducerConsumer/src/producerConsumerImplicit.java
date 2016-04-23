import java.util.Random;
	
class Producer2 implements Runnable {
    BoundedBufferMonitorImplicit b = null;
    public Producer2(BoundedBufferMonitorImplicit initb) {
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
            myUtil.mySleep(200);
        }
    }
}

class Consumer2 implements Runnable {
    BoundedBufferMonitorImplicit b = null;
    public Consumer2(BoundedBufferMonitorImplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
        Double item;
        while (true) {
            item = (Double) b.fetch();
	        System.out.println("fetched item " + item);
	        myUtil.mySleep(50);
        }
    }
}

public class producerConsumerImplicit {

	public static void main(String[] args) {
        BoundedBufferMonitorImplicit buffer = new BoundedBufferMonitorImplicit();
        Producer2 producer = new Producer2(buffer);
        Consumer2 consumer = new Consumer2(buffer);
    }
}
