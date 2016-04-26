import java.util.Random;
import org.apache.commons.lang3.time.StopWatch;

class Producer implements Runnable {
    BoundedBufferMonitorExplicit b = null;
    public Producer(BoundedBufferMonitorExplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
    	StopWatch watch = new StopWatch();
    	watch.start();
        Double item;
        Random r = new Random();
        for(int i = 0; i<100; i++) {
            item = r.nextDouble();
            b.deposit(item);
            myUtil.mySleep(200);
        }
        watch.stop();
        String result = watch.toString();
        System.out.println("producer" + result);
    }
}
class Consumer implements Runnable {
    BoundedBufferMonitorExplicit b = null;
    public Consumer(BoundedBufferMonitorExplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
    	StopWatch watch2 = new StopWatch();
    	watch2.start();
        Double item;
        for(int i = 0; i<100; i++) {
            item = (Double) b.fetch();
             myUtil.mySleep(50);
        }
        watch2.stop();
        String result = watch2.toString();
        System.out.println("producer" + result);
    }
}


public class producerConsumerExplicit {

    public static void main(String[] args) {
        BoundedBufferMonitorExplicit buffer = new BoundedBufferMonitorExplicit();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
    }

}
