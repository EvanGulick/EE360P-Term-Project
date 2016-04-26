import java.util.Random;
import org.apache.commons.lang3.time.StopWatch;	

class Producer2 implements Runnable {
    BoundedBufferMonitorImplicit b = null;
    public Producer2(BoundedBufferMonitorImplicit initb) {
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

class Consumer2 implements Runnable {
    BoundedBufferMonitorImplicit b = null;
    StopWatch watch2 = new StopWatch();
    public Consumer2(BoundedBufferMonitorImplicit initb) {
        b = initb;
        new Thread(this).start();
    }
    public void run() {
    	watch2.start();
        Double item;
       for(int i = 0; i<100; i++) {
            item = (Double) b.fetch();
	        myUtil.mySleep(50);
        }
        watch2.stop();
        String result = watch2.toString();
        System.out.println(result);
    }
}

public class producerConsumerImplicit {

	public static void main(String[] args) {
        BoundedBufferMonitorImplicit buffer = new BoundedBufferMonitorImplicit();
        Producer2 producer = new Producer2(buffer);
        Consumer2 consumer = new Consumer2(buffer);
    }
}
